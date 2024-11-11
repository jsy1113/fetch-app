plugins {
    alias(libs.plugins.fetch.android.library)
    alias(libs.plugins.fetch.hilt)
}

android {
    namespace = "com.fetch.core.network"
    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://fetch-hiring.s3.amazonaws.com/hiring.json\""
            )
        }
        release {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://fetch-hiring.s3.amazonaws.com/hiring.json\""
            )
        }
    }
}
