import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

object Versions {
    const val kotlinBom = "1.8.0"
    const val androidCore = "1.10.1"
    const val androidLifecycle = "2.6.1"
    const val activityCompose = "1.7.2"
    const val composeBom = "2022.10.00"
    const val jUnit = "4.13.2"
    const val jUnitExt = "1.1.5"
    const val espresso = "3.5.1"
    const val dagger = "2.47"
    const val retrofit = "2.9.0"
    const val moshi = "1.14.0"
    const val okHttp = "4.11.0"
}

private fun DependencyHandler.implementation(depName: Dependency) {
    add("implementation", depName)
}
private fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}
private fun DependencyHandler.testImplementation(depName: String) {
    add("testImplementation", depName)
}
private fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}
private fun DependencyHandler.androidTestImplementation(depName: Dependency) {
    add("androidTestImplementation", depName)
}
private fun DependencyHandler.debugImplementation(depName: String) {
    add("debugImplementation", depName)
}
private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}
private fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}
private fun DependencyHandler.api(depName: String) {
    add("api", depName)
}

object Android {
    const val core = "androidx.core:core-ktx:${Versions.androidCore}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidLifecycle}"
    const val compose = "androidx.activity:activity-compose:${Versions.activityCompose}"
}

object KotlinBom {
    const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom:${Versions.kotlinBom}"
}

object Compose {
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val ui = "androidx.compose.ui:ui"
    const val graphics = "androidx.compose.ui:ui-graphics"
    const val tooling = "androidx.compose.ui:ui-tooling-preview"
    const val material = "androidx.compose.material3:material3"
    const val debugTooling = "androidx.compose.ui:ui-tooling"
    const val debugManifest = "androidx.compose.ui:ui-test-manifest"
    const val composeJUnit = "androidx.compose.ui:ui-test-junit4"
}

object Test {
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val androidJUnit = "androidx.test.ext:junit:${Versions.jUnitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val jsonConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

}
object Moshi {
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
}

fun DependencyHandler.androidCore() {
    implementation(Android.core)
}
fun DependencyHandler.androidUi() {
    implementation(Android.lifecycleRuntime)
    implementation(Android.compose)
}
fun DependencyHandler.kotlinBom(project: Project) {
    implementation(project.dependencies.platform(KotlinBom.kotlinBom))
}
fun DependencyHandler.compose(project: Project, test: Boolean) {
    implementation(project.dependencies.platform(Compose.composeBom))
    androidTestImplementation(project.dependencies.platform(Compose.composeBom))
    implementation(Compose.ui)
    implementation(Compose.graphics)
    implementation(Compose.tooling)
    implementation(Compose.material)
    debugImplementation(Compose.debugTooling)
    debugImplementation(Compose.debugManifest)
    if (test) {
        androidTestImplementation(Compose.composeJUnit)
    }
}
fun DependencyHandler.test() {
    testImplementation(Test.jUnit)
    androidTestImplementation(Test.androidJUnit)
    androidTestImplementation(Test.espresso)
}

fun DependencyHandler.dagger() {
    implementation(Dagger.dagger)
    kapt(Dagger.compiler)
}

fun DependencyHandler.retrofit() {
    implementation(Retrofit.retrofit)
    implementation(Retrofit.jsonConverter)
    implementation(Retrofit.okHttp)
}

fun DependencyHandler.moshi() {
    implementation(Moshi.moshi)
}


