import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")

    id("io.fabric")
    id("com.github.triplet.play")

    id("deps-updates")
    id("detekt-config")
    id("checkstyle-config")
    id("errorprone-config")
    id("spotbugs-android-config")
}

play {
    track = "internal"
    releaseStatus = "draft"
    serviceAccountCredentials = rootProject.file("google-play-publisher.json")
}

kotlin {
    sourceSets.configureEach {
        languageSettings.progressiveMode = true
    }
}

android {
    compileSdkVersion(Versions.compileSdk)

    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }

    defaultConfig {
        applicationId = "com.alapshin.boilerplate"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        versionCode = Git.getRevisionNumber()
        versionName = "1.0"
        versionNameSuffix = "-${versionCode}.${Git.getRevisionId()}"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        resValue("string", "fabric_api_key", "e9c70adcc0565fff7b6bdc0086c7f5ac15a4b7e8")
    }

    signingConfigs {
        create("common") {
            val keystoreProperties = Properties().apply {
                load(FileInputStream(rootProject.file("keystore.properties")))
            }
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword =  keystoreProperties["keyPassword"] as String
            storeFile  = rootProject.file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            signingConfig = signingConfigs.getByName("common")
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("common")
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    lintOptions {
        isQuiet = false
        // Stop the gradle build if errors are found
        isAbortOnError = true
        // Don't check disabled warnings
        isCheckAllWarnings = false
        // Don't treat warnings as errors
        isWarningsAsErrors = false
        lintConfig = rootProject.file("lint.xml")
    }

    dependencies {
        implementation(Libraries.crashlytics) {
            isTransitive = true
        }

        errorprone(Libraries.errorprone)
        errorproneJavac(Libraries.errorpronejavac)

        implementation(Libraries.firebasecore)
        implementation(Libraries.kotlinstdlib)

        debugImplementation(Libraries.leakcanary)
        releaseImplementation(Libraries.leakcanarynoop)

        implementation(Libraries.supportappcompat)
        implementation(Libraries.supportconstraintlayout)
        implementation(Libraries.supportdesign)
        implementation(Libraries.supportfragment)
        implementation(Libraries.supportmultidex)
        implementation(Libraries.supportrecyclerview)
        implementation(Libraries.supportvectordrawable)

        testImplementation(Libraries.junit)
        testImplementation(Libraries.mockito)

        androidTestImplementation(Libraries.espresso)
        androidTestImplementation(Libraries.supporttestcore)
        androidTestImplementation(Libraries.supporttestmonitor)
        androidTestImplementation(Libraries.supporttestrunner)
    }
}