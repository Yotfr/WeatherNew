@file:Suppress("UnstableApiUsage")
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    packagingOptions {
        resources {
            exclude("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(":ui:actualweather"))
    implementation(project(":ui:shared"))
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:location"))
    implementation(project(":core:ui"))
    implementation(project(":core:utility"))
    implementation(project(":domain:shared"))
    implementation(project(":domain:places"))
    implementation(project(":domain:actualweather"))
    implementation(project(":data:actualweather"))
    implementation(project(":data:places"))
    implementation(project(":data:shared"))

    androidCore()
    androidUi()
    kotlinBom(project)
    compose(project, test = true)
    test()
    hilt()
    navigation()
}