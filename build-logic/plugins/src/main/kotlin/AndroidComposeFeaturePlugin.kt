import com.android.build.gradle.LibraryExtension
import com.fetch.plugins.extensions.getLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "fetch.android.library")
            apply(plugin = "fetch.hilt")

            extensions.configure<LibraryExtension>() {

                buildFeatures {
                    compose = true
                }

                packaging{
                    resources{
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                        merges += "META-INF/LICENSE.md"
                        merges += "META-INF/LICENSE-notice.md"
                    }
                }

                dependencies {
                    val bom = getLibrary("androidx-compose-bom")

                    add("implementation", project(":core:ui"))
                    add("implementation", platform(bom))
                    add("implementation", getLibrary("androidx-ui-tooling-preview"))
                    add("implementation", getLibrary("androidx-navigation-compose"))
                    add("debugImplementation", getLibrary("androidx-ui-tooling"))

                    add("implementation", getLibrary("androidx.hilt.navigation.compose"))

                    add("androidTestImplementation", platform(bom))
                    add("androidTestImplementation", getLibrary("androidx-ui-test-junit4"))
                    add("androidTestImplementation", getLibrary("mockk-android"))
                }
            }

        }
    }
}