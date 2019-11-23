plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    compileSdkVersion(Versions.compileSdk)

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
    }

    buildTypes {
        getByName("debug") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    dependencies {
        implementation(Libraries.lifecycle)
        implementation(Libraries.kotlinstdlib)

        implementation(Libraries.rxjava)
        implementation(Libraries.rxkotlin)
        implementation(Libraries.rxandroid)
        implementation(Libraries.rxrelay)

        testImplementation(Libraries.junit)
        
        androidTestImplementation(Libraries.androidxtestcore)
        androidTestImplementation(Libraries.androidxtestjunit)
        androidTestImplementation(Libraries.androidxtestrunner)
    }
}
