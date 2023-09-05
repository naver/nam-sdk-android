# InMobi 

This guide is intended for publishers who want to use NAM SDK to load and display ads from the InMobi through waterfall mediation.
It covers how to integrate the InMobi and adapter into an Android app.

## Supported ad formats

| Ad format     | Description |
|:--------------|:------------|
| Banner        | ✔️          |
| Native        | ✔️          |
| Native Simple | ❌          |
| Interstitial  | ❌️          |
| Rewarded      | ❌️          |

## Requirements

- Android API level 19 or higher
- Latest NAM SDK

## Step 1: Import the InMobi SDK extension

```gradle
repositories {
    google()
    mavenCentral()
}

...
dependencies {
    implementation 'com.naver.gfpsdk:nam-inmobi:6.3.0'  
}
```

## Step 2: Additional code required

No additional code is required for InMobi integration.

## Step 3: Test your implementation

Enable **Test Mode** for your placement on all live impressions or certain test devices only.

>Note: Test mode is turned `Off` by default for all placements. However, test mode can be enabled for placements across all app instances or only for devices you specify.

## Using native ads

### Ad rendering

| Ad format         | Is supported | Description                                                                                                                                                             |
|:------------------|:-------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Advertiser name   | ❌️           | -                                                                                                                                                                       |
| Title             | ✔️           | -                                                                                                                                                                       |
| Body              | ✔️           | -                                                                                                                                                                       |
| Media view        | ✔️           | InMobi native adapter does not provide direct access to the main image asset for its native ads. Instead, adapter populates the `GfpMediaView` with a video or an image. |
| Icon              | ✔️           | -                                                                                                                                                                       |
| Call to action    | ✔️           | -                                                                                                                                                                       |
| Social context    | ❌️           | -                                                                                                                                                                       |

### Impression and click tracking 

NAM SDK uses the InMobi SDK's callbacks for impression and click tracking, so the reports on both dashboards should match up with few to no discrepancies.