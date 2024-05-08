/*
 * NAM(Naver Ad Manager) SDK for Android
 *
 * Copyright 2022-present NAVER Corp.
 * All rights reserved.
 *
 * Unauthorized use, modification and redistribution of this software are strongly prohibited.
 */
package com.naver.namexample.video

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.LoadEventInfo
import com.google.android.exoplayer2.source.MediaLoadData
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MediaSourceEventListener
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.naver.gfpsdk.provider.AdVideoPlayer
import com.naver.gfpsdk.provider.AdVideoPlayer.PlayerCallback
import java.io.IOException
import java.util.Locale
import java.util.concurrent.CopyOnWriteArraySet

class SampleExoPlayerView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : StyledPlayerView(context, attrs, defStyleAttr) {
    private val videoPlayerCallbacks = CopyOnWriteArraySet<PlayerCallback>()
    private var player: ExoPlayer? = null
    private var playbackState: PlaybackState? = null
    private var adPlayer: AdVideoPlayer? = null
    private var contentVideoUrl: String? = null
    private var savedContentPosition = 0L

    init {
        player = ExoPlayer.Builder(context).build()
        player?.addListener(
            object : Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    // 재생 중 종료 요청이 온 경우에만 complete 처리
                    if (state == Player.STATE_ENDED && playbackState == PlaybackState.PLAYING) {
                        adPlayer?.disablePlaybackControls()
                        player?.stop()
                        player?.clearMediaItems()
                        playbackState = PlaybackState.STOPPED
                        for (callback in videoPlayerCallbacks) {
                            callback.onCompleted()
                        }
                        adPlayer?.enablePlaybackControls()
                    }
                }

                override fun onPlayerError(e: PlaybackException) {
                    Log.e(LOG_TAG, "onPlayerError", e)
                    for (callback in videoPlayerCallbacks) {
                        callback.onError()
                    }
                }
            }
        )

        setShowBuffering(SHOW_BUFFERING_WHEN_PLAYING)
        useController = false
        player?.playWhenReady = true
        setPlayer(player)
    }

    fun createAdVideoPlayer(contentVideoUrl: String?): AdVideoPlayer? {
        this.contentVideoUrl = contentVideoUrl
        initAdVideoPlayer()
        return adPlayer
    }

    private fun initAdVideoPlayer() {
        playbackState = PlaybackState.STOPPED
        adPlayer = object : AdVideoPlayer {
            override fun play() {
                player?.playWhenReady = true
                playbackState = PlaybackState.PLAYING
                videoPlayerCallbacks.forEach { it.onPlay() }
            }

            override fun pause() {
                player?.playWhenReady = false
                playbackState = PlaybackState.PAUSED
                videoPlayerCallbacks.forEach { it.onPause() }
            }

            override fun resume() {
                player?.playWhenReady = true
                playbackState = PlaybackState.PLAYING
                videoPlayerCallbacks.forEach { it.onResume() }
            }

            override fun getCurrentPosition(): Long {
                return player?.currentPosition ?: 0L
            }

            override fun seekTo(videoPosition: Long) {
                player?.seekTo(videoPosition)
            }

            override fun getDuration(): Long {
                return player?.duration ?: 0
            }

            override fun getBufferedTime(): Int {
                return player?.let {
                    (it.duration * it.bufferedPercentage / 100).toInt()
                } ?: 0
            }

            override fun getVolume(): Float {
                return player?.volume ?: 0f
            }

            override fun stopPlayback() {
                if (playbackState == PlaybackState.STOPPED) {
                    return
                }
                if (isPlaying()) {
                    player?.stop()
                }
                playbackState = PlaybackState.STOPPED
            }

            override fun disablePlaybackControls() {
                // nothing
            }

            override fun enablePlaybackControls() {
                // nothing
            }

            override fun setVideoPath(videoUrl: String) {
                player?.setMediaSource(generateMediaSource(videoUrl), true)
                player?.prepare()
            }

            override fun addPlayerCallback(callback: PlayerCallback) {
                videoPlayerCallbacks.add(callback)
            }

            override fun removePlayerCallback(callback: PlayerCallback) {
                videoPlayerCallbacks.remove(callback)
            }
        }
        adPlayer?.enablePlaybackControls()
    }

    fun pauseContentsRequest() {
        adPlayer?.let {
            savedContentPosition = it.currentPosition
            it.stopPlayback()
        }
        useController = false
    }

    fun resumeContentsRequest() {
        useController = true
        player?.let {
            it.setMediaSource(generateMediaSource(contentVideoUrl), true)
            it.prepare()
            adPlayer?.seekTo(savedContentPosition)
            adPlayer?.play()
        }
    }

    private fun generateMediaSource(videoUrl: String?): MediaSource {
        return if (videoUrl != null && videoUrl.lowercase(Locale.getDefault()).contains(".m3u8")) {
            val hlsMediaSource = HlsMediaSource.Factory(
                DefaultDataSource.Factory(
                    context,
                    DefaultHttpDataSource.Factory().setUserAgent("user_agent")
                )
            )
                .createMediaSource(MediaItem.fromUri(videoUrl))
            hlsMediaSource.addEventListener(
                Handler(Looper.getMainLooper()),
                object : MediaSourceEventListener {
                    override fun onLoadStarted(
                        windowIndex: Int,
                        mediaPeriodId: MediaSource.MediaPeriodId?,
                        loadEventInfo: LoadEventInfo,
                        mediaLoadData: MediaLoadData
                    ) {
                        // do nothing
                    }

                    override fun onLoadCompleted(
                        windowIndex: Int,
                        mediaPeriodId: MediaSource.MediaPeriodId?,
                        loadEventInfo: LoadEventInfo,
                        mediaLoadData: MediaLoadData
                    ) {
                        // do nothing
                    }

                    override fun onLoadCanceled(
                        windowIndex: Int,
                        mediaPeriodId: MediaSource.MediaPeriodId?,
                        loadEventInfo: LoadEventInfo,
                        mediaLoadData: MediaLoadData
                    ) {
                        // do nothing
                    }

                    override fun onLoadError(
                        windowIndex: Int,
                        mediaPeriodId: MediaSource.MediaPeriodId?,
                        loadEventInfo: LoadEventInfo,
                        mediaLoadData: MediaLoadData,
                        error: IOException,
                        wasCanceled: Boolean
                    ) {
                        // do nothing
                    }

                    override fun onUpstreamDiscarded(
                        windowIndex: Int,
                        mediaPeriodId: MediaSource.MediaPeriodId,
                        mediaLoadData: MediaLoadData
                    ) {
                        // do nothing
                    }

                    override fun onDownstreamFormatChanged(
                        windowIndex: Int,
                        mediaPeriodId: MediaSource.MediaPeriodId?,
                        mediaLoadData: MediaLoadData
                    ) {
                        // do nothing
                    }
                }
            )
            hlsMediaSource
        } else {
            ProgressiveMediaSource.Factory(
                DefaultDataSource.Factory(
                    context,
                    DefaultHttpDataSource.Factory().setUserAgent("user_agent")
                )
            )
                .createMediaSource(MediaItem.fromUri(videoUrl!!))
        }
    }

    /** player reset  */
    fun reset() {
        savedContentPosition = 0
        player?.stop()
        player?.clearMediaItems()
    }

    /** playerView & player release  */
    fun release() {
        player?.release()
        player = null
        adPlayer = null
        videoPlayerCallbacks.clear()
        removeAllViews()
    }

    fun isPlaying(): Boolean {
        return playbackState == PlaybackState.PLAYING
    }

    fun isPaused(): Boolean {
        return playbackState == PlaybackState.PAUSED
    }

    companion object {
        private val LOG_TAG = SampleExoPlayerView::class.java.simpleName
    }

    enum class PlaybackState {
        STOPPED, PAUSED, PLAYING
    }
}
