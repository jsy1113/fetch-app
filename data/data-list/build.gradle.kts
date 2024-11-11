plugins {
    alias(libs.plugins.fetch.android.library)
    alias(libs.plugins.fetch.hilt)
}

android {
    namespace = "com.fetch.data.list"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:network"))
}