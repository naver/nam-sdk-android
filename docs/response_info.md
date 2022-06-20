# Response Info

For debugging and logging purpose, successfully loaded ads provide a `GfpResponseInfo` object.
This object contains information about the ad it loaded. Each ad format class has a property to get the response info. 
On banner ads for example, use the `responseInfo` property.

```java
GfpBannerAdView bannerAdView = new GfpBannerAdView(this, adParam);
bannerAdView.setAdListener(new BannerAdListener() {
    @Override
    public void onAdLoaded(GfpBannerAd ad) {
        GfpResponseInfo responseInfo = ad.getResponseInfo();
        Log.d("response info", responseInfo.toString());   
    }

    @Override
    public void onError(GfpBannerAd ad, GfpError error) {
        GfpResponseInfo responseInfo = ad.getResponseInfo();
        Log.d("response info", responseInfo.toString());   
    }
}
bannerAdView.loadAd();
```

복수개의 광고 타입의 로드를 지원하는 `GfpAdLoader` 를 통한 광고 요청시에는 각각의 광고 타입별 load event callback 메서드와\
`AdEventListener` 내 `onError(GfpError, GfpResponseInfo)` 메서드의 두번째 인자만을 통해서 광고 응답정보를 확인하실 수 있습니다.

```
GfpAdLoader.Builder builder = new GfpAdLoader.Builder(this, adParam)
    .withAdListener(new AdEventListener() {
        ...

        @Override
        public void onError(GfpError error, GfpResponseInfo responseInfo) {
            Log.d("on error response info", nativeSimpleAd.getResponseInfo();
        }
    })
    .withBannerAd(getBannerAdOptions(), bannerAd -> {
        ...
        Log.d("banner ad response info", nativeSimpleAd.getResponseInfo();
    })
    .withNativeAd(getNativeAdOptions(false), nativeAd -> {
        ...
        Log.d("native ad response info", nativeSimpleAd.getResponseInfo();
    })
    .withNativeSimpleAd(getNativeSimpleAdOptions(), nativeSimpleAd -> {
        ...
        Log.d("native simple ad response info", nativeSimpleAd.getResponseInfo();
    }).build();
```

## Response info properties 

Properties of the `GfpResponseInfo` object include: 

### requestId 
A unique identifier of the ad request. This can be used to identify the ad.

### adCallLatency


### totalLoadLatency

### adapterLoadLatency

### loadedAdapterName

### adapterResponses

### errors


##### `GfpResponseInfo` 객체의 메서드에는 다음이 포함됩니다.
  - getRequestId()\
Request ID 값은 광고 요청에 대한 고유한 식별자이고 이 값을 토대로 특정 광고 요청에 문제가 발생했을 때 원인을 파악하는데 도움이 되는 키값이 될 수 있습니다.

  - getAdCallLatency()\
SDK 내에서 광고 서버(GFP 서버)로 광고를 요청(이하 adcall)하고 응답받는데 걸린 시간(millis)입니다.\
광고 요청을 보내지 않았으면 0 값이 반환됩니다.

  - getTotalLoadLatency()\
adcall 요청시점 부터 렌더링 될 광고를 로드하는데 성공하는 시점까지 걸린 시간(millis)입니다.\
광고 요청을 보내지 않았으면 0 값이 반환됩니다.

  - getAdapterLoadLatency()\
로드하는데 성공한 Adapter 기준으로 adapter 레벨에서의 광고 요청부터 로드 성공 시점까지 걸린 시간(millis)입니다.\
광고 요청을 보내지 않았거나 로드하는데 성공한 adapter 가 없을 경우 0 값이 반환됩니다.

  - getLoadedAdapterName()\
로드하는데 성공한 Adapter 의 클래스명입니다.\
광고 로드를 성공했을 경우 DfpBannerAdapter, MoPubNativeAdapter, FanNativeAdapter 와 같은 클래스명을 반환합니다.

  - getAdapterResponses()\
광고 응답에 포함된 각 Adapter 의 메타데이터를 포함하는 `GfpAdapterResponseInfo` 목록을 반환하며,\
Mediation 의 Waterfall 구조 실행을 디버깅하는데 사용될 수 있습니다.

##### `GfpAdapterResponseInfo` 는 Waterfall 구조의 각 광고 Adapter 에 대해 다음 속성을 제공합니다.

| 속성 | 설명 |
|:---:|:---:|
| getAdapterName | 광고 네트워크를 식별하는 클래스 이름입니다. |
| getLoadLatency | adapter 내부에서 광고를 요청하고 로드하는데 걸린 시간(millis)입니다. adapter 내부에서 광고를 요청 하지 않았거나 광고 요청이 실패했을 경우 0 값이 반환됩니다. |
| getLoadErrorLatency | adapter 내부에서 광고를 요청하고 에러가 발생하는 시점까지 걸린 시간(millis)입니다. adapter 내부에서 광고를 요청하지 않았거나 광고 요청이 성공했을 경우 0 값이 반환됩니다. |
| getError | adapter 내부에서 발생한 오류입니다. `GfpError` 객체로 에러 정보를 반환하며 해당 객체에서 오류와 관련된 정보를 확인할 수 있습니다. 광고 요청이 성공했을 경우에는 null 값이 반환됩니다. |
