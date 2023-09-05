# InStream (Video) ads

In-Stream (Video) AD is placed between the beginning and the end of the video content.

## Prerequisites

The player is injected from Service app to play ad. This guide using `exoplayer` for example.
Please check the following 'Implement the AdVideoPlayer'

## Step 1: Add dependencies
```groovy
dependencies {
    implementation platform('com.naver.gfpsdk:nam-bom:6.3.0')
    implementation 'com.naver.gfpsdk:nam-core'
    implementation 'com.naver.gfpsdk:nam-ndavideo'                      // (optional) for instream ads
    implementation 'com.google.android.exoplayer:exoplayer-core:2.18.0' // using exoplayer for example
    implementation 'com.google.android.exoplayer:exoplayer-hls:2.18.0'
    implementation 'com.google.android.exoplayer:exoplayer-ui:2.18.0'
}
```

## Step 2: Implement the AdVideoPlayer
To play the ad, you have to implement the interface `com.naver.gfpsdk.provider.AdVideoPlayer`.
```java
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
            player.setMediaSource(generateMediaSource(contentVideoUrl), true);
            player.prepare();
            adPlayer.seekTo(savedContentPosition);
            adPlayer.play();
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
        } else {
            return new ProgressiveMediaSource.Factory(
                            new DefaultDataSource.Factory(
                                    getContext(),
                                    new DefaultHttpDataSource.Factory().setUserAgent("user_agent")))
                    .createMediaSource(MediaItem.fromUri(videoUrl));
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
```

## Step 3: Add layout to locate player
Please, add video player and UI container for AD to your layout.
In this example, `ad_video_player` and `ad_ui_container` are used in constructor of ad loader(GfpVideoAdScheduleManager)

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    ... >
    <com.naver.gfpsdk.adssample.video.SampleExoPlayerView
        android:id="@+id/ad_video_player"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_centerHorizontal="true" />

    <FrameLayout
        android:id="@+id/ad_ui_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</RelativeLayout>
```

## Step 4: Set parameters for AD and schedule
### 4-1. VideoAdScheduleParam
In-Stream ads, needs a schedule to place it in the content.
- `contentDuration`
  in seconds, this is mandatory.
- `adScheduleId`
  Required value. Should be informed in advance from NAM admin.
- `setAdSchedulePolicy`
  Optional value. Can optionally exclude ad.
  Actually NAM admin sets the schedule policy.
  But if you want to disable 'pre/mid/post' roll ad, using this.
- `setContentStartOffset`
  Optional value. Ad schedule offset will be adjusted to this.
- `setAdNoticeDurationSec`
  Optional value. In mid roll ad, there is notification text that informs ad to user.
```java
long contentDuration = 653L; // content (video) length in seconds
String adScheduleId = "YOUR_ID"; // get informed from NAM admin like Ad Unit ID
boolean pre = true;
boolean mid = false; // this will disable mid roll ad.
boolean post = true;

VideoAdScheduleParam videoAdScheduleParam = new VideoAdScheduleParam.Builder(adScheduleId)
                .setDuration(contentDuration)
                .setAdSchedulePolicy(pre, mid, post)
                .build();
```
### 4-2. AdParam
Unlike other ads, In-Stream ad does not need the `Ad Unit ID`.
But some custom parameters are necessary, please check it with NAM admin.

## Step 5: Creating ad loader (GfpVideoAdScheduleManager)
Have to initiating object for loading ad. Please passing `AdParam` and `VideoAdScheduleParam` to `GfpVideoAdScheduleManager`
```java
videoAdScheduleManager = new GfpVideoAdScheduleManager(
                context,
                videoAdScheduleParam,
                adParam,
                adVideoPlayer,
                adUiContainer
        );
```
### 5-1. GfpVideoAdOptions
Can set options by using `GfpVideoAdScheduleManager.setVideoAdOptions()`.
- support hls or not (default is 'video/mp4')
- bitrateKbps
set ad quality. If `hls` is disabled, Naver ad takes lowest resolution by default.
- videoLoadTimeout (default is 5000ms)
set timeout from ad start request to actual playing.
```java
GfpVideoAdOptions videoAdOptions = new GfpVideoAdOptions();
videoAdOptions.setSupportedStreamingHLS(false);
videoAdOptions.setBitrateKbps(-1);
videoAdOptions.setVideoLoadTimeout(5000);

