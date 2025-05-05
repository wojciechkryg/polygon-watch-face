package com.wojdor.buildsrc

object Application {
    object Mobile {
        val versionCode = 100106
        val versionName = "1.0.6"
    }

    object Wear {
        val versionCode = 106
        val versionName = "1.0.6"
    }

    val id = "com.wojdor.polygonwatchface"
    val compileSdk = 35
    val minSdk = 23
    val targetSdk = 35
}

object Version {
    val appcompat = "1.7.0"
    val constraintLayout = "2.1.4"
    val core = "1.13.1"
    val gradle = "8.5.2"
    val junit = "4.13.2"
    val junitExtension = "1.2.1"
    val koin = "3.5.6"
    val kotlin = "2.0.10"
    val legacySupport = "1.0.0"
    val material = "1.12.0"
    val mockk = "1.13.12"
    val recyclerView = "1.3.2"
    val percentLayout = "1.0.0"
    val playServices = "18.5.0"
    val playServicesWearable = "18.2.0"
    val remoteInteractions = "1.0.0"
    val wear = "1.3.0"
    val wearable = "2.9.0"
}

internal object Library {
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
        val remoteInteractions =
            "androidx.wear:wear-remote-interactions:${Version.remoteInteractions}"
        val wear = "androidx.wear:wear:${Version.wear}"
        val wearable = "com.google.android.wearable:wearable:${Version.wearable}"
        val wearableSupport = "com.google.android.support:wearable:${Version.wearable}"
    }

    object Test {
        val junit = "junit:junit:${Version.junit}"
        val junitExtension = "androidx.test.ext:junit:${Version.junitExtension}"
        val mockk = "io.mockk:mockk:${Version.mockk}"
    }

    val koin = "io.insert-koin:koin-android:${Version.koin}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
}

object Module {
    val common = ":common"
    val commonAndroid = ":commonAndroid"
    val mobile = ":mobile"
    val wear = ":wear"
}
