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
import com.naver.gfpsdk.AdEventListener
import com.naver.gfpsdk.AdParam
import com.naver.gfpsdk.GfpAdLoader
import com.naver.gfpsdk.GfpError
import com.naver.gfpsdk.GfpNativeSimpleAd
import com.naver.gfpsdk.GfpNativeSimpleAdOptions
import com.naver.gfpsdk.GfpResponseInfo
import com.naver.namexample.databinding.FragmentSmartChannelBinding
import com.naver.namexample.util.DateUtil

class SmartChannelFragment : Fragment() {
    private var adLoader: GfpAdLoader? = null
    private lateinit var logTextView: TextView
    private lateinit var binding: FragmentSmartChannelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSmartChannelBinding.inflate(inflater)
        val nativeSimpleAdView = binding.nativeSimpleAdView
        logTextView = binding.logTextView
        val adParam = AdParam.Builder()
            .setAdUnitId(AD_UNIT_ID)
            .build()
        adLoader = GfpAdLoader.Builder(requireActivity(), adParam)
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
                    logTextView.append(
                        "[${DateUtil.CURR_TIME_STR}] Error occurred.\n\tcode[${error.errorCode}]\n\tsubCode[${error.errorSubCode}]\n\t\tmessage[${error.errorMessage}]\n"
                    )
                    Log.e("SmartchannelFragment", responseInfo.toString())
                }
            })
            .withNativeSimpleAd(
                GfpNativeSimpleAdOptions.Builder()
                    .setAdChoicesPlacement(GfpNativeSimpleAdOptions.ADCHOICES_TOP_RIGHT)
                    .build()
            ) { nativeSimpleAd: GfpNativeSimpleAd ->
                logTextView.append(
                    "[${DateUtil.CURR_TIME_STR}] AD Loaded.\n\t\tAdProviderName: ${nativeSimpleAd.adProviderName}\n"
                )
                nativeSimpleAdView.setNativeSimpleAd(nativeSimpleAd)
            }
            .build()
        logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Requested.\n")
        adLoader?.loadAd()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adLoader?.cancel()
    }

    companion object {
        private const val AD_UNIT_ID = "AOS_nw_native-N345765840"
    }
}
