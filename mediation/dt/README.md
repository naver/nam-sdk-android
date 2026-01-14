# Digital Turbine Mediation for NAM SDK for Android

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
    implementation 'com.naver.gfpsdk.mediation:nam-dt:<latest-version>'  
}
```

# Changelog
## 8.4.0.0 (2026-01-14)


### Bug Fixes

* **dt:** support non bidding ad request


### Code Refactoring

* support in-app bidding for DigitalTurbine
* unify error message format when getting signal
* verified compatibility with DigitalTurbine SDK 8.4.0

### Built and tested with
- NAM SDK version 8.11.1
- DT SDK version 8.4.0

## 8.3.8.1 (2025-12-15)


### Code Refactoring

* verified compatibility with mediation network modules

### Built and tested with
- NAM SDK version 8.11.0
- DT SDK version 8.3.8

## 8.3.8.0 (2025-09-30)
### Code Refactoring
* verified compatibility with DigitalTurbine SDK 8.3.8

### Built and tested with
- NAM SDK version 8.9.0
- DT SDK version 8.3.8

## 8.3.6.4 (2025-09-03)

### Bug Fixes
* set mediation name and version before DT initialization

### Built and tested with
- NAM SDK version 8.7.0
- DT SDK version 8.3.6

## 8.3.6.3 (2025-08-08)

### Features (Experimental)
* support Ad Inspector

### Built and tested with
- NAM SDK version 8.7.0
- DT SDK version 8.3.6

## 8.3.6.2 (2025-05-23)
### Code Refactoring
* remove unnecessary internal method

### Built and tested with
- NAM SDK version 8.4.3
- DT SDK version 8.3.6

## 8.3.6.1 (2025-04-28)
### Code Refactoring
* adjust rendered impressions fire timing

### Built and tested with
- NAM SDK version 8.2.3
- DT SDK version 8.3.6

## 8.3.6.0 (2025-04-10)
### Code Refactoring
* verified compatibility with DT SDK 8.3.6

### Built and tested with
- NAM SDK version 8.2.3
- DT SDK version 8.3.6

## 8.3.5.2 (2025-03-14)
### Code Refactoring
* **dt:** refactor the DT banner ad loading process

### Built and tested with
- NAM SDK version 8.2.3
- DT SDK version 8.3.5

## 8.3.5.1 (2025-01-16)
### Code Refactoring
* update the minimum required NAM SDK version

### Built and tested with
- NAM SDK version 8.2.4
- DT SDK version 8.3.5

## 8.3.0.0 (2024-11-08)

### Code Refactoring

* verified compatibility with DT SDK 8.3.0 

### Built and tested with
- NAM SDK version 8.0.0
- DT SDK version 8.3.0

## 8.2.7.0 (2024-11-07)

### Code Refactoring

* add global privacy policy(CCPA) setting for DSPs 
* verified compatibility with DT SDK 8.2.7 

### Built and tested with
- NAM SDK version 8.0.0
- DT SDK version 8.2.7