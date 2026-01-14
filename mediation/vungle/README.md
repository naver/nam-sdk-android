# Vungle Mediation for NAM SDK for Android

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
dependencies {
    implementation 'com.naver.gfpsdk.mediation:nam-vungle:<latest-version>'  
}
```

# Changelog
## 7.6.1.0 (2026-01-14)


### Bug Fixes

* **vungle:** support non bidding ad request


### Code Refactoring

* unify error message format when getting signal
* verified compatibility with Vungle SDK 7.6.1

### Built and tested with
- NAM SDK version 8.11.1
- VUNGLE SDK version 7.6.1

## 7.5.1.0 (2025-09-30)
### Code Refactoring
* add ProductType when creating a bid token
* use SignalData for bidding token requests in RTB mediation networks
* verified compatibility with Vungle SDK 7.5.1
* **vungle:** provide media aspect ratio and icon asset drawable

### Built and tested with
- NAM SDK version 8.9.0
- VUNGLE SDK version 7.5.1

## 7.4.1.3 (2025-08-08)

### Features (Experimental)
* support Ad Inspector

### Code Refactoring

* **vungle:** support Banner and Native ad of Vungle ([2ce5d06](https://oss.navercorp.com/da-ssp-app-sdk/naver_sdk_aos/commit/2ce5d067d6e0efddd0dbb53e9d95f0cec56717de))

### Built and tested with
- NAM SDK version 8.7.0
- VUNGLE SDK version 7.4.1

## 7.4.1.2 (2025-05-23)
### Code Refactoring
* remove unnecessary internal method

### Built and tested with
- NAM SDK version 8.4.3
- VUNGLE SDK version 7.4.1

## 7.4.1.1 (2025-01-16)
### Code Refactoring
* update the minimum required NAM SDK version

### Built and tested with
- NAM SDK version 8.2.4
- VUNGLE SDK version 7.4.1

## 7.4.1.0 (2024-11-08)

### Code Refactoring

* verified compatibility with Vungle SDK 7.4.1 

### Built and tested with
- NAM SDK version 8.0.0
- VUNGLE SDK version 7.4.1

## 7.4.0.0 (2024-11-07)

### Code Refactoring

* add global privacy policy(CCPA) setting for DSPs 
* verified compatibility with Vungle SDK 7.4.0 

### Built and tested with
- NAM SDK version 8.0.0
- VUNGLE SDK version 7.4.0