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

apply {
    from("$rootDir/base.gradle")
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
        versionCode = 9
        versionName = "1.1.6"

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

    // Navigation
    ksp(Libs.Navigation.raamcostaDestination)
    implementation(Libs.Navigation.raamcostaDestinationCore)
}
