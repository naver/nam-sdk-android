# Banner Ads

Banner ads occupy a spot within an app's layout, either at the top or bottom of the device screen. They stay on screen while users are interacting with the app. If you're new to mobile advertising, they're a great place to start.

## Prerequisites 

Complete the [Get Started guide](../../README.md)

## Step 1: Add `ViewGroup` to the layout

The first step toward displaying banner is to place `ViewGroup` such as `RelativeLayout` 
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

## Step 2: Configure `AdParam` and Create a `GfpBannerAdView`

A. Create an `AdParam` to contain Ad Unit ID and targeting information. 
B. When creating a `GfpBannerAdView` where Banner ads will be displayed, the previously generated `AdParam` instance is delivered as a parameter. 
C. Add `GfpBannerAdView` to the ViewGroup set in [step 1](#step-1-add-viewgroup-to-the-layout).

```java
package ...

import ...
import com.naver.gfpsdk.AdParam;
import com.naver.gfpsdk.GfpBannerAdView;

public class MainActivity extends AppCompatActivity {
    GfpBannerAdView bannerAdView;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout bannerAdContainer = findViewById(R.id.ad_container);

        AdParam adParam = new AdParam.Builder()
                .setAdUnitId("YOUR_AD_UNIT_ID")
                .build();
        bannerAdView = new GfpBannerAdView(this, adParam);
        bannerAdContainer.addView(bannerAdView);
    }
}
```

## Step 3. Load an ad

Once the GfpBannerAdView is in place, the next step is to load an ad. That's done with the `loadAd()` method in the `GfpBannerAdView` class.

```java
package ...

import ...
import com.naver.gfpsdk.AdParam;
import com.naver.gfpsdk.GfpBannerAdView;

public class MainActivity extends AppCompatActivity {
    GfpBannerAdView bannerAdView;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout bannerAdContainer = findViewById(R.id.ad_container);

        AdParam adParam = new AdParam.Builder()
                .setAdUnitId("YOUR_AD_UNIT_ID")
                .build();
        bannerAdView = new GfpBannerAdView(this, adParam);
        bannerAdContainer.addView(bannerAdView);
        bannerAdView.loadAd();
    }
}
```

## Step 4. Adding an Banner Ad Listener

Now that you have the basic code running, you can set an `BannerAdListener`to your `GfpBannerAdView` to listen for specific events.

```java
package ...

import ...
import com.naver.gfpsdk.AdParam;
import com.naver.gfpsdk.BannerAdListener;
import com.naver.gfpsdk.GfpBannerAdView;

public class MainActivity extends AppCompatActivity {
    GfpBannerAdView bannerAdView;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout bannerAdContainer = findViewById(R.id.ad_container);

        AdParam adParam = new AdParam.Builder()
                .setAdUnitId("YOUR_AD_UNIT_ID")
                .build();
        bannerAdView = new GfpBannerAdView(this, adParam);
        bannerAdContainer.addView(bannerAdView);
        
        BannerAdListener adListener = new BannerAdListener() {
            @Override
            public void onAdLoaded(GfpBannerAd ad) {
                // Ad loaded callback
            }
            
            @Override 
            public void onAdClicked(GfpBannerAd ad) {
                // Ad clicked callback
            }
            
            @Override
            public void onAdImpression(GfpBannerAd ad) {
                // Ad impression callback
            }
            
            @Override
            public void onError(GfpBannerAd ad, GfpError error) {
                // Ad error callback
            }
        };
        bannerAdView.setAdListener(adListener);
        
        bannerAdView.loadAd();
    }
}
```

## Step 5. Cleaning up

Lastly, add the following code to your activity's `onDestroy()` method to release resource the `GfpBannerAdView`.

```java
@Override
protected void onDestroy() {
  if (bannerAdView != null) {
        bannerAdView.destroy(); 
  }
  super.onDestroy();
}
```

## (Optional) Configure timeout

You can configure timeout for a single ad request may be set in milliseconds. If not set, the value set in `GfpSdk.setSdkProperties()` uses the `bannerAdRequestTimeout` value.

```java
bannerAdView.setTimeoutMillis(30_000L);
```
