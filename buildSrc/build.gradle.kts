plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    jcenter()
    gradlePluginPortal()
    maven(url = "https://maven.fabric.io/public")
}

dependencies {
    implementation("com.android.tools.build:gradle:3.3.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.20")

    // Utility plugins
    implementation("io.fabric.tools:gradle:1.27.0")
    implementation("com.github.triplet.gradle:play-publisher:2.0.0")

    // Code-quality plugins
    implementation("net.ltgt.gradle:gradle-errorprone-plugin:0.6")
    implementation("com.github.ben-manes:gradle-versions-plugin:0.20.0")
    implementation("gradle.plugin.com.github.spotbugs:spotbugs-gradle-plugin:1.6.6")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.0.0-RC12")
}
