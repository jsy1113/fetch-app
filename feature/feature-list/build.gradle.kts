plugins {
    alias(libs.plugins.fetch.android.compose.feature)
    alias(libs.plugins.fetch.android.compose.library)
}

android {
    namespace = "com.fetch.feature.list"
}

dependencies{
    implementation(project(":data:data-list"))
}
