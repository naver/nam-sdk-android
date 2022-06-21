# Native ads

Customizable ads that match the look and feel of your app. You decide how and where they're placed, so the layout is more consistent with your app's design.

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

    <RelativeLayout
        android:id="@+id/ad_container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

## Step 2: Add layout to contains `GfpNativeAdView`

For the native ad format, there is the corresponding `GfpNativeAdView` class. This class is a `ViewGroup` that publishers should use 
as the root for the native ad. A single `GfpNativeAdView` correspond to a single native ad. Each view used to display that ad's assets
(the ImageView that display the screenshot asset, for instance) should be a child of the `GfpNativeAdView` object.

The view hierarchy for a native ad that uses a `LinearLayout` to display its asset views might look like this:

```xml
<?xml version="1.0" encoding="utf-8"?>
<com.naver.gfpsdk.GfpNativeAdView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/gfp_native_ad"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <FrameLayout
        android:id="@+id/assets_container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:background="#FFFFFF"
            android:minHeight="50dp"
            android:orientation="vertical">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                android:padding="10dp">

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginBottom="12dp"
                    android:orientation="horizontal">

                    <ImageView
                        android:id="@+id/ad_app_icon"
                        android:layout_width="46dp"
                        android:layout_height="46dp"
                        android:layout_marginRight="10dp"
                        android:adjustViewBounds="true" />

                    <LinearLayout
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center_vertical"
                        android:layout_weight="1"
                        android:orientation="vertical">

                        <TextView
                            android:id="@+id/ad_headline"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:layout_marginBottom="4dp"
                            android:ellipsize="end"
                            android:lines="1"
                            android:textColor="#000000"
                            android:textSize="12dp" />

                        <TextView
                            android:id="@+id/ad_sponsored"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:textColor="#999999"
                            android:textSize="12dp" />

                    </LinearLayout>

                    <com.naver.gfpsdk.GfpAdChoicesView
                        android:id="@+id/ad_choices_view"
                        android:layout_width="20dp"
                        android:layout_height="20dp"
                        android:layout_gravity="center_vertical"
                        android:layout_marginLeft="10dp" />

                </LinearLayout>

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="vertical">

                    <TextView
                        android:id="@+id/ad_body"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:layout_marginBottom="12dp"
                        android:ellipsize="end"
                        android:maxLines="2"
                        android:textColor="#000000"
                        android:textSize="15dp" />

                    <com.naver.gfpsdk.GfpMediaView
                        android:id="@+id/ad_media"
                        android:layout_width="match_parent"
                        android:layout_height="220dp"
                        android:layout_marginBottom="12dp"
                        android:adjustViewBounds="true" />

                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:orientation="horizontal">

                        <LinearLayout
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_weight="3"
                            android:orientation="vertical">

                            <TextView
                                android:id="@+id/ad_advertiser"
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:ellipsize="end"
                                android:lines="1"
                                android:textColor="@android:color/darker_gray"
                                android:textSize="13dp" />

                            <TextView
                                android:id="@+id/ad_social_context"
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:ellipsize="end"
                                android:gravity="center_vertical"
                                android:lines="1"
                                android:textColor="@android:color/black"
                                android:textSize="13dp" />

                        </LinearLayout>

                        <Button
                            android:id="@+id/ad_call_to_action"
                            android:layout_width="100dp"
                            android:layout_height="40dp"
                            android:layout_gravity="center_vertical"
                            android:layout_weight="1"
                            android:background="#3c3c3c"
                            android:paddingLeft="3dp"
                            android:paddingRight="3dp"
                            android:textColor="#ffffff"
                            android:textSize="13dp" />

                    </LinearLayout>
                </LinearLayout>
            </LinearLayout>
        </LinearLayout>
    </FrameLayout>
</com.naver.gfpsdk.GfpNativeAdView>
```

### Constraints

When configuring layout with `GfpNativeAdView`, the following constraints must be observed.

- The uppermost view must be GfpNativeAdView.
- The sub view of `GfpNativeAdView` must be assigned a ViewGroup to hold the assets.
- You must include `GfpAdChoiceView` where ad choice view is rendered.
  - Ad choice view of FAN, S2S ads is rendered in the area and DFP is rendered directly by the DFP SDK with a separate View.
  - The width and height of the `GfpAdChoiceView` must be greater than or equal to 20dp, and the recommended value is 20dp for both width and height.
- The area where the image or video will be rendered as the main, not the icon, must be declared `GfpMediaView`.

## Step 3: Load an Ad

Native ads are loaded via the `GfpAdLoader` class, which has its own `Builder` class to customize it during creation.
By adding listeners to the `GfpAdLoader` while building it, an app specifies which types of native ads it is ready to recieve.
The `GfpAdLoader` then requests just those types.

### Build an GfpAdLoader

The following code demonstrates how to build an `GfpAdLoader` that can load native ads.

```java
package ...

import ...
import com.naver.gfpsdk.AdParam;
import com.naver.gfpsdk.GfpAdLoader;
import com.naver.gfpsdk.GfpNativeAdOptions;

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

        GfpNativeAdOptions nativeAdOptions = new GfpNativeAdOptions.Builder()
                .setHasMediaView(true)
                .build();
        
        adLoader = new GfpAdLoader.Builder(this, adParam)
                .withNativeAd(nativeAdOptions, nativeAd -> {
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

## Step 4: Display an ad

Once you have loaded an ad, all that remains is to display it to your users. Here is an example that creates a `GfpNativeAdView` 
and populates it with a `GfpNativeAd`.

```java
package ...

