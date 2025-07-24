import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val composeMaterial = "androidx.compose.material3:material3:${DependencyVersions.COMPOSE_MATERIAL3}"
    const val composeUi = "androidx.compose.ui:ui:${DependencyVersions.COMPOSE}"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics:${DependencyVersions.COMPOSE}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${DependencyVersions.COMPOSE}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${DependencyVersions.COMPOSE}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${DependencyVersions.COMPOSE}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${DependencyVersions.HILT}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${DependencyVersions.HILT}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${DependencyVersions.HILT}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${DependencyVersions.OK_HTTP}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${DependencyVersions.OK_HTTP}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${DependencyVersions.RETROFIT}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${DependencyVersions.RETROFIT}"

    const val roomRuntime = "androidx.room:room-runtime:${DependencyVersions.ROOM}"
    const val roomCompiler = "androidx.room:room-compiler:${DependencyVersions.ROOM}"
    const val roomKtx = "androidx.room:room-ktx:${DependencyVersions.ROOM}"

    const val coreKtx = "androidx.core:core-ktx:${DependencyVersions.CORE_KTX}"
}

fun DependencyHandler.core() {
    implementation(Dependencies.coreKtx)
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshiConverter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeRuntime)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeMaterial)
    debugImplementation(Dependencies.composeUiToolingPreview)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.performanceTrackerSDK() {
    implementation(project(":PerformanceTracker"))
}

fun DependencyHandler.nativeLoggerSDK() {
    implementation(project(":NativeLogger"))
}