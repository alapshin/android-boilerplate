import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("androidx.navigation.safeargs.kotlin")

    id("deps-updates")
    id("detekt-configuration")

    id("io.fabric")
    id("com.github.triplet.play")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

    viewBinding {
        isEnabled = true
    }

    defaultConfig {
        applicationId = "com.alapshin.boilerplate"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        versionCode = Git.getRevisionNumber()
        versionName = "1.0"
        versionNameSuffix = "-${versionCode}.${Git.getRevisionId()}"

        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        resValue("string", "fabric_api_key", "e9c70adcc0565fff7b6bdc0086c7f5ac15a4b7e8")

        buildConfigField("String", "API_URL", "\"https://jsonplaceholder.typicode.com/\"")
        buildConfigField("String", "IMAGES_BASE_URL", "\"https://picsum.photos/\"")
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
        implementation(project(":mvi"))

        implementation(Libraries.androidxcorektx)
        implementation(Libraries.androidxconstraintlayout)
        implementation(Libraries.androidxfragmentktx)
        implementation(Libraries.androidxmultidex)
        implementation(Libraries.androidxrecyclerview)
        implementation(Libraries.materialdesign)

        implementation(Libraries.crashlytics) {
            isTransitive = true
        }
        implementation(Libraries.dagger)
        implementation(Libraries.daggerandroid)
        implementation(Libraries.daggerandroidsupport)
        kapt(Libraries.daggercompiler)
        kapt(Libraries.daggerandroidprocessor)
        detektPlugins(Libraries.detektformatting)
        implementation(Libraries.firebasecore)
        implementation(Libraries.glide)
        kapt(Libraries.glidecompiler)
        implementation(Libraries.glideokhttp)
        implementation(Libraries.kotlinstdlib)
        implementation(Libraries.lifecycle)
        implementation(Libraries.lifecyclereactivektx)
        implementation(Libraries.moshi)
        kapt(Libraries.moshicodegen)
        implementation(Libraries.navigationuiktx)
        implementation(Libraries.navigationfragmentktx)
        implementation(Libraries.okhttp)
        implementation(Libraries.retrofit)
        implementation(Libraries.retrofitmoshi)
        implementation(Libraries.retrofitrxjava2)
        implementation(Libraries.rxandroid)
        implementation(Libraries.rxjava)
        implementation(Libraries.rxlint)
        implementation(Libraries.rxrelay)
        implementation(Libraries.rxkotlin)
        implementation(Libraries.threetenabp)
        implementation(Libraries.timber)

        debugImplementation(Libraries.leakcanary)

        testImplementation(Libraries.junit)
        testImplementation(Libraries.mockito)

        androidTestImplementation(Libraries.espresso)
        androidTestImplementation(Libraries.espressocontrib)
        androidTestImplementation(Libraries.espressointents)
        androidTestImplementation(Libraries.espressookhttpidlingresource)
        androidTestImplementation(Libraries.supporttestcore)
        androidTestImplementation(Libraries.supporttestmonitor)
        androidTestImplementation(Libraries.supporttestrunner)
    }
}