# Changelog

## 8.6.1 (2025-08-01)
### Bug Fixes
* **core:** crash at AdEventListener.onError on certain circumstance

### Code Refactoring
* **core:** modify AdChoice icon position on NativeSimple AD with background
* modify the AdMute icon for NDA native ad

### NAM SDKs mapped to this BoM version 8.6.1
|Artifact name|Version mapped this BoM|
|---|---|
|com.naver.gfpsdk:nam-core|8.6.1|
|com.naver.gfpsdk:nam-adplayer|8.6.1|
|com.naver.gfpsdk.mediation:nam-nda|8.6.1|
|com.naver.gfpsdk.mediation:nam-ndavideo|8.6.1|
|com.naver.gfpsdk.mediation:nam-applovin|13.2.0.1|
|com.naver.gfpsdk.mediation:nam-aps|9.10.2.2|
|com.naver.gfpsdk.mediation:nam-bidmachine|3.1.1.3|
|com.naver.gfpsdk.mediation:nam-chartboost|9.7.0.1|
|com.naver.gfpsdk.mediation:nam-dfp|23.3.0.4|
|com.naver.gfpsdk.mediation:nam-dt|8.3.6.2|
|com.naver.gfpsdk.mediation:nam-fan|6.18.0.4|
|com.naver.gfpsdk.mediation:nam-inmobi|10.8.0.1|
|com.naver.gfpsdk.mediation:nam-ironsource|8.4.0.4|
|com.naver.gfpsdk.mediation:nam-lan|2.9.20250110.0|
|com.naver.gfpsdk.mediation:nam-unity|4.12.3.2|
|com.naver.gfpsdk.mediation:nam-vungle|7.4.1.2|

## 8.6.0 (2025-07-11)
### Features
* **core:** pass AdContextInfo on ad event callback

### Bug Fixes
* **nda:** check the vendor name in Verification tag for OM SDK
* **nda:** disable click of reward text on the companion ad
* **nda:** fix the rare NaN case in the NativeSimple AD

### Code Refactoring
* fire `onAdSlotClicked` on native slot ad clicked
* **nda:** add store badge on ShoppingLabel AD
* refactoring the GfpVideoAdOptions

### GFP SDKs mapped to this BoM version 8.6.0
|Artifact name|Version mapped this BoM|
|---|---|
|com.naver.gfpsdk:nam-core|8.6.0|
|com.naver.gfpsdk:nam-adplayer|8.6.0|
|com.naver.gfpsdk.mediation:nam-nda|8.6.0|
|com.naver.gfpsdk.mediation:nam-ndavideo|8.6.0|
|com.naver.gfpsdk.mediation:nam-applovin|13.2.0.1|
|com.naver.gfpsdk.mediation:nam-aps|9.10.2.2|
|com.naver.gfpsdk.mediation:nam-bidmachine|3.1.1.3|
|com.naver.gfpsdk.mediation:nam-chartboost|9.7.0.1|
|com.naver.gfpsdk.mediation:nam-dfp|23.3.0.4|
|com.naver.gfpsdk.mediation:nam-dt|8.3.6.2|
|com.naver.gfpsdk.mediation:nam-fan|6.18.0.4|
|com.naver.gfpsdk.mediation:nam-inmobi|10.8.0.1|
|com.naver.gfpsdk.mediation:nam-ironsource|8.4.0.4|
|com.naver.gfpsdk.mediation:nam-lan|2.9.20250110.0|
|com.naver.gfpsdk.mediation:nam-unity|4.12.3.2|
|com.naver.gfpsdk.mediation:nam-vungle|7.4.1.2|

## 8.5.1 (2025-06-20)


### Bug Fixes

* **nda:** fix click handling issue for specific native ad

### NAM SDKs mapped to this BoM version 8.5.1
|Artifact name|Version mapped this BoM|
|---|---|
|com.naver.gfpsdk:nam-core|8.5.1|
|com.naver.gfpsdk:nam-adplayer|8.5.1|
|com.naver.gfpsdk.mediation:nam-nda|8.5.1|
|com.naver.gfpsdk.mediation:nam-ndavideo|8.5.1|
|com.naver.gfpsdk.mediation:nam-applovin|13.2.0.1|
|com.naver.gfpsdk.mediation:nam-aps|9.10.2.2|
|com.naver.gfpsdk.mediation:nam-bidmachine|3.1.1.3|
|com.naver.gfpsdk.mediation:nam-chartboost|9.7.0.1|
|com.naver.gfpsdk.mediation:nam-dfp|23.3.0.4|
|com.naver.gfpsdk.mediation:nam-dt|8.3.6.2|
|com.naver.gfpsdk.mediation:nam-fan|6.18.0.4|
|com.naver.gfpsdk.mediation:nam-inmobi|10.8.0.1|
|com.naver.gfpsdk.mediation:nam-ironsource|8.4.0.4|
|com.naver.gfpsdk.mediation:nam-lan|2.9.20250110.0|
|com.naver.gfpsdk.mediation:nam-unity|4.12.3.2|
|com.naver.gfpsdk.mediation:nam-vungle|7.4.1.2|

## 8.5.0 (2025-06-17)

### Features

* add global `GfpTheme` setting in `SdkProperties`
* support native template ad

### Code Refactoring

* **nda:** add the overlay badge on Shopping NDA AD
* **nda:** refactoring the reward ad resources
* support server side reward validation

> 💡 From version 8.5.0, RewardedAdListener is migrated to Kotlin as an interface.

### NAM SDKs mapped to this BoM version 8.5.0
|Artifact name|Version mapped this BoM|
|---|---|
|com.naver.gfpsdk:nam-core|8.5.0|
|com.naver.gfpsdk:nam-adplayer|8.5.0|
|com.naver.gfpsdk.mediation:nam-nda|8.5.0|
|com.naver.gfpsdk.mediation:nam-ndavideo|8.5.0|
|com.naver.gfpsdk.mediation:nam-applovin|13.2.0.1|
|com.naver.gfpsdk.mediation:nam-aps|9.10.2.2|
|com.naver.gfpsdk.mediation:nam-bidmachine|3.1.1.3|
|com.naver.gfpsdk.mediation:nam-chartboost|9.7.0.1|
|com.naver.gfpsdk.mediation:nam-dfp|23.3.0.4|
|com.naver.gfpsdk.mediation:nam-dt|8.3.6.2|
|com.naver.gfpsdk.mediation:nam-fan|6.18.0.4|
|com.naver.gfpsdk.mediation:nam-inmobi|10.8.0.1|
|com.naver.gfpsdk.mediation:nam-ironsource|8.4.0.4|
|com.naver.gfpsdk.mediation:nam-lan|2.9.20250110.0|
|com.naver.gfpsdk.mediation:nam-unity|4.12.3.2|
|com.naver.gfpsdk.mediation:nam-vungle|7.4.1.2|

