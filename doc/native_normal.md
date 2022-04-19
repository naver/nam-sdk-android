# Native Normal 광고

본 페이지는 Native Normal(이하 NN) 사용 가이드입니다.\
네이티브 광고는 media view 설정에 따라 조금씩 부르는 용어가 다르기도 합니다.
- Native (media view 가 이미지)
- OutStream Native (media view 가 동영상)
- Native Banner (media view 가 없음)

----
### [Step 1] Dependency 추가 및 초기화
[공통 통합 내용을 참고해 주세요](../README.md)

----

### [Step 2] 레이아웃 구성
네이티브 광고를 게재하기 위해서는 먼저 광고를 게재하려는 `Activity` 또는 `Fragment` 의 레이아웃에 네이티브 광고가 삽입될 `ViewGroup` 을 추가해야 합니다.\
아래 예제에서는 RelativeLayout ViewGroup 에 `native_container` 라는 id 로 네이티브 광고가 게재될 ViewGroup 을 선언했습니다.

```
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

        <RelativeLayout
            android:id="@+id/native_container"
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

#### [Step 3] GfpNativeAdView 레이아웃 구성
네이티브 광고가 게재될 `ViewGroup` 이 포함된 레이아웃을 구성했다면 이어서 네이티브 광고가 렌더링 될 레이아웃을 구성해야 합니다.\
레이아웃을 구성시에 주의해야 할 사항은 아래와 같습니다.

- 최상단의 레이아웃은 `com.naver.gfpsdk.GfpNativeAdView` 가 되어야 합니다.
- `GfpNativeAdView` 하위에는 `Asset` 들을 담을 `ViewGroup` 이 할당되어야 합니다. 아래 예제 에서는 assets_container 라는 id 로 선언했습니다.
  - 해당 ViewGroup 은 이후에 렌더링을 시도하게 되는 광고 제공자의 필수 layout 의 child 로 포함되게 됨으로 필수로 할당되어야 합니다.
- `AdChoicesView` 가 렌더링 되는 `GfpAdChoicesView` 를 할당해야 합니다.
  - FAN, MoPub, S2S 광고의 `AdChoicesView` 가 해당 영역에 렌더링 됩니다.
  - Asset 들은 container 인 ViewGroup 하위에 할당되어야 합니다.
  - `width`, `height` 값은 `20dp` 이상이어야 하고 권장 사이즈는 `width` 값 `20dp`, `height` 값 `20dp` 입니다.
  - 광고 표준(IAB) 스펙으로 **네이티브 광고는 반드시 표시해야 합니다.**\
  [참고 - 구글 안내](https://support.google.com/admanager/answer/2695279?hl=en)
- icon 이 아닌 메인으로 사용될 이미지나 동영상이 그려질 영역은 `GfpMediaView` 로 선언해야만 합니다.

위의 사항들이 고려된 예제는 아래와 같습니다.

아래에서 생성한 xml 파일의 이름은 `content_native_ad.xml` 이라고 가정합니다.

```
<?xml version="1.0" encoding="utf-8"?>
<com.naver.gfpsdk.GfpNativeAdView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/gfp_native_ad"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <FrameLayout
        android:id="@+id/assets_container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:background="#FFFFFF"
            android:minHeight="50dp"
            android:orientation="vertical">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                android:padding="10dp">

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginBottom="12dp"
                    android:orientation="horizontal">

                    <ImageView
                        android:id="@+id/ad_app_icon"
                        android:layout_width="46dp"
                        android:layout_height="46dp"
                        android:layout_marginRight="10dp"
                        android:adjustViewBounds="true" />

                    <LinearLayout
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center_vertical"
                        android:layout_weight="1"
                        android:orientation="vertical">

                        <TextView
                            android:id="@+id/ad_headline"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:layout_marginBottom="4dp"
                            android:ellipsize="end"
                            android:lines="1"
                            android:textColor="#000000"
                            android:textSize="12dp" />

                        <TextView
                            android:id="@+id/ad_sponsored"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:text="@string/ad_attribution"
                            android:textColor="#999999"
                            android:textSize="12dp" />

                    </LinearLayout>

                    <com.naver.gfpsdk.GfpAdChoicesView
                        android:id="@+id/ad_choices_view"
                        android:layout_width="20dp"
                        android:layout_height="20dp"
                        android:layout_gravity="center_vertical"
                        android:layout_marginLeft="10dp" />

                </LinearLayout>

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="vertical">

                    <TextView
                        android:id="@+id/ad_body"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:layout_marginBottom="12dp"
                        android:ellipsize="end"
                        android:maxLines="2"
                        android:textColor="#000000"
                        android:textSize="15dp" />

                    <!--
                        if there is no media view
                            new GfpNativeAdOptions.Builder().setHasMediaView(false)
                        this should be deleted
                    -->
                    <com.naver.gfpsdk.GfpMediaView
                        android:id="@+id/ad_media"
                        android:layout_width="match_parent"
                        android:layout_height="220dp"
                        android:layout_marginBottom="12dp"
                        android:adjustViewBounds="true" />

                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:orientation="horizontal">

                        <LinearLayout
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_weight="3"
                            android:orientation="vertical">

                            <TextView
                                android:id="@+id/ad_advertiser"
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:ellipsize="end"
                                android:lines="1"
                                android:textColor="@android:color/darker_gray"
                                android:textSize="13dp" />

                            <TextView
                                android:id="@+id/ad_social_context"
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:ellipsize="end"
                                android:gravity="center_vertical"
                                android:lines="1"
                                android:textColor="@android:color/black"
                                android:textSize="13dp" />

                        </LinearLayout>

                        <Button
                            android:id="@+id/ad_call_to_action"
                            android:layout_width="100dp"
                            android:layout_height="40dp"
                            android:layout_gravity="center_vertical"
                            android:layout_weight="1"
                            android:background="@color/colorPrimary"
                            android:paddingLeft="3dp"
                            android:paddingRight="3dp"
                            android:textColor="#ffffff"
                            android:textSize="13dp" />

                    </LinearLayout>

                </LinearLayout>
            </LinearLayout>
        </LinearLayout>

    </FrameLayout>
