# Meta Audience Network Mediation for NAM SDK for Android

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
    implementation 'com.naver.gfpsdk.mediation:nam-fan:<latest-version>'  
}
```

## Additional code required

Follow Meta Audience Network's Android [network security config guide](https://developers.facebook.com/docs/audience-network/android-network-security-config) to modify your network security config file to support media caching.

# Changelog

## 6.18.0.0 (2024-11-08)

### Code Refactoring

* verified compatibility with FAN SDK 6.18.0 

### Built and tested with
- GFP SDK version 8.0.0
- FAN SDK version 6.18.0

## 6.17.0.0 (2024-11-07)

### Code Refactoring

* add global privacy policy(CCPA) setting for DSPs
* verified compatibility with FAN SDK 6.17.0

### Built and tested with
- GFP SDK version 8.0.0
- FAN SDK version 6.17.0