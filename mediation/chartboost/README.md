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
repositories {
    maven {
        url "https://cboost.jfrog.io/artifactory/chartboost-ads/"
    }
}

dependencies {
    implementation 'com.naver.gfpsdk.mediation:nam-chartboost:<latest-version>'  
}

productFlavors {
        create("example") {
            dimension = "version"
            matchingFallbacks.add("production") // if you define product flavor, need to add this from 9.9.3.0
        }
    }
```

# Changelog
## 9.10.2.0 (2026-01-14)


### Code Refactoring

* unify error message format when getting signal
* verified compatibility with Chartboost SDK 9.10.2

### Built and tested with
- NAM SDK version 8.11.1
- CHARTBOOST SDK version 9.10.2

## 9.9.3.1 (2025-12-15)


### Code Refactoring

* verified compatibility with mediation network modules

### Built and tested with
- NAM SDK version 8.11.0
- CHARTBOOST SDK version 9.9.3

## 9.9.3.0 (2025-09-30)
### Code Refactoring
* add ProductType when creating a bid token
* improve SDK initialization data structure
* use SignalData for bidding token requests in RTB mediation networks
* verified compatibility with ChartBoost SDK 9.9.3

### Built and tested with
- NAM SDK version 8.9.0
- CHARTBOOST SDK version 9.9.3

## 9.7.0.2 (2025-08-08)

### Features (Experimental)
* support Ad Inspector

### Built and tested with
- NAM SDK version 8.7.0
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
- NAM SDK version 8.3.0
- CHARTBOOST SDK version 9.7.0