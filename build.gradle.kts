buildscript {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        maven(url = "https://maven.fabric.io/public")
    }

    dependencies {
        classpath(Dependencies.Plugins.android)
        classpath(Dependencies.Plugins.kotlin)

        classpath(Dependencies.Plugins.detekt)
        classpath(Dependencies.Plugins.errorprone)
        classpath(Dependencies.Plugins.fabric)
        classpath(Dependencies.Plugins.google)
        classpath(Dependencies.Plugins.publisher)
        classpath(Dependencies.Plugins.spotbugs)
        classpath(Dependencies.Plugins.versions)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://maven.fabric.io/public")
    }
}
