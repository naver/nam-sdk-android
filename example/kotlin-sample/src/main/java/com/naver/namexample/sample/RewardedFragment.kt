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
import com.naver.gfpsdk.GfpRewardedAd
import com.naver.gfpsdk.GfpRewardedAdManager
import com.naver.gfpsdk.RewardedAdListener
import com.naver.namexample.databinding.FragmentRewardedBinding
import com.naver.namexample.util.DateUtil

class RewardedFragment : Fragment() {
    private var rewardedAdManager: GfpRewardedAdManager? = null
    private lateinit var binding: FragmentRewardedBinding
    private lateinit var logTextView: TextView
    private lateinit var showButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRewardedBinding.inflate(inflater)
        logTextView = binding.logTextView
        showButton = binding.showButton.apply {
            isEnabled = false
            setOnClickListener(
                View.OnClickListener {
                    if (rewardedAdManager?.isAdInvalidated != false) {
                        logTextView.append("[${DateUtil.CURR_TIME_STR}] AD is not valid.\n")
                        return@OnClickListener
                    }
                    rewardedAdManager?.showAd(requireActivity())
                }
            )
        }

        val adParam = AdParam.Builder()
            .setAdUnitId(AD_UNIT_ID)
            .build()
        rewardedAdManager = GfpRewardedAdManager(requireActivity(), adParam)

        rewardedAdManager?.setAdListener(
            object : RewardedAdListener() {
                override fun onAdLoaded(ad: GfpRewardedAd) {
                    logTextView.append(
                        "[${DateUtil.CURR_TIME_STR}] AD Loaded.\n\t\tAdProviderName: ${ad.adProviderName}\n" // ktlint-disable max-line-length
                    )
                    showButton.isEnabled = true
                }

                override fun onAdStarted(ad: GfpRewardedAd) {
                    logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Started.\n")
                }

                override fun onAdClicked(ad: GfpRewardedAd) {
                    logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Clicked.\n")
                }

                override fun onAdClosed(ad: GfpRewardedAd) {
                    logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Closed.\n")
                    showButton.isEnabled = false
                }

                override fun onAdCompleted(ad: GfpRewardedAd) {
                    logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Completed.\n")
                }

                override fun onError(ad: GfpRewardedAd, error: GfpError) {
                    logTextView.append(
                        "[${DateUtil.CURR_TIME_STR}] Error occurred.\n\tcode[${error.errorCode}]\n\tsubCode[${error.errorSubCode}]\n\t\tmessage[${error.errorMessage}]\n" // ktlint-disable max-line-length
                    )
                }
            }
        )
        logTextView.append("[${DateUtil.CURR_TIME_STR}] AD Requested.\n")
        rewardedAdManager?.loadAd()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rewardedAdManager?.destroy()
    }

    companion object {
        private const val AD_UNIT_ID = "aos_rv_test"
    }
}
