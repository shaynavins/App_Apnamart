plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.apnamart.arrowlogx"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.apnamart.arrowlogx"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        disable += "NullSafeMutableLiveData"
        checkReleaseBuilds = false // Optional: disables lint for release
        abortOnError = false       // Optional: lets the build proceed on lint error
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://arrow-uat.apnamart.in/\"")
            buildConfigField("String", "API_KEY", "\"dT+NLQIHqljOR1CuidQ62h0a0MVc14JPleTMeBs5usA=\"")
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
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx.v1120)
    implementation(libs.androidx.appcompat.v161)
    implementation(libs.material.v1110)

    // Compose
    implementation(libs.ui)
    //noinspection UseTomlInstead
    implementation("androidx.compose.material3:material3:1.3.2")
    implementation(libs.androidx.activity.compose.v182)
    implementation(libs.androidx.lifecycle.runtime.ktx.v262)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.camera.core)
    implementation(libs.navigation.testing.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.androidx.hilt.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
    implementation(libs.converter.gson.v290)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)




}

kapt {
    correctErrorTypes = true
}