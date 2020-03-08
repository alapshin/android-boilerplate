object Versions {
    const val minSdk = 21
    const val compileSdk = 29
    const val targetSdk = 29

    const val assisstedinject = "0.5.2"
    const val dagger = "2.26"
    const val detekt = "1.6.0"
    const val espresso = "3.2.0"
    const val glide = "4.11.0"
    const val kotlin = "1.3.70"
    const val leakcanary = "2.2"
    const val lifecycle = "2.2.0"
    const val mockk = "1.9.3"
    const val moshi = "1.9.2"
    const val navigation = "2.2.1"
    const val okhttp = "4.4.0"
    const val paging = "2.1.1"
    const val retrofit = "2.7.1"
    const val robolectric = "4.0"
    const val viewmodelinject = "0.3.3"

    const val androidxTest = "1.2.0"
}

fun isNonStable(version: String): Boolean {
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    return !(stableKeyword || regex.matches(version))
}
