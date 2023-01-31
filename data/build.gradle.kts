
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

}
