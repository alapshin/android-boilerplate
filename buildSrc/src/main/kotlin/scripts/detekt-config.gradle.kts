import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

apply<DetektPlugin>()

configure<DetektExtension> {
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

tasks.named("check").configure {
    dependsOn(tasks.named("detekt"))
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    autoCorrect = false
}
