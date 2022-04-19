# Ad Mute Event
NAM SDK 는 Banner, Native Simple 타입에 대해 광고 숨기기(ad mute) 기능을 제공합니다.

#### 1. GfpBannerAdView 를 통해 배너 광고를 호출하는 경우 BannerAdListener 에 정의된 onAdMuted() 를 통해 콜백 전달
```
bannerAdView.setAdListener(new BannerAdListener() {

    /* onAdLoaded(), onAdClicked() 등 */

    @Override
    public void onAdMuted(GfpBannerAd ad) {
        /* ... */
    }
});
```
#### 2. GfpAdLoader 를 통해 배너 및 네이티브 광고를 호출하는 경우 AdEventListener 에 정의된 onAdMuted() 를 통해 콜백 전달
```
GfpAdLoader adLoader = new GfpAdLoader.Builder(activity, adParam)
        .withAdListener(new AdEventListener() {

            /* onAdClicked(), onAdImpression() 등 */

           @Override
            public void onAdMuted() {
                 /* ... */
            }
        })
        .withNativeSimpleAd(nativeSimpleAdOptions, nativeSimpleAd -> {
            /*  ... */
        })
        .build();
```


## Dark Theme
Ad Mute 아이콘에 대해 다크모드 / 라이트모드 설정도 지원하고 있습니다.\
설정 가능한 타입은 3가지로, 시스템 설정을 따라가는 `SYSTEM`, 라이트 모드인 `LIGHT`, 다크 모드인 `DARK` 를 제공하고 있습니다.\
테마 설정 방식으로는 `NdaProviderOptions` 를 통한 전역 설정 방법과 `GfpNativeSimpleAdOptions` 를 통한 개별 설정 방식이 있습니다.

### 1. 전역 설정 방식
  - `AdManager` 를 통한 광고 초기화시에 `GfpSdkConfiguration` 을 구성하면서 `NdaProviderOptions` 를 통해 설정
```
GfpSdkConfiguration sdkConfiguration = new GfpSdkConfiguration.Builder(PUBLISHER_CODE)
        .withProviderOptions(new NdaProviderOptions.Builder()
                .setTheme(GfpTheme.DARK).build())
        .build();
AdManager.initialize(application, sdkConfiguration);
```
  - 전역 설정을 할 경우 별도의 개별 설정을 하지 않은 모든 S2S 배너 광고 및 NativeSimple 광고에 일괄 적용됩니다.
  - 아무런 설정을 하지 않을 경우 기본값은 `SYSTEM` 입니다.

### 2. 개별 설정 방식
  - `GfpAdLoader` 를 통해 광고를 요청할 때 함께 전달하는 `GfpNativeSimpleAdOptions` 객체를 통해 설정
```
GfpNativeSimpleAdOptions nativeSimpleAdOptions = new GfpNativeSimpleAdOptions.Builder()
        .setTheme(GfpTheme.DARK)
        .build();
GfpAdLoader adLoader = new GfpAdLoader.Builder(activity, adParam)
        .withAdListener(new AdEventListener() {

            /* onAdClicked(), onAdImpression() 등 */

           @Override
            public void onAdMuted() {
                 /* ... */
            }
        })
        .withNativeSimpleAd(nativeSimpleAdOptions, nativeSimpleAd -> {
            /*  ... */
        })
        .build();
```

> 전역 설정보다 개별 설정이 우선 순위가 높습니다. 따라서 전역 설정과 개별 설정이 모두 설정된 경우에는 개별 설정된 값이 적용됩니다.
