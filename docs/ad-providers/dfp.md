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
    implementation 'com.naver.gfpsdk:nam-dfp:5.1.3'  
}
```

## Step 2: Additional code required

Add a `<meta-data>` tag with `android:name="com.google.android.gms.ads.AD_MANAGER_APP"` in your `AndroidManifest.xml` file. For `android:value`, insert `true`, surrounded by quotation marks.

```xml
<meta-data
    android:name="com.google.android.gms.ads.AD_MANAGER_APP"
    android:value="true" />
```

In a real app, use your actual Ad Manager app ID, not the one listed above. If you're just looking to experiment with the SDK in a Hello World app, you can use the sample app ID shown above.

Note also that failure to add the <meta-data> tag as shown above results in a crash with the message:

```
The Google Mobile Ads SDK was initialized incorrectly.
```

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