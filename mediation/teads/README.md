# Teads Mediation for NAM SDK for Android

## Supported ad formats

| Ad format     | Description |
|:--------------|:------------|
| Banner        | X           |
| Native        | O           |
| Native Simple | O           |
| Interstitial  | X           |
| Rewarded      | X           |

## Integration

```gradle
...
repositories {
    maven {
        url "https://teads.jfrog.io/artifactory/SDKAndroid-maven-prod"
    }
}

dependencies {
    implementation 'com.naver.gfpsdk.mediation:nam-teads:<latest-version>'  
}
```

# Changelog

## 6.1.0.3 (2026-09-18)
### Bug Fixes
* declare native simple overlay containers as friendly obstructions

### Code Refactoring
* add Teads validation mode option and apply debug logs with test mode

### Built and tested with
- NAM SDK version 8.15.0
- TEADS SDK version 6.1.0

## 6.1.0.2 (2026-08-19)
### Bug Fixes
* specify wildcard module for compose exclusions
* render out-stream video creatives in native normal

### Built and tested with
- NAM SDK version 8.15.0
- TEADS SDK version 6.1.0

## 6.1.0.1 (2026-05-11)

### Code Refactoring

* verified compatibility with Teads SDK 6.1.0  

### Built and tested with
- GFP SDK version 8.15.1
- TEADS SDK version 6.1.0
