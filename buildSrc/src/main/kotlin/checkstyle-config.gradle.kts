apply<CheckstylePlugin>()

configure<CheckstyleExtension> {
    toolVersion = Versions.checkstyle
}

tasks.register("checkstyle", Checkstyle::class) {
    group = "Verification"
    description = "Run Checkstyle."
    configFile = rootProject.file("checkstyle.xml")
    ignoreFailures = false
    isShowViolations = true

    source  = project.fileTree("src/main/java")
    include("**/*.java")
    classpath = files()
}

tasks.named("check").configure {
    dependsOn(tasks.named("checkstyle"))
}

