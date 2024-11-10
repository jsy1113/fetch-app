import com.android.build.api.dsl.ApplicationExtension
import com.fetch.plugins.extensions.getLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")


            extensions.configure<ApplicationExtension> {
                buildFeatures {
                    compose = true
                }

                dependencies {
                    val bom = getLibrary("androidx-compose-bom")
                    add("implementation", platform(bom))
                    add("implementation", getLibrary("androidx-ui-tooling-preview"))
                    add("debugImplementation", getLibrary("androidx-ui-tooling"))
                    add("implementation",getLibrary("androidx-material3-adaptive-navigation-suite"))

                    add("androidTestImplementation", platform(bom))
                }

            }
        }
    }
}