</com.naver.gfpsdk.GfpNativeAdView>
```

----

### [Step 4] AdParam 과 GfpAdLoader 생성
네이티브 광고를 로드해주는 `GfpAdLoader` 를 생성해야 합니다.\
그리고 광고 요청에 대한 Parameter 를 생성하고 `GfpAdLoader` 생성자에 할당하게 됩니다.

샘플을 보기 전에 네이티브 광고에 사용되는 옵션과 리스너를 먼저 확인하겠습니다.


#### 4-1. 광고 요청 타임아웃 설정
GfpAdLoader 를 통해 네이티브 광고 요청마다 타임아웃을 설정할 경우에는 아래와 같이 GfpAdLoader 의 Builder 설정시 withTimeoutMillis() 를 추가해야만 합니다.\
withTimeoutMillis() 는 밀리 초 단위이며, 설정하지 않을 경우 `GfpSdk.setSdkProperties()` 에서 전역적으로 설정된 값(기본값 : 60초)으로 설정됩니다.

```
adLoader = new GfpAdLoader.Builder(this, adParam)
    .withTimeoutMillis(30_000L)
    ...
    .build();
```

#### 4-2. GfpNativeAdOptions
DFP 네이티브 광고의 adChoicesView 위치를 변경하거나\
MediaView 를 가지지 않는 네이티브 광고를 요청하고 싶을 경우 사용합니다.\
네이티브 광고 옵션 값은 GfpAdLoader 생성시 withNativeAd() 를 통해 전달합니다.

- setAdChoicePlacement(int)\
DFP 네이티브 광고(**만**)의 adChoicesView 위치를 변경하는데 사용됩니다.\
DFP 네이티브 광고의 AdChoicesView 는 렌더링을 DFP 내부적으로 처리하게 됩니다.\
따라서 네이티브 광고 레이아웃의 GfpAdChoicesView 위치와 관계 없이 렌더링이 되는데,\
이 때 AdChoicesView 의 위치를 지정하고 싶다면 설정가능한 값은 4가지가 있습니다.
  - ADCHOICES_TOP_LEFT
  - ADCHOICES_TOP_RIGHT
  - ADCHOICES_BOTTOM_RIGHT
  - ADCHOICES_BOTTOM_LEFT

> InMobi 네이티브 광고는 AdChoicesView 의 렌더링 뿐만 아니라 렌더링 될 위치를 InMobi SDK 내부적으로 처리하게 됩니다.\
따라서 네이티브 광고 레이아웃의 GfpAdChoicesView 위치 그리고 GfpNativeAdOptions 에 설정되는 adChoicesPlacement 값에 **관계없이 렌더링 됩니다.**

- setHasMediaView(boolean)\
default 값은 true 이고, false 로 할당할 경우 MediaView 를 가지지 않는 네이티브 광고를 요청 후 렌더링 할 수 있습니다.\
그리고 해당 method 를 false 로 할당할 경우에는 아래와 같은 제약사항이 있습니다.
  - 현재 GFP SDK 기준으로 MediaView 를 가지지 않는 네이티브 광고는 `InMobi` ***를 제외한***\
 `DFP`, `FAN` 모듈 에서만 제공됩니다.
  - FAN 모듈을 사용한다면 광고 unit 을 생성할 시에 네이티브 광고의 타입을 `NativeBannerAd` 로 설정해야 합니다. (아래 샘플 참고)
  - `GfpNativeAdView` 가 포함된 레이아웃을 생성할 때 mediaView 를 제거해야 합니다. (위 레이아웃의 `ad_media` id 참고)

----

### [Step 5] 광고 이벤트 수신
`GfpNativeAd.OnNativeAdLoadedListener` 를 통해 로드 이벤트를 수신할 수 있고,\
`AdEventListener` 를 통해 click, impression, error 등에 대한 이벤트를 수신할 수 있습니다.

#### 5-1. 광고 로드 이벤트 수신 - GfpNativeAd.OnNativeAdLoadedListener
네이티브 광고를 로드하고자 할 때 아래와 같이 GfpAdLoader 의 Builder 설정에 withNativeAd() 를 추가하게 됩니다.\
withNativeAd() 의 두번째 인자인 GfpNativeAd.OnNativeAdLoadedListener() 의 onNativeAdLoaded() 를 통해서 광고가 로드 됐을 경우에 대한 이벤트를 수신할 수 있는데,\
이때 `onNativeAdLoaded()` 의 파라미터로 로드된 광고 정보를 담고 있는 `GfpNativeAd` 객체가 전달됩니다.\
로드가 완료된 이후 렌더링이 필요하므로 해당 리스너에서 `GfpNativeAdView`를 생성하고 `native_container`에 inflate 해야 합니다.

```
adLoader = new GfpAdLoader.Builder(this, adParam)
    .withNativeAd(nativeAdOptions, new GfpNativeAd.OnNativeAdLoadedListener() {
        @Override
        public void onNativeAdLoaded(GfpNativeAd nativeAd) {
            // 광고 로드에 성공했을 경우
            inflateNativeAd(nativeAd);
        }
    })
    .build();
