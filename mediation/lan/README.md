# Line Mediation for NAM SDK for Android

## Supported ad formats

| Ad format     | Description |
|:--------------|:------------|
| Banner        | X           |
| Native        | O           |
| Native Simple | O           |
| Interstitial  | O           |
| Rewarded      | O           |

## Integration

```gradle
...
dependencies {
    implementation 'com.naver.gfpsdk.mediation:nam-lan:<latest-version>'  
}
```

# Changelog
## 2.9.20250110.4 (2025-12-15)


### Code Refactoring

* verified compatibility with mediation network modules

### Built and tested with
- NAM SDK version 8.11.0
- LAN SDK version 2.9.20250110

## 2.9.20250110.3 (2025-10-30)
### Code Refactoring
* Refactor the NativeSimpleAd to support new rich media ad

### Built and tested with
- NAM SDK version 8.10.0
- LAN SDK version 2.9.20250110

## 2.9.20250110.2 (2025-09-30)
### Code Refactoring
* add ProductType when creating a bid token

### Built and tested with
- NAM SDK version 8.9.0
- LAN SDK version 2.9.20250110

## 2.9.20250110.1 (2025-08-08)

### Features (Experimental)
* support Ad Inspector

### Built and tested with
- NAM SDK version 8.7.0
- LAN SDK version 2.9.20250110

## 2.9.20250110.0 (2025-06-10)


### Code Refactoring

* verified compatibility with LAN SDK v2.9.20250110

### Built and tested with
- NAM SDK version 8.4.3
- LAN SDK version 2.9.20250110

## 2.9.20241129.3 (2025-05-23)
### Code Refactoring
* remove unnecessary internal method

### Built and tested with
- NAM SDK version 8.4.3
- LAN SDK version 2.9.20241129

## 2.9.20241129.2 (2025-04-28)


### Code Refactoring

* adjust rendered impressions fire timing

### Built and tested with
- NAM SDK version 8.3.0
- LAN SDK version 2.9.20241129

## 2.9.20241129.1 (2025-03-14)
### Bug Fixes
* prevent adRenderedImpression firing multiple times on NS adapter

### Built and tested with
- NAM SDK version 8.3.0
- LAN SDK version 2.9.20241129

## 2.9.20241129.0 (2025-01-22)
### Code Refactoring
* update the minimum required NAM SDK version
* verified compatibility with LAN SDK 2.9.20241129

### Built and tested with
- NAM SDK version 8.2.5
- LAN SDK version 2.9.20241129

## 2.7.20240214.1 (2025-01-16)
### Code Refactoring
* update the minimum required NAM SDK version

### Built and tested with
- NAM SDK version 8.2.4
- LAN SDK version 2.7.20240214

## 2.7.20240214.0 (2024-11-07)

### Code Refactoring

* verified compatibility with LAN SDK 2.7.20240214

### Built and tested with
- NAM SDK version 8.0.0
- LAN SDK version 2.7.20240214