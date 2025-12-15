# BidMachine Mediation for NAM SDK for Android

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
    implementation 'com.naver.gfpsdk.mediation:nam-bidmachine:<latest-version>'  
}
```

# Changelog
## 3.3.0.1 (2025-12-15)


### Code Refactoring

* verified compatibility with mediation network modules

### Built and tested with
- NAM SDK version 8.11.0
- BIDMACHINE SDK version 3.3.0

## 3.3.0.0 (2025-09-30)
### Code Refactoring
* add ProductType when creating a bid token
* use SignalData for bidding token requests in RTB mediation networks
* verified compatibility with BidMachine SDK 3.3.0

### Built and tested with
- NAM SDK version 8.9.0
- BIDMACHINE SDK version 3.3.0

## 3.1.1.4 (2025-08-08)

### Features (Experimental)
* support Ad Inspector

### Built and tested with
- NAM SDK version 8.7.0
- BIDMACHINE SDK version 3.1.1

## 3.1.1.3 (2025-06-10)


### Code Refactoring

* improve event handling to ensure consistent behavior

### Built and tested with
- NAM SDK version 8.4.3
- BIDMACHINE SDK version 3.1.1

## 3.1.1.2 (2025-05-23)
### Code Refactoring
* remove unnecessary internal method

### Built and tested with
- NAM SDK version 8.4.3
- BIDMACHINE SDK version 3.1.1

## 3.1.1.1 (2025-04-28)
### Code Refactoring
* adjust rendered impressions fire timing

### Built and tested with
- NAM SDK version 8.3.0
- BIDMACHINE SDK version 3.1.1

## 3.1.1.0 (2025-03-14)

### Code Refactoring

* verified compatibility with BidMachine SDK 3.1.1

### Built and tested with
- NAM SDK version 8.3.0
- BIDMACHINE SDK version 3.1.1
