import com.flexcode.yummy.buidlsrc.Libs
import com.flexcode.yummy.buidlsrc.SDK
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version "1.7.20-1.0.6"
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("com.github.ben-manes.versions")
}

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

android {
    compileSdk = SDK.max

    defaultConfig {
        applicationId = "com.flexcode.yummy"
        minSdk = SDK.min
        targetSdk = SDK.max
        versionCode = 6
        versionName = "1.1.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("benchmark") {
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
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
    ktlint {
        disabledRules.set(setOf("no-wildcard-imports", "filename"))
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Kotlin.kotlinCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

tasks.getByPath("preBuild").dependsOn("ktlintFormat")

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        mavenCentral()
    }

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        version.set("11.0.0")
        debug.set(true)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        outputColorName.set("RED")
        ignoreFailures.set(true)
        enableExperimentalRules.set(true)
        additionalEditorconfigFile.set(file("/some/additional/.editorconfig"))
        baseline.set(file("my-project-ktlint-baseline.xml"))
        reporters {
            reporter(ReporterType.PLAIN)
            reporter(ReporterType.CHECKSTYLE)
            reporter(ReporterType.SARIF)

            customReporters {
                register("csv") {
                    fileExtension = "csv"
                    dependency = project(":project-reporters:csv-reporter")
                }
                register("yaml") {
                    fileExtension = "yml"
                    dependency = "com.example:ktlint-yaml-reporter:1.0.0"
                }
            }
        }
        kotlinScriptAdditionalPaths {
            include(fileTree("scripts/"))
        }
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }

    dependencies {
        ktlintRuleset("com.github.username:rulseset:master-SNAPSHOT")
        ktlintRuleset(files("/path/to/custom/rulseset.jar"))
        ktlintRuleset(project(":chore:project-ruleset"))
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":common"))

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.toolingPreview)
    implementation(Libs.AndroidX.Activity.activityCompose)

    // lifecycle
    implementation(Libs.AndroidX.Lifecycle.runtime)
    implementation(Libs.AndroidX.Lifecycle.viewModelCompose)

    implementation(Libs.AndroidX.Compose.materialIcons)

    implementation(Libs.Accompanist.flowLayout)
    implementation(Libs.Accompanist.swipeRefresh)
    implementation(Libs.Accompanist.systemUi)

    // Navigation
    ksp(Libs.Navigation.raamcostaDestination)
    implementation(Libs.Navigation.raamcostaDestinationCore)
    implementation(Libs.Navigation.navCompose)

    // Coil
    implementation(Libs.Coil.coil)

    // firebase
    implementation(Libs.Firebase.crashlytics)
    implementation(Libs.Firebase.analytics)

    // Dagger - Hilt
    implementation(Libs.Dagger.hilt)
    kapt(Libs.Dagger.hiltCompiler)
    kapt(Libs.Dagger.androidXHilt)
    implementation(Libs.Dagger.hiltNavigation)

    // Retrofit
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.retrofitConverter)
    implementation(Libs.Retrofit.logging)

    // Room
    implementation(Libs.Room.runtime)
    kapt(Libs.Room.compiler)
    implementation(Libs.Room.roomKtx)

    // Timber logging
    implementation(Libs.Logging.timber)

    // Testing
    androidTestImplementation(Libs.Testing.truth)
    androidTestImplementation(Libs.Testing.mockWebServer)
    testImplementation(Libs.Testing.jUnit)
    androidTestImplementation(Libs.Testing.testJUnit)
    androidTestImplementation(Libs.Testing.espresso)

    testImplementation(Libs.Testing.mockito)
    androidTestImplementation(Libs.Testing.mockito)
    androidTestImplementation(Libs.Testing.composeUi)

//    androidTestImplementation "com.google.truth:truth:1.1.3"
//    testImplementation "junit:junit:4.13.2"
//    androidTestImplementation "androidx.test.ext:junit:1.1.3"
//    androidTestImplementation "androidx.test.espresso:espresso-core:3.4.0"
//    debugImplementation "androidx.compose.ui:ui-test-manifest:1.4.0-alpha01"
//    androidTestImplementation "junit:junit:4.13.2"
//    androidTestImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1"
//    androidTestImplementation "androidx.arch.core:core-testing:2.1.0"
//    androidTestImplementation "androidx.test:core-ktx:1.4.0"
//    androidTestImplementation "com.squareup.okhttp3:mockwebserver:4.9.3"
//    androidTestImplementation "com.google.dagger:hilt-android-testing:2.42"
//    kaptAndroidTest "com.google.dagger:hilt-android-compiler:2.44"
//    debugImplementation "androidx.compose.ui:ui-tooling:1.4.0-alpha01"
}
