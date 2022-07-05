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
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.naver.gfpsdk.AdParam
import com.naver.gfpsdk.BannerAdListener
import com.naver.gfpsdk.BannerViewLayoutType
import com.naver.gfpsdk.GfpBannerAd
import com.naver.gfpsdk.GfpBannerAdOptions
import com.naver.gfpsdk.GfpBannerAdView
import com.naver.gfpsdk.GfpError
import com.naver.gfpsdk.HostParam
import com.naver.namexample.databinding.FragmentImageBannerBinding
import com.naver.namexample.util.DateUtil

class ImageBannerFragment : Fragment() {
    private var bannerAdView: GfpBannerAdView? = null
    private lateinit var logTextView: TextView
    private lateinit var binding: FragmentImageBannerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentImageBannerBinding.inflate(layoutInflater)
        val bannerAdContainer = binding.bannerContainer
        logTextView = binding.logTextView

        val adParam = AdParam.Builder()
            .setAdUnitId(AD_UNIT_ID)
            .build()
        val bannerAdOptions = GfpBannerAdOptions.Builder()
            .setBannerViewLayoutType(BannerViewLayoutType.FLUID_WIDTH)
            .setHostParam(
                HostParam.Builder()
                    .addMetaParam("theme", "dark")
                    // if you have specific dark mode, few ad will change it's color (some button or background).
                    // not for everyone.
                    .build()
            )
            .build()

        bannerAdView = GfpBannerAdView(requireContext(), adParam)
        bannerAdView?.run {
            setBannerAdOptions(bannerAdOptions)
            setAdListener(
                object : BannerAdListener() {
                    override fun onAdLoaded(ad: GfpBannerAd) {
                        logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Loaded.\n\tAdProviderName: ${ad.adProviderName} Size: ${bannerAdView?.bannerAdSize}\n")
                    }

                    override fun onAdClicked(ad: GfpBannerAd) {
                        logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Clicked.\n")
                    }

                    override fun onAdImpression(ad: GfpBannerAd) {
                        logTextView.append("[${DateUtil.CURR_TIME_STR}] AD impression.\n")
                    }

                    override fun onAdMetaChanged(ad: GfpBannerAd, params: Map<String, String>?) {
                        params?.entries?.map {
                            "\n\tkey: ${it.key} value: ${it.value}"
                        }?.reduce { resultString, newString -> resultString + newString }?.let {
                            logTextView.append("[${DateUtil.CURR_TIME_STR}] AD MetaChanged.\n\t$it")
                        }
                    }

                    override fun onAdSizeChanged(ad: GfpBannerAd) {
                        logTextView.append("[${DateUtil.CURR_TIME_STR}] AD SizeChanged.\n\tSize: ${bannerAdView?.bannerAdSize}\n")
                    }

                    override fun onError(ad: GfpBannerAd, error: GfpError) {
                        logTextView.append("[${DateUtil.CURR_TIME_STR}] Error occurred.\n\tcode[${error.errorCode}]\n\tsubCode[${error.errorSubCode}]\n\t\tmessage[${error.errorMessage}]\n")
                        Log.e("ImageBannerFragment", ad.responseInfo.toString())
                    }
                })
            bannerAdContainer.addView(this)
            logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Requested.\n")
            loadAd()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bannerAdView?.destroy()
    }

    companion object {
        private const val AD_UNIT_ID = "AOS_nw_banner-N345765840"
    }
}