## 8.4.5 (2025-06-10)

### Code Refactoring

* improve event handling to ensure consistent behavior

### NAM SDKs mapped to this BoM version 8.4.5
|Artifact name|Version mapped this BoM|
|---|---|
|com.naver.gfpsdk:nam-core|8.4.5|
|com.naver.gfpsdk:nam-adplayer|8.4.5|
|com.naver.gfpsdk.mediation:nam-nda|8.4.5|
|com.naver.gfpsdk.mediation:nam-ndavideo|8.4.5|
|com.naver.gfpsdk.mediation:nam-applovin|13.2.0.1|
|com.naver.gfpsdk.mediation:nam-aps|9.10.2.2|
|com.naver.gfpsdk.mediation:nam-bidmachine|3.1.1.3|
|com.naver.gfpsdk.mediation:nam-chartboost|9.7.0.1|
|com.naver.gfpsdk.mediation:nam-dfp|23.3.0.3|
|com.naver.gfpsdk.mediation:nam-dt|8.3.6.2|
|com.naver.gfpsdk.mediation:nam-fan|6.18.0.4|
|com.naver.gfpsdk.mediation:nam-inmobi|10.8.0.1|
|com.naver.gfpsdk.mediation:nam-ironsource|8.4.0.4|
|com.naver.gfpsdk.mediation:nam-lan|2.9.20250110.0|
|com.naver.gfpsdk.mediation:nam-unity|4.12.3.2|
|com.naver.gfpsdk.mediation:nam-vungle|7.4.1.2|

## 8.4.4 (2025-06-02)
### Code Refactoring
* add the background style option to NativeSimple AD

### NAM SDKs mapped to this BoM version 8.4.4
|Artifact name|Version mapped this BoM|
|---|---|
|com.naver.gfpsdk:nam-core|8.4.4|
|com.naver.gfpsdk:nam-adplayer|8.4.4|
|com.naver.gfpsdk.mediation:nam-nda|8.4.4|
|com.naver.gfpsdk.mediation:nam-ndavideo|8.4.4|
|com.naver.gfpsdk.mediation:nam-applovin|13.2.0.1|
|com.naver.gfpsdk.mediation:nam-aps|9.10.2.2|
|com.naver.gfpsdk.mediation:nam-bidmachine|3.1.1.2|
|com.naver.gfpsdk.mediation:nam-chartboost|9.7.0.1|
|com.naver.gfpsdk.mediation:nam-dfp|23.3.0.3|
|com.naver.gfpsdk.mediation:nam-dt|8.3.6.2|
|com.naver.gfpsdk.mediation:nam-fan|6.18.0.4|
|com.naver.gfpsdk.mediation:nam-inmobi|10.8.0.1|
|com.naver.gfpsdk.mediation:nam-ironsource|8.4.0.3|
|com.naver.gfpsdk.mediation:nam-lan|2.9.20241129.3|
|com.naver.gfpsdk.mediation:nam-unity|4.12.3.2|
|com.naver.gfpsdk.mediation:nam-vungle|7.4.1.2|

## 8.4.3 (2025-05-23)
### Code Refactoring
* remove unnecessary internal method

### NAM SDKs mapped to this BoM version 8.4.3
|Artifact name|Version mapped this BoM|
|---|---|
|com.naver.gfpsdk:nam-core|8.4.3|
|com.naver.gfpsdk:nam-adplayer|8.4.3|
|com.naver.gfpsdk.mediation:nam-nda|8.4.3|
|com.naver.gfpsdk.mediation:nam-ndavideo|8.4.3|
|com.naver.gfpsdk.mediation:nam-applovin|13.2.0.1|
|com.naver.gfpsdk.mediation:nam-aps|9.10.2.2|
|com.naver.gfpsdk.mediation:nam-bidmachine|3.1.1.2|
|com.naver.gfpsdk.mediation:nam-chartboost|9.7.0.1|
|com.naver.gfpsdk.mediation:nam-dfp|23.3.0.3|
|com.naver.gfpsdk.mediation:nam-dt|8.3.6.2|
|com.naver.gfpsdk.mediation:nam-fan|6.18.0.4|
|com.naver.gfpsdk.mediation:nam-inmobi|10.8.0.1|
|com.naver.gfpsdk.mediation:nam-ironsource|8.4.0.3|
|com.naver.gfpsdk.mediation:nam-lan|2.9.20241129.3|
|com.naver.gfpsdk.mediation:nam-unity|4.12.3.2|
|com.naver.gfpsdk.mediation:nam-vungle|7.4.1.2|

## 8.4.2 (2025-05-13)
### Bug Fixes
* **nda:** add `asynclayoutinflater` dependency to prevent runtime error

### NAM SDKs mapped to this BoM version 8.4.2
|Artifact name|Version mapped this BoM|
|---|---|
|com.naver.gfpsdk:nam-core|8.4.2|
|com.naver.gfpsdk:nam-adplayer|8.4.2|
|com.naver.gfpsdk.mediation:nam-nda|8.4.2|
|com.naver.gfpsdk.mediation:nam-ndavideo|8.4.2|
|com.naver.gfpsdk.mediation:nam-applovin|13.2.0.0|
|com.naver.gfpsdk.mediation:nam-aps|9.10.2.1|
|com.naver.gfpsdk.mediation:nam-bidmachine|3.1.1.1|
|com.naver.gfpsdk.mediation:nam-chartboost|9.7.0.0|
|com.naver.gfpsdk.mediation:nam-dfp|23.3.0.2|
|com.naver.gfpsdk.mediation:nam-dt|8.3.6.1|
|com.naver.gfpsdk.mediation:nam-fan|6.18.0.3|
|com.naver.gfpsdk.mediation:nam-inmobi|10.8.0.0|
|com.naver.gfpsdk.mediation:nam-ironsource|8.4.0.2|
|com.naver.gfpsdk.mediation:nam-lan|2.9.20241129.2|
|com.naver.gfpsdk.mediation:nam-unity|4.12.3.1|
|com.naver.gfpsdk.mediation:nam-vungle|7.4.1.1|

## 8.4.1 (2025-05-02)

### Bug Fixes

* **nda:** size of special da shopping nda ad was miscalculated

