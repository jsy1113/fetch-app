import com.android.build.gradle.LibraryExtension
import com.fetch.plugins.extensions.getLibrary
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = 34

                defaultConfig {
                    minSdk = 21
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_18
                    targetCompatibility = JavaVersion.VERSION_18
                }

                defaultConfig.targetSdk = 34
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                testOptions.animationsDisabled = true

                // The resource prefix is derived from the module name,
                // so resources inside ":core:module1" must be prefixed with "core_module1_"
                resourcePrefix =
                    path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_")
                        .lowercase() + "_"
            }

            dependencies {
                add("implementation", project(":core:common"))
                add("implementation", getLibrary("kotlinx.serialization.json"))
                add("implementation", getLibrary("kotlinx.coroutines.core"))
                add("implementation", getLibrary("retrofit.core"))
                add("implementation", getLibrary("retrofit.kotlinx.serialization"))
                add("implementation", getLibrary("okhttp"))
                add("implementation", getLibrary("kotlinx-collections-immutable"))
                add("implementation", getLibrary("androidx-room-ktx"))
                add("implementation", getLibrary("androidx-room-runtime"))
                add("ksp", getLibrary("androidx-room-compiler"))
                add(
                    "annotationProcessor", getLibrary("androidx-room-compiler")
                )


                add("testImplementation", getLibrary("kotlinx.coroutines.test"))
                add("testImplementation", getLibrary("mockk"))
                add("testImplementation", getLibrary("junit"))
            }
        }
    }
}