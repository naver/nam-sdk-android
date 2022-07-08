# Targeting

This guide explains how to provider targeting information to an ad request.

## Prerequisite

Complete the [Get Started guide](../README.md)

## SdkProperties

`SdkProperties` is an object that collects targeting information related to sdk, such as request timeout by ad format or option by ad provider to be applied globally via a `GfpSdk` static method.

To update the sdk properties, obtain a builder from the existing configuration, perform any desired updates, and set it as follows:

```java
SdkProperties sdkProperties = GfpSdk.getSdkProperties()
        .buildUpon()
        ...
        .build();
GfpSdk.setSdkProperties(sdkProperties);
```

### Banner ad request timeout

Timeout from requesting to responding to Banner Ad loaded via `GfpBannerAdView`.

```java
SdkProperties sdkProperties = GfpSdk.getSdkProperties()
    .buildUpon()
    .bannerAdRequestTimeout(30_000L)
    .build();
GfpSdk.setSdkProperties(sdkProperties);
```
>Note: Default value is `60_000ms`.

### Unified ad request timeout

Timeout from requesting to responding to Banner Ad loaded via `GfpAdLoader`.

```java
SdkProperties sdkProperties = GfpSdk.getSdkProperties()
    .buildUpon()
    .unifiedAdRequestTimeout(30_000L)
    .build();
GfpSdk.setSdkProperties(sdkProperties);
```
>Note: Default value is `60_000ms`.

### Rewarded ad request timeout

Timeout from requesting to responding to Banner Ad loaded via `GfpRewardedAdManager`.

```java
SdkProperties sdkProperties = GfpSdk.getSdkProperties()
    .buildUpon()
    .rewardedAdRequestTimeout(30_000L)
    .build();
GfpSdk.setSdkProperties(sdkProperties);
```
>Note: Default value is `60_000ms`.

### Interstitial ad request timeout

Timeout from requesting to responding to Banner Ad loaded via `GfpInterstitialAdManager`.

```java
SdkProperties sdkProperties = GfpSdk.getSdkProperties()
    .buildUpon()
    .interstitialAdRequestTimeout(30_000L)
    .build();
GfpSdk.setSdkProperties(sdkProperties);
```
>Note: Default value is `60_000ms`.

## UserProperties

`UserProperties` is an object that collects targeting information related to user, such as gender, year of birth, country to be applied globally via a `GfpSdk` static method.

To update the sdk properties, obtain a builder from the existing configuration, perform any desired updates, and set it as follows:

```java
UserProperties userProperties = GfpSdk.getUserProperties()
    .buildUpon()
    ...     
    .build();
GfpSdk.setUserProperties(userProperties);
```

### Gender

If you know the `gender` information, apply it as below.

```java
UserProperties userProperties = GfpSdk.getUserProperties()
    .buildUpon()
    .gender(GenderType.FEMALE)
    .build();
GfpSdk.setUserProperties(userProperties);
```

### Year of birth

If you know the `year of birth` information, apply it as below.

```java
UserProperties userProperties = GfpSdk.getUserProperties()
    .buildUpon()
    .yob(1990)
    .build();
```

### Country

If you know the `country` information, apply it as below.

```java
UserProperties userProperties = GfpSdk.getUserProperties()
    .buildUpon()
    .country("KR")
    .build();
```
>Note: set the **ISO 3166-1 alpha-2** value.

### Language

If you know the `language` information, apply it as below.

```java
UserProperties userProperties = GfpSdk.getUserProperties()
    .buildUpon()
    .language("ko")
    .build();
```
>Note: set the **ISO 639-1** value.

### User ID

If you know the `user id` information, apply it as below.\
This is a key which is used in error log to tracking ad (NAM internally, **not used in targeting**).

```java
UserProperties userProperties = GfpSdk.getUserProperties()
    .buildUpon()
    .userId("ADD_USER_ID")
    .build();
```

## AdParam

`AdParam` object collects targeting information to be sent with an ad request

### Keyword

You can pass keyword to target Naver Ad Manager through `AdParam.Builder.addKeyword()`\
The keyword will be send to DSP directly(ex. DFP, IMA, InMobi) through their SDK.\
For example, please check [this manual(from Google Ads)](https://ads.google.com/intl/ko_kr/home/tools/keyword-planner/)

```java
AdParam adParam = new AdParam.Builder()
    .setAdUnitId("YOUR AD UNIT ID")
    ...
    .addKeyword("lo:Y,dh:720")
    .build();
```

### Custom parameter

You can pass custom key-value pairs to target Naver Ad Manager through `AdParam.Builder.addCustomParam()`\
This is a custom defined targeting.\
***Please contact with NAM admin first.***\
It could be used in NAM report, NAM targeting, DSP targeting and so on.\
If you want to add multiple values in a single key, please concatenate the values with specific delimiter - '|'.

```java
AdParam adParam = new AdParam.Builder()
    .setAdUnitId("YOUR AD UNIT ID")
    ...      
    .addCustomParam("foo", "bar")
    .addCustomParam("foo1", "bar1|bar2|bar3")
    .build();
```

### Current page url

To provide a content URL for content-targeted ads and brand safety, you can call `AdParam.Builder.setCurrentPageUrl()`

```java
AdParam adParam = new AdParam.Builder()
    .setAdUnitId("YOUR AD UNIT ID")
    ...
    .setCurrentPageUrl("https://www.naver.com")
    .build();
```
