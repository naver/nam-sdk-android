plugins {
		    id("com.android.application")
		    id("org.jetbrains.kotlin.android")
		}

		android {
		    namespace = "com.naver.namexample"
		    defaultConfig {
		        minSdk = 21
		        compileSdk = 35
		        targetSdk = 35
		        versionCode = 1
		        versionName = "1.0"

		        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		    }

		    buildTypes {
		        getByName("release") {
		            isMinifyEnabled = false
		            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		        }
		    }
		    compileOptions {
		        sourceCompatibility = JavaVersion.VERSION_17
		        targetCompatibility = JavaVersion.VERSION_17
		    }

		    buildFeatures {
				viewBinding = true
			}
		}

		dependencies {
		    implementation("androidx.core:core-ktx:1.7.0")
		    implementation("androidx.appcompat:appcompat:1.4.1")
		    implementation("com.google.android.material:material:1.4.0")
		    implementation("androidx.constraintlayout:constraintlayout:2.1.3")

		    implementation(platform("com.naver.gfpsdk:nam-bom:8.8.2"))
		    implementation("com.naver.gfpsdk:nam-core")
		    implementation("com.naver.gfpsdk.mediation:nam-nda") // (optional) s2s mediation dependency
		    implementation("com.naver.gfpsdk.mediation:nam-dfp") // (optional) dfp mediation dependency
		    implementation("com.naver.gfpsdk.mediation:nam-fan") // (optional) fan mediation dependency
		    implementation("com.naver.gfpsdk.mediation:nam-inmobi") // (optional) inmobi mediation dependency
		    implementation("com.naver.gfpsdk.mediation:nam-unity") // (optional) unity mediation dependency
		    implementation("com.naver.gfpsdk.mediation:nam-applovin") // (optional) applovin mediation dependency
		    implementation("com.naver.gfpsdk.mediation:nam-vungle") // (optional) vungle mediation dependency
		    implementation("com.naver.gfpsdk.mediation:nam-ndavideo") // (optional) naver instream ads dependency
		    implementation("com.naver.gfpsdk.mediation:nam-dt") // (optional) digital turbine mediation dependency
		    implementation("com.naver.gfpsdk.mediation:nam-aps") // (optional) AmazonPublisherServices mediation (for header bidding)
		    implementation("com.naver.gfpsdk.mediation:nam-ironsource") // (optional) ironsource mediation
		    implementation("com.naver.gfpsdk.mediation:nam-lan") // (optional) lan mediation
		    implementation("com.naver.gfpsdk.mediation:nam-bidmachine") // (optional) bidmachine mediation
		    implementation("com.naver.gfpsdk.mediation:nam-chartboost") // (optional) chartboost mediation

		    // for video player, this example using exoplayer
		    implementation("com.google.android.exoplayer:exoplayer-core:2.18.0")
		    implementation("com.google.android.exoplayer:exoplayer-hls:2.18.0")
		    implementation("com.google.android.exoplayer:exoplayer-ui:2.18.0")

		    testImplementation("junit:junit:4.13.2")
		    androidTestImplementation("androidx.test.ext:junit:1.1.3")
		    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
		}