```

#### 5-2. AdEventListener
`GfpAdLoader.Builder#withAdListener(AdEventListener)` 를 설정함으로써 click, impression, handleClick, error 등에 대한 이벤트를 수신 할 수 있습니다.

```
adLoader = new GfpAdLoader.Builder(this, adParam)
    ...
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
        public boolean handleClick(String... urls) {
            // Click 처리를 override 했고 사용자가 광고를 클릭했을 경우
        }

        @Override
        public void onError(GfpError error) {
            // 광고 로드 또는 렌더링 시에 오류가 발생했을 경우
        }    
    })
    ...
    .build();
```

----

### [Step 6] 광고 로드
`GfpAdLoader` 에 대한 설정이 마무리 되었다면 광고를 로드할 수 있습니다.

다음은 Activity 의 onCreate() method 에서 광고를 로드하는 방법을 보여주는 예시입니다.

```
package ...

import ...

public class MainActivity extends AppCompatActivity {
    private RelativeLayout nativeContainer;
    private GfpAdLoader adLoader;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nativeContainer = findViewById(R.id.native_container);

        AdParam adParam = new AdParam.Builder()
                        .setAdUnitId("NS_AOS_MIX_NATIVE_TEST")
                        .addCustomParam("testKey", "testValue")
                        .build();

        GfpNativeAdOptions nativeAdOptions = new GfpNativeAdOptions.Builder()
            .setHasMediaView(true)
            .build();

        adLoader = new GfpAdLoader.Builder(this, adParam)
            .withTimeoutMillis(60_000L)
            .withAdListener(new AdEventListener() {
                @Override
                public void onAdClicked() {
                    Log.d("MainActivity", "클릭 발생");
                }

                @Override
                public void onAdImpression() {
                    Log.d("MainActivity", "노출 성공");
                }

                public void onError(GfpError error, GfpResponseInfo responseInfo) {
                    String errorString = String.format("code[%d] subCode[%s] message[%s] responseInfo[%s]",
                            error.getErrorCode(),
                            error.getErrorSubCode(),
                            error.getErrorMessage(),
                            responseInfo.toString()
                    );
                    Log.e("MainActivity", errorString);
                }
            })
            .withNativeAd(nativeAdOptions, new GfpNativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(GfpNativeAd nativeAd) {
                    inflateAd(nativeAd);
                }
            })
            .build();

        adLoader.loadAd();
    }

    private void inflateAd(GfpNativeAd nativeAd) {
        // native 광고 렌더링
    }
}
```

