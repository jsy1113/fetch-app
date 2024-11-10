import com.android.build.gradle.LibraryExtension
import com.fetch.plugins.extensions.getLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            extensions.configure<LibraryExtension>() {

                buildFeatures {
                    compose = true
                }

                dependencies {
                    val bom = getLibrary("androidx-compose-bom")

                    add("implementation", platform(bom))
                    add("implementation", getLibrary("androidx-ui-tooling-preview"))
                    add("implementation", getLibrary("coil-kt"))
                    add("implementation", getLibrary("coil-kt-compose"))

                    add("debugImplementation", getLibrary("androidx-ui-tooling"))
                    add("debugImplementation", getLibrary("androidx-ui-test-manifest"))


                    add("testImplementation", getLibrary("turbine"))
                    add("testImplementation", getLibrary("junit"))

                    add("androidTestImplementation", getLibrary("coil-test"))
                    add("androidTestImplementation", platform(bom))
                    add("androidTestImplementation", getLibrary("androidx-ui-test-junit4"))
                }
            }

        }
    }

}