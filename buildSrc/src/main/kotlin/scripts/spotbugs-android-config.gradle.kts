import com.android.build.api.dsl.extension.AndroidExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.FeatureExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.github.spotbugs.SpotBugsExtension
import com.github.spotbugs.SpotBugsReports
import com.github.spotbugs.SpotBugsTask
import com.github.spotbugs.SpotBugsXmlReport

plugins {
    id("com.github.spotbugs")
}

configure<SpotBugsExtension> {
    toolVersion = Versions.spotbugs
    effort = "default"
    reportLevel = "high"
    isIgnoreFailures = false
}

afterEvaluate {
    val android = extensions.getByType(BaseExtension::class)

    val variants = when (android) {
        is AppExtension -> android.applicationVariants
        is LibraryExtension -> android.libraryVariants
        is FeatureExtension -> android.featureVariants
        else -> null
    }

    variants?.forEach { variant ->

        val variantName = variant.name
        val capitalizedVariantName = variant.name.capitalize()

        tasks.register("findbugs${capitalizedVariantName}", SpotBugsTask::class) {
            group = "Verification"
            description = "Run FindBugs on ${variantName} build."

            source = fileTree("src/main/java")
            classes = files("${buildDir}/intermediates/javac/${variantName}/compile${capitalizedVariantName}JavaWithJavac/classes/")
            classpath = files()
            excludeFilter = rootProject.file("findbugs-filter.xml")

            reports {
                xml.isEnabled = false
                xml.destination = file("${buildDir}/reports/findbugs/${variantName}/findbugs.xml")
                html.isEnabled = true
                html.destination = file("${buildDir}/reports/findbugs/${variantName}/findbugs.html")
            }
        }

        tasks.named("check").configure {
            dependsOn(tasks.named("findbugs${capitalizedVariantName}"))
        }
    }
}
