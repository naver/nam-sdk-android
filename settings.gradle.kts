pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://artifactory.bidmachine.io/bidmachine") }
        maven { url = uri("https://cboost.jfrog.io/artifactory/chartboost-ads/") }
        maven { url = uri("https://artifact.bytedance.com/repository/pangle") }
    }
}
rootProject.name = "NamExample"
include(":java-sample")
include(":kotlin-sample")