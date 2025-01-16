# AppLovin Mediation for NAM SDK for Android

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
    implementation 'com.naver.gfpsdk.mediation:nam-applovin:<latest-version>'  
}
```

# Changelog
## 12.6.1.1 (2025-01-16)
### Code Refactoring
* update the minimum required GFP SDK version

### Built and tested with
- GFP SDK version 8.2.4
- APPLOVIN SDK version 12.6.1

## 12.6.1.0 (2024-11-08)

### Code Refactoring

* verified compatibility with AppLovin SDK 12.6.1

### Built and tested with
- NAM SDK version 8.0.0
- APPLOVIN SDK version 12.6.1

## 12.6.0.0 (2024-11-07)

### Code Refactoring

* add global privacy policy(CCPA) setting for DSPs 
* verified compatibility with AppLovin SDK 12.6.0 

### Built and tested with
- NAM SDK version 8.0.0
- APPLOVIN SDK version 12.6.0