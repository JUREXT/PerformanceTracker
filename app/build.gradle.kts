plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id(Dependencies.COMPOSE_PLUGIN_ID) version DependencyVersions.KOTLIN
}

android {
    namespace = "com.programming.performancetrackersample"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = "com.programming.sample"
        minSdk = ProjectConfig.minSdk
        targetSdk =  ProjectConfig.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = ProjectConfig.sourceCompatibility
        targetCompatibility = ProjectConfig.targetCompatibility
    }

    kotlinOptions {
        jvmTarget = ProjectConfig.javaVersion.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion =
            DependencyVersions.COMPOSE_COMPILER // Must match the compiler plugin version
    }
}

dependencies {
    core()
    lifecycle()
    compose()

    performanceTrackerSDK()
    nativeLoggerSDK()
    gradleHiltSDK()
}