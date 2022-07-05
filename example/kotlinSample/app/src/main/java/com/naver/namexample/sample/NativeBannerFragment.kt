/*
 * NAM(Naver Ad Manager) SDK for Android
 *
 * Copyright 2022-present NAVER Corp.
 * All rights reserved.
 *
 * Unauthorized use, modification and redistribution of this software are strongly prohibited.
 */
package com.naver.namexample.sample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.naver.gfpsdk.AdEventListener
import com.naver.gfpsdk.AdParam
import com.naver.gfpsdk.GfpAdLoader
import com.naver.gfpsdk.GfpError
import com.naver.gfpsdk.GfpNativeAd
import com.naver.gfpsdk.GfpNativeAdOptions
import com.naver.gfpsdk.GfpResponseInfo
import com.naver.namexample.databinding.ContentNativeAdBinding
import com.naver.namexample.databinding.FragmentNativeBannerBinding
import com.naver.namexample.util.DateUtil

class NativeBannerFragment : Fragment() {
    private var adLoader: GfpAdLoader? = null
    private var nativeContainer: RelativeLayout? = null
    private lateinit var logTextView: TextView
    private lateinit var binding: FragmentNativeBannerBinding
    private lateinit var nativeAdBinding: ContentNativeAdBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNativeBannerBinding.inflate(inflater)
        nativeContainer = binding.nativeContainer
        logTextView = binding.logTextView
        val adParam = AdParam.Builder()
            .setAdUnitId(AD_UNIT_ID)
            .build()
        val nativeAdOptions = GfpNativeAdOptions.Builder()
            .setHasMediaView(true)
            .build()
        adLoader = GfpAdLoader.Builder(requireContext(), adParam)
            .withTimeoutMillis(60000L)
            .withAdListener(object : AdEventListener() {
                override fun onAdClicked() {
                    logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Clicked.\n")
                }

                override fun onAdImpression() {
                    logTextView.append("[${DateUtil.CURR_TIME_STR}] AD impression.\n")
                }

                override fun onAdMetaChanged(params: Map<String, String>?) {
                    params?.entries?.map {
                        "\n\tkey: ${it.key} value: ${it.value}"
                    }?.reduce { resultString, newString -> resultString + newString }?.let {
                        logTextView.append("[${DateUtil.CURR_TIME_STR}] AD MetaChanged.\n\t$it")
                    }
                }

                override fun onError(error: GfpError, responseInfo: GfpResponseInfo) {
                    logTextView.append("[${DateUtil.CURR_TIME_STR}] Error occurred.\n\tcode[${error.errorCode}]\n\tsubCode[${error.errorSubCode}]\n\t\tmessage[${error.errorMessage}]\n")
                    Log.e("NativeBannerFragment", responseInfo.toString())
                }
            })
            .withNativeAd(nativeAdOptions) { nativeAd: GfpNativeAd ->
                logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Loaded.\n\t\tAdProviderName: ${nativeAd.adProviderName}\n")
                inflateAd(nativeAd)
            }
            .build()
        logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Requested.\n")
        adLoader?.loadAd()
        return binding.root
    }

    private fun inflateAd(nativeAd: GfpNativeAd) {
        nativeContainer?.removeAllViews()
        nativeAdBinding = ContentNativeAdBinding.inflate(layoutInflater)
        val nativeAdView = nativeAdBinding.gfpNativeAd
        val assetsContainer = nativeAdBinding.assetsContainer
        val mediaView = nativeAdBinding.adMedia
        val adChoicesView = nativeAdBinding.adChoicesView
        val iconView = nativeAdBinding.adAppIcon
        val titleView = nativeAdBinding.adHeadline
        val bodyView = nativeAdBinding.adBody
        val advertiserView = nativeAdBinding.adAdvertiser
        val socialContextView = nativeAdBinding.adSocialContext
        val callToActionButton = nativeAdBinding.adCallToAction

        nativeAdView.assetsContainer = assetsContainer
        nativeAdView.mediaView = mediaView
        nativeAdView.adChoicesView = adChoicesView
        nativeAdView.iconView = iconView
        nativeAdView.titleView = titleView
        nativeAdView.bodyView = bodyView
        nativeAdView.advertiserView = advertiserView
        nativeAdView.socialContextView = socialContextView
        nativeAdView.callToActionView = callToActionButton
        titleView.text = nativeAd.title
        bodyView.text = nativeAd.body
        callToActionButton.text = nativeAd.callToAction
        advertiserView.text = nativeAd.advertiserName

        nativeAd.icon?.let {
            iconView.setImageDrawable(it.drawable)
            iconView.visibility = View.VISIBLE
        } ?: run {
            iconView.visibility = View.GONE
        }

        nativeAd.socialContext?.let {
            socialContextView.text = it
            socialContextView.visibility = View.VISIBLE
        } ?: run {
            socialContextView.visibility = View.GONE
        }

        nativeAdView.setNativeAd(nativeAd)
        nativeContainer?.addView(nativeAdView)
    }

    override fun onDestroy() {
        super.onDestroy()
        adLoader?.cancel()
    }

    companion object {
        private const val AD_UNIT_ID = "INSERT YOUR AD UNIT ID"
    }
}
