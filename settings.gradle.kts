rootProject.buildFileName = "build.gradle.kts"

include(":app", ":mvi")

pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        maven(url = "https://maven.fabric.io/public")
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "io.fabric" ->
                    useModule("io.fabric.tools:gradle:${requested.version}")
                "com.android.application" ->
                    useModule("com.android.tools.build:gradle:${requested.version}")
                "androidx.navigation.safeargs.kotlin" ->
                    useModule("androidx.navigation:navigation-safe-args-gradle-plugin:${requested.version}")
            }
        }
    }
}
