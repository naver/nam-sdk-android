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
## 9.1.0.1 (2026-02-02)


### Code Refactoring

* support GPP specs

### Built and tested with
- NAM SDK version 8.12.0
- IRONSOURCE SDK version 9.1.0

## 9.1.0.0 (2026-01-14)


### Code Refactoring

* verified compatibility with IronSource SDK 9.1.0

### Built and tested with
- NAM SDK version 8.11.0
- IRONSOURCE SDK version 9.1.0

## 8.11.1.1 (2025-12-15)


### Code Refactoring

* verified compatibility with mediation network modules

### Built and tested with
- NAM SDK version 8.11.0
- IRONSOURCE SDK version 8.11.1

## 8.11.1.0 (2025-09-30)
### Code Refactoring
* improve SDK initialization data structure
* verified compatibility with IronSource SDK 8.11.1

### Built and tested with
- NAM SDK version 8.9.0
- IRONSOURCE SDK version 8.11.1

## 8.4.0.5 (2025-08-08)

### Features
* support Ad Inspector

### Built and tested with
- NAM SDK version 8.7.0
- IRONSOURCE SDK version 8.4.0

## 8.4.0.4 (2025-06-10)


### Code Refactoring

* update to use ironSource SDK hosted on MavenCentral

### Built and tested with
- NAM SDK version 8.4.3
- IRONSOURCE SDK version 8.4.0

## 8.4.0.3 (2025-05-23)
### Code Refactoring
* remove unnecessary internal method

### Built and tested with
- NAM SDK version 8.4.3
- IRONSOURCE SDK version 8.4.0

## 8.4.0.2 (2025-03-14)
### Built and tested with
- NAM SDK version 8.2.3
- IRONSOURCE SDK version 8.4.0

## 8.4.0.1 (2025-01-16)
### Code Refactoring
* update the minimum required NAM SDK version

### Built and tested with
- NAM SDK version 8.2.4
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