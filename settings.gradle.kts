rootProject.buildFileName = "build.gradle.kts"

include(":app")

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
                "com.android.application" ->
                    useModule("com.android.tools.build:gradle:${requested.version}")
                "com.google.gms.google-services" ->
                    useModule("com.google.gms:google-services:${requested.version}")
                "com.google.gms.oss.licenses.plugin" ->
                    useModule("com.google.gms:oss-licenses:${requested.version}")
                "com.github.ben-manes.versions" ->
                    useModule("com.github.ben-manes:gradle-versions-plugin:${requested.version}")
                "com.github.triplet.play" ->
                    useModule("com.github.triplet.gradle:play-publisher:${requested.version}")
                "io.fabric" ->
                    useModule("io.fabric.tools:gradle:${requested.version}")
            }
        }
    }
}
