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
## 6.18.0.3 (2025-03-14)
### Bug Fixes
* height of FAN media view was miscalculated on ConstraintLayout

### Built and tested with
- GFP SDK version 8.2.5
- FAN SDK version 6.18.0

## 6.18.0.2 (2025-01-22)
### Code Refactoring
* update the minimum required NAM SDK version

### Built and tested with
- NAM SDK version 8.2.5
- FAN SDK version 6.18.0

## 6.18.0.1 (2025-01-16)
### Code Refactoring
* update the minimum required GFP SDK version

### Built and tested with
- GFP SDK version 8.2.4
- FAN SDK version 6.18.0

## 6.18.0.0 (2024-11-08)

### Code Refactoring

* verified compatibility with FAN SDK 6.18.0 

### Built and tested with
- NAM SDK version 8.0.0
- FAN SDK version 6.18.0

## 6.17.0.0 (2024-11-07)

### Code Refactoring

* add global privacy policy(CCPA) setting for DSPs
* verified compatibility with FAN SDK 6.17.0

### Built and tested with
- NAM SDK version 8.0.0
- FAN SDK version 6.17.0