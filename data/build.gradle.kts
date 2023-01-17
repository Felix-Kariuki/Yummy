import com.flexcode.yummy.buidlsrc.Libs
import com.flexcode.yummy.buidlsrc.SDK

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("org.jlleitschuh.gradle.ktlint")
}

apply {
    from("$rootDir/base.gradle")
}

android {
    namespace = "com.flexcode.yummy.data"
    compileSdk = SDK.max

    defaultConfig {
        minSdk = SDK.min
        targetSdk = SDK.max

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles = "consumer-rules.pro"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    ktlint {
        disabledRules.set(setOf("no-wildcard-imports"))
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

    implementation(project(":domain"))
    implementation(project(":common"))

//    implementation(Libs.AndroidX.coreKtx)
//    implementation(Libs.AndroidX.appCompat)
//
//    // Dagger - Hilt
//    implementation(Libs.Dagger.hilt)
//    kapt(Libs.Dagger.hiltCompiler)
//    kapt(Libs.Dagger.androidXHilt)
//    implementation(Libs.Dagger.hiltNavigation)
//
//    // Retrofit
//    implementation(Libs.Retrofit.retrofit)
//    implementation(Libs.Retrofit.retrofitConverter)
//    implementation(Libs.Retrofit.logging)
//
//    // Room
//    implementation(Libs.Room.runtime)
//    kapt(Libs.Room.compiler)
//    implementation(Libs.Room.roomKtx)
//
//    // Testing
//    androidTestImplementation(Libs.Testing.truth)
//    androidTestImplementation(Libs.Testing.mockWebServer)
//    testImplementation(Libs.Testing.jUnit)
//    androidTestImplementation(Libs.Testing.testJUnit)
//    androidTestImplementation(Libs.Testing.espresso)
}