videoAdScheduleManager.setVideoAdOptions(videoAdOptions);
```

## Step 6: Receive schedule event
SDK handles one ad schedule event at a time. So please resume/pause the content on schedule event.
```java
videoAdScheduleManager.setAdScheduleListener(new VideoAdScheduleListener() {
        @Override
        public void onScheduleLoaded(VideoScheduleResponse schedule) {
            // needs to pause the content.
        }

        @Override
        public void onContentResumeRequest() {
            // when the content was started
        }

        @Override
        public void onContentPauseRequest() {
            // when the content was paused
        }

        @Override
        public void onScheduleCompleted() {
            // when every ads are finished. needs to resume the content.
        }

        @Override
        public void onError(GfpError error) {
            // after handing error, needs to comback to the content.
        }
});
```

## Step 7: Receive AD event
`VideoAdListener` interface gives you ad lifecycle event.
- on `onAdStartReady` callback, should call the method `GfpVideoAd.start(boolean)`.
to start the ad on your timing, calling the method is mandatory.
```java
videoAdScheduleManager.setAdListener(new VideoAdListener() {
   @Override
    public void onAdLoaded(GfpVideoAd ad) {
        // when loading ad is a success
        // can get ad info by using `ad.getResponseInfo()`
        // `ad.getNonLinearAdInfo()` lets you know the Remind Ad.
    }
    
    @Override
    public void onAdStartReady(GfpVideoAd ad) {
        // after ready state, please call 'start' method on time
        ad.start(true); // 광고 재생
    }
    
    @Override
    public void onAdStarted(GfpVideoAd ad) {
        // when ad is started
    }
    
    @Override
    public void onAdNonLinearStartReady(GfpVideoAd ad) {
        // when Remind Ad(NonLinear) is ready to show
    }            

    @Override
    public void onAdClicked(GfpVideoAd ad) {
        // user clicked ad
    }

    @Override
    public void onAdCompleted(GfpVideoAd ad) {
        // ad is finished
    }

    @Override
    public void onError(GfpVideoAd ad, GfpError error) {
        // error occurs
    }
});
```

## Step 8: Set timeout
```java
...
// set timeout globally
GfpSdk.setSdkProperties(
                GfpSdk.getSdkProperties()
                        .buildUpon()
                        .videoAdRequestTimeout(60_000L)
                        .build());
...
// set timeout on each request
videoAdScheduleManager.setGfpVideoProperties(new GfpVideoProperties(60_000L));
...
```

## Step 9: Load AD (sample)
```java
package ...

import ...

public class InStreamFragment extends Fragment {
    private AdVideoPlayer adVideoPlayer;
    private GfpVideoAdScheduleManager videoAdScheduleManager;
    private SampleExoPlayerView exoPlayerView;
    private FrameLayout adUiContainer;
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_in_stream, container, false);
        
        // this example using Sample player for playing content and ad
        exoPlayerView = view.findViewById(R.id.ad_video_player);
        adUiContainer = view.findViewById(R.id.ad_ui_container);
        // for example, SampleExoPlayerView contains AdVideoPlayer
        adVideoPlayer = exoPlayerView.createAdVideoPlayer("YOUR_CONTENTS_URL");
        
        // AdParam
        // set the referrer and current page url
        // needs guide from NAM admin to set custom param.
        AdParam adParam = new AdParam.Builder()
                        .build();

        // Schedule Param
        long contentDuration = ***L; // content length in seconds
        String adScheduleId = "VIDEO_AD_SCHEDULE_ID"; // get ID from NAM admin.
        VideoAdScheduleParam videoAdScheduleParam = new VideoAdScheduleParam.Builder(adScheduleId)
                        .setDuration(contentDuration)
                        .build();
        
        // initiating loader
        videoAdScheduleManager = new GfpVideoAdScheduleManager(
                getContext(),
                videoAdScheduleParam,
                adParam,
                adVideoPlayer,
                adUiContainer
        );
        
        // set options
        GfpVideoAdOptions videoAdOptions = new GfpVideoAdOptions();
        videoAdOptions.setSupportedStreamingHLS(true);
        videoAdScheduleManager.setVideoAdOptions(videoAdOptions);
        
        videoAdScheduleManager.setAdScheduleListener(new VideoAdScheduleListener() {
            @Override
            public void onScheduleLoaded(VideoScheduleResponse schedule) {
                // scedule is loaded
            }

            @Override
            public void onContentResumeRequest() {
                // resume the content
                if (!exoPlayerView.isPlaying()) {
                    exoPlayerView.resumeContentsRequest();
                }
            }

            @Override
            public void onContentPauseRequest() {
                // pause the content
                exoPlayerView.pauseContentsRequest();
            }

            @Override
            public void onScheduleCompleted() {
                // every ads are finished
                if (!exoPlayerView.isPlaying()) {
                    exoPlayerView.resumeContentsRequest();
                }
            }

            @Override
            public void onError(GfpError gfpError) {
                // schedule error means no ad to show
                // go back to content
                if (!exoPlayerView.isPlaying()) {
                    exoPlayerView.resumeContentsRequest();
                }
            }
        });
        
        videoAdScheduleManager.setAdListener(new VideoAdListener() {
            @Override
            public void onAdLoaded(GfpVideoAd ad) {
                // load the ad
            }

            @Override
            public void onAdStartReady(GfpVideoAd ad) {
                // ready to show ad
                ad.start(true); // play ad
            }

            @Override
            public void onAdNonLinearStartReady(GfpVideoAd ad) {
                // ready to show Remind AD(Non Linear)
                // contact NAM admin before using
            }

            @Override
            public void onAdStarted(GfpVideoAd ad) {
                // ad is stared
            }

            @Override
            public void onAdClicked(GfpVideoAd ad) {
                // ad is clicked
            }

            @Override
            public void onAdCompleted(GfpVideoAd ad) {
                // ad is finished, play the content
                if (!exoPlayerView.isPlaying()) {
                    exoPlayerView.resumeContentsRequest();
                }
            }

            @Override
            public void onError(GfpVideoAd ad, GfpError error) {
                // errors, go back to content
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
```

----

## Step 10: Cleaning up
When leaving the ad, please release the resource to prevent memory leak.

```java
if (exoPlayerView != null) {
    exoPlayerView.release();
    exoPlayerView = null;
}
if (videoAdScheduleManager != null) {
    videoAdScheduleManager.destroy();
}
```
