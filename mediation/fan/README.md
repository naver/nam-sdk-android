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

# Changelog
## 6.21.0.1 (2026-02-02)


### Code Refactoring

* support GPP specs

### Built and tested with
- NAM SDK version 8.12.0
- FAN SDK version 6.21.0

## 6.21.0.0 (2026-01-14)


### Code Refactoring

* unify error message format when getting signal
* verified compatibility with META SDK 6.21.0

### Built and tested with
- NAM SDK version 8.11.1
- FAN SDK version 6.21.0

## 6.20.0.1 (2025-12-15)


### Code Refactoring

* verified compatibility with mediation network modules

### Built and tested with
- NAM SDK version 8.11.0
- FAN SDK version 6.20.0

## 6.20.0.0 (2025-09-30)
### Code Refactoring
* add ProductType when creating a bid token
* use SignalData for bidding token requests in RTB mediation networks
* verified compatibility with META SDK 6.20.0

### Built and tested with
- NAM SDK version 8.9.0
- FAN SDK version 6.20.0

## 6.18.0.5 (2025-08-08)

### Features (Experimental)
* support Ad Inspector

### Built and tested with
- NAM SDK version 8.7.0
- FAN SDK version 6.18.0

## 6.18.0.4 (2025-05-23)
### Code Refactoring
* remove unnecessary internal method

### Built and tested with
- NAM SDK version 8.4.3
- FAN SDK version 6.18.0

## 6.18.0.3 (2025-03-14)
### Bug Fixes
* height of FAN media view was miscalculated on ConstraintLayout

### Built and tested with
- NAM SDK version 8.2.5
- FAN SDK version 6.18.0

## 6.18.0.2 (2025-01-22)
### Code Refactoring
* update the minimum required NAM SDK version

### Built and tested with
- NAM SDK version 8.2.5
- FAN SDK version 6.18.0

## 6.18.0.1 (2025-01-16)
### Code Refactoring
* update the minimum required NAM SDK version

### Built and tested with
- NAM SDK version 8.2.4
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