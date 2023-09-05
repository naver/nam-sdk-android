# Changelog

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
