import java.io.File
import java.io.FileInputStream
import java.util.*

object Versions {

    fun getProperty(file: File, key: String): String = Properties().apply { load(FileInputStream(file)) }.getProperty(key)

    const val COMPOSE = "1.2.1"
    const val COMPOSE_COMPILER = "1.2.0"
    const val GRADLE_VERSIONS_PLUGIN = "0.42.0"
    const val HILT_AGP = "2.44"
    const val KOTLIN = "1.7.0"
}