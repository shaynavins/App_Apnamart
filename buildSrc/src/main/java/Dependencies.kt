

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.material3}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    const val composeTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"

    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltDaggerCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }

    // For Hilt Jetpack integrations like @HiltViewModel
    val hiltJetpackCompiler by lazy { "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}" }

}

object Modules {
    const val utilities = ":utilities"
}
