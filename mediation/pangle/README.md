# Pangle Mediation for NAM SDK for Android

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
repositories {
    maven {
        url "https://artifact.bytedance.com/repository/pangle"
    }
}

dependencies {
    implementation 'com.naver.gfpsdk.mediation:nam-pangle:<latest-version>'  
}
```

# Changelog
## 7.5.0.3.1 (2025-12-15)


### Code Refactoring

* verified compatibility with mediation network modules

### Built and tested with
- NAM SDK version 8.11.0
- PANGLE SDK version 7.5.0.3

## 7.5.0.3.0 (2025-09-30)
### Code Refactoring
* verified compatibility with Pangle SDK 7.5.0.3

### Built and tested with
- NAM SDK version 8.9.0
- PANGLE SDK version 7.5.0.3