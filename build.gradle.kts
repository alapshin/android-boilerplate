import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.github.ben-manes.versions") version "0.27.0"
    id("io.gitlab.arturbosch.detekt") version Versions.detekt

    id("com.android.application") version "3.6.0-rc01" apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("org.jetbrains.kotlin.kapt") version Versions.kotlin apply false
    id("androidx.navigation.safeargs.kotlin") version Versions.navigation apply false

    id("io.fabric") version "1.31.2" apply false
    id("com.github.triplet.play") version "2.6.1" apply false
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }
}

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
    }
    dependencies {
        detektPlugins(Libraries.detektformatting)
    }
    detekt {
        input = project.files("src/main/java", "src/main/kotlin")
        config = rootProject.files("detekt-config.yml")
        filters = ".*test.*,.*/resources/.*,.*/tmp/.*"
        reports {
            xml {
                enabled = true
                destination = project.file("build/reports/detekt/report.xml")
            }
            html {
                enabled = true
                destination = project.file("build/reports/detekt/report.html")
            }
        }
    }

    tasks.withType<DependencyUpdatesTask>  {
        rejectVersionIf {
            isNonStable(candidate.version) && !isNonStable(currentVersion)
        }
    }

    afterEvaluate {
        tasks.named("check").configure {
            dependsOn(tasks.named("detekt"))
        }
    }
    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        autoCorrect = true
    }
}
