# Changelog

### 4.3.2 (2022-07-18)


### Bug Fixes

* **nda:** fix a bug that user cannot click the ad mute button when the same ad is rebind

### 4.3.1 (2022-07-05)


### Bug Fixes

* change the artifact id of the external build

    >⚠️ Important: To prevent conflicts with NAVER internal build, we changed the artifact id of NAM SDK, and from version `4.3.1` you have to add dependencies as below.

    ```groovy
    implementation platform('com.naver.gfpsdk:nam-bom:4.3.1')
    implementation 'com.naver.gfpsdk:nam-core'
    implementation 'com.naver.gfpsdk:nam-nda'
    implementation 'com.naver.gfpsdk:nam-dfp'
    implementation 'com.naver.gfpsdk:nam-fan'
    implementation 'com.naver.gfpsdk:nam-inmobi'
    implementation 'com.naver.gfpsdk:nam-unity'
    ```

## 4.3.0 (2022-07-05)


### Features

* add new module to generate Bill of Materials 

    ```groovy
    implementation platform('com.naver.gfpsdk:gfpsdk-bom:4.3.0')
    implementation 'com.naver.gfpsdk:gfpsdk-core'
    implementation 'com.naver.gfpsdk:extension-nda'
    implementation 'com.naver.gfpsdk:extension-dfp'
    implementation 'com.naver.gfpsdk:extension-fan'
    implementation 'com.naver.gfpsdk:extension-inmobi'
    implementation 'com.naver.gfpsdk:extension-unity'
    ```


### Bug Fixes

* **core:** fix a potential bug in `GfpAdLoader.Builder` 


### Code Refactoring

* **core:** migrate `GfpError` class to kotlin 
* **inmobi:** remove optional dependencies of inmobi module 
* migrate some of internal code to kotlin


## 4.3.0-beta.1 (2022-06-30)

### Features

* add new module to generate Bill of Materials

### Bug Fixes

* **core:** fix a potential bug in `GfpAdLoader.Builder`

### Code Refactoring

* **core:** migrate `GfpError` class to kotlin
* **inmobi:** remove optional dependencies of inmobi module
* migrate some of internal code to kotlin