### NAM SDKs mapped to this BoM version 8.4.1
|Artifact name|Version mapped this BoM|
|---|---|
|com.naver.gfpsdk:nam-core|8.4.1|
|com.naver.gfpsdk:nam-adplayer|8.4.1|
|com.naver.gfpsdk.mediation:nam-nda|8.4.1|
|com.naver.gfpsdk.mediation:nam-ndavideo|8.4.1|
|com.naver.gfpsdk.mediation:nam-applovin|13.2.0.0|
|com.naver.gfpsdk.mediation:nam-aps|9.10.2.1|
|com.naver.gfpsdk.mediation:nam-dfp|23.3.0.2|
|com.naver.gfpsdk.mediation:nam-dt|8.3.6.1|
|com.naver.gfpsdk.mediation:nam-fan|6.18.0.3|
|com.naver.gfpsdk.mediation:nam-inmobi|10.8.0.0|
|com.naver.gfpsdk.mediation:nam-ironsource|8.4.0.2|
|com.naver.gfpsdk.mediation:nam-lan|2.9.20241129.2|
|com.naver.gfpsdk.mediation:nam-unity|4.12.3.1|
|com.naver.gfpsdk.mediation:nam-vungle|7.4.1.1|
|com.naver.gfpsdk.mediation:nam-bidmachine|3.1.1.1|
|com.naver.gfpsdk.mediation:nam-chartboost|9.7.0.0|

## 8.4.0 (2025-04-28)

### Code Refactoring

* **core:** add reset methods for `UserProperties` and `SdkProperties`
* support the OMSDK on Native, Rewarded AD

### NAM SDKs mapped to this BoM version 8.4.0
|Artifact name|Version mapped this BoM|
|---|---|
|com.naver.gfpsdk:nam-core|8.4.0|
|com.naver.gfpsdk:nam-adplayer|8.4.0|
|com.naver.gfpsdk.mediation:nam-nda|8.4.0|
|com.naver.gfpsdk.mediation:nam-ndavideo|8.4.0|
|com.naver.gfpsdk.mediation:nam-applovin|13.0.1.0|
|com.naver.gfpsdk.mediation:nam-aps|9.10.2.1|
|com.naver.gfpsdk.mediation:nam-dfp|23.3.0.2|
|com.naver.gfpsdk.mediation:nam-dt|8.3.6.1|
|com.naver.gfpsdk.mediation:nam-fan|6.18.0.3|
|com.naver.gfpsdk.mediation:nam-inmobi|10.8.0.0|
|com.naver.gfpsdk.mediation:nam-ironsource|8.4.0.2|
|com.naver.gfpsdk.mediation:nam-lan|2.9.20241129.2|
|com.naver.gfpsdk.mediation:nam-unity|4.12.3.1|
|com.naver.gfpsdk.mediation:nam-vungle|7.4.1.1|
|com.naver.gfpsdk.mediation:nam-bidmachine|3.1.1.1|
|com.naver.gfpsdk.mediation:nam-chartboost|9.7.0.0|

## 8.3.1 (2025-04-10)
### Bug Fixes
* **adplayer:** fire untracked progress event on ENDED event

### Code Refactoring
* add video load timeout option on out-stream ad
* **core:** support checking whether the webView is registered or not
* support for privacy click event tracking

### NAM SDKs mapped to this BoM version 8.3.1
|Artifact name| Version mapped this BoM |
|---|-------------------------|
|com.naver.gfpsdk:nam-core| 8.3.1                   |
|com.naver.gfpsdk:nam-adplayer| 8.3.1                   |
|com.naver.gfpsdk.mediation:nam-nda| 8.3.1                   |
|com.naver.gfpsdk.mediation:nam-ndarichmedia| 8.3.1                   |
|com.naver.gfpsdk.mediation:nam-ndavideo| 8.3.1                   |
|com.naver.gfpsdk.mediation:nam-applovin| 12.6.1.1                |
|com.naver.gfpsdk.mediation:nam-aps| 9.10.2.1                |
|com.naver.gfpsdk.mediation:nam-dfp| 23.3.0.2                |
|com.naver.gfpsdk.mediation:nam-dt| 8.3.6.0                 |
|com.naver.gfpsdk.mediation:nam-fan| 6.18.0.3                |
|com.naver.gfpsdk.mediation:nam-inmobi| 10.8.0.0                |
|com.naver.gfpsdk.mediation:nam-ironsource| 8.4.0.2                 |
|com.naver.gfpsdk.mediation:nam-lan| 2.9.20241129.1          |
|com.naver.gfpsdk.mediation:nam-unity| 4.12.3.1                |
|com.naver.gfpsdk.mediation:nam-vungle| 7.4.1.1                 |
|com.naver.gfpsdk.mediation:nam-chartboost| 9.7.0.0                 |
|com.naver.gfpsdk.mediation:nam-bidmachine| 3.1.1.0                 |

## 8.3.0 (2025-03-14)

