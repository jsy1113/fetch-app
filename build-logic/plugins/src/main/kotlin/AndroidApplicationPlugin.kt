import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.configure
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                apply {
                    compileSdk = 34

                    defaultConfig {
                        minSdk = 21
                    }

                    compileOptions {
                        sourceCompatibility = JavaVersion.VERSION_18
                        targetCompatibility = JavaVersion.VERSION_18
                    }
                    defaultConfig.targetSdk = 34
                    @Suppress("UnstableApiUsage")
                    testOptions.animationsDisabled = true
                }
            }
        }
    }
}