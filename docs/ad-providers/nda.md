# NDA

This guide is intended for publishers who want to use NAM SDK to load and display ads from the S2S ad provider through waterfall mediation.
It covers how to integrate the S2S ad provider and adapter into an Android app.

## Supported ad formats

| Ad format     | Description |
|:--------------|:------------|
| Banner        | ✔️          |
| Native        | ✔️          |
| Native Simple | ✔️          |
| Interstitial  | ❌️          |
| Rewarded      | ✔️          |

## Requirements

- Android API level 19 or higher
- Latest NAM SDK

## Step 1: Import the NDA extension

```gradle
repositories {
    google()
    mavenCentral()
}

...
dependencies {
    implementation 'com.naver.gfpsdk:nam-nda:6.3.0'  
}
```

## Step 2: Additional code required

No additional code is required for NDA integration.
