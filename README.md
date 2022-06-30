# Naver Ad Manager SDK for Android

Integrating the `Naver Ad Manager(NAM) SDK` into an app is the first step toward displaying ads and earning revenue. Once you've integrated the SDK,
you can choose an ad format (such as banner or native or rewarded or interstitial) and follow the steps to implement it.

## Before you begin

To prepare your app, complete the steps in the following sections.

### App prerequisites

- Use Android Studio 3.2 or higher
- Make sure that your app's build file uses the following values:
  - A `minSdkVersion` of `19` or higher
  - A `compileSdkVersion` of `28` or higher

## Configure your app

### 1. In your project-level `build.gradle` file, include [Google's Maven repository](https://maven.google.com/web/index.html) and [Maven central repository](https://search.maven.org/artifact) in both your buildscript and allprojects sections:

```gradle
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
```

### 2. Set the compile options to `java 8`, in your module's app-level Gradle file, normally `app/build.gradle`:

```gradle
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```

### 3. Add the dependencies for the Naver Ad Manager SDK to your module's app-level Gradle file, normally `app/build.gradle`:

```gradle
dependencies {
    implementation(platform("com.naver.gfpsdk:gfpsdk-bom:4.3.0-beta.1"))
	implementation "com.naver.gfpsdk:gfpsdk-core"

	... (add your mediation dependencies)
	implementation "com.naver.gfpsdk:extension-nda"
	implementation "com.naver.gfpsdk:extension-dfp"
	implementation "com.naver.gfpsdk:extension-fan"
	implementation "com.naver.gfpsdk:extension-inmobi"
	implementation "com.naver.gfpsdk:extension-unity"
}
```
  

### 4. Add your Publisher Code to your app's `AndroidManifest.xml` file. 

To do so, add a `<meta-data>` tag with `android:name="com.naver.gfpsdk.PUBLISHER_CD"`. For `android:value`, insert your own Publisher Code.

```xml
<manifest>
    <application>
        <meta-data
            android:name="com.naver.gfpsdk.PUBLISHER_CD"
            android:value="@string/publisher_cd" />
    </application>
</manifest>
```
>Note: `GfpSdkInitProvider` will handle the initialization of NAM SDK. This `ContentProvider` is merged into the app's manifest by default when building with Gradle, and it runs automatically at app launch. **No additional lines of code are needed in this case.**

### 5. Select an ad format 

NAM SDK is now imported and you're ready to implement an ad. NAM SDK offers a number of differenct ad formats, so you can choose the one that best fits your app's user experience.


| Ad Provider                                           | Description                                                                                                                                                                                                                                                                   |
|:------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Banner Ads](docs/ad-formats/banner.md)               | Rectangular ads that appear at the top or bottom of the device screen. Banner ads stay on screen while users are interacting with the app, and can refresh automatically after a certain period of time. If you're new to mobile advertising, they're a great place to start. |
| [Native Ads](docs/ad-formats/native_normal.md)        | Customizable ads that match the look and feel of your app. You decide how and where they're placed, so the layout is more consistent with your app's design.                                                                                                                  |
| [Native Simple Ads](docs/ad-formats/native_simple.md) | Native Simple has been designed to make the implementation of native ads as easy as possible, and it's a great choice if you are new to the format.                                                                                                                           |

### 6. Select ad provider you want to integrate

NAM SDK mediation supports several ad provider, with a mix of bidding and waterfall mediation integrations. Select an ad provider below for integration instructions specific to that ad provider.

| Ad Provider                           | Banner | Native | Native Simple | Rewarded | Interstitial | Description                                            |
|:--------------------------------------|:-------|:-------|:--------------|:---------|:-------------|:-------------------------------------------------------|
| [NDA](docs/ad-providers/nda.md)       | O      | O      | O             | X        | X            | S2S provider (Naver, Pubmatic, AppNexus, Rubicon, ...) |
| [DFP](docs/ad-providers/dfp.md)       | O      | O      | X             | O        | O            | Google Mobile Ads provider                             |
| [FAN](docs/ad-providers/fan.md)       | O      | O      | X             | O        | O            | Meta Audience Network provider                         |
| [INMOBI](docs/ad-providers/inmobi.md) | O      | O      | X             | X        | X            | InMobi provider                                        |
>Note: After contacting the NAM manager, add the module of the Ad provider you want to add to.

### 7. (Optional) Targeting your ads

NAM SDK provides an option for developers to send targeting data. More information can be found [here](docs/targeting.md).

## Demo apps 

- [Java Example](https://github.com/naver/nam-sdk-android/tree/main/example/javaSample)
- [Kotlin Example](https://github.com/naver/nam-sdk-android/tree/main/example/kotlinSample)
>Note: These applications are provided for demo purposes only. Do NOT use in production.

## Feedback and getting help

Bugs and feature requests can be filed with [GitHub Issues](https://github.com/naver/nam-sdk-android/issues).

## License

NAM(Naver Ad Manager) SDK의 저작권은 네이버(주)에 있습니다.

```
NAM(Naver Ad Manager) SDK for Android
Copyright 2022-present NAVER Corp.
All rights reserved.
Unauthorized use, modification and redistribution of this software are strongly prohibited.
```
