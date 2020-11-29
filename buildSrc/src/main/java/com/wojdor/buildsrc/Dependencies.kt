package com.wojdor.buildsrc

object Application {
    object Mobile {
        val versionCode = 100100
        val versionName = "1.0.0"
    }
    object Wear {
        val versionCode = 100
        val versionName = "1.0.0"
    }
    val id = "com.wojdor.polygonwatchface"
    val compileSdk = 29
    val minSdk = 23
    val targetSdk = 29
    val sourceCompatibility = "1.8"
    val targetCompatibility = "1.8"
}

object Version {
    val appcompat = "1.2.0"
    val constraintLayout = "1.1.3"
    val core = "1.3.1"
    val gradle = "4.0.1"
    val junit = "4.12"
    val junitExtension = "1.1.1"
    val koin = "2.1.6"
    val kotlin = "1.3.72"
    val legacySupport = "1.0.0"
    val material = "1.2.1"
    val mockk = "1.10.2"
    val recyclerView = "1.1.0"
    val percentLayout = "1.0.0"
    val playServices = "17.3.0"
    val playServicesWearable = "17.0.0"
    val wear = "1.1.0"
    val wearable = "2.7.0"
}

object Library {
    object Android {
        val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
        val core = "androidx.core:core-ktx:${Version.core}"
        val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
        val legacySupport = "androidx.legacy:legacy-support-v4:${Version.legacySupport}"
        val material = "com.google.android.material:material:${Version.material}"
        val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
        val percentLayout = "androidx.percentlayout:percentlayout:${Version.percentLayout}"
        val playServices = "com.google.android.gms:play-services-base:${Version.playServices}"
        val playServicesWearable =
            "com.google.android.gms:play-services-wearable:${Version.playServicesWearable}"
        val wear = "androidx.wear:wear:${Version.wear}"
        val wearable = "com.google.android.wearable:wearable:${Version.wearable}"
        val wearableSupport = "com.google.android.support:wearable:${Version.wearable}"
    }

    object Test {
        val junit = "junit:junit:${Version.junit}"
        val junitExtension = "androidx.test.ext:junit:${Version.junitExtension}"
        val mockk = "io.mockk:mockk:${Version.mockk}"
    }

    val koin = "org.koin:koin-android:${Version.koin}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
}

object Module {
    val common = ":common"
    val commonAndroid = ":commonAndroid"
    val mobile = ":mobile"
    val wear = ":wear"
}