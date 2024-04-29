# IronSource

This guide is intended for publishers who want to use NAM SDK to load and display ads from the IronSource through waterfall mediation.

## Supported ad formats

| Ad format     | Description |
|:--------------|:------------|
| Banner        | ✔️          |
| Native        | ❌️          |
| Native Simple | ❌          |
| Interstitial  | ✔️          |
| Rewarded      | ✔️          |

## Requirements

- Android API level 19 or higher
- Latest NAM SDK

## Step 1: Import the IronSource SDK extension

```gradle
repositories {
    mavenCentral()
    maven {
			url "https://android-sdk.is.com/"
	}
}

...
dependencies {
    implementation 'com.naver.gfpsdk:nam-ironsource:7.4.4'  
}
```

## Step 2: Additional code required

No additional code is required for IronSource integration.