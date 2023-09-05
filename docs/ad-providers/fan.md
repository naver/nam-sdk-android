# Meta Audience Network

This guide is intended for publishers who want to use NAM SDK to load and display ads from the Meta Audience Network through waterfall mediation.
It covers how to integrate the Meta Audience Network SDK and adapter into an Android app.

## Supported ad formats 

| Ad format     | Description |
|:--------------|:------------|
| Banner        | ✔️          |
| Native        | ✔️          |
| Native Simple | ❌          |
| Interstitial  | ✔️          |
| Rewarded      | ✔️          |

## Requirements 

- Android API level 19 or higher
- Latest NAM SDK

## Step 1: Import the Meta Audience Network SDK extension

```gradle
repositories {
    google()
    mavenCentral()
}

...
dependencies {
    implementation 'com.naver.gfpsdk:nam-fan:6.3.0'  
}
```

## Step 2: Additional code required 

Follow Meta Audience Network's Android [network security config guide](https://developers.facebook.com/docs/audience-network/android-network-security-config) to modify your network security config file to support media caching.

## Step 3: Test your implementation

See the [Testing Audience Network Implementation guide](https://developers.facebook.com/docs/audience-network/setting-up/testing/platform) for detailed instructions on how to enable Meta Audience Network test ads.

That's it! You now have a working mediation integration with Meta Audience Network.

## Using native ads

### Ad rendering 

| Ad format         | Is supported | Description                                                                                                                                                                             |
|:------------------|:-------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Advertiser name   | ✔️           | -                                                                                                                                                                                       |
| Title             | ✔️           | -                                                                                                                                                                                       |
| Body              | ✔️           | -                                                                                                                                                                                       |
| Media view        | ✔️           | Meta audience network native adapter does not provide direct access to the main image asset for its native ads. Instead, adapter populates the `GfpMediaView` with a video or an image. |
| Icon              | ✔️           | -                                                                                                                                                                                       |
| Call to action    | ✔️           | -                                                                                                                                                                                       |
| Social context    | ✔️           | -                                                                                                                                                                                       |

### Using Meta Audience Network Native Ads without a MediaView

Meta Audience Network's native ad format requires rendering the `GfpMediaView` asset. 
If you plan to render native ads without that asset, make sure to use Meta Audience Network's `native banner` ad format.

To use Meta Audience Network's native banner ads instead, you must

- Set the `hasMediaView` property in `GfpNativeAdOptions` to `false` as shown below.

```java 
GfpNativeAdOptions options = new GfpNativeAdOptions.Builder()
            .setHasMediaView(false)
            .build();
... 
GfpAdLoader adLoader = new GfpAdLoader.Builder(this, adParam)
            ...
            .withNativeAd(options, nativeAd -> {
               // Ad loaded callback 
            })
            .build();
```

### Impression and click tracking

NAM SDK uses the Meta audience network SDK's callbacks for impression and click tracking, so the reports on both dashboards should match up with few to no discrepancies.