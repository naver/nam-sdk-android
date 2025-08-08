# Chartboost Mediation for NAM SDK for Android

## Supported ad formats

| Ad format     | Description |
|:--------------|:------------|
| Banner        | O           |
| Native        | X           |
| Native Simple | X           |
| Interstitial  | O           |
| Rewarded      | O           |

## Integration

```gradle
...
dependencies {
    implementation 'com.naver.gfpsdk.mediation:nam-chartboost:<latest-version>'  
}
```

# Changelog
## 9.7.0.2 (2025-08-08)

### Features (Experimental)
* support Ad Inspector

### Built and tested with
- GFP SDK version 8.7.0
- CHARTBOOST SDK version 9.7.0

## 9.7.0.1 (2025-05-23)
### Code Refactoring
* remove unnecessary internal method

### Built and tested with
- NAM SDK version 8.4.3
- CHARTBOOST SDK version 9.7.0

## 9.7.0.0 (2025-03-14)
### Code Refactoring

* verified compatibility with Chartboost SDK 9.7.0

### Built and tested with
- GFP SDK version 8.3.0
- CHARTBOOST SDK version 9.7.0