/*
 * NAM(Naver Ad Manager) SDK for Android
 *
 * Copyright 2022-present NAVER Corp.
 * All rights reserved.
 *
 * Unauthorized use, modification and redistribution of this software are strongly prohibited.
 */
package com.naver.namexample.video;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.naver.ads.NasLogger;
import com.naver.gfpsdk.provider.AdVideoPlayer;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

public class SampleExoPlayerView extends StyledPlayerView {
    private static final String LOG_TAG = SampleExoPlayerView.class.getSimpleName();
    private final CopyOnWriteArraySet<AdVideoPlayer.PlayerCallback> videoPlayerCallbacks =
            new CopyOnWriteArraySet<>();
    private ExoPlayer player;
    private PlaybackState playbackState;
    private AdVideoPlayer adPlayer;
    private String contentVideoUrl;
    private long savedContentPosition = 0L;

    public SampleExoPlayerView(Context context) {
        this(context, null);
    }

    public SampleExoPlayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleExoPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        player = new ExoPlayer.Builder(context).build();
        player.addListener(
                new Player.Listener() {
                    @Override
                    public void onPlaybackStateChanged(int state) {
                        // 재생 중 종료 요청이 온 경우에만 complete 처리
                        if (state == Player.STATE_ENDED && playbackState == PlaybackState.PLAYING) {
                            adPlayer.disablePlaybackControls();
                            player.stop();
                            player.clearMediaItems();
                            playbackState = PlaybackState.STOPPED;

                            for (AdVideoPlayer.PlayerCallback callback : videoPlayerCallbacks) {
                                callback.onCompleted();
                            }
                            adPlayer.enablePlaybackControls();
                        }
                    }

                    @Override
                    public void onPlayerError(@NonNull PlaybackException e) {
                        Log.e(LOG_TAG, "onPlayerError", e);

                        for (AdVideoPlayer.PlayerCallback callback : videoPlayerCallbacks) {
                            callback.onError();
                        }
                    }
                });

