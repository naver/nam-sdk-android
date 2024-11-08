# Google Ad Manager Mediation for NAM SDK for Android

## Supported ad formats

| Ad format     | Description |
|:--------------|:------------|
| Banner        | O           |
| Native        | O           |
| Native Simple | X           |
| Interstitial  | O           |
| Rewarded      | O           |

## Integration

```gradle
...
dependencies {
    implementation 'com.naver.gfpsdk.mediation:nam-dfp:<latest-version>'  
}
```

## Additional code required

To enable Google Ad Manager Mediation Module, Google App ID should be set in `AndroidManifest.xml` file.

Add your Ad Manager app ID (identified in the Ad Manager UI) to your app's `AndroidManifest.xml` file. 
To do so, add a `<meta-data>` tag with `android:name="com.google.android.gms.ads.APPLICATION_ID"`. 
You can find your app ID in the Ad Manager UI. For `android:value`, insert your own Ad Manager app ID, surrounded by quotation marks.

```xml
<manifest>
    <application>
        <!-- Sample Ad Manager app ID: ca-app-pub-3940256099942544~3347511713 -->
        <meta-data
                android:name="com.google.android.gms.ads.APPLICATION_ID"
                android:value="ca-app-pub-xxxxxxxxxxxxxxxx~yyyyyyyyyy"/>
    </application>
</manifest>
```

# Changelog

## 23.3.0.0 (2024-11-07)

### Code Refactoring

* verified compatibility with DFP SDK 23.3.0 

### Built and tested with
- GFP SDK version 8.0.0
- DFP SDK version 23.3.0