> 단일 GfpAdLoader 를 통해서 광고를 로드할 경우 한 번에 하나씩만 요청해야 합니다.\
GfpAdLoader 를 다시 사용하는 경우 각 요청이 완료된 후에 `loadAd()` 를 다시 호출해 다음 요청을 시작해야 합니다.\
만약 요청이 완료되기 전 `loadAd()` 를 다시 호출할 경우에는 이전 로드가 취소되거나 이미 로드된 광고가 삭제될 수 있습니다.

----

### [Step 7] 광고 렌더링
광고를 성공적으로 로드했을 때 전달받게 되는 `GfpNativeAd` 객체에서 획득할 수 있는 `icon, title, body, callToAction, advertiser, ...` 정보들의
getter method 를 사용하여 asset 들의 meta 정보들을 알 수 있습니다.

> `GfpNativeAd` 객체의 `getOriginalNativeAd()` 를 사용하여 광고 제공자가 제공하는 광고 객체를 통해서도 meta 정보들을 얻을 수 있지만 이 방법은 권장하지 않습니다.  

렌더링 시에 주의해야 할 내용은 아래와 같습니다.

- `OnNativeAdLoadedListener#onNativeAdLoaded()` 콜백시에 전달받는 `GfpNativeAd` 객체를 사용하여 렌더링합니다.
- `GfpNativeAd` 객체에서 `icon, title, body, callToAction, advertiser, socialContext` 의 meta 정보들을 얻을 수 있습니다.
- `GfpNativeAdView` 객체에 `assetsContainer` 와 `adChoicesView` 를 반드시 set 해줘야 합니다.
  - set 을 해주지 않을 경우 exception 이 발생합니다.
- `GfpNativeAdView` 객체에 icon, title, body, callToAction, advertiser, socialContext 에 대한 assetView 들을 set 해줘야 합니다.
  - set 을 해주지 않을 경우 click event 가 발생하지 않을 수 있습니다.
- `GfpNativeAdView` 객체에 `setNativeAd()` 를 꼭 호출해야만 합니다.
  - 해당 작업을 하지 않을 경우 광고 제공자들이 강제하는 레이아웃을 추가해주는 작업과 재사용시에\
  이전 광고객체에 대한 clear 작업이 이뤄지지 않으므로 동작에 문제가 생길 수 있습니다.

위의 사항들이 고려된 예제는 아래와 같습니다.

