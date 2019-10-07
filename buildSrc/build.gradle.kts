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
    implementation("com.android.tools.build:gradle:3.5.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.1.0")

    // Utility plugins
    implementation("io.fabric.tools:gradle:1.29.0")
    implementation("com.github.triplet.gradle:play-publisher:2.4.1")

    // Code-quality plugins
    implementation("com.github.ben-manes:gradle-versions-plugin:0.21.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.0.1")
}
