import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.fetch.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_18
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        // application plugins
        register("androidApplicationPlugin") {
            id = "fetch.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidComposeApplicationPlugin") {
            id = "fetch.compose.application"
            implementationClass = "AndroidComposeApplicationPlugin"
        }

        register("androidLibraryPlugin") {
            id = "fetch.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }

        register("jvmLibraryPlugin") {
            id = "fetch.jvm.library"
            implementationClass = "JvmLibraryPlugin"
        }

        register("hiltPlugin") {
            id = "fetch.hilt"
            implementationClass = "HiltPlugin"
        }

        register("androidComposeLirbaryPlugin") {
            id = "fetch.android.compose.library"
            implementationClass = "AndroidComposeLibraryPlugin"
        }

        register("androidComposeFeaturePlugin") {
            id = "fetch.android.compose.feature"
            implementationClass = "AndroidComposeFeaturePlugin"
        }
    }
}