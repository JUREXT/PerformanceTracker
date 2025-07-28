import org.gradle.api.Plugin
import org.gradle.api.Project

class DaggerComposeGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        applyPlugins(project)
        setProjectConfig(project)
    }

    private fun applyPlugins(project: Project) {
        project.apply {
            plugin("android-library")
            plugin("kotlin-android")
            plugin("kotlin-kapt")
        }
    }

    private fun setProjectConfig(project: Project) {
        project.android().apply {
            compileSdk = ProjectConfig.compileSdk

            defaultConfig {
                minSdk = ProjectConfig.minSdk
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            compileOptions {
                sourceCompatibility = ProjectConfig.sourceCompatibility
                targetCompatibility = ProjectConfig.targetCompatibility
            }

            buildFeatures {
                compose = true
            }

            composeOptions {
                // Must match the compiler plugin version.
                kotlinCompilerExtensionVersion = DependencyVersions.COMPOSE_COMPILER
            }
        }
    }
}