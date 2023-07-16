@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ru.yotfr.weather"
    compileSdk = WeatherConfig.compileSdk

    defaultConfig {
        applicationId = "ru.yotfr.weather"
        minSdk = WeatherConfig.minSdk
        targetSdk = WeatherConfig.targetSdk
        versionCode = WeatherConfig.versionCode
        versionName = WeatherConfig.versionName

        testInstrumentationRunner = WeatherConfig.androidTestInstrumentation
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            exclude("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    androidCore()
    androidUi()
    kotlinBom(project)
    compose(project, test = true)
    test()
}