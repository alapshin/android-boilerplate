object Dependencies {
    object Versions {
        const val minSdk = 19
        const val compileSdk = 28
        const val targetSdk = 28

        const val support = "1.0.0"
        const val supportTest = "1.1.0-beta02"

        const val detekt = "1.0.0.RC8"
        const val espresso = "3.1.0-beta02"
        const val kotlin = "1.2.71"
        const val leakcanary = "1.6.2"
        const val mockito = "2.23.0"
        const val okhttp = "3.10.0"
        const val retrofit = "2.4.0"
        const val robolectric = "4.0-beta-1"
        const val spotbugs = "3.1.3"
    }

    object Plugins {
        const val android = "com.android.tools.build:gradle:3.2.1"
        const val detekt = "gradle.plugin.io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detekt}"

        const val errorprone = "net.ltgt.gradle:gradle-errorprone-plugin:0.6"
        const val google = "com.google.gms:google-services:4.1.0"
        const val fabric = "io.fabric.tools:gradle:1.26.1"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val publisher = "com.github.triplet.gradle:play-publisher:2.0.0-beta1"
        const val spotbugs = "gradle.plugin.com.github.spotbugs:spotbugs-gradle-plugin:1.6.4"
        const val versions = "com.github.ben-manes:gradle-versions-plugin:0.20.0"
    }

    object Libraries {
        const val supportappcompat = "androidx.appcompat:appcompat:${Versions.support}"
        const val supportannotations = "androidx.annotation:annotation:${Versions.support}"
        const val supportconstraintlayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val supportcoordinatorlayout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.support}"
        const val supportdesign = "com.google.android.material:material:${Versions.support}"
        const val supportfragment = "androidx.fragment:fragment:${Versions.support}"
        const val supportmultidex = "androidx.multidex:multidex:2.0.0"
        const val supportrecyclerview = "androidx.recyclerview:recyclerview:${Versions.support}"
        const val supportvectordrawable = "androidx.vectordrawable:vectordrawable:${Versions.support}"
        const val supportviewpager = "androidx.viewpager:viewpager:${Versions.support}"

        const val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.9.5@aar"
        const val errorprone = "com.google.errorprone:error_prone_core:2.3.2"
        const val errorpronejavac = "com.google.errorprone:javac:9+181-r4173-1"
        const val firebasecore = "com.google.firebase:firebase-core:16.0.4"
        const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
        const val leakcanarynoop = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakcanary}"
        const val kotlinstdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val okhttplogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitrxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
        const val rxjava = "io.reactivex.rxjava2:rxjava:2.1.12"
        const val rxjava2interop = "com.github.akarnokd:rxjava2-interop:0.12.6"
        const val rxkotlin = "io.reactivex.rxjava2:rxkotlin:2.2.0"
        const val rxandroid = "io.reactivex.rxjava2:rxandroid:2.0.2"
        const val rxlint = "nl.littlerobots.rxlint:rxlint:1.7.0"
        const val rxrelay = "com.jakewharton.rxrelay2:rxrelay:2.0.0"
        const val spotbugsannotations = "com.github.spotbugs:spotbugs-annotations:${Versions.spotbugs}"
        const val threetenabp = "com.jakewharton.threetenabp:threetenabp:1.1.0"

        const val junit = "junit:junit:4.12"
        const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
        const val mockitoandroid = "org.mockito:mockito-android:${Versions.mockito}"
        const val mockitokotlin = "com.nhaarman:mockito-kotlin:1.5.0"
        const val restmock = "com.github.andrzejchm.RESTMock:android:0.3.1"
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
        const val robolectricmultidex = "org.robolectric:shadows-multidex:${Versions.robolectric}"
        const val robolectricsupport = "org.robolectric:shadows-supportv4:${Versions.robolectric}"

        const val supporttestcore = "androidx.test:core:1.0.0-beta02"
        const val supporttesttruth = "androidx.test.ext:truth:1.0.0-beta02"
        const val supporttestrules = "androidx.test:rules:${Versions.supportTest}"
        const val supporttestrunner = "androidx.test:runner:${Versions.supportTest}"
        const val supporttestmonitor = "androidx.test:monitor:${Versions.supportTest}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val espressocontrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
        const val espressointents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
        const val espressookhttpidlingresource = "com.jakewharton.espresso:okhttp3-idling-resource:1.0.0"
    }


}