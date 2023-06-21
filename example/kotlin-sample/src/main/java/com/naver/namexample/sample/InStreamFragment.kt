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
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.naver.gfpsdk.AdParam
import com.naver.gfpsdk.GfpError
import com.naver.gfpsdk.GfpNonLinearAdView
import com.naver.gfpsdk.GfpVideoAd
import com.naver.gfpsdk.GfpVideoAdOptions
import com.naver.gfpsdk.GfpVideoAdScheduleManager
import com.naver.gfpsdk.VideoAdListener
import com.naver.gfpsdk.VideoAdScheduleListener
import com.naver.gfpsdk.VideoAdScheduleParam
import com.naver.gfpsdk.VideoScheduleResponse
import com.naver.gfpsdk.provider.AdVideoPlayer
import com.naver.namexample.R
import com.naver.namexample.util.DateUtil
import com.naver.namexample.video.SampleExoPlayerView

class InStreamFragment : Fragment() {
    private var adVideoPlayer: AdVideoPlayer? = null
    private var videoAdScheduleManager: GfpVideoAdScheduleManager? = null
    private lateinit var exoPlayerView: SampleExoPlayerView
    private lateinit var adUiContainer: FrameLayout
    private lateinit var logTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_in_stream, container, false)

        // 본영상과 광고영상 재생을 위한 Sample player 를 가정.
        exoPlayerView = view.findViewById(R.id.ad_video_player)
        adUiContainer = view.findViewById(R.id.ad_ui_container)
        logTextView = view.findViewById(R.id.log_text_view)
        adVideoPlayer = exoPlayerView.createAdVideoPlayer(CONTENTS_URL)

        // adParam setting, 타겟팅이나 동영상 광고에 사용되는 파라미터를 설정합니다.
        // referrer와 current page url은 알맞게 수정하여 주시고
        // custom param의 경우 NAM 담당자를 통해 가이드 받아 주셔야 합니다.
        val adParam = AdParam.Builder()
            .setRefererPageUrl("https://tv.naver.com/r/")
            .setCurrentPageUrl("https://tv.naver.com/v/36037577/list/67096")
            .setVrr(1) // Request a Remind Ad for SMR,where 1 = request, 0 = don't request
            .addCustomParam("lo", "Y") // Loudness Normalazation 적용 여부
            .addCustomParam("pt", "653") // 콘텐츠 클립 전체 시간 (second 단위)
            .addCustomParam("ac", "wavve_aos") // application code (player type) - 고정된 값
            .addCustomParam("pcmo", "mo") // pc or mo or tv - mo로 고정된 값
            .addCustomParam("vid", "0A7CFF5F2F5791DEE667757BD6F9E80411AA") // video ID
            .addCustomParam("tid", "3Cb51Nn2ufoyPok6Tpct-A") // 트랜잭션 ID
            .addCustomParam("cp", "1403") // 제휴사(창작자) 구분 ID
            .addCustomParam("chl", "motline") // 채널 구분 ID
            .addCustomParam("cl", "20539479") // 클립 ID
            .addCustomParam("svc", "WAVVE_AOS") // 서비스 ID - 고정된 값
            .addCustomParam("cc", "N") // 어린이 보호 콘텐츠 적용 여부
            .addCustomParam("AreaId", "clip") // 광고 영역
            .build()
        // 스케줄 아이디는 NAM 담당자로 부터 사전에 안내 받아야 합니다.
        val adScheduleId = "WAVVE_SCH"
        val videoAdScheduleParam = VideoAdScheduleParam.Builder(adScheduleId)
            .setDuration(653L)
            .setAdSchedulePolicy(true, true, true)
            .setContentStartOffset(0)
            .build()

        adVideoPlayer?.let {
            videoAdScheduleManager = GfpVideoAdScheduleManager(
                requireContext(),
                videoAdScheduleParam,
                adParam,
                it,
                adUiContainer
            )
        }

        loadAd()
        return view
    }

    private fun loadAd() {
        // 비디오 옵션 설정
        val videoAdOptions = GfpVideoAdOptions()
        videoAdOptions.setSupportedStreamingHLS(true)
        videoAdScheduleManager?.setVideoAdOptions(videoAdOptions)

        // 스케쥴 리스너 설정
        videoAdScheduleManager?.setAdScheduleListener(object : VideoAdScheduleListener {
            override fun onScheduleLoaded(schedule: VideoScheduleResponse) {
                logTextView.append("[${DateUtil.CURR_TIME_STR}] 광고 스케쥴 로드\n")
                schedule.adBreaks.forEach { adBreak ->
                    logTextView.append(
                        "\t${adBreak.id}, ad cont to play: ${adBreak.adSources.size}, video unit id: ${adBreak.adUnitId}, ad star position(sec): ${adBreak.startDelay}\n" // ktlint-disable max-line-length
                    )
                }
            }

            override fun onContentResumeRequest() {
                logTextView.append("[${DateUtil.CURR_TIME_STR}] 컨텐츠 재생\n")
                // 본 영상 재생
                if (!exoPlayerView.isPlaying()) {
                    exoPlayerView.resumeContentsRequest()
                }
            }

            override fun onContentPauseRequest() {
                logTextView.append("[${DateUtil.CURR_TIME_STR}] 컨텐츠 일시 정지\n")
                exoPlayerView.pauseContentsRequest()
            }

            override fun onScheduleCompleted() {
                logTextView.append("[${DateUtil.CURR_TIME_STR}] 스케줄 내 모든 광고 완료\n")
            }

            override fun onError(gfpError: GfpError) {
                logTextView.append("[${DateUtil.CURR_TIME_STR}] 스케줄 광고 요청 에러($gfpError)\n")
                // 본 영상 재생
                if (!exoPlayerView.isPlaying()) {
                    exoPlayerView.resumeContentsRequest()
                }
            }
        })

        // 광고 응답 정보 리스너 설정
        videoAdScheduleManager?.setAdListener(object : VideoAdListener() {
            override fun onAdLoaded(ad: GfpVideoAd) {
                // NonLinearAdInfo nonLinearAdInfo = ad.getNonLinearAdInfo();
                // remind 배너 정보 확인 가능
                logTextView.append(
                    "[${DateUtil.CURR_TIME_STR}] 광고 로드\n"
                )
            }

            override fun onAdStartReady(ad: GfpVideoAd) {
                logTextView.append("[${DateUtil.CURR_TIME_STR}] 광고 재생 준비 완료\n")
                exoPlayerView.useController = false
                ad.start(true)
            }

            override fun onAdNonLinearStartReady(ad: GfpVideoAd) {
                logTextView.append("[${DateUtil.CURR_TIME_STR}] Non Linear 광고 재생 준비 완료\n")
                // NAM 담당자로 부터, RemindText/RemindBanner 광고에 대해 안내 받으신 이후 사용 부탁드립니다.
                // 아래는 영상 영역 안에 Remind 광고를 설정하는 부분입니다.
                ad.showNonLinearAd(GfpNonLinearAdView.ContainerType.INNER)
            }

            override fun onAdStarted(ad: GfpVideoAd) {
                logTextView.append("[${DateUtil.CURR_TIME_STR}] 광고 재생\n")
            }

            override fun onAdClicked(ad: GfpVideoAd) {
                logTextView.append("[${DateUtil.CURR_TIME_STR}] 클릭 발생\n")
            }

            override fun onAdCompleted(ad: GfpVideoAd) {
                logTextView.append("[${DateUtil.CURR_TIME_STR}] 광고 재생 완료\n")

                // 본 영상 재생
                if (!exoPlayerView.isPlaying()) {
                    exoPlayerView.resumeContentsRequest()
                }
            }

            override fun onError(ad: GfpVideoAd, error: GfpError) {
                logTextView.append("[${DateUtil.CURR_TIME_STR}] 에러 발생(${error.errorMessage})\n")

                // 본 영상 재생
                if (!exoPlayerView.isPlaying()) {
                    exoPlayerView.resumeContentsRequest()
                }
            }
        })
        videoAdScheduleManager?.load()
    }

    override fun onPause() {
        super.onPause()
        if (videoAdScheduleManager != null) {
            videoAdScheduleManager?.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (videoAdScheduleManager != null) {
            videoAdScheduleManager?.resume()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayerView.release()
        if (videoAdScheduleManager != null) {
            videoAdScheduleManager?.destroy()
        }
    }

    companion object {
        private const val CONTENTS_URL =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
    }
}
