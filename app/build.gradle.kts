plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

baseConfig()

compose()

android {
    defaultConfig {
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    kapt {
        correctErrorTypes = true
        useBuildCache = true
    }
}

dependencies {
    // implementation("androidx.room:room-coroutines:2.1.0-alpha03")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
    //   debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")

    androidBase()
    compose()
    ktor()
    room()
    kapt("androidx.room:room-compiler:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
    dataStore()
    jackson()
}