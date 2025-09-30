# Naver Ad Manager SDK for Android

![Maven Central](https://img.shields.io/maven-central/v/com.naver.gfpsdk/nam-core) ![latest build](https://github.com/naver/nam-sdk-android/actions/workflows/android.yml/badge.svg)

Integrating the `Naver Ad Manager(NAM) SDK` into an app is the first step toward displaying ads and earning revenue. Once you've integrated the SDK,
you can choose an ad format (such as banner or native or rewarded or interstitial) and follow the steps to implement it.

## Before you begin

To prepare your app, complete the steps in the following sections.

### App prerequisites

- Use Android Studio 3.2 or higher
- Make sure that your app's build file uses the following values:
  - A `minSdkVersion` of `23` or higher
  - A `compileSdkVersion` of `28` or higher

## Configure your app

### 1. In your project-level `build.gradle` file, include [Google's Maven repository](https://maven.google.com/web/index.html) and [Maven central repository](https://search.maven.org/artifact) in both your buildscript and allprojects sections:

```groovy
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

```groovy
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```

### 3. Add the dependencies for the Naver Ad Manager SDK to your module's app-level Gradle file, normally `app/build.gradle`:

#### (Required) Add the core dependency

```groovy
dependencies {
    implementation platform('com.naver.gfpsdk:nam-bom:<latest-version>')
    implementation 'com.naver.gfpsdk:nam-core' // no version specified
}
```
> [!NOTE]
> You can avoid specifying the version of each dependency with a `Bill Of Materials`.

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
> [!NOTE]
> `GfpSdkInitProvider` will handle the initialization of NAM SDK. This `ContentProvider` is merged into the app's manifest by default when building with Gradle, and it runs automatically at app launch. **No additional lines of code are needed in this case.**

### 5. Select an ad format

NAM SDK is now imported and you're ready to implement an ad. NAM SDK offers a number of different ad formats, so you can choose the one that best fits your app's user experience.


| Ad Provider                                           | Description                                                                                                                                                                                                                                                                   |
|:------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Banner Ads](docs/ad-formats/banner.md)               | Rectangular ads that appear at the top or bottom of the device screen. Banner ads stay on screen while users are interacting with the app, and can refresh automatically after a certain period of time. If you're new to mobile advertising, they're a great place to start. |
| [Native Ads](docs/ad-formats/native_normal.md)        | Customizable ads that match the look and feel of your app. You decide how and where they're placed, so the layout is more consistent with your app's design.                                                                                                                  |
| [Native Simple Ads](docs/ad-formats/native_simple.md) | Native Simple has been designed to make the implementation of native ads as easy as possible, and it's a great choice if you are new to the format.                                                                                                                           |
| [In Stream Ads](docs/ad-formats/in_stream.md)         | In-Stream (Video) AD is placed between the beginning and the end of the video content.                                                                                                                                                                                        |
| [Rewarded Ads](docs/ad-formats/rewarded.md)           | Rewarded ads provide incentives and rewards to users when they complete watching a video in full screen.                                                                                                                                                                      |
| [Interstitial Ads](docs/ad-formats/interstitial.md)   | Interstitial ads are a type of ad format that occupies the entire screen of the app.                                                                                                                                                                                          |

### 6. Select mediation you want to integrate

Mediation in NAM supports several ad source, with a mix of bidding and waterfall mediation integrations. Select an ad provider below for integration instructions specific to that ad source.

| Ad Source                                 |
|:------------------------------------------|
| [NAVER](mediation/nda)                    |
| [AppLovin](mediation/applovin)            |
| [Amazon Publisher Service](mediation/aps) |
| [BidMachine](mediation/bidmachine)        |
| [Chartboost](mediation/chartboost)        |
| [Google Ad Manager](mediation/dfp)        |
| [Digital Turbine](mediation/dt)           |
| [Meta Audience Network](mediation/fan)    |
| [InMobi](mediation/inmobi)                |
| [IronSource](mediation/ironsource)        |
| [Line](mediation/lan)                     |
| [Pangle](mediation/pangle)                |
| [Unity](mediation/unity)                  |
| [Vungle](mediation/vungle)                |

> [!NOTE]
> After contacting the NAM manager, add the module of the Mediation you want to integrate.

### 7. (Optional) Targeting your ads

NAM SDK provides an option for developers to send targeting data. More information can be found [here](docs/targeting.md).

## API

The documentation for latest version is available on [here](https://naver.github.io/nam-sdk-android/)

## Demo apps

- [Java Example](https://github.com/naver/nam-sdk-android/tree/main/java-sample)
- [Kotlin Example](https://github.com/naver/nam-sdk-android/tree/main/kotlin-sample)
> [!NOTE]
> These applications are provided for demo purposes only. Do NOT use in production.

## Feedback and getting help

Bugs and feature requests can be filed with [GitHub Issues](https://github.com/naver/nam-sdk-android/issues).

## Getting Started with NAM SDK

See [documentation](https://naver.github.io/nam-sdk-guide/en/android/) for more information.

## License

```
NAM(Naver Ad Manager) SDK for Android
Copyright 2022-present NAVER Corp.
All rights reserved.
Unauthorized use, modification and redistribution of this software are strongly prohibited.
```
