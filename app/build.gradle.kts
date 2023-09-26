plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.jeancorzo.rickandmorty"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jeancorzo.rickandmorty"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
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
}

dependencies {

    implementation(libs.android.ktx)
    implementation(libs.android.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.datastore.preferences)
    implementation(libs.koin.android)
    implementation(libs.coil)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)
    testImplementation(libs.junit)
    kapt(libs.databinding.compiler)
    androidTestImplementation(libs.android.junit)
    androidTestImplementation(libs.android.espresso)
}