import ...

public class MainActivity extends AppCompatActivity {
    private RelativeLayout adContainer;
    private GfpAdLoader adLoader;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adContainer = findViewById(R.id.ad_container);

        AdParam adParam = new AdParam.Builder()
                        .setAdUnitId("YOUR AD UNIT ID")
                        ...
                        .build();

        GfpNativeAdOptions nativeAdOptions = new GfpNativeAdOptions.Builder()
            .setHasMediaView(true)
            .build();

        adLoader = new GfpAdLoader.Builder(this, adParam)
            .withAdListener(new AdEventListener() {
                public void onError(GfpError error, GfpResponseInfo responseInfo) {
                  ...
                }
            })
            .withNativeAd(nativeAdOptions, new GfpNativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(GfpNativeAd nativeAd) {
                    inflateAd(nativeAd);
                }
            })
            .build();

        adLoader.loadAd();
    }

    private void inflateAd(GfpNativeAd nativeAd) {
        GfpNativeAdView adView = (GfpNativeAdView) getLayoutInflater()
                .inflate(R.layout.content_native_ad, nativeContainer, false);

        // Ensure that the adContainer view doesn't already contain an ad view.
        adContainer.removeAllViews();
        
        // Place the native ad view into the adContainer
        adContainer.addView(nativeAdView);

        FrameLayout assetsContainer = nativeAdView.findViewById(R.id.assets_container);
        GfpMediaView mediaView = nativeAdView.findViewById(R.id.ad_media);
        GfpAdChoicesView adChoicesView = nativeAdView.findViewById(R.id.ad_choices_view);
        ImageView iconView = nativeAdView.findViewById(R.id.ad_app_icon);
        TextView titleView = nativeAdView.findViewById(R.id.ad_headline);
        TextView bodyView = nativeAdView.findViewById(R.id.ad_body);
        TextView advertiserView = nativeAdView.findViewById(R.id.ad_advertiser);
        TextView socialContextView = nativeAdView.findViewById(R.id.ad_social_context);
        Button callToActionButton = nativeAdView.findViewById(R.id.ad_call_to_action);

        nativeAdView.setAssetsContainer(assetsContainer);
        nativeAdView.setMediaView(mediaView);
        nativeAdView.setAdChoicesView(adChoicesView);
        nativeAdView.setIconView(iconView);
        nativeAdView.setTitleView(titleView);
        nativeAdView.setBodyView(bodyView);
        nativeAdView.setAdvertiserView(advertiserView);
        nativeAdView.setSocialContextView(socialContextView);
        nativeAdView.setCallToActionView(callToActionButton);

        titleView.setText(nativeAd.getTitle());
        bodyView.setText(nativeAd.getBody());
        callToActionButton.setText(nativeAd.getCallToAction());
        advertiserView.setText(nativeAd.getAdvertiserName());

        Image icon = nativeAd.getIcon();
        if (icon != null) {
            iconView.setImageDrawable(icon.getDrawable());
            iconView.setVisibility(View.VISIBLE);
        } else {
            iconView.setVisibility(View.GONE);
        }

        if (nativeAd.getSocialContext() != null) {
            socialContextView.setText(nativeAd.getSocialContext());
            socialContextView.setVisibility(View.VISIBLE);
        } else {
            socialContextView.setVisibility(View.GONE);
        }

        // Call the GfpNativeAdView's setNativeAd method to register the native ad object.
        nativeAdView.setNativeAd(nativeAd);
    }
}
```

### Inflate the layout

```java
GfpNativeAdView adView = (GfpNativeAdView) getLayoutInflater()
        .inflate(R.layout.content_native_ad, nativeContainer, false);
```

In this example, we're inflating an XML layout that contains views for displaying a native ad and 
then locating a reference to the `GfpNativeAdView`. Note that you could also reuse an existing `GfpNativeAdView` 
if there's one in your fragment or activity, or even create an instance dynamically without using a layout file.

### Register `asset container`

For normal rendering of native ad using `GfpNativeAdView`, it is necessary to register a view group containing assets as follows.

```java
FrameLayout assetsContainer = nativeAdView.findViewById(R.id.assets_container);
nativeAdView.setAssetsContainer(assetsContainer);
```
>Note: ViewGroup must not be `GfpNativeAdView`, but must be a child view of `GfpNativeAdView`.

### Populate and register the asset views

This sample code locates the view used to display the headline, sets its text using the string asset provided by the ad object, 
and registers it with the `GfpNativeAdView` object:

```java
TextView titleView = nativeAdView.findViewById(R.id.ad_headline);
titleView.setText(nativeAd.getTitle());
nativeAdView.setTitleView(titleView);
```

This process of locating the view, setting its value, and registering it with the ad view class should be repeated for each of the assets 
provided by the native ad object that the app will display.

### Register the MediaView 

The `GfpMediaView` is a special `View` desinged to display the main media asset, either video or image.

`GfpMediaView` can be defined in an XML layout or constructed dynamically. It should be placed within the view hierarchy of a `GfpNativeAdView`, 
just like any other asset view. Apps using a `GfpMediaView` must register it with the `GfpNativeAdView` like this:

```java 
GfpMediaView mediaView = nativeAdView.findViewById(R.id.ad_media);
nativeAdView.setMediaView(mediaView);
```

### Register the native ad object

This final step registers the native ad object with the view that's responsible for displaying it: 

```java 
nativeAdView.setNativeAd(nativeAd);
```

## Step 5. Cleaning up

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
