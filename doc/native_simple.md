# Native Simple 광고
본 페이지는 Native Simple(이하 NS) 사용 가이드입니다.\
네이티브 심플 광고는 단순 이미지형의 네이티브 광고로써 네이티브 노말과는 다르게 복잡한 설정 및 추가 작업 없이 배너광고와 유사하게 네이티브 광고를 게재할 수 있게끔 합니다.

----

### [Step 1] Dependency 추가 및 초기화
네이티브 심플 광고는 NDA 모듈만 지원됩니다.\
[공통 통합 내용을 참고해 주세요](../README.md)

----

### [Step 2] 레이아웃 구성

네이티브 광고를 게재하기 위해서는 먼저 광고를 게재하려는 Activity 또는 Fragment 의 레이아웃에 네이티브 심플 광고가 삽입될 `GfpNativeSimpleAdView` 을 추가해야 합니다.

아래 예제에서는 ConstraintLayout ViewGroup 에 `native_simple_ad_view` 라는 id 로 네이티브 광고가 게재될 GfpNativeSimpleAdView 를 선언했습니다.

```
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <com.naver.gfpsdk.GfpNativeSimpleAdView
        android:id="@+id/native_simple_ad_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_alignParentBottom="true"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

----

### [Step 3] AdParam 과 GfpAdLoader 생성
네이티브 광고를 로드해주는 `GfpAdLoader` 를 생성합니다.\
그리고 광고 요청에 대한 Parameter 를 생성하고 `GfpAdLoader` 생성자에 할당하게 됩니다.

#### 3-1. 광고 요청 타임아웃 설정
GfpAdLoader 를 통해 네이티브 심플 광고 요청마다 타임아웃을 설정할 경우에는 아래와 같이 GfpAdLoader 의 Builder 설정시 withTimeoutMillis() 를 추가해야만 합니다.\
withTimeoutMillis() 는 밀리 초 단위이며, 설정하지 않을 경우 `GfpSdk.setSdkProperties()` 에서 전역적으로 설정된 값(기본값 : 60초)으로 설정됩니다.

```
adLoader = new GfpAdLoader.Builder(this, adParam)
    .withTimeoutMillis(30_000L)
    .build();
```

#### 3-2. GfpNativeSimpleAdOptions

네이티브 심플 광고에서는 아래와 같은 옵션들을 제공합니다.

- setAdChoicePlacement\
Native Simple 광고 표시될 `AD` badge 에 대한 위치를 설정합니다.\
미 설정시에는 우측 상단에 자동적으로 렌더링 되게 됩니다.\
AdMute에 대한 내용은 [AdMute](ad_mute.md) 를 참고하여 주시기 바랍니다.\
옵션 값은 `GfpNativeSimpleAdOptions` 의  ADCHOICES_* 변수 값을 확인해 주세요.

> GFP SDK 를 통해서 렌더링되는 네이티브 심플 광고는 실제 광고 영역 우상단에 'AD,X' 마크를 자동으로 렌더링 하게 됩니다.\
해당 마크는 AdMute icon 으로, 광고에 따라서 없을 수도 있고 'i', 'i,X' 마크 등이 렌더링 될 수 있습니다.

```
GfpNativeSimpleAdOptions adOptions = new GfpNativeSimpleAdOptions.Builder()
    .setAdChoicePlacement(GfpNativeSimpleAdOptions.ADCHOICES_BOTTOM_LEFT)
    .build();
```

----

### [Step 4] 광고 이벤트 수신
`GfpNativeSimpleAd.OnNativeSimpleAdLoadedListener` 를 통해 로드 이벤트를 수신할 수 있고, `AdEventListener` 를 통해 click, impression, handleClick, error, adMute 등에 대한 이벤트를 수신할 수 있습니다.

#### 4-1. 광고 로드 이벤트 수신 - GfpNativeSimpleAd.OnNativeSimpleAdLoadedListener
아래와 같이 GfpAdLoader 의 Builder 설정시 withNativeSimpleAd() 를 추가하여 네이티브 심플 광고를 로드 합니다.\
withNativeSimpleAd() 의 두번째 인자인 GfpNativeSimpleAd.OnNativeSimpleAdLoadedListener() 의 onNativeSimpleAdLoaded() 를 통해서\
성공적으로 네이티브 심플 광고가 로드됐을 경우에 대한 이벤트를 수신할 수 있습니다.\
이때 `onNativeSimpleAdLoaded()` 의 파라미터로 로드된 광고 정보를 담고 있는 `GfpNativeSimpleAd` 객체가 전달됩니다.\
로드가 완료되면 `GfpNativeSimpleAdView` 에 셋팅합니다.

```
adLoader = new GfpAdLoader.Builder(this, adParam)
    .withNativeSimpleAd(adOptions, new GfpNativeSimpleAd.OnNativeSimpleAdLoadedListener() {
        @Override
        public void onNativeSimpleAdLoaded(GfpNativeSimpleAd nativeSimpleAd) {
            nativeSimpleAdView.setNativeSimpleAd(nativeSimpleAd);
        }
    })
    .build();