        setShowBuffering(SHOW_BUFFERING_WHEN_PLAYING);
        setUseController(false);
        player.setPlayWhenReady(true);
        setPlayer(player);
    }

    public AdVideoPlayer createAdVideoPlayer(String contentVideoUrl) {
        this.contentVideoUrl = contentVideoUrl;

        initAdVideoPlayer();

        return adPlayer;
    }

    public void initAdVideoPlayer() {
        playbackState = PlaybackState.STOPPED;

        adPlayer =
                new AdVideoPlayer() {
                    @Override
                    public void play() {
                        if (player != null) {
                            player.setPlayWhenReady(true);
                        }
                        playbackState = PlaybackState.PLAYING;

                        for (PlayerCallback callback : videoPlayerCallbacks) {
                            callback.onPlay();
                        }
                    }

                    @Override
                    public void pause() {
                        if (player != null) {
                            player.setPlayWhenReady(false);
                        }
                        playbackState = PlaybackState.PAUSED;

                        for (PlayerCallback callback : videoPlayerCallbacks) {
                            callback.onPause();
                        }
                    }

                    @Override
                    public void resume() {
                        if (player != null) {
                            player.setPlayWhenReady(true);
                        }
                        playbackState = PlaybackState.PLAYING;

                        for (PlayerCallback callback : videoPlayerCallbacks) {
                            callback.onResume();
                        }
                    }

                    @Override
                    public long getCurrentPosition() {
                        if (player != null) {
                            return player.getCurrentPosition();
                        } else {
                            return 0;
                        }
                    }

                    @Override
                    public void seekTo(long videoPosition) {
                        if (player != null) {
                            player.seekTo(videoPosition);
                        }
                    }

                    @Override
                    public long getDuration() {
                        return playbackState == PlaybackState.STOPPED || player == null
                                ? 0
                                : player.getDuration();
                    }

                    @Override
                    public int getBufferedTime() {
                        return playbackState == PlaybackState.STOPPED || player == null
                                ? 0
                                : (int)
                                        (player.getDuration()
                                                * player.getBufferedPercentage()
                                                / 100);
                    }

                    @Override
                    public float getVolume() {
                        if (player != null) {
                            return player.getVolume();
                        } else {
                            return 0;
                        }
                    }

                    @Override
                    public void stopPlayback() {
                        if (playbackState == PlaybackState.STOPPED) {
                            return;
                        }

                        if (SampleExoPlayerView.this.isPlaying() && player != null) {
                            player.stop();
                        }

                        playbackState = PlaybackState.STOPPED;
                    }

                    @Override
                    public void disablePlaybackControls() {
                        // nothing
                    }

                    @Override
                    public void enablePlaybackControls() {
                        // nothing
                    }

                    @Override
                    public void setVideoPath(String videoUrl) {
                        if (player != null) {
                            player.setMediaSource(generateMediaSource(videoUrl), true);
                            player.prepare();
                        }
                    }

                    @Override
                    public void addPlayerCallback(PlayerCallback callback) {
                        videoPlayerCallbacks.add(callback);
                    }

                    @Override
                    public void removePlayerCallback(PlayerCallback callback) {
                        videoPlayerCallbacks.remove(callback);
                    }
                };
        adPlayer.enablePlaybackControls();
    }

    public void pauseContentsRequest() {
        savedContentPosition = adPlayer.getCurrentPosition();
        adPlayer.stopPlayback();
        setUseController(false);
    }

    public void resumeContentsRequest() {
        setUseController(true);
        if (player != null) {
            MediaSource mediaSource = generateMediaSource(contentVideoUrl);
            if (mediaSource != null) {
                player.setMediaSource(generateMediaSource(contentVideoUrl), true);
                player.prepare();
                adPlayer.seekTo(savedContentPosition);
                adPlayer.play();
            } else {
                NasLogger.e(LOG_TAG, "null mediaSource");
                for (AdVideoPlayer.PlayerCallback callback : videoPlayerCallbacks) {
                    callback.onError();
                }
                player.stop();
            }
        }
    }

    private MediaSource generateMediaSource(String videoUrl) {
        if (videoUrl != null && videoUrl.toLowerCase().contains(".m3u8")) {
            HlsMediaSource hlsMediaSource =
                    new HlsMediaSource.Factory(
                                    new DefaultDataSource.Factory(
                                            getContext(),
                                            new DefaultHttpDataSource.Factory()
                                                    .setUserAgent("user_agent")))
                            .createMediaSource(MediaItem.fromUri(videoUrl));

            hlsMediaSource.addEventListener(
                    new Handler(Looper.getMainLooper()),
                    new MediaSourceEventListener() {
                        @Override
                        public void onLoadStarted(
                                int windowIndex,
                                @Nullable MediaSource.MediaPeriodId mediaPeriodId,
                                LoadEventInfo loadEventInfo,
                                MediaLoadData mediaLoadData) {
                            // do nothing
                        }

                        @Override
                        public void onLoadCompleted(
                                int windowIndex,
                                @Nullable MediaSource.MediaPeriodId mediaPeriodId,
                                LoadEventInfo loadEventInfo,
                                MediaLoadData mediaLoadData) {
                            // do nothing
                        }

                        @Override
                        public void onLoadCanceled(
                                int windowIndex,
                                @Nullable MediaSource.MediaPeriodId mediaPeriodId,
                                LoadEventInfo loadEventInfo,
                                MediaLoadData mediaLoadData) {
                            // do nothing
                        }

                        @Override
                        public void onLoadError(
                                int windowIndex,
                                @Nullable MediaSource.MediaPeriodId mediaPeriodId,
                                LoadEventInfo loadEventInfo,
                                MediaLoadData mediaLoadData,
                                IOException error,
                                boolean wasCanceled) {
                            // do nothing
                        }

                        @Override
                        public void onUpstreamDiscarded(
                                int windowIndex,
                                MediaSource.MediaPeriodId mediaPeriodId,
                                MediaLoadData mediaLoadData) {
                            // do nothing
                        }

                        @Override
                        public void onDownstreamFormatChanged(
                                int windowIndex,
                                @Nullable MediaSource.MediaPeriodId mediaPeriodId,
                                MediaLoadData mediaLoadData) {
                            // nothing
                        }
                    });

            return hlsMediaSource;
        } else if (videoUrl != null) {
            return new ProgressiveMediaSource.Factory(
                            new DefaultDataSource.Factory(
                                    getContext(),
                                    new DefaultHttpDataSource.Factory().setUserAgent("user_agent")))
                    .createMediaSource(MediaItem.fromUri(videoUrl));
        } else {
            return null;
        }
    }

    /** player reset */
    public void reset() {
        savedContentPosition = 0;

        if (player != null) {
            player.stop();
            player.clearMediaItems();
        }
    }

    /** playerView & player release */
    public void release() {
        if (player != null) {
            player.release();
            player = null;
        }

        adPlayer = null;
        videoPlayerCallbacks.clear();
        removeAllViews();
    }

    public boolean isPlaying() {
        return playbackState == PlaybackState.PLAYING;
    }

    public boolean isPaused() {
        return playbackState == PlaybackState.PAUSED;
    }

    public enum PlaybackState {
        STOPPED,
        PAUSED,
        PLAYING
    }
}
