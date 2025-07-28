import org.gradle.api.JavaVersion

object ProjectConfig {
    const val minSdk = 23
    const val compileSdk = 36
    const val targetSdk = 36
    val sourceCompatibility = JavaVersion.VERSION_21
    val targetCompatibility = JavaVersion.VERSION_21
    val javaVersion = JavaVersion.VERSION_21
}