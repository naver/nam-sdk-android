/*
 * NAM(Naver Ad Manager) SDK for Android
 *
 * Copyright 2022-present NAVER Corp.
 * All rights reserved.
 *
 * Unauthorized use, modification and redistribution of this software are strongly prohibited.
 */
package com.naver.namexample.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.naver.gfpsdk.AdParam;
import com.naver.gfpsdk.GfpError;
import com.naver.gfpsdk.GfpNonLinearAdView;
import com.naver.gfpsdk.GfpVideoAd;
import com.naver.gfpsdk.GfpVideoAdOptions;
import com.naver.gfpsdk.GfpVideoAdQoeInfo;
import com.naver.gfpsdk.GfpVideoAdQoeListener;
import com.naver.gfpsdk.GfpVideoAdScheduleManager;
import com.naver.gfpsdk.GfpVideoProperties;
import com.naver.gfpsdk.RemindTextAdViewAttributes;
import com.naver.gfpsdk.VideoAdListener;
import com.naver.gfpsdk.VideoAdScheduleListener;
import com.naver.gfpsdk.VideoAdScheduleParam;
import com.naver.gfpsdk.provider.AdVideoPlayer;
import com.naver.namexample.R;
import com.naver.namexample.video.SampleExoPlayerView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InStreamFragment extends Fragment {
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
    private static final String CONTENTS_URL =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4";
    private AdVideoPlayer adVideoPlayer;
    private GfpVideoAdScheduleManager videoAdScheduleManager;
    private SampleExoPlayerView exoPlayerView;
    private FrameLayout outerRemindAdContainer;
    private FrameLayout adUiContainer;
    private TextView logTextView;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_in_stream, container, false);

        // 본영상과 광고영상 재생을 위한 Sample player 를 가정.
        exoPlayerView = view.findViewById(R.id.ad_video_player);
        adUiContainer = view.findViewById(R.id.ad_ui_container);
        outerRemindAdContainer = view.findViewById(R.id.outer_remind_ad_container);
        logTextView = view.findViewById(R.id.log_text_view);
        adVideoPlayer = exoPlayerView.createAdVideoPlayer(CONTENTS_URL);

        // adParam setting, 타겟팅이나 동영상 광고에 사용되는 파라미터를 설정합니다.
        // referrer와 current page url은 알맞게 수정하여 주시고
        // custom param의 경우 NAM 담당자를 통해 가이드 받아 주셔야 합니다.
        AdParam adParam =
                new AdParam.Builder()
                        .setRefererPageUrl("https://tv.naver.com/r/")
                        .setCurrentPageUrl("https://tv.naver.com/v/36037577/list/67096")
                        .setVrr(1) // Request a Remind Ad for SMR (1 = request, 0 = don't request)
                        .addCustomParam("lo", "Y") // Loudness Normalazation 적용 여부
                        .addCustomParam("pt", "635") // 콘텐츠 클립 전체 시간 (second 단위)
                        .addCustomParam("pcmo", "mo") // pc or mo or tv - mo로 고정된 값
                        .addCustomParam("vid", "0A7CFF5F2F5791DEE667757BD6F9E80411AA") // video ID
                        .addCustomParam("tid", "3Cb51Nn2ufoyPok6Tpct-A") // 트랜잭션 ID
                        .addCustomParam("cp", "1403") // 제휴사(창작자) 구분 ID
                        .addCustomParam("chl", "motline") // 채널 구분 ID
                        .addCustomParam("cl", "20539479") // 클립 ID
                        .addCustomParam("svc", "WAVVE_AOS") // 서비스 ID - 고정된 값
                        .addCustomParam("cc", "N") // 어린이 보호 콘텐츠 적용 여부
                        .addCustomParam("AreaId", "clip") // 광고 영역
                        .build();
        // 스케줄 아이디는 NAM 담당자로 부터 사전에 안내 받아야 합니다.
        String adScheduleId = "WAVVE_SCH";
        VideoAdScheduleParam videoAdScheduleParam =
                new VideoAdScheduleParam.Builder(adScheduleId)
                        .setDuration(653L)
                        .setAdSchedulePolicy(true, true, true)
                        .setContentStartOffset(0)
                        .build();
        videoAdScheduleManager =
                new GfpVideoAdScheduleManager(
                        getContext(), videoAdScheduleParam, adParam, adVideoPlayer, adUiContainer);

        loadAd();
        return view;
    }

    private void loadAd() {
        // 비디오 옵션 설정
        GfpVideoAdOptions videoAdOptions = new GfpVideoAdOptions();
        videoAdOptions.setSupportedStreamingHLS(true);
        videoAdScheduleManager.setVideoAdOptions(videoAdOptions);

        // 스케쥴 리스너 설정
        videoAdScheduleManager.setAdScheduleListener(
                new VideoAdScheduleListener() {
                    @Override
                    public void onScheduleLoaded() {
                        logTextView.append(String.format("[%s] 광고 스케쥴 로드", sdf.format(new Date())));
                    }

                    @Override
                    public void onContentResumeRequest() {
                        logTextView.append(String.format("[%s] 컨텐츠 재생", sdf.format(new Date())));
                        // 본 영상 재생
                        if (!exoPlayerView.isPlaying()) {
                            exoPlayerView.resumeContentsRequest();
                        }
                    }

                    @Override
                    public void onContentPauseRequest() {
                        logTextView.append(String.format("[%s] 컨텐츠 일시 정지", sdf.format(new Date())));
                        exoPlayerView.pauseContentsRequest();
                    }

                    @Override
                    public void onScheduleCompleted() {
                        logTextView.append(
                                String.format("[%s] 스케줄 내 모든 광고 완료", sdf.format(new Date())));
                    }

                    @Override
                    public void onError(GfpError gfpError) {
                        logTextView.append(
                                String.format(
                                        "[%s] 스케줄 광고 요청 에러(%s)",
                                        sdf.format(new Date()), gfpError.toString()));
                        // 본 영상 재생
                        if (!exoPlayerView.isPlaying()) {
                            exoPlayerView.resumeContentsRequest();
                        }
                    }
                });

        videoAdScheduleManager.setGfpVideoProperties(new GfpVideoProperties(100L));

        // 광고 응답 정보 리스너 설정
        videoAdScheduleManager.setAdListener(
                new VideoAdListener() {
                    @Override
                    public void onAdLoaded(GfpVideoAd ad) {
                        // NonLinearAdInfo nonLinearAdInfo = ad.getNonLinearAdInfo();
                        // remind 배너 정보 확인 가능
                        logTextView.append(
                                String.format(
                                        "[%s] 광고 로드\tResponseInfo: %s",
                                        sdf.format(new Date()), ad.getResponseInfo().toString()));
                    }

                    @Override
                    public void onAdStartReady(GfpVideoAd ad) {
                        ad.start(true);
                        logTextView.append(
                                String.format("[%s] 광고 재생 준비 완료", sdf.format(new Date())));
                    }

                    @Override
                    public void onAdNonLinearStartReady(GfpVideoAd ad) {
                        logTextView.append(
                                String.format(
                                        "[%s] Non Linear 광고 재생 준비 완료", sdf.format(new Date())));
                        // NAM 담당자로 부터, RemindText/RemindBanner 광고에 대해 안내 받으신 이후 사용 부탁드립니다.
                        // 아래는 영상 영역 안에 Remind 광고를 설정하는 부분입니다.
                        ad.showNonLinearAd(GfpNonLinearAdView.ContainerType.INNER);
                    }

                    @Override
                    public void onAdStarted(GfpVideoAd ad) {
                        logTextView.append(String.format("[%s] 광고 재생", sdf.format(new Date())));
                    }

                    @Override
                    public void onAdClicked(GfpVideoAd ad) {
                        logTextView.append(String.format("[%s] 클릭 발생", sdf.format(new Date())));
                    }

                    @Override
                    public void onAdCompleted(GfpVideoAd ad) {
                        logTextView.append(String.format("[%s] 광고 재생 완료", sdf.format(new Date())));

                        // 본 영상 재생
                        if (!exoPlayerView.isPlaying()) {
                            exoPlayerView.resumeContentsRequest();
                        }
                    }

                    @Override
                    public void onError(GfpVideoAd ad, GfpError error) {
                        logTextView.append(
                                String.format(
                                        "[%s] 에러 발생(%s)",
                                        sdf.format(new Date()), error.getErrorMessage()));

                        // 본 영상 재생
                        if (!exoPlayerView.isPlaying()) {
                            exoPlayerView.resumeContentsRequest();
                        }
                    }
                });

        videoAdScheduleManager.setQoeListener(
                new GfpVideoAdQoeListener() {
                    @Override
                    public void onAdLoaded(GfpVideoAdQoeInfo info) {
                        /**
                         * 광고 로드 info.getPlacementType(), info.getProvider(), info.getMediaHeight(),
                         * info.getMediaWidth(), info.getCurrentTime(), info.getDuration(),
                         * info.getSkipOffset()
                         */
                    }

                    @Override
                    public void onAdStarted(GfpVideoAdQoeInfo info) {
                        // 광고 시작
                    }

                    @Override
                    public void onAdPaused(GfpVideoAdQoeInfo info) {
                        // 광고 일시 정지
                    }

                    @Override
                    public void onAdSkipped(GfpVideoAdQoeInfo info) {
                        // 광고 skip
                    }

                    @Override
                    public void onAdResumed(GfpVideoAdQoeInfo info) {
                        // 광고 재생
                    }

                    @Override
                    public void onAdClicked(GfpVideoAdQoeInfo info) {
                        // 광고 클릭
                    }

                    @Override
                    public void onAdCompleted(GfpVideoAdQoeInfo info) {
                        // 광고 완료
                    }

                    @Override
                    public void onError(GfpError error) {
                        // 에러
                    }
                });

        videoAdScheduleManager.load();
    }

    private void setOuterRemindTextAdSetting() {
        // NAM 담당자로 부터, RemindText 광고에 대해 안내 받으신 이후 사용 부탁드립니다.
        // RemindText 광고가 영상 영역 바깥에 그려지는 경우 사용하기 위한 설정입니다. (GfpNonLinearAdView.ContainerType.OUTER)
        RemindTextAdViewAttributes.Builder remindTextAttributeBuilder =
                new RemindTextAdViewAttributes.Builder();
        videoAdScheduleManager.setOuterRemindTextAdViewAttributes(
                remindTextAttributeBuilder.build());
        videoAdScheduleManager.setOuterRemindTextAdViewContainer(outerRemindAdContainer);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (videoAdScheduleManager != null) {
            videoAdScheduleManager.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (videoAdScheduleManager != null) {
            videoAdScheduleManager.resume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (exoPlayerView != null) {
            exoPlayerView.release();
            exoPlayerView = null;
        }

        if (videoAdScheduleManager != null) {
            videoAdScheduleManager.destroy();
        }
    }
}
