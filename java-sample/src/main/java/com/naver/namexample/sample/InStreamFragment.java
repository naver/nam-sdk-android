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
import com.naver.gfpsdk.GfpVideoAdScheduleManager;
import com.naver.gfpsdk.GfpVideoProperties;
import com.naver.gfpsdk.VideoAdListener;
import com.naver.gfpsdk.VideoAdScheduleListener;
import com.naver.gfpsdk.VideoAdScheduleParam;
import com.naver.gfpsdk.VideoScheduleResponse;
import com.naver.gfpsdk.mediation.AdVideoPlayer;
import com.naver.namexample.R;
import com.naver.namexample.video.SampleExoPlayerView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InStreamFragment extends Fragment {
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
    private static final String CONTENTS_URL =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4";
    private GfpVideoAdScheduleManager videoAdScheduleManager;
    private SampleExoPlayerView exoPlayerView;
    private TextView logTextView;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_in_stream, container, false);

        // 본영상과 광고영상 재생을 위한 Sample player 를 가정.
        exoPlayerView = view.findViewById(R.id.ad_video_player);
        FrameLayout adUiContainer = view.findViewById(R.id.ad_ui_container);
        logTextView = view.findViewById(R.id.log_text_view);
        AdVideoPlayer adVideoPlayer = exoPlayerView.createAdVideoPlayer(CONTENTS_URL);

        // adParam setting, 타겟팅이나 동영상 광고에 사용되는 파라미터를 설정합니다.
        // referrer와 current page url은 알맞게 수정하여 주시고
        // custom param의 경우 NAM 담당자를 통해 가이드 받아 주셔야 합니다.
        AdParam adParam =
                new AdParam.Builder()
                        .setRefererPageUrl("https://tv.naver.com/r/")
                        .setCurrentPageUrl("https://tv.naver.com/v/36037577/list/67096")
                        .setVrr(1) // Request a Remind Ad for SMR (1 = request, 0 = don't request)
                        .addCustomParam("lo", "Y") // Loudness Normalazation 적용 여부
                        .addCustomParam("pt", "653") // 콘텐츠 클립 전체 시간 (second 단위)
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
        String adScheduleId = "YOUR_SCH_ID";
        VideoAdScheduleParam videoAdScheduleParam =
                new VideoAdScheduleParam.Builder(adScheduleId)
                        .setDuration(653L)
                        .setAdSchedulePolicy(true, true, true)
                        .setContentStartOffset(0)
                        .build();
        videoAdScheduleManager =
                new GfpVideoAdScheduleManager(
                        requireContext(),
                        videoAdScheduleParam,
                        adParam,
                        adVideoPlayer,
                        adUiContainer);

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
                    public void onScheduleLoaded(VideoScheduleResponse schedule) {
                        logTextView.append(
                                String.format("[%s] 광고 스케쥴 로드%n", sdf.format(new Date())));

                        for (VideoScheduleResponse.AdBreak adBreak : schedule.getAdBreaks()) {
                            String format = "\t%s[%d, %s, %s]%n";
                            if (adBreak.getStartDelay() == -2) {
                                logTextView.append(
                                        String.format(
                                                Locale.getDefault(),
                                                format,
                                                adBreak.getId(),
                                                adBreak.getAdSources().size(),
                                                adBreak.getAdUnitId(),
                                                "종료시"));
                            } else {
                                logTextView.append(
                                        String.format(
                                                Locale.getDefault(),
                                                format,
                                                adBreak.getId(),
                                                adBreak.getAdSources().size(),
                                                adBreak.getAdUnitId(),
                                                adBreak.getStartDelay() + "s"));
                            }
                        }
                    }

                    @Override
                    public void onContentResumeRequest() {
                        logTextView.append(String.format("[%s] 컨텐츠 재생%n", sdf.format(new Date())));
                        // 본 영상 재생
                        if (!exoPlayerView.isPlaying()) {
                            exoPlayerView.resumeContentsRequest();
                        }
                    }

                    @Override
                    public void onContentPauseRequest() {
                        logTextView.append(
                                String.format("[%s] 컨텐츠 일시 정지%n", sdf.format(new Date())));
                        exoPlayerView.pauseContentsRequest();
                    }

                    @Override
                    public void onScheduleCompleted() {
                        logTextView.append(
                                String.format("[%s] 스케줄 내 모든 광고 완료%n", sdf.format(new Date())));
                    }

                    @Override
                    public void onError(GfpError gfpError) {
                        logTextView.append(
                                String.format(
                                        "[%s] 스케줄 광고 요청 에러(%s)%n",
                                        sdf.format(new Date()), gfpError.toString()));
                        // 본 영상 재생
                        if (!exoPlayerView.isPlaying()) {
                            exoPlayerView.resumeContentsRequest();
                        }
                    }
                });

        // timeout 설정
        videoAdScheduleManager.setGfpVideoProperties(new GfpVideoProperties(60_000L));

        // 광고 응답 정보 리스너 설정
        videoAdScheduleManager.setAdListener(
                new VideoAdListener() {
                    @Override
                    public void onAdLoaded(@NonNull GfpVideoAd ad) {
                        logTextView.append(String.format("[%s] 광고 로드%n", sdf.format(new Date())));
                    }

                    @Override
                    public void onAdStartReady(@NonNull GfpVideoAd ad) {
                        ad.start(true);
                        logTextView.append(
                                String.format("[%s] 광고 재생 준비 완료%n", sdf.format(new Date())));
                    }

                    @Override
                    public void onAdNonLinearStartReady(@NonNull GfpVideoAd ad) {
                        logTextView.append(
                                String.format(
                                        "[%s] Non Linear 광고 재생 준비 완료%n", sdf.format(new Date())));
                        // NAM 담당자로 부터, RemindText/RemindBanner 광고에 대해 안내 받으신 이후 사용 부탁드립니다.
                        // 아래는 영상 영역 안에 Remind 광고를 설정하는 부분입니다.
                        ad.showNonLinearAd(GfpNonLinearAdView.ContainerType.INNER);
                    }

                    @Override
                    public void onAdStarted(@NonNull GfpVideoAd ad) {
                        logTextView.append(String.format("[%s] 광고 재생%n", sdf.format(new Date())));
                    }

                    @Override
                    public void onAdClicked(@NonNull GfpVideoAd ad) {
                        logTextView.append(String.format("[%s] 클릭 발생%n", sdf.format(new Date())));
                    }

                    @Override
                    public void onAdCompleted(@NonNull GfpVideoAd ad) {
                        logTextView.append(
                                String.format("[%s] 광고 재생 완료%n", sdf.format(new Date())));

                        // 본 영상 재생
                        if (!exoPlayerView.isPlaying()) {
                            exoPlayerView.resumeContentsRequest();
                        }
                    }

                    @Override
                    public void onError(@NonNull GfpVideoAd ad, @NonNull GfpError error) {
                        logTextView.append(
                                String.format(
                                        "[%s] 에러 발생(%s)%n",
                                        sdf.format(new Date()), error.getErrorMessage()));

                        // 본 영상 재생
                        if (!exoPlayerView.isPlaying()) {
                            exoPlayerView.resumeContentsRequest();
                        }
                    }
                });
        videoAdScheduleManager.load();
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
