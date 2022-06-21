# Native simple ads

Native Simple has been designed to make the implementation of native ads as easy as possible, and it's a great choice if you are new to the format.

It is a native format advertisement that has only a simple image, and unlike native ad, 
you can rendering a native ad similar to a banner ad without complicated settings or additional tasks.

## Prerequisites

Complete the [Get Started guide](../../README.md)

## Step 1: Add `ViewGroup` to the layout

The first step toward displaying native ads is to place `ViewGroup` such as `RelativeLayout`
in the layout for the `Activity` or `Fragment` in which you'd like to display it.

```xml
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.naver.gfpsdk.GfpNativeSimpleAdView
        android:id="@+id/native_simple_ad_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

## Step 2: Load an ad

Native simple ads are loaded via the `GfpAdLoader` class, which has its own `Builder` class to customize it during creation.
By adding listeners to the `GfpAdLoader` while building it, an app specifies which types of native ads it is ready to receive.
The `GfpAdLoader` then requests just those types.

### Build an GfpAdLoader

The following code demonstrates how to build an `GfpAdLoader` that can load native simple ads.

```java
package ...

import ...
import com.naver.gfpsdk.AdParam;
import com.naver.gfpsdk.GfpAdLoader;

public class MainActivity extends AppCompatActivity {
    RelativeLayout adContainer;
    GfpAdLoader adLoader;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adContainer = findViewById(R.id.ad_container);

        AdParam adParam = new AdParam.Builder()
                .setAdUnitId("YOUR_AD_UNIT_ID")
                .build();
        
        adLoader = new GfpAdLoader.Builder(this, adParam)
                .withNativeSimpleAd(nativeAd -> {
                    // Display the ad
                })
                .withAdListener(new AdEventListener() {
                    @Override 
                    public void onError(GfpError error, GfpResponseInfo responseInfo) {
                        // Handle the failure by logging.
                    }
                })
                .build();
    }
}
```
>Note: Make all calls to the NAM SDK on the main thread.

### Use AdEventListener with an GfpAdLoader

During creating of the `GfpAdLoader` above, the `withAdListener` method sets an `AdEventListener`.

This is an optional step. The method takes an `AdEventListener` as its lone parameter, which receives callbacks from the
`GfpAdLoader` when ad lifecycle events take place:

```java
.withAdListener(new AdEventListener() {
    // AdEventListener callbacks can be overriden here.
}) 
```

### Load the native ad 

Once you've finished building an `GfpAdLoader`, call its `loadAd()` method to request an ad.

```java
adLoader.loadAd() 
```

## Step 3: Display an ad

Once you have loaded an ad, all that remains is to display it to your users. Here is an example that creates a `GfpNativeSimpleAdView`
and populates it with a `GfpNativeSimpleAd`.

```java
package ...

import ...

public class MainActivity extends AppCompatActivity {
    private GfpAdLoader adLoader;
    private GfpNativeSimpleAdView nativeSimpleAdView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nativeSimpleAdView = findViewById(R.id.native_simple_ad_view);

        AdParam adParam = new AdParam.Builder()
                .setAdUnitId("YOUR AD UNIT ID")
                ...
                .build();

        adLoader = new GfpAdLoader.Builder(this, adParam)
                .withAdListener(new AdEventListener() {
                    ...
                })
                .withNativeSimpleAd(new GfpNativeSimpleAd.OnNativeSimpleAdLoadedListener() {
                    @Override
                    public void onNativeSimpleAdLoaded(GfpNativeSimpleAd nativeSimpleAd) {
                        nativeSimpleAdView.setNativeSimpleAd(nativeSimpleAd);
                    }
                })
                .build();

        adLoader.loadAd();
    }
} 
```
>Note: After successfully loading ads via `OnNativeSimpleAdLoadedListener`, you must invoke `nativeSimpleAdView.setNativeSimpleAd(nativeSimpleAd)` to render ads normally. 

## Step 4. Cleaning up

Lastly, add the following code to your activity's `onDestroy()` method to release resource the `GfpAdLoader`.

```java
@Override
protected void onDestroy() {
  if (adLoader != null) {
        adLoader.cancel(); 
  }
  super.onDestroy();
}
```
