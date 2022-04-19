# Naver Ad Manager SDK for Android
Naver Ad Manager는 높은 단가의 ‘네이버 성과형 광고 플랫폼’(네이버SSP에서만 물량 제공)을 비롯해 국내/외 대표적인 DSP를 모두 확보하고 있어 매체 수익화를 위한 최적의 환경을 제공합니다.

![네이버 광고 종류](doc/images/NAM_ADS_CATEGORY.png?raw=true "Title")

----
## Getting Started
광고를 게재하고 수익을 올리려면 먼저 NAM SDK를 앱에 적용해야 합니다.
#### Prerequisites
- com.android.tools.build:gradle 버전 3.2.1 이상
- minSdkVersion 19 이상
- AndroidX 대응
  - DFP (구글) 광고 모듈을 사용하기 위해서는 Jetpack 라이브러리로 migration이 필수입니다.
  - `compileSdkVersion` 을 28 버전 이후로 업그레이드 해야만 합니다.
  - 앱에서 Jetpack(AndroidX) 을 사용할 수 있도록 업데이트 해야합니다.
    자세한 가이드는 [Migrating to AndroidX](https://developer.android.com/jetpack/androidx/migrate) 를 참고해주시면 됩니다.
- 만약 minSdkVersion이 21 미만이라면 [안드로이드 문서를 통해 멀티덱스 사용 설정](https://developer.android.com/studio/build/multidex?hl=ko)을 해 주시기 바랍니다.

#### NAM SDK 에서 사용하는 dependencies
***DFP, FAN 등 DSP 모듈의 각 라이브러리 버전은 NAM SDK 버전에 따라 변경될 수 있습니다.***
- Build 관련\
코틀린: 1.4.32\
minSdkVersion: 19\
targetSdkVersion: 30\
compileSdkVersion: 30
- NDA Video\
androidx.constraintlayout:constraintlayout:1.1.3
- NDA\
androidx.recyclerview:recyclerview:1.2.1
- NDA Richmedia\
androidx.constraintlayout:constraintlayout:1.1.3
- DFP\
com.google.android.gms:play-services-ads:20.2.0
- FAN\
com.facebook.android:audience-network-sdk:6.5.1
- IMA\
com.google.ads.interactivemedia.v3:interactivemedia:3.24.0
- InMobi\
com.inmobi.monetization:inmobi-ads:9.2.0\
com.squareup.picasso:picasso:2.71828\
com.google.android.gms:play-services-location:17.0.0
- Unity\
com.unity3d.ads:unity-ads:3.7.5

#### 지원하는 Mediation DSP 별 광고 종류
- 각 Mediation DSP(extension) 이름은 다음과 같습니다\
  NDA: Naver Display Ads\
  DFP: Doubleclick for Publishers (구글 광고)\
  IMA: Interactive Media Ads (구글의 동영상 광고 제공자)\
  FAN: Facebook(Meta) Audience Network\
  InMobi
- Pubmatic, AppNexus, Rubicon 등 외부 Ad Provider 의 광고는 ***NDA 모듈***을 통해 제공 됩니다.

**아래 지원되는 광고를 참고하시고, GFP 관리자와 협의하여 연결하고자 하는 DSP를 확인하시고 각 모듈을 추가 하여야 합니다.**

| Ad Provider | Banner | Video(Instream) | Native | Native (without media view) | Reward | Interstitial |
|:--------------------------|:------|:---------------|:------|:--------------------------|:------|:------------|
| NDA | O | X | O | O | X | X |
| NDAVideo | X | **O** | X | X | X | X |
| DFP | O | X | O | O | O | O |
| IMA | X | **O** | X | X | X | X |
| FAN | O | X | O | O | O | O |
| InMobi | O | X | ***O*** | ***X*** | X | X |
| Unity | O(320*50 크기만 지원) | X | X | X | **O** | **O** |

----

#### 1. Add the NAM SDK
최신 버전의 NAM SDK를 다운 받으신 후, application module의 `libs` 폴더에 복사합니다.\
그리고 해당 module의 `build.gradle` 파일에 아래 dependencies 항목을 추가합니다.
```
implementation fileTree(dir: 'libs', include: ['*.aar'])
```

- 현재 파일로 aar을 추가하시는 경우\
  application의 dependencies 항목에 아래 NAM SDK가 사용중인 라이브러리가 있는지 확인해 주시고\
  없다면 추가해 주세요.
```
// kotlin
implementation 'androidx.core:core-ktx:1.3.2'
implementation 'org.jetbrains.kotlin:kotlin-stdlib:1.4.32'
// android
implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
implementation 'androidx.recyclerview:recyclerview:1.2.1'
// DFP
implementation 'com.google.android.gms:play-services-ads:20.2.0'
// FAN
implementation 'com.facebook.android:audience-network-sdk:6.5.1'
// IMA
implementation 'com.google.ads.interactivemedia.v3:interactivemedia:3.24.0'
// InMobi
implementation 'com.inmobi.monetization:inmobi-ads:9.2.0'
implementation 'com.squareup.picasso:picasso:2.71828'
implementation 'com.google.android.gms:play-services-location:17.0.0'
// UNITY
implementation 'com.unity3d.ads:unity-ads:3.7.5@aar'
```

#### 2. Edit `AndroidManifest.xml`
- 초기화 관련 설정\
GFP SDK를 사용하기 위해서는 아래 메타 정보를 추가해 주셔야 합니다.
```
<manifest>
    <application>
        <!-- publisher code 값은 보통 숫자로만 이루어져 있으므로 string.xml을 통해 관리/설정 부탁드립니다. -->
        <meta-data
            android:name="com.naver.gfpsdk.PUBLISHER_CD"
            android:value="@string/publisher_cd" />
    </application>
</manifest>
```
- DFP 광고 관련 설정\
DFP(구글-extension-dfp) 모듈을 추가할 경우 아래 태그를 추가해 주셔야 합니다.
```
<manifest>
    <application>
        <!-- Google playservice ads 버전업 이후 필수로 추가해줘야 하는 태그. -->
        <meta-data
            android:name="com.google.android.gms.ads.AD_MANAGER_APP"
            android:value="true"/>
    </application>
</manifest>
```
- FAN 광고 관련 설정\
Meta Audience Network (extension-fan) 모듈을 추가하시는 경우\
미디어 파일 캐시를 위해 [Android 네트워크 보안 구성](https://developer.android.com/training/articles/security-config)과 함께
[FAN 네트워크 보안 구성](https://developers.facebook.com/docs/audience-network/android-network-security-config) 내용을 참고하시어 관련 설정을 추가해야 합니다.\
또한 FAN의 네이티브 광고를 사용하신다면 하드웨어 가속화 렌더링을 활성화해야 하며, 그렇지 않을 경우 동영상 조회 시 검은 화면이 나타날 수 있습니다.\
타겟 API 수준이 14(Ice Cream Sandwich, Android 4.0.1) 이상이면 하드웨어 가속화가 기본적으로 활성화되지만 앱 수준이나 활동 수준에서 명시적으로 이 기능을 활성화할 수도 있습니다.\
앱 전체에 대하여 하드웨어 가속화를 활성화하려면 Android 매니페스트 파일의 `<application>` 태그에 다음과 같은 속성을 추가하세요.
```
<application android:hardwareAccelerated="true" ...>
```

- IMA (구글 동영상 광고) 관련 설정\
IMA 광고를 처리하고자 하면 아래 권한이 추가 되어야 합니다.
```
<!-- Required permissions for the IMA SDK -->
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```

#### 3. 광고 파라미터 설정
각 광고 형태에 맞는 광고 요청시에는 광고 요청에 필요한 정보를 담은 `AdParam` 객체를 생성해야 합니다.\
세부적인 타겟팅 및 리포팅은 customParam 에 적용된 항목으로 적용됩니다.

##### 3-1. Parameter 설명
광고 요청시에 사용되는 `AdParam` 내의 parameter 들은 각각의 역할이 있습니다.

- Ad Unit ID (required)\
  필수적인 param 으로 광고 Unit ID 에 해당됩니다.

- Current Page URL (optional)\
  현재 화면을 표현하는 page 의 url 에 해당됩니다.\
  해당 값은 타겟팅 목적의 parameter 로 외부 demand 의 광고 요청시에 사용됩니다.

- Keyword (optional)\
  현재 화면과 관련된 keyword 값에 해당됩니다.\
  해당 keyword 값들은 IMA 에서 세부적인 타게팅 및 리포팅을 위해서 사용됩니다.\
  Set<String> 형태의 collection 을 이용해서 `setKeywords()` 에 할당하거나
  `addKeyword()` 를 사용해서 복수개의 keyword 를 할당할 수 있습니다.

- Custom Parameter (optional)\
  DFP 및 외부 demand 에서 사용되는 parameter 로 세부적인 타게팅 및 리포팅을 위해서 사용됩니다.

- 동영상 관련 파라미터 (required in video)\
  video 광고에서 필요한 메타 정보입니다.\
  관련된 예시는 동영상 광고 sector를 확인해 주세요.
  - vsi: 비디오 스케줄 아이디 (Video ad Schedule Id)\
    vri: 비디오 스케줄 요청 아이디 (Video ad schedule Request id)\
    vcl: 비디오 켄텐츠 길이 (Video Content Length)\
    vsd: 비디오 전/중/후 여부 (Video StartDelay)\

```
AdParam adParam = new AdParam.Builder()
    .setAdUnitId("AOS_VOD_END") // 광고 Unit ID
    .setCurrentPageUrl("https://www.naver.com")  // 해당 페이지에 대한 설명 link
    .addKeyword("lo:Y,dh:720") // GFP Admin 을 통한 타겟팅 설정 및 리포트 지원
    .addCustomParam("channel_id", "41312")
    .build();
```

##### 3-2. SDK 관련 공통 설정
광고 요청마다 설정하는 AdParam 외에, SDK 관련된 `SdkProperties` 통해 공통 설정을 할 수 있습니다.
- `addProviderOptions(GfpProviderOptions)`\
  광고제공자별로 해당 광고제공자에 대한 TestMode 설정을 할 수 있습니다.\
  (DfpProviderOptions, FanProviderOptions, UnityProviderOptions, NdaProviderOptions)\
  설정하지 않을 경우 각 광고제공자의 TestMode 는 Off 상태입니다.\
  일부 DSP의 경우 테스트 광고를 받기 위해서 TestMode 설정이 필요한 경우가 있습니다.\
  NdaProviderOptions의 경우 [AdMute](/doc/ad_mute.md)에서 사용하는 옵션이 있으므로, 관련 가이드 확인 부탁드립니다.
- 광고 요청 Timeout 관련\
광고 요청 Timeout 과 관련된 API 목록은 다음과 같습니다. 설정시 넘겨지는 long 값은 양수의 millis 값으로 설정해야만 합니다.\
기본적으로 60초가 설정되어 있습니다.
  
| 이름 | 설명 |
|:---|:---|
| bannerAdRequestTimeout(long) | `GfpBannerAdView` 를 통해서 호출하는 광고의 타임아웃값을 설정. |
| videoAdRequestTimeout(long) | `GfpVideoAdManager` 및 `GfpVideoAdScheduleManager` 를 통해서 호출하는 광고의 타임아웃값을 설정. |
| unifiedAdRequestTimeout(long) | `GfpAdLoader` 를 통해서 호출하는 광고의 타임아웃값을 설정. |
| rewardedAdRequestTimeout(long) | `GfpRewardedAdManager` 를 통해서 호출하는 광고의 타임아웃값을 설정. |
| interstitialAdRequestTimeout(long) | `GfpInterstitialAdManager` 를 통해서 호출하는 광고의 타임아웃값을 설정. |
  
- `logLevel(Log.LogLevel)`\
  GFP SDK 의 log level 을 설정할 수 있습니다. default 값은 `ASSERT` 레벨입니다.
```
GfpSdk.setSdkProperties(GfpSdk.getSdkProperties().buildUpon()
    .addProviderOptions(new DfpProviderOptions.Builder()
        .setTestMode(false).build())
    .addProviderOptions(new FanProviderOptions.Builder()
        .setTestMode(false).build())
    .addProviderOptions(new NdaProviderOptions.Builder()
        .setTheme(GfpTheme.SYSTEM).build())
    .addProviderOptions(new UnityProviderOptions.Builder()
        .setTestMode(false).build())
    .logLevel(GfpLogger.LogLevel.VERBOSE)
    .bannerAdRequestTimeout(60_000L)
    .unifiedAdRequestTimeout(60_000L)
    .videoAdRequestTimeout(60_000L)
    .interstitialAdRequestTimeout(60_000L)
    .rewardedAdRequestTimeout(60_000L)
.build());
```

##### 3-3. 유저(타겟팅) 관련
광고 요청마다 설정하는 AdParam 외에, 타겟팅 등에 사용되는 유저 관련 설정인 `UserProperties`를 통해 공통 설정을 할 수 있습니다.\
광고 타게팅에 사용되는 설정 목록은 다음과 같습니다.

| 속성 | 설명 |
|:---|:---|
| gender(GenderType) | 사용자의 성별값을 설정. |
| yob(Integer) | 사용자의 출생년도를 설정. (Integer) |
| country(String) | 사용자의 국가값을 설정. |
| language(String) | 사용자의 언어값을 설정. |
| id(String) | SDK 로그 추적을 위해 사용될 수 있는 매체쪽 유저 식별자 값을 설정. |

```
GfpSdk.setUserProperties(GfpSdk.getUserProperties().buildUpon()
    // .id() 광고 로그 추적을 위해서 사용될 수 있는 매체쪽 유저 식별자값
    .yob(1987)
    .country("KR")
    .language("KO")
    .gender(GenderType.MALE)
    .build());
```

#### 4. 각 광고 타입 별 설정
- [배너 광고](doc/banner.md)
- [네이티브 광고](doc/native_normal.md)
- [네이티브 심플 광고](doc/native_simple.md)
- [보상형 광고](doc/rewarded.md)
- [전면형 광고](doc/interstitial.md)

----

## License

NAM(Naver Ad Manager) SDK의 저작권은 네이버(주)에 있습니다.

```
NAM(Naver Ad Manager) SDK for Android
Copyright 2022-present NAVER Corp.
All rights reserved.
Unauthorized use, modification and redistribution of this software are strongly prohibited.
```
