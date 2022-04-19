> NAM SDK는 Content Provider 를 활용한 자동 초기화를 지원합니다.\
이하 내용은 앱 구동 초기에 자동 초기화를 원하지 않는 경우에 대한 가이드입니다.

## 자동 초기화 Off 설정
광고를 로드하기 전, NAM SDK 초기화가 반드시 필요합니다.\
초기화 과정을 통해 모바일 광고 SDK 또는 Mediation 파트너 SDK 의 초기화를 미리 수행하게 됩니다.\
원활한 파트너 SDK의 초기화를 위해 **`Application` 레벨에서의 초기화 호출**을 권장합니다.\
또한, 이 작업은 앱 실행시 한번만 처리하면 됩니다.

기본적으로 NAM SDK는 자동 초기화를 수행하게 됩니다. 수동으로 초기화 하기 위해 Manifest 파일에 아래 설정이 추가 되어야 합니다.
```
<!-- 기본 값은 true = 자동 초기화 -->
<meta-data
    android:name="com.naver.gfpsdk.AUTO_INIT"
    android:value="false" />
```

## 초기화 수행
`GfpSdk` 를 통해 초기화 여부와 초기화를 진행 할 수 있습니다.
또한 `GfpSdk.InitializationCallback`를 통해서 초기화 수행 이후 상태 값을 알 수 있습니다.

```
public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...
        if (!GfpSdk.isInitialized()) {
            GfpSdk.initialize(getApplicationContext(),
                new GfpSdk.InitializationCallback() {
                    @Override
                    public void onInitializationComplete(@NonNull GfpSdk.InitializationResult initializationResult) {
                        // 성공 여부: initializationResult.getSuccess()
                        // (참고용) 성공/실패시 내부 메시지: initializationResult.getMessage()
                    }
            });
        }
    }
}
```

> 광고 요청 전에 호출 했을 때, 초기화에 대한 콜백인 `GfpSdk.InitializationCallback#onInitializationComplete()` 내\
`GfpSdk.InitializationResult` 에서 조회할 수 있는 `getSuccess()` 값이 false 를 return 하더라도 광고를 요청하는데 이슈가
발생하지 않습니다.\
하지만 효과적인 광고 요청을 위해 정확한 Publisher code 값과 Ad Unit ID 가 제공 되어야 합니다.
