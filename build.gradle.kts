buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
        classpath("com.android.tools.build:gradle:7.4.0")
        classpath("com.google.gms:google-services:4.3.14")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.2")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.38.0")
    }
} // Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.0" apply false
    id("com.android.library") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("com.android.test") version "7.4.0" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

task<Delete>("clean") {
    delete = setOf(rootProject.buildDir)
}
