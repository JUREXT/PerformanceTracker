plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(Dependencies.COMPOSE_PLUGIN_ID) version DependencyVersions.KOTLIN
}

apply<HiltComposeGradlePlugin>()

android {
    namespace = "com.programming.gradlehilt"
}

dependencies {
    hilt()
    compose()
}