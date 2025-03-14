# IronSource Mediation for NAM SDK for Android

## Supported ad formats

| Ad format     | Description |
|:--------------|:------------|
| Banner        | X           |
| Native        | X           |
| Native Simple | X           |
| Interstitial  | O           |
| Rewarded      | O           |

## Integration

```gradle
...
repositories {
    maven {
        url "https://android-sdk.is.com/"
    }
}

dependencies {
    implementation 'com.naver.gfpsdk.mediation:nam-ironsource:<latest-version>'  
}
```

# Changelog
## 8.4.0.2 (2025-03-14)
### Built and tested with
- GFP SDK version 8.2.3
- IRONSOURCE SDK version 8.4.0

## 8.4.0.1 (2025-01-16)
### Code Refactoring
* update the minimum required GFP SDK version

### Built and tested with
- GFP SDK version 8.2.4
- IRONSOURCE SDK version 8.4.0

## 8.4.0.0 (2024-11-08)

### Code Refactoring

* verified compatibility with IronSource SDK 8.4.0 

### Built and tested with
- NAM SDK version 8.0.0
- IRONSOURCE SDK version 8.4.0

## 8.2.1.0 (2024-11-07)

### Code Refactoring

* add global privacy policy(CCPA) setting for DSPs 
* verified compatibility with IronSource SDK 8.2.1 

### Built and tested with
- NAM SDK version 8.0.0
- IRONSOURCE SDK version 8.2.1