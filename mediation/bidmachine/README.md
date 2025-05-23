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
- GFP SDK version 8.3.0
- BIDMACHINE SDK version 3.1.1
