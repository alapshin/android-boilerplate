import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    compileSdkVersion(Dependencies.Versions.compileSdk)

    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }

    defaultConfig {
        applicationId = "com.alapshin.boilerplate"
        minSdkVersion(Dependencies.Versions.minSdk)
        targetSdkVersion(Dependencies.Versions.targetSdk)

        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        implementation(Dependencies.Libraries.crashlytics) {
            isTransitive = true
        }
        implementation(Dependencies.Libraries.firebasecore)

        implementation(Dependencies.Libraries.kotlinstdlib)

        debugImplementation(Dependencies.Libraries.leakcanary)
        releaseImplementation(Dependencies.Libraries.leakcanarynoop)

        implementation(Dependencies.Libraries.supportappcompat)
        implementation(Dependencies.Libraries.supportconstraintlayout)
        implementation(Dependencies.Libraries.supportdesign)
        implementation(Dependencies.Libraries.supportfragment)
        implementation(Dependencies.Libraries.supportmultidex)
        implementation(Dependencies.Libraries.supportrecyclerview)
        implementation(Dependencies.Libraries.supportvectordrawable)

        testImplementation(Dependencies.Libraries.junit)
        testImplementation(Dependencies.Libraries.mockito)

        androidTestImplementation(Dependencies.Libraries.espresso)
        androidTestImplementation(Dependencies.Libraries.supporttestcore)
        androidTestImplementation(Dependencies.Libraries.supporttestmonitor)
        androidTestImplementation(Dependencies.Libraries.supporttestrunner)
    }
}