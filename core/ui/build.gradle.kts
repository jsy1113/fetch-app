plugins {
    alias(libs.plugins.fetch.android.compose.feature)
    alias(libs.plugins.fetch.android.compose.library)
}

android {
    namespace = "com.fetch.feature.list"
}

dependencies{
    api(libs.androidx.material3)
    api(libs.androidx.material3.adaptive.navigation.suite)
    api(libs.androidx.compose.ui.util)
}
