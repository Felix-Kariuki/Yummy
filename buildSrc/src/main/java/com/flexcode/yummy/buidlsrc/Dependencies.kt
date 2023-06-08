package com.flexcode.yummy.buidlsrc


object SDK {
    const val min = 24
    const val max = 33
}

object Libs {

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"

        const val appCompat = "androidx.appcompat:appcompat:1.5.1"

        object Compose {
            const val version = "1.4.0-alpha01"

            const val ui = "androidx.compose.ui:ui:$version"
            const val material = "androidx.compose.material:material:$version"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val materialIcons = "androidx.compose.material:material-icons-extended:$version"

        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.6.1"
        }

        object Lifecycle {
            const val version = "2.5.1"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"

        }
    }

    object Coroutines {
     const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
    }

    object Kotlin {
        const val kotlinCompiler = "1.3.2"
    }

    object Accompanist {
        const val flowLayout = "com.google.accompanist:accompanist-flowlayout:0.28.0"

        //implementation "androidx.paging:paging-compose:1.0.0-alpha17"
        const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:0.28.0"
        const val systemUi = "com.google.accompanist:accompanist-systemuicontroller:0.28.0"
    }

    object Navigation {
        const val raamcostaDestination = "io.github.raamcosta.compose-destinations:ksp:1.4.4-beta"
        const val raamcostaDestinationCore =
            "io.github.raamcosta.compose-destinations:core:1.4.2-beta"
        const val navCompose = "androidx.navigation:navigation-compose:2.5.3"
    }

    object Coil {
        const val coil = "io.coil-kt:coil-compose:2.2.2"
    }

    object Firebase {
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx:18.3.2"
        const val analytics = "com.google.firebase:firebase-analytics-ktx:21.2.0"



    }

    object Dagger {
        const val version = "2.44"

        const val hilt = "com.google.dagger:hilt-android:$version"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$version"
        const val androidXHilt = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0"

    }

    object Retrofit {
        const val version = "2.9.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val retrofitConverter = "com.squareup.retrofit2:converter-gson:$version"

        const val logging = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3"
    }

    object Room {
        const val version = "2.4.3"
        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
        const val roomKtx = "androidx.room:room-ktx:$version"
    }

    object Logging {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
    }
    object Billing {
        const val inAppPurchases = "com.github.Felix-Kariuki:InAppPurchasesComposeLibrary:0.1.4"
    }
    object Testing {
        const val truth = "com.google.truth:truth:1.1.3"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:4.9.3"
        const val jUnit = "junit:junit:4.13.2"
        const val testJUnit  = "androidx.test.ext:junit:1.1.4"
        const val espresso ="androidx.test.espresso:espresso-core:3.5.0"
        const val mockitoVersion = "4.6.1"
        const val mockito = "org.mockito:mockito-android:$mockitoVersion"
        const val composeUi = "androidx.compose.ui:ui-test-junit4:1.4.0-alpha01"
        const val uiAutomator = "androidx.test.uiautomator:uiautomator:2.2.0"
        const val benchmark = "androidx.benchmark:benchmark-macro-junit4:1.1.0"

    }

}