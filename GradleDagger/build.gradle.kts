plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(Dependencies.COMPOSE_PLUGIN_ID) version DependencyVersions.KOTLIN
}

apply<DaggerComposeGradlePlugin>()

android {
    namespace = "com.programming.gradledagger"
    kotlinOptions {
        freeCompilerArgs = listOf("-XXLanguage:+PropertyParamAnnotationDefaultTargetMode")
    }
}

dependencies {
    dagger()
    compose()
}