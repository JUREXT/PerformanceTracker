plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    //id("dagger.hilt.android.plugin")
}

apply<SDKGradlePlugin>()

android {
    namespace = "com.programming.performancetracker"
}

dependencies {
    // Ignore.
}