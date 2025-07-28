import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

// https://medium.com/@ruikg0857/migrating-buildsrc-to-version-catalog-and-build-logic-5ab6866b8194
object Dependencies {

    const val composeBom = "androidx.compose:compose-bom:${DependencyVersions.COMPOSE_BOM}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeRuntime = "androidx.compose.runtime:runtime"
    const val material3 = "androidx.compose.material3:material3"

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
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${DependencyVersions.LIFECYCLE_RUNTIME_KTX}"

    const val activityCompose = "androidx.activity:activity-compose:${DependencyVersions.ACTIVITY_COMPOSE}"
}

fun DependencyHandler.core() {
    implementation(Dependencies.coreKtx)
}

fun DependencyHandler.lifecycle() {
    implementation(Dependencies.lifecycleRuntimeKtx)
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
    implementationPlatform(Dependencies.composeBom)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeRuntime)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.material3)
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