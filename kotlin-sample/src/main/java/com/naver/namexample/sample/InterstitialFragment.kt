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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.naver.gfpsdk.AdParam
import com.naver.gfpsdk.GfpError
import com.naver.gfpsdk.GfpInterstitialAd
import com.naver.gfpsdk.GfpInterstitialAdManager
import com.naver.gfpsdk.InterstitialAdListener
import com.naver.namexample.databinding.FragmentInterstitialBinding
import com.naver.namexample.util.DateUtil

class InterstitialFragment : Fragment() {
    private var interstitialAdManager: GfpInterstitialAdManager? = null
    private lateinit var binding: FragmentInterstitialBinding
    private lateinit var logTextView: TextView
    private lateinit var showButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInterstitialBinding.inflate(inflater)
        logTextView = binding.logTextView
        showButton = binding.showButton.apply {
            isEnabled = false
            setOnClickListener(
                View.OnClickListener {
                    if (interstitialAdManager?.isAdInvalidated != false) {
                        logTextView.append("[${DateUtil.CURR_TIME_STR}] AD is not valid.\n")
                        return@OnClickListener
                    }
                    interstitialAdManager?.showAd(requireActivity())
                }
            )
        }

        val adParam = AdParam.Builder()
            .setAdUnitId(AD_UNIT_ID)
            .build()
        interstitialAdManager = GfpInterstitialAdManager(requireActivity(), adParam)

        interstitialAdManager?.setAdListener(
            object : InterstitialAdListener() {
                override fun onAdLoaded(ad: GfpInterstitialAd) {
                    logTextView.append(
                        "[${DateUtil.CURR_TIME_STR}] AD Loaded.\n\t\tAdProviderName: ${ad.adProviderName}\n" // ktlint-disable max-line-length
                    )
                    showButton.isEnabled = true
                }

                override fun onAdStarted(ad: GfpInterstitialAd) {
                    logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Started.\n")
                }

                override fun onAdClicked(ad: GfpInterstitialAd) {
                    logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Clicked.\n")
                }

                override fun onAdClosed(ad: GfpInterstitialAd) {
                    logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Closed.\n")
                    showButton.isEnabled = false
                }

                override fun onError(ad: GfpInterstitialAd, error: GfpError) {
                    logTextView.append(
                        "[${DateUtil.CURR_TIME_STR}] Error occurred.\n\tcode[${error.errorCode}]\n\tsubCode[${error.errorSubCode}]\n\t\tmessage[${error.errorMessage}]\n" // ktlint-disable max-line-length
                    )
                }
            }
        )
        logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Requested.\n")
        interstitialAdManager?.loadAd()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        interstitialAdManager?.destroy()
    }

    companion object {
        private const val AD_UNIT_ID = "AOS_INTERSTITIAL_EB"
    }
}