> [!NOTE]
> Starting from version 8.3.0, there are changes in the internal behavior related to reusing ad objects for loading:
> - When reusing a [GfpBannerAdView](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-banner-ad-view), you should call [loadAd()](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-banner-ad-view-base/load-ad.html) directly instead of calling [destroy()](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-banner-ad-view-base/destroy.html) followed by [loadAd()](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-banner-ad-view-base/load-ad.html)
> - When reusing a [GfpAdLoader](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-ad-loader), you should call [loadAd()](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-ad-loader-base/load-ad.html) directly instead of calling [cancel()](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-ad-loader-base/cancel.html) followed by [loadAd()](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-ad-loader-base/load-ad.html)
> - When reusing a [GfpRewardedAdManager](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-rewarded-ad-manager), you should call [loadAd()](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-rewarded-ad-manager-base/load-ad.html) directly instead of calling [destroy()](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-rewarded-ad-manager-base/destroy.html) followed by [loadAd()](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-rewarded-ad-manager-base/load-ad.html).
> - When reusing a [GfpInterstitialAdManager](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-interstitial-ad-manager), you should call [loadAd()](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-interstitial-ad-manager-base/load-ad.html) directly instead of calling [destroy()](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-interstitial-ad-manager-base/destroy.html) followed by [loadAd()](https://naver.github.io/nam-sdk-android/library-core/com.naver.gfpsdk/-gfp-interstitial-ad-manager-base/load-ad.html).

### Features

* target Android API 35
* support BidMachine mediation
* support Chartboost mediation


### Bug Fixes

* **adplayer:** add missing german string resources
* fix certain carousel type native ads rendering incorrectly
* fix memory leak
* **nda:** fix title alignment issue in certain slot native ads
* **nda:** revert the refactoring code in native ad
* prevent adRenderedImpression firing multiple times on NS adapter


### Code Refactoring

* **core:** force initialization when app version changed
* optimize rendering performance of carousel type native ads


### NAM SDKs mapped to this BoM version 8.3.0
|Artifact name|Version mapped this BoM|
|---|---|
|com.naver.gfpsdk:nam-core|8.3.0|
|com.naver.gfpsdk:nam-adplayer|8.3.0|
|com.naver.gfpsdk.mediation:nam-nda|8.3.0|
|com.naver.gfpsdk.mediation:nam-ndarichmedia|8.3.0|
|com.naver.gfpsdk.mediation:nam-ndavideo|8.3.0|
|com.naver.gfpsdk.mediation:nam-applovin|12.6.1.1|
|com.naver.gfpsdk.mediation:nam-aps|9.10.2.1|
|com.naver.gfpsdk.mediation:nam-dfp|23.3.0.2|
|com.naver.gfpsdk.mediation:nam-dt|8.3.5.2|
|com.naver.gfpsdk.mediation:nam-fan|6.18.0.3|
|com.naver.gfpsdk.mediation:nam-inmobi|10.8.0.0|
|com.naver.gfpsdk.mediation:nam-ironsource|8.4.0.2|
|com.naver.gfpsdk.mediation:nam-lan|2.9.20241129.1|
|com.naver.gfpsdk.mediation:nam-unity|4.12.3.1|
|com.naver.gfpsdk.mediation:nam-vungle|7.4.1.1|
|com.naver.gfpsdk.mediation:nam-chartboost|9.7.0.0|
|com.naver.gfpsdk.mediation:nam-bidmachine|3.1.1.0|


## 8.2.6 (2025-02-10)


### Bug Fixes

* fix url parsing error when handling MRAID command

### NAM SDKs mapped to this BoM version 8.2.6
|Artifact name|Version mapped this BoM|
|---|---|
|com.naver.gfpsdk:nam-core|8.2.6|
|com.naver.gfpsdk:nam-adplayer|8.2.6|
|com.naver.gfpsdk.mediation:nam-nda|8.2.6|
|com.naver.gfpsdk.mediation:nam-ndavideo|8.2.6|
|com.naver.gfpsdk.mediation:nam-applovin|12.6.1.1|
|com.naver.gfpsdk.mediation:nam-aps|9.10.2.1|
|com.naver.gfpsdk.mediation:nam-dfp|23.3.0.2|
|com.naver.gfpsdk.mediation:nam-dt|8.3.5.1|
|com.naver.gfpsdk.mediation:nam-fan|6.18.0.2|
|com.naver.gfpsdk.mediation:nam-inmobi|10.7.7.2|
|com.naver.gfpsdk.mediation:nam-ironsource|8.4.0.1|
|com.naver.gfpsdk.mediation:nam-lan|2.9.20241129.0|
|com.naver.gfpsdk.mediation:nam-unity|4.12.3.1|
|com.naver.gfpsdk.mediation:nam-vungle|7.4.1.1|


## 8.2.5 (2025-01-22)
### Bug Fixes
* Ad view is not displayed on some devices

### NAM SDKs mapped to this BoM version 8.2.5
| Artifact name                             | Version mapped this BoM |
|-------------------------------------------|-------------------------|
| com.naver.gfpsdk:nam-core                 | 8.2.5                   |
| com.naver.gfpsdk:nam-adplayer             | 8.2.5                   |
| com.naver.gfpsdk.mediation:nam-nda        | 8.2.5                   |
| com.naver.gfpsdk.mediation:nam-ndavideo   | 8.2.5                   |
| com.naver.gfpsdk.mediation:nam-applovin   | 12.6.1.1                |
| com.naver.gfpsdk.mediation:nam-aps        | 9.10.2.1                |
| com.naver.gfpsdk.mediation:nam-dfp        | 23.3.0.2                |
| com.naver.gfpsdk.mediation:nam-dt         | 8.3.5.1                 |
| com.naver.gfpsdk.mediation:nam-fan        | 6.18.0.2                |
| com.naver.gfpsdk.mediation:nam-inmobi     | 10.7.7.2                |
| com.naver.gfpsdk.mediation:nam-ironsource | 8.4.0.1                 |
| com.naver.gfpsdk.mediation:nam-lan        | 2.9.20241129.0          |
| com.naver.gfpsdk.mediation:nam-unity      | 4.12.3.1                |
| com.naver.gfpsdk.mediation:nam-vungle     | 7.4.1.1                 |

## 8.2.4 (2025-01-16)
### Bug Fixes
* fix `NetworkOnMainThreadException` when calling `HttpUrlConnection.disconnect()`
* fix redirect logic to handle relative url

### Code Refactoring
* **core:** add an option to decide whether to use the adid

### NAM SDKs mapped to this BoM version 8.2.4
| Artifact name                             | Version mapped this BoM |
|-------------------------------------------|-------------------------|
| com.naver.gfpsdk:nam-core                 | 8.2.4                   |
| com.naver.gfpsdk:nam-adplayer             | 8.2.4                   |
| com.naver.gfpsdk.mediation:nam-nda        | 8.2.4                   |
| com.naver.gfpsdk.mediation:nam-ndavideo   | 8.2.4                   |
| com.naver.gfpsdk.mediation:nam-applovin   | 12.6.1.1                |
| com.naver.gfpsdk.mediation:nam-aps        | 9.10.2.1                |
| com.naver.gfpsdk.mediation:nam-dfp        | 23.3.0.1                |
| com.naver.gfpsdk.mediation:nam-dt         | 8.3.5.1                 |
| com.naver.gfpsdk.mediation:nam-fan        | 6.18.0.1                |
| com.naver.gfpsdk.mediation:nam-inmobi     | 10.7.7.1                |
| com.naver.gfpsdk.mediation:nam-ironsource | 8.4.0.1                 |
| com.naver.gfpsdk.mediation:nam-lan        | 2.7.20240214.1          |
| com.naver.gfpsdk.mediation:nam-unity      | 4.12.3.1                |
| com.naver.gfpsdk.mediation:nam-vungle     | 7.4.1.1                 |

## 8.2.1 (2024-12-17)
### Bug Fixes
* **adplayer:** fixing how skip offsets are calculated in in-stream ad

### Code Refactoring
* modify user interest visible ratio

### NAM SDKs mapped to this BoM version 8.2.1
| Artifact name                             | Version mapped this BoM |
|-------------------------------------------|-------------------------|
| com.naver.gfpsdk:nam-core                 | 8.2.1                   |
| com.naver.gfpsdk:nam-adplayer             | 8.2.1                   |
| com.naver.gfpsdk.mediation:nam-nda        | 8.2.1                   |
| com.naver.gfpsdk.mediation:nam-ndavideo   | 8.2.1                   |
| com.naver.gfpsdk.mediation:nam-applovin   | 12.6.1.0                |
| com.naver.gfpsdk.mediation:nam-aps        | 9.10.2.0                |
| com.naver.gfpsdk.mediation:nam-dfp        | 23.3.0.0                |
| com.naver.gfpsdk.mediation:nam-dt         | 8.3.0.0                 |
| com.naver.gfpsdk.mediation:nam-fan        | 6.18.0.0                |
| com.naver.gfpsdk.mediation:nam-inmobi     | 10.7.7.0                |
| com.naver.gfpsdk.mediation:nam-ironsource | 8.4.0.0                 |
| com.naver.gfpsdk.mediation:nam-lan        | 2.7.20240214.0          |
| com.naver.gfpsdk.mediation:nam-unity      | 4.12.3.0                |
| com.naver.gfpsdk.mediation:nam-vungle     | 7.4.1.0                 |

## 8.2.0 (2024-12-04)

### Bug Fixes

* disconnect even before receiving response when cancelling network request

### Code Refactoring

* load scaled down image into memory when loading image

### NAM SDKs mapped to this BoM version 8.2.0
| Artifact name                              | Version mapped this BoM |
|--------------------------------------------|-------------------------|
| com.naver.gfpsdk:nam-core                  | 8.2.0                   |
| com.naver.gfpsdk:nam-adplayer              | 8.2.0                   |
| com.naver.gfpsdk.mediation:nam-nda         | 8.2.0                   |
| com.naver.gfpsdk.mediation:nam-ndavideo    | 8.2.0                   |
| com.naver.gfpsdk.mediation:nam-applovin    | 12.6.1.0                |
| com.naver.gfpsdk.mediation:nam-aps         | 9.10.2.0                |
| com.naver.gfpsdk.mediation:nam-dfp         | 23.3.0.0                |
| com.naver.gfpsdk.mediation:nam-dt          | 8.3.0.0                 |
| com.naver.gfpsdk.mediation:nam-fan         | 6.18.0.0                |
| com.naver.gfpsdk.mediation:nam-inmobi      | 10.7.7.0                |
| com.naver.gfpsdk.mediation:nam-ironsource  | 8.4.0.0                 |
| com.naver.gfpsdk.mediation:nam-lan         | 2.7.20240214.0          |
| com.naver.gfpsdk.mediation:nam-unity       | 4.12.3.0                |
| com.naver.gfpsdk.mediation:nam-vungle      | 7.4.1.0                 |

## 8.1.0 (2024-11-14)

### Features
* **nda:** add NN flicking AD

### NAM SDKs mapped to this BoM version 8.1.0
| Artifact name                             | Version mapped this BoM |
|-------------------------------------------|-------------------------|
| com.naver.gfpsdk:nam-core                 | 8.1.0                   |
| com.naver.gfpsdk:nam-adplayer             | 8.1.0                   |
| com.naver.gfpsdk.mediation:nam-nda        | 8.1.0                   |
| com.naver.gfpsdk.mediation:nam-ndavideo   | 8.1.0                   |
| com.naver.gfpsdk.mediation:nam-applovin   | 12.6.0.0                |
| com.naver.gfpsdk.mediation:nam-aps        | 9.10.2.0                |
| com.naver.gfpsdk.mediation:nam-dfp        | 23.3.0.0                |
| com.naver.gfpsdk.mediation:nam-dt         | 8.2.7.0                 |
| com.naver.gfpsdk.mediation:nam-fan        | 6.17.0.0                |
| com.naver.gfpsdk.mediation:nam-inmobi     | 10.7.5.0                |
| com.naver.gfpsdk.mediation:nam-ironsource | 8.2.1.0                 |
| com.naver.gfpsdk.mediation:nam-lan        | 2.7.20240214.0          |
| com.naver.gfpsdk.mediation:nam-unity      | 4.12.2.0                |
| com.naver.gfpsdk.mediation:nam-vungle     | 7.4.0.0                 |


## 8.0.0 (2024-11-07)

> [!NOTE]
> This release is a MAJOR version update with several breaking changes. See [Migration To SDK v8](https://naver.github.io/nam-sdk-guide/en/android/integrate/migration_to_8) for more information on how to migrate.

### ⚠ BREAKING CHANGES
* **core:** move `UserPropertiesBuilder` to the `com.naver.gfpsdk.properties` package
* **core:** move `SdkPropertiesBuilder` to the `com.naver.gfpsdk.properties` package
* change 3-party mediation module to handle versioning separately
* change the artifact group id of the mediation module from `com.naver.gfpsdk` to `com.naver.gfpsdk.mediation`
* change `com.naver.gfpsdk.provider` package to `com.naver.gfpsdk.mediation` package

### Features
* change 3-party mediation module to handle versioning separately

### Bug Fixes
* **nda:** add 1px visibility check in Slots

### Code Refactoring
* **core:** move `SdkPropertiesBuilder` to the `com.naver.gfpsdk.properties` package
* **core:** move `UserPropertiesBuilder` to the `com.naver.gfpsdk.properties` package

### NAM SDKs mapped to this BoM version 8.0.0
| Artifact name                             | Version mapped this BoM |
|-------------------------------------------|-------------------------|
| com.naver.gfpsdk:nam-core                 | 8.0.0                   |
| com.naver.gfpsdk:nam-adplayer             | 8.0.0                   |
| com.naver.gfpsdk.mediation:nam-nda        | 8.0.0                   |
| com.naver.gfpsdk.mediation:nam-ndavideo   | 8.0.0                   |
| com.naver.gfpsdk.mediation:nam-applovin   | 12.6.0.0                |
| com.naver.gfpsdk.mediation:nam-aps        | 9.10.2.0                |
| com.naver.gfpsdk.mediation:nam-dfp        | 23.3.0.0                |
| com.naver.gfpsdk.mediation:nam-dt         | 8.2.7.0                 |
| com.naver.gfpsdk.mediation:nam-fan        | 6.17.0.0                |
| com.naver.gfpsdk.mediation:nam-inmobi     | 10.7.5.0                |
| com.naver.gfpsdk.mediation:nam-ironsource | 8.2.1.0                 |
| com.naver.gfpsdk.mediation:nam-lan        | 2.7.20240214.0          |
| com.naver.gfpsdk.mediation:nam-unity      | 4.12.2.0                |
| com.naver.gfpsdk.mediation:nam-vungle     | 7.4.0.0                 |


## 7.9.0 (2024-10-24)

### Bug Fixes
* fix animation rendering issues on devices below Android 9
* fix VAST parsing error

### Code Refactoring
* allow redirects for all http requests by default

## 7.8.1 (2024-10-10)
### Bug Fixes
* fix height value calculation error in `NativeTemplateView`

## 7.8.0 (2024-09-26)
### Features
* **nda:** support the Smart Channel Flicking AD

### Bug Fixes
* **nda:** fix image badge size error in shopping label ad

### Code Refactoring
* **nda:** add content description to SlotsView

## 7.7.2 (2024-09-10)
### Bug Fixes
* fix a bug that ViewTreeObserver is not properly removed
* fix missing disconnect in `HttpUrlConnection`
* **nda:** fix rendering the slot in GridLayout

### Code Refactoring
* **nda:** modify shopping nda ad design
* **nda:** modify shopping NDA minor design
* **nda:** use NasImageView on shopping search ad store icon

## 7.7.1 (2024-08-30)
### Bug Fixes
* **core:** now SdkPropertiesBuilder accept only one GfpProviderOptions per providerType
* **nda:** fix a scroll issue on shopping search native ad

### Code Refactoring
* optimize the use of ViewObserver in GfpAdAdapter
* support Android API 34

## 7.7.0 (2024-08-20)

### Features

* support shopping search native ad


### Bug Fixes

* **nda:** fix video not pausing when clicking on ad choices
* **video:** show saved last frame on FullScreenAD


### Code Refactoring

* add NDA 1+3 View
* **ironsource:** refactor IronSource Integration to load multiple instance id
* **nda:** modify badge UI on shopping nda ad
* **nda:** show the CTA button on last frame for the Rewarded AD
* reuse innerAdView inside `GfpNativeAdView` without inflating it

## 7.6.2 (2024-07-25)
### ⚠ Deprecated
* **This version has an issue. Use the older NAM SDK v7.6.1 instead.**

### Code Refactoring
* add a max redirection config for vast parsing
* **core:** add apis to clear `customParameter`
* **core:** add content id to ad request parameter
* **core:** handle bounce back tracking event

## 7.6.1 (2024-07-10)
### Bug Fixes

- fix a bug that prevented out-stream video ad from resuming

## 7.6.0 (2024-07-09)

### Features

* **lan:** add lan extension
* support shopping label ad

## 7.5.3 (2024-06-13)
### Code Refactoring

* **applovin:** bump `applovin` to 12.5.0
* **nda:** refactor `SlotsView`'s padding handling
* **unity:** update `unity` to 4.10.0

## 7.5.2 (2024-05-30)
### Bug Fixes
* **nda:** fix maximum width of badge for `Shopping NDA` ad
* **nda:** fix price font size for price highlighted case in `Shopping NDA` ad

### Code Refactoring
* bump minSdk from 19 to 21
* **core:** send ad loaded event after listener setting in Rewarded/Interstitial AD
* update DSP sdk

## 7.5.1 (2024-05-13)
### Bug Fixes
* **nda:** fix incorrect layout handling in `ShoppingNdaView`

### Code Refactoring
* **nda:** change the rewarded text on the Rewarded AD

## 7.5.0 (2024-05-09)
### Features

* support carousel media in native ads

### Bug Fixes

* **nda:** align `discount` and `price` TextViews in Shopping NDA ad

### Code Refactoring

* **nda:** remove unused resources

## 7.4.4 (2024-04-29)
### Bug Fixes
* **applovin:** set applovin banner view size
* **core:** add child as centered to GfpBannerAdView

## 7.4.3 (2024-04-26)
### Code Refactoring
- add corner option for where the ad choices icon is

## 7.4.2 (2024-03-27)
### Bug Fixes
* **dfp:** fix the video mute setting in DFP Rewarded/Interstitial AD

### Code Refactoring
* **applovin:** refactor the integration of AppLovin SDK
* **core:** additional device properties parameters on AdCallRequest
* **dt:** add the mute video setting on DigitalTurbine
* refactor log level
* support additional event tracking types
* **vungle:** refactor the no fill ad in Vungle

## 7.4.1 (2024-03-20)
### Code Refactoring
* **core:** add theme to `GfpRewardedAdOptions` and `GfpInterstitialAdOptions`

## 7.4.0 (2024-03-18)
### ⚠ Deprecated
* **This version has an issue. Use the older NAM SDK v7.3.0 instead.**

### Features
* support clickable control view type for native outstream ad

### Code Refactoring
* add background color and text colors to native ad

## 7.3.0 (2024-03-08)
### Code Refactoring
* improve logging process

## 7.2.1 (2024-02-28)
### Bug Fixes
* **applovin:** add error handling at initialization
* **fan:** add error handling when load native ad
* **ironsource:** remove IronSource banner
* **unity:** fix crash when passing application context when requesting Unity banner
* **vungle:** add error handling at initialization

## 7.2.0 (2024-02-21)
### Features
* add a language setting on in-stream ad

### Bug Fixes
* `ViewObserver` does not work with ad that is displayed on top of other apps

## 7.1.1 (2024-02-06)
### Bug Fixes
* **nda:** fix the load failure of EndCard in RewardedVideo AD

### Code Refactoring
* **core:** add an UA parameter on ad request
* **core:** support OMSDK version 1.4.10

## 7.1.0 (2024-01-26)
### Features
* **aps:** add APS Banner
* **ironsource:** support IronSource SDK
* **nda:** add a dialog to continue to see the RewardedAd
* **vungle:** support in-app bidding on Vungle

### Bug Fixes
* **applovin:** fix a AppLovin sequential caching setting

### Code Refactoring
* **applovin:** bump `applovin` sdk to 11.11.3
* **dfp:** bump `dfp` sdk to 22.6.0
* **dt:** bump DigitalTurbine sdk to 8.2.4
* **inmobi:** bump `inmobi` sdk to 10.6.2
* **nda:** change the default background of `GfpMediaView` from `black` to `transparent`
* **nda:** refactor the ad choice validation on RewardedVideo AD
* **unity:** bump `unity` sdk to 4.9.2

## 7.0.1 (2024-01-19)
### Bug Fixes
* **fan:** fix ANR issue while fetching the bid token

## 7.0.0 (2024-01-12)
### ⚠ BREAKING CHANGES
* **core:** removed `GfpNativeSimpleAdOptions.Builder.setBackgroundOptions()`
* **core:** removed `GfpTheme.SYSTEM`, `GfpTheme.LIGHT` and `GfpTheme.DARK`

### Features
* does not collect adid on child directed app

### Code Refactoring
* **core:** remove deprecated GfpNativeBackgroundOption
* **core:** remove deprecated GfpTheme fields

## 6.7.2 (2023-12-20)
### Code Refactoring
* **unity:** bump unity sdk to 4.9.2

## 6.7.1 (2023-12-11)
### Bug Fixes
* **core:** fix the initialization api
* **core:** fix the VideoAdOptions setting after the video schedule is loaded

### Code Refactoring
* add validation for media types
* **core:** refactor internal initialization to prevent ConcurrentModificationException
* **unity:** remove unnecessary size validation on banner

## 6.7.0 (2023-11-21)
### Bug Fixes
* **nda:** fix a clickable area on rewarded companion ad

### Code Refactoring
* **adplayer:** organize drawables for skip button in in-stream
* **applovin:** bump `applovin` sdk to 11.10.1
* **dfp:** bump `dfp` sdk to 22.2.0
* **fan:** bump `fan` sdk to 6.16.0
* **inmobi:** bump `inMobi` sdk to 10.5.9
* **unity:** bump `unity` sdk to 4.8.0
* **vungle:** bump `vungle` sdk to 7.0.0

## 6.6.3 (2023-11-20)
### Bug Fixes
* **core:** fix a bug when binding native ads in RecyclerView

## 6.6.1 (2023-11-14)
### Bug Fixes
* **nda:** pause button misbehavior in outstream video ads

### Code Refactoring
* **core:** don’t pass the AD ID at initialization

## 6.6.0 (2023-11-06)
### ⚠ Deprecated
* **This version has an issue with mediation not performing correctly. Use the older NAM SDK v6.5.0 instead.**

### Bug Fixes
* **fan:** fix for native banner not supporting `GfpNativeAd.getMediaData()` ([f588fb8](https://oss.navercorp.com/da-ssp-app-sdk/naver_sdk_aos/commit/f588fb84dd723c77626a6754dd27cc7c311649d7)), closes [#1512](https://oss.navercorp.com/da-ssp-app-sdk/naver_sdk_aos/issues/1512)
* fix cases where vast tracking does not work ([89a69b6](https://oss.navercorp.com/da-ssp-app-sdk/naver_sdk_aos/commit/89a69b66296201eb991e6dd448a7c8c4655b9db1)), closes [#1502](https://oss.navercorp.com/da-ssp-app-sdk/naver_sdk_aos/issues/1502)

### Code Refactoring
* **applovin:** add a validation check in the AppLovin native ad for CTA ([2866eaa](https://oss.navercorp.com/da-ssp-app-sdk/naver_sdk_aos/commit/2866eaa27c7398df7c8d591560a28303e1e0d266)), closes [#1489](https://oss.navercorp.com/da-ssp-app-sdk/naver_sdk_aos/issues/1489)

## 6.5.0 (2023-10-24)
### Features
* add api to get `text` and `highlightedBgColor` of `callToAction` asset

### Bug Fixes
* **fan:** fix the edge case of NPE during FAN initialization

### Code Refactoring
* **core:** optimize `GfpNativeAdView` and `GfpNativeSimpleAdView`
* **nda:** update ui translations for ad mute
* remove night resources
* separate extension for label assets

## 6.4.2 (2023-10-12)
### Bug Fixes
* **core:** background color does not changed on re-bound ad
* **core:** NativeSimple background makes IllegalArgumentException(NaN)
* fix lint errors
* fix the NativeSimple AD's background rendering

### Code Refactoring
* add drawable resources for hdpi resolution

## 6.4.1 (2023-09-15)
### Bug Fixes
* **nda:** fix the NPE created by empty background option in NativeSimple AD

## 6.4.0 (2023-09-14)
### Features
* **core:** support DigitalTurbine SDK

### Bug Fixes
* **core:** fix a cached TFCD value on external module
* **core:** fix the case where load error event is lost
* **core:** NullPointerException on GfpResponseInfo
* **dfp:** remove `DfpInitializer`
* fix a ConcurrentModificationException issue on DSP initialization (Vungle, Unity, DT)
* fix the no_fill error stat code in Vungle, AppLovin
* **nda:** fix bug with companion ad using `WebView`

### Code Refactoring
* adjust c2s dsp video mute setting

## 6.3.0 (2023-09-05)

### Features

* support global privacy policy on C2S

### Bug Fixes

* **vungle:** fix a mis matching error code on Vungle rendering error

## 6.2.0 (2023-08-30)

### ⚠ BREAKING CHANGES

* **core:** removed `GfpNativeSimpleAdOptions.Builder.setAdChoicePlacement(int)` method. use `GfpNativeSimpleAdOptions.Builder.setAdChoicesPlacement(int)` instead
* **core:** removed `GfpNativeAdOptions.Builder.setAdChoicePlacement(int)` method. use `GfpNativeAdOptions.Builder.setAdChoicesPlacement(int)` instead

>Note: There is an interface name change to unify the words used across platforms. We appreciate your understanding that this is a breaking change without a major version change.

### Features

* add api to get media information in native ad objects
* **core:** add an ad request uri interface on AdParam
* **core:** add user show interest listener to `GfpAdLoader`
* **nda:** change ad choices icon in S2S Native Normal ads
* support s2s native out-stream video ads

### Bug Fixes

* **core:** fix crash when tracking to invalid url
* **core:** make `GfpDedupeManager` reusable after destroying it
* **nda:** update ui translations
* **nda:** video ad pauses when restored after being rewound

### Code Refactoring

* **nda:** ignore playback restriction when user-activated play control
* **nda:** improve ad mute feedback list page
* **nda:** modify the logic that determines the ad mute layout type
* **nda:** only generate blur image only when blur image settings are present

## 6.1.1 (2023-07-21)
### Bug Fixes
* unplayed ad makes the video content stop after ad schedule is finished

### Code Refactoring
* add an attribute to GfpAdChoicesView for AdMute icon resource
* **core:** add `GfpTheme` type that can be used lazily

## 6.1.0 (2023-07-04)
### Features
* **core:** support Global Privacy Policy for GDPR, CCPA, COPPA

### Bug Fixes
* **core:** do not re-create the ViewObserver on every ad-load event
* **core:** the AdBreak processing is stopped, if ad fails in a series of ads

### Code Refactoring
* add extra assets to native ad
* **core:** specify non-null parameter for VideoAdListener
* **nda:** remove ConstraintLayout on RewardVideo AD

## 6.0.5 (2023-06-21)
### Bug Fixes
* **core:** can do skip on multiple in-stream ad

### Code Refactoring
* **core:** make public the schedule response

## 6.0.4 (2023-06-15)
### Bug Fixes
* **adplayer:** add clickable information to GfpVideoAdQoeInfo
* **core:** fix AdBreak processing to play in-stream ads sequentially

## 6.0.3 (2023-06-08)
>Note: NAM SDK with versions 6.0.0 through 6.0.2 may encounter permissions issues when installing apps, causing apps to fail to install properly. **For this reason, we strongly recommend using version 6.0.3.**
### Bug Fixes
* **nda:** fix missing webview provider crash

### Code Refactoring
* **nda:** remove broadcast permission on RewardVideo AD

## 6.0.2 (2023-05-31)

### Bug Fixes

* **nda:** NDA Banner does not render properly when set to `FLUID_WIDTH`

## 6.0.1 (2023-05-26)

### Bug Fixes

* **core:** fix meta(nam-fan module) ads not loading

## 6.0.0 (2023-05-25)

### ⚠ BREAKING CHANGES

* **core:** removed `GfpLogger`
* **core:** removed `SdkPropertiesBuilder.logLevel()` and `SdkProperties.logLevel`

### Features

* **nda:** support s2s reward video
* support Vungle SDK

### Bug Fixes

* **applovin:** Fix the AppLovin Rewarded ad callback
* **nda:** privacy icon is duplicated when re-attaching on recyclerview

### Code Refactoring

* **applovin:** bump `applovin` sdk version to 11.7.1
* **core:** support custom parameter as a global property
* **dfp:** bump `gma` sdk to 21.5.0
* **fan:** bump `meta` sdk to 6.13.7
* **ima:** bump `ima` sdk version to 3.27.1
* **inmobi:** bump `inmobi` sdk to 10.1.2
* **unity:** bump `unity` sdk version to 4.6.1

## 5.3.0 (2023-03-24)
### Bug Fixes
* fix the valid expire time in C2S Rewarded Ad

### Code Refactoring
* **applovin:** disable AppLovin's cache option
* **core:** Supporting OMSDK version 1.4.2

## 5.2.0 (2023-03-13)

### Features
- **core:** add `setContentId()` method for targeting
### Bug Fixes
- **applovin:** Fix the AppLovin Rewarded ad callback
### Code Refactoring
- **applovin:** refactoring initializing process on AppLovin
- **applovin:** version up AppLovin SDK to 11.8.1
- **core:** change `contentId` param to `contentInfo` param
- **nda:** migrate all code to kotlin
- **nda:** provide `AdMute` for S2S Native Normal ad
- **unity:** version up unity sdk to 4.6.0

## 5.1.3 (2023-02-14)

### Bug Fixes
- **core:** fix a potential memory issue when injecting omid script
- **core:** property `logLevel` of `SdkProperties` is never used
- **nda:** Do not assign WebView for two part in `MraidController`

### Code Refactoring
- **core:** unify the adcall host to NAM

## 5.1.2 (2023-01-11)
### Bug Fixes
- **applovin:** fix a bug of handling MREC format in banner
- **core:** fix rare ANR after calling `loadAd()`
- **nda:** fix missing webview provider crash

## 5.1.0 (2022-12-28)
### Features
- **applovin:** add the AppLovin Max SDK module
- **nda:** support In-App JS Tag on NdaBannerAdapter

### Bug Fixes
- **adplayer:** fix the font size on instream ad
- **applovin:** fix a rendering bug in AppLovin Native ad
- **applovin:** fix a rendering issue on Native Banner for AppLovin
- **core:** sometimes `OneTimeAction` doesn't work
- **fan:** modify to request bidder token for every ad call
- **nda:** support JS-Tag provided by tappx

### Code Refactoring
- **dfp:** bump `gma` sdk to 21.3.0
- **fan:** bump `meta` sdk to 6.12.0
- **inmobi:** bump `inmobi` sdk to 10.0.8
- **unity:** bump `unity` sdk version to 4.4.1
- **unity:** migrate unity module to kotlin

## 5.0.0 (2022-11-04)
### ⚠ BREAKING CHANGES
* **core:** removed `GfpNativeAdView.setIconView(View)` method. use `GfpNativeAdView.setIconView(ImageView)` instead

### Bug Fixes
* **core:** fix an obfuscation error in OmidVisibilityTracker
* **core:** fix Exception on SDK initialization
* **nda:** destroy `WebView` with a delay
* **nda:** fix scrolling issue of webview inside scrollable view

### Code Refactoring
* **core:** change scope of `play-service-appset` to compileOnly
* **core:** remove `GfpNativeAdView.setIconView(View)` method
* **nda:** migrate some code related to banner ads to kotlin

## 4.4.1 (2022-09-27)

### Bug Fixes
* **nda:** fix a bug where `NdaAdMuteView` is not rendered properly

## 4.4.0 (2022-09-01)

### Features

* **core:** add network type parameter in ad request
* **core:** support App Set ID
* support OM SDK in Banner ad (webview)

### Code Refactoring

* **fan:** remove validation login for c2s, migrate to kotlin
* **nda:** change default value of `GfpTheme` from `SYSTEM` to `LIGHT`

## 4.3.6 (2022-08-04)

### Bug Fixes

* **core:** fix a bug which is making crash after obfuscation in NS.


## 4.3.5 (2022-08-03)

### Bug Fixes

* **core:** fix a bug which is not updating background margin in NS after drawing rich media ad.


## 4.3.4 (2022-08-02)

### Bug Fixes

* **nda:** AdMuteView render abnormally if api level is less than 24


## 4.3.3 (2022-08-01)


### Bug Fixes

* **core:** does not set a content description on Native Banner
* **core:** fix a bug that is not drawn if width of `GfpNativeSimpleAdView` is `wrap_content`
* **nda:** fix a bug calculating size of NS image in recycler view


### Code Refactoring

* migrate some of internal code to kotlin
* **nda:** refactor ad mute view
* **nda:** remove `recyclerView` dependency


## 4.3.2 (2022-07-18)


### Bug Fixes

* **nda:** fix a bug that user cannot click the ad mute button when the same ad is rebind

## 4.3.1 (2022-07-05)


### Bug Fixes

* change the artifact id of the external build

>⚠️ Important: To prevent conflicts with NAVER internal build, we changed the artifact id of NAM SDK, and from version `4.3.1` you have to add dependencies as below.

```groovy
implementation platform('com.naver.gfpsdk:nam-bom:4.3.1')
implementation 'com.naver.gfpsdk:nam-core'
implementation 'com.naver.gfpsdk:nam-nda'
implementation 'com.naver.gfpsdk:nam-dfp'
implementation 'com.naver.gfpsdk:nam-fan'
implementation 'com.naver.gfpsdk:nam-inmobi'
implementation 'com.naver.gfpsdk:nam-unity'
```

## 4.3.0 (2022-07-05)


### Features

* add new module to generate Bill of Materials

```groovy
implementation platform('com.naver.gfpsdk:gfpsdk-bom:4.3.0')
implementation 'com.naver.gfpsdk:gfpsdk-core'
implementation 'com.naver.gfpsdk:extension-nda'
implementation 'com.naver.gfpsdk:extension-dfp'
implementation 'com.naver.gfpsdk:extension-fan'
implementation 'com.naver.gfpsdk:extension-inmobi'
implementation 'com.naver.gfpsdk:extension-unity'
```


### Bug Fixes

* **core:** fix a potential bug in `GfpAdLoader.Builder`


### Code Refactoring

* **core:** migrate `GfpError` class to kotlin
* **inmobi:** remove optional dependencies of inmobi module
* migrate some of internal code to kotlin


## 4.3.0-beta.1 (2022-06-30)

### Features

* add new module to generate Bill of Materials

### Bug Fixes

* **core:** fix a potential bug in `GfpAdLoader.Builder`

### Code Refactoring

* **core:** migrate `GfpError` class to kotlin
* **inmobi:** remove optional dependencies of inmobi module
* migrate some of internal code to kotlin
