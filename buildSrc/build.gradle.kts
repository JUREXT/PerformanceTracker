plugins {
    `kotlin-dsl`
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.0")
    implementation("com.android.tools.build:gradle:8.11.1")

    implementation("com.google.dagger:hilt-android-gradle-plugin:2.57")
}