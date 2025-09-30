# Interstitial ads

Interstitial ads are a type of ad format that occupies the entire screen of the app and is typically displayed naturally during moments when app usage is temporarily paused, such as during screen transitions within the app.

## Prerequisites

Complete the [Get Started guide](../../README.md)

## Step 1: (Sample options) Add layout

(Unlike banner or native ads) it is not necessary to create an Ad Container for interstitial-type advertising.

While banner ads or native ads are displayed once the loading is complete, interstitial-type ads have a separate method to display the ads after loading.

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

After creating a `GfpInterstitialAdManager` instance, interstitial-type advertisements can be loaded by calling the `loadAd()` method.

At this point, an activity context will be required. Some DSPs may malfunction if the activity context is not transmitted.

In banner or native ads, loading is connected to ad display (show). However, in interstitial ads, load and show are separated.

`load` is the step for preparing to show an advertisement.

## Step 3: Receiving advertising events

Receive various advertising events by setting `InterstitialAdListener` in `GfpInterstitialAdManager` instance as shown below.

```java
interstitialAdManager.setAdListener(new InterstitialAdListener() {
    @Override
    public void onAdLoaded(GfpInterstitialAd ad) {
        // Called when an ad is loaded.
        // You can show the ad here.
    }

    @Override
    public void onAdStarted(GfpInterstitialAd ad) {
        // Called when an ad is started or shown.
    }

    @Override
    public void onAdClicked(GfpInterstitialAd ad) {
        // Called when an ad is clicked.
    }

    @Override
    public void onAdClosed(GfpInterstitialAd ad) {
        // Called when an ad is closed.
    }

    @Override
    public void onError(GfpInterstitialAd ad, GfpError error) {
        // Called when an error happened while the ad is
        // attempting to load or rendering an ad.
    }
});
```

## Step 4: Load and display ads (Show)
- Once `GfpInterstitialAdManager` settings have been completed, advertisements can be loaded through `loadAd()`.
- If the ad has been loaded successfully, the ad can be displayed.
    - Before displaying an ad, the validity of the ad can be checked optionally by calling `isAdInvalidated()`.

      If the method result is true, the advertisement is invalid. Therefore, the advertisement could be displayed depending on the advertisement provider but the charging may not occur.

      Note that each DSP may have a valid ad time. Although it may differ from each DSP, it may become invalid if it has been a while since loading.
    - Advertisements that have been `shown` once will not be `shown` again.

```java
public class InterstitialFragment extends Fragment {
    private GfpInterstitialAdManager interstitialAdManager;
    private Button showButton;

    @Override
    protected View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interstitial, container, false);
        showButton = view.findViewById(R.id.show_button);
        showButton.setEnabled(false);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check validation
                if (interstitialAdManager.isAdInvalidated()) {
                    Log.d("InterstitialFragment", "the ad is invalid");
                    return;
                }

                interstitialAdManager.showAd(requireActivity());
            }
        });

        AdParam adParam = new AdParam.Builder()
                .setAdUnitId("YOUR_AD_UNIT_ID")
                .addUserParam("testKey", "testValue")
                .build();

        interstitialAdManager = new GfpInterstitialAdManager(requireActivity(), adParam);

        // set timeout (optional)
        interstitialAdManager.setTimeoutMillis(60_000L);

        interstitialAdManager.setAdListener(new InterstitialAdListener() {
            @Override
            public void onAdLoaded(GfpInterstitialAd ad) {
                Log.d("InterstitialFragment", String.format("succeed to load. responseInfo[%s]",ad.getResponseInfo().toString()));
                showButton.setEnabled(true);
            }

            @Override
            public void onAdStarted(GfpInterstitialAd ad) {
                Log.d("InterstitialFragment", "start the ad");
            }

            @Override
            public void onAdClicked(GfpInterstitialAd ad) {
                Log.d("InterstitialFragment", "click occurs");
            }

            @Override
            public void onAdClosed(GfpInterstitialAd ad) {
                Log.d("InterstitialFragment", "close the ad");
                showButton.setEnabled(false);
            }

            @Override
            public void onError(GfpInterstitialAd ad, GfpError error) {
                Log.e("InterstitialFragment", ad.getResponseInfo().toString());
            }
        });

        interstitialAdManager.loadAd();

        return view;
    }
}
```
>Note: Make all calls to the NAM SDK on the main thread.

## Step 5. Cleaning up

Once the display of the interstitial ad is terminated, the ad must be removed for proper disposal.

To remove an ad, call `destroy()` provided by `GfpInterstitialAdManager` as shown below.

```java
@Override
protected void onDestroy() {
    if (interstitialAdManager != null) {
        interstitialAdManager.destroy();
        interstitialAdManager = null;
    }
    super.onDestroy();
}
```
