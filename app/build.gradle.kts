/*buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
    }
}*/

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    /*id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")*/

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
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
  //   debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")

    androidBase()
    compose()
    ktor()

    room()
    kapt("androidx.room:room-compiler:2.5.2")

    dataStore()
    // firebase()

    /*implementation(platform("com.google.firebase:firebase-bom:31.3.0"))
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.4")*/

    jackson()
/*
    // ImageLoad
    implementation("com.github.skydoves:landscapist-glide:2.1.9")
    implementation("com.github.skydoves:landscapist-placeholder:2.1.9")*/

//     debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")

}
/*

android {
    namespace = "com.ilhomsoliev.consolepro"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ilhomsoliev.consolepro"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}*/
