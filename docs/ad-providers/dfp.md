# Google Ad Manager 

This guide is intended for publishers who want to use NAM SDK to load and display ads from the Google Mobile Ads through waterfall mediation.
It covers how to integrate the Google Mobile Ads SDK and adapter into an Android app.

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

## Step 1: Import the Google Mobile Ads SDK extension

```gradle
repositories {
    google()
    mavenCentral()
}

...
dependencies {
    implementation 'com.naver.gfpsdk:nam-dfp:6.3.0'  
}
```

## Step 2: Additional code required

To enable DFP module, Google App ID should be set in `AndroidManifest.xml` file.

Add your Ad Manager app ID (identified in the Ad Manager UI) to your app's `AndroidManifest.xml` file. To do so, add a `<meta-data>` tag with `android:name="com.google.android.gms.ads.APPLICATION_ID"`. You can find your app ID in the Ad Manager UI. For `android:value`, insert your own Ad Manager app ID, surrounded by quotation marks.

```xml
<manifest>
    <application>
        <!-- Sample Ad Manager app ID: ca-app-pub-3940256099942544~3347511713 -->
        <meta-data
                android:name="com.google.android.gms.ads.APPLICATION_ID"
                android:value="ca-app-pub-xxxxxxxxxxxxxxxx~yyyyyyyyyy"/>
    </application>
</manifest>
```

Add a `<meta-data>` tag with `android:name="com.google.android.gms.ads.AD_MANAGER_APP"` in your `AndroidManifest.xml` file. For `android:value`, insert `true`, surrounded by quotation marks.

```xml
<meta-data
    android:name="com.google.android.gms.ads.AD_MANAGER_APP"
    android:value="true" />
```

>Note: In a real app, use your actual Ad Manager app ID, not the one listed above. 

## Step 3: Test your implementation

See the [Enable test devices](https://developers.google.com/ad-manager/mobile-ads-sdk/android/test-ads#enable_test_devices)

## Using native ads

### Ad rendering

| Ad format         | Is supported | Description |
|:------------------|:-------------|:------------|
| Advertiser name   | ✔️           | -           |
| Title             | ✔️           | -           |
| Body              | ✔️           | -           |
| Media view        | ✔️           | -           |
| Icon              | ✔️           | -           |
| Call to action    | ✔️           | -           |
| Social context    | ❌️           | -           |

### Impression and click tracking

NAM SDK uses the Google Mobile Ads SDK's callbacks for impression and click tracking, so the reports on both dashboards should match up with few to no discrepancies.