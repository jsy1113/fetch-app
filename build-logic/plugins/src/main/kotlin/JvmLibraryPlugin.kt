import com.fetch.plugins.extensions.getLibrary
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<JavaPluginExtension>() {
                sourceCompatibility = JavaVersion.VERSION_18
                targetCompatibility = JavaVersion.VERSION_18
            }

            dependencies {

                add("implementation", getLibrary("kotlinx.serialization.json"))
                add("implementation", getLibrary("kotlinx.coroutines.core"))
                add("implementation", getLibrary("retrofit.core"))
                add("implementation", getLibrary("retrofit.kotlinx.serialization"))
                add("implementation", getLibrary("okhttp"))

                add("testImplementation", getLibrary("kotlinx.coroutines.test"))
                add("testImplementation", getLibrary("mockk"))

            }
        }
    }
}