```
    private void inflateAd(GfpNativeAd nativeAd) {
        nativeContainer.removeAllViews();
        GfpNativeAdView adView = (GfpNativeAdView) getLayoutInflater()
                         .inflate(R.layout.content_native_ad, nativeContainer, false);
        nativeContainer.addView(nativeAdView);

        FrameLayout assetsContainer = nativeAdView.findViewById(R.id.assets_container);
        GfpMediaView mediaView = nativeAdView.findViewById(R.id.ad_media);
        GfpAdChoicesView adChoicesView = nativeAdView.findViewById(R.id.ad_choices_view);
        ImageView iconView = nativeAdView.findViewById(R.id.ad_app_icon);
        TextView titleView = nativeAdView.findViewById(R.id.ad_headline);
        TextView bodyView = nativeAdView.findViewById(R.id.ad_body);
        TextView advertiserView = nativeAdView.findViewById(R.id.ad_advertiser);
        TextView socialContextView = nativeAdView.findViewById(R.id.ad_social_context);
        Button callToActionButton = nativeAdView.findViewById(R.id.ad_call_to_action);

        nativeAdView.setAssetsContainer(assetsContainer);
        nativeAdView.setMediaView(mediaView);
        nativeAdView.setAdChoicesView(adChoicesView);
        nativeAdView.setIconView(iconView);
        nativeAdView.setTitleView(titleView);
        nativeAdView.setBodyView(bodyView);
        nativeAdView.setAdvertiserView(advertiserView);
        nativeAdView.setSocialContextView(socialContextView);
        nativeAdView.setCallToActionView(callToActionButton);

        titleView.setText(nativeAd.getTitle());
        bodyView.setText(nativeAd.getBody());
        callToActionButton.setText(nativeAd.getCallToAction());
        advertiserView.setText(nativeAd.getAdvertiserName());

        Image icon = nativeAd.getIcon();
        if (icon != null) {
            iconView.setImageDrawable(icon.getDrawable());
            iconView.setVisibility(View.VISIBLE);
        } else {
            iconView.setVisibility(View.GONE);
        }

        if (nativeAd.getSocialContext() != null) {
            socialContextView.setText(nativeAd.getSocialContext());
            socialContextView.setVisibility(View.VISIBLE);
        } else {
            socialContextView.setVisibility(View.GONE);
        }

        nativeAdView.setNativeAd(nativeAd);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (adLoader != null) {
            adLoader.cancel();
        }
    }
```

----

### [Step 8] 광고 삭제
네이티브 광고의 게재가 끝나면 광고가 올바르게 폐기되도록 광고를 삭제해야 합니다.\
광고 삭제는 아래와 같이 `GfpAdLoader` 가 제공하는 `cancel()` 을 호출하면 됩니다.

```
adLoader.cancel();
```

### 부록) 각 DSP 별 GFP SDK 가 지원하는 Assets
#### 1. FAN Native 광고에 대해서 GFP SDK 가 지원하는 Assets
| Asset | 지원 여부 | 비고 |
|:-----:|:------:|:----:|
| title | O | |
| image | O | Native Banner 로 렌더링할 때는 사용되지 않음 <br>uri, width, height 값을 전달 |
| body | O | |
| icon | O | uri, width, height 값을 전달 |
| call to action | O | |
| advertiser name | O | FAN SDK 4.99.0 이상 버전에서부터 추가된 항목 |
| social context | O | |

### 2. DFP Native 광고에 대해서 GFP SDK 가 지원하는 Assets
 | Asset | 지원 여부 | 비고 |
 |:-----:|:------:|:----:|
 | title | O | |
 | image | O | drawable, uri, scale, width, height 값을 전달 |
 | body | O | |
 | icon | O | drawable, uri, scale, width, height 값을 전달 |
 | call to action | O | |
 | advertiser name | O |  |
 | social context | X | |

### 3. InMobi Native 광고에 대해서 GFP SDK 가 지원하는 Assets
| Asset | 지원 여부 | 비고 |
|:-----:|:------:|:----:|
| title | O | |
| image | O | InMobi 는 네이티브 광고(동영상, 이미지) 에서 이미지 asset 을 제공하지 않음 |
| body | O | |
| icon | O | drawable, uri 값 전달 |
| call to action | O | |
| advertiser name | X |  |
| social context | X | |