```

#### 4-2. AdEventListener
광고 이벤트는 아래와 같이 withAdListener(AdEventListener) 를 설정함으로써 수신을 할 수 있습니다.

```
adLoader = new GfpAdLoader.Builder(this, adParam)
    .withAdListener(new AdEventListener() {
        @Override
        public void onAdClicked() {
            // 광고를 클릭했을 경우
        }

        @Override
        public void onAdImpression() {
            // 광고의 노출이 발생했을 경우
        }

        @Override
        public void onAdMuted() {
            // 광고의 숨기기가 발생했을 경우
        }

        @Override
        public boolean handleClick(String... urls) {
            // Click 처리를 override 했고 사용자가 광고를 클릭했을 경우
        }

        @Override
        public void onError(GfpError error) {
            // 광고 로드 또는 렌더링 시에 오류가 발생했을 경우
        }    
    })
    .build();
```

### [Step 5] 광고 로드
`GfpAdLoader` 에 대한 설정이 마무리 되었다면 광고를 로드할 수 있습니다.

다음은 Activity 의 onCreate() method 에서 광고를 로드하는 방법을 보여주는 예시입니다.

```
package ...

import ...

public class MainActivity extends AppCompatActivity {
    private GfpAdLoader adLoader;
    GfpNativeSimpleAdView nativeSimpleAdView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nativeSimpleAdView = findViewById(R.id.native_simple_ad_view);

        AdParam adParam = new AdParam.Builder()
                        .setAdUnitId("광고_UNIT_ID")
                        .addCustomParam("testKey", "testValue")
                        .build();

        GfpNativeSimpleAdOptions adOptions = new GfpNativeSimpleAdOptions.Builder()
            .setAdChoicePlacement(GfpNativeSimpleAdOptions.ADCHOICES_BOTTOM_LEFT)
            .build();

        adLoader = new GfpAdLoader.Builder(this, adParam)
            .withAdListener(new AdEventListener() {
                @Override
                public void onAdClicked() {
                    Log.d("MainActivity", "클릭 발생");
                }

                @Override
                public void onAdImpression() {
                    Log.d("MainActivity", "노출 성공");
                }

                @Override
                public void onAdMuted() {
                    Log.d("MainActivity", "광고 숨기기 성공");
                }

                public void onError(GfpError error, GfpResponseInfo responseInfo) {
                    String errorString = String.format(Locale.getDefault(),
                            "code[%d] subCode[%s] message[%s] responseInfo[%s]",
                            error.getErrorCode(),
                            error.getErrorSubCode(),
                            error.getErrorMessage(),
                            responseInfo.toString()
                    );
                    Log.e("MainActivity", "에러 발생 " + errorString);
                }
            })
            .withNativeSimpleAd(adOptions, new GfpNativeSimpleAd.OnNativeSimpleAdLoadedListener() {
                @Override
                public void onNativeSimpleAdLoaded(GfpNativeSimpleAd nativeSimpleAd) {
                    nativeSimpleAdView.setNativeSimpleAd(nativeSimpleAd);
                }
            })
            .build();

        adLoader.loadAd();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (adLoader != null) {
            adLoader.cancel();
        }
    }
}
```

> 단일 GfpAdLoader 를 통해서 광고를 로드할 경우 한 번에 하나씩만 요청해야 합니다.\
GfpAdLoader 를 다시 사용하는 경우 각 요청이 완료된 후에 loadAd() 를 다시 호출해 다음 요청을 시작해야 합니다.\
만약 요청이 완료되기 전 loadAd() 를 다시 호출할 경우에는 이전 로드가 취소되거나 이미 로드된 광고가 삭제될 수 있습니다.

### [Step 6] 광고 삭제
네이티브 광고의 게재가 끝나면 광고가 올바르게 폐기되도록 광고를 삭제해야 합니다.\
광고 삭제는 아래와 같이 `GfpAdLoader` 가 제공하는 `cancel()` 을 호출하면 됩니다.

```
adLoader.cancel();
```
