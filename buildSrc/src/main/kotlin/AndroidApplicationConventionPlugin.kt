import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import kr.pe.ssun.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.FileInputStream
import java.util.*

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                val propFile = file(rootProject.file("build.properties"))
                val properties = Properties().apply { load(FileInputStream(propFile))}

                configureKotlinAndroid(this)
                defaultConfig.targetSdk = properties.getProperty("targetSdk").toInt()
                //configureFlavors(this)
            }
            extensions.configure<ApplicationAndroidComponentsExtension> {
                //configurePrintApksTask(this)
            }
        }
    }
}