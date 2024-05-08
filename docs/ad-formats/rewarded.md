# Rewarded ads

Rewarded ads provide incentives and rewards to users when they complete watching a video in full screen.

## Prerequisites

Complete the [Get Started guide](../../README.md)

## Step 1: (Sample options) Add layout

(Unlike banner or native ads) it is not necessary to create an Ad Container for reward-type advertising.

While banner ads or native ads are displayed once the loading is complete, reward-type ads have a separate method to display the ads after loading.

Therefore, to distinguish between the load and show steps of the advertisement, a show button is made and used in the sample.

```xml
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/show_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:text="SHOW" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

## Step 2: Create and load advertising parameters

After creating a `GfpRewardedAdManager` instance, reward-type advertisements can be loaded by calling the `loadAd()` method.

At this point, an activity context will be required. Some DSPs may malfunction if the activity context is not transmitted.

In banner or native ads, loading is connected to ad display (show). However, in reward-type/interstitial ads, load and show are separated.

`load` is the step for preparing to show an advertisement.

## Step 3: Receiving advertising events

Receive various advertising events by setting `RewardedAdListener` in `GfpRewardedAdManager` instance as shown below.

Information on reward-type advertising can be received via the `onAdCompleted` method.

```java
rewardedAdManager.setAdListener(new RewardedAdListener() {
    @Override
    public void onAdLoaded(GfpRewardedAd ad) {
        // when the ad is loaded
    }

    @Override
    public void onAdStarted(GfpRewardedAd ad) {
        // when the ad is on
    }

    @Override
    public void onAdClicked(GfpRewardedAd ad) {
        // when the ad is clicked
    }

    @Override
    public void onAdClosed(GfpRewardedAd ad) {
        // when the ad is closed
    }

    @Override
    public void onAdCompleted(GfpRewardedAd ad) {
        // when the ad is completed
    }

    @Override
    public void onError(GfpRewardedAd ad, GfpError error) {
        // when failed to load the ad or error occurs during rendering
    }
});
```

## Step 4: Load and display ads (Show)
- Once `GfpRewardedAdManager` settings have been completed, advertisements can be loaded through `loadAd()`.
- If the ad has been loaded successfully, the ad can be displayed.
    - Before displaying an ad, the validity of the ad can be checked optionally by calling `isAdInvalidated()`.

      If the method result is true, the advertisement is invalid. Therefore, the advertisement could be displayed depending on the advertisement provider but the charging may not occur.

      Note that each DSP may have a valid ad time. Although it may differ from each DSP, it may become invalid if it has been a while since loading.
    - Advertisements that have been `shown` once will not be `shown` again.

```java
public class RewardedFragment extends Fragment {
    private GfpRewardedAdManager rewardedAdManager;
    private Button showButton;

    @Override
    protected View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewarded, container, false);
        showButton = view.findViewById(R.id.show_button);
        showButton.setEnabled(false);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check validation
                if (rewardedAdManager.isAdInvalidated()) {
                    Log.d("MainActivity", "the ad is invalid");
                    return;
                }

                rewardedAdManager.showAd(requireActivity());
            }
        });

        AdParam adParam = new AdParam.Builder()
                .setAdUnitId("YOUR_AD_UNIT_ID")
                .addUserParam("testKey", "testValue")
                .build();

        rewardedAdManager = new GfpRewardedAdManager(requireActivity(), adParam);

        // set timeout (optional)
        rewardedAdManager.setTimeoutMillis(60_000L);

        rewardedAdManager.setAdListener(new RewardedAdListener() {
            @Override
            public void onAdLoaded(GfpRewardedAd ad) {
                Log.d("RewardedFragment", String.format("succeed to load. responseInfo[%s]",ad.getResponseInfo().toString()));
                showButton.setEnabled(true);
            }

            @Override
            public void onAdStarted(GfpRewardedAd ad) {
                Log.d("RewardedFragment", "start the ad");
            }

            @Override
            public void onAdClicked(GfpRewardedAd ad) {
                Log.d("RewardedFragment", "click occurs");
            }

            @Override
            public void onAdClosed(GfpRewardedAd ad) {
                Log.d("RewardedFragment", "close the ad");
                showButton.setEnabled(false);
            }

            @Override
            public void onAdCompleted(GfpRewardedAd ad) {
                // you can get reward here.
                Log.d("RewardedFragment", String.format("complete - responseInfo[%s]",ad.getResponseInfo().toString()));
            }

            @Override
            public void onError(GfpRewardedAd ad, GfpError error) {
                Log.e("RewardedFragment", ad.getResponseInfo().toString());
            }
        });

        rewardedAdManager.loadAd();

        return view;
    }
}
```
>Note: Make all calls to the NAM SDK on the main thread.

## Step 5. Cleaning up

Once the display of the banner ad is terminated, the ad must be removed for proper disposal.

To remove an ad, call `destroy()` provided by `GfpRewardedAdManager` as shown below.

```java
@Override
protected void onDestroy() {
   rewardedAdManager.destroy();
}
```
