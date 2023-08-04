plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}
android {
    namespace = "ru.yotfr.database"
    compileSdk = WeatherConfig.compileSdk

    defaultConfig {
        minSdk = WeatherConfig.minSdk
        targetSdk = WeatherConfig.targetSdk

        testInstrumentationRunner = WeatherConfig.androidTestInstrumentation
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:location"))
    implementation(project(":core:common"))
    implementation(project(":domain:shared"))

    desugaring()
    hilt()
    androidCore()
    kotlinBom(project)
}