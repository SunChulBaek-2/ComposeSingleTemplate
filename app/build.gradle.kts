import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    val buildProp = file(rootProject.file("build.properties"))
    compileSdk = getProperty(buildProp, "compileSdk").toInt()

    defaultConfig {
        applicationId = getProperty(buildProp, "applicationId")
        minSdk = getProperty(buildProp, "minSdk").toInt()
        targetSdk = getProperty(buildProp, "targetSdk").toInt()
        versionCode = getProperty(buildProp, "versionCode").toInt()
        versionName = getProperty(buildProp, "versionName")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "baseUrl", "\"${getProperty(buildProp, "baseUrl")}\"")
    }
    signingConfigs {
        getByName("debug") {
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storeFile = file(rootProject.file("debug.keystore"))
            storePassword = "android"
        }
        create("release") {

        }
    }
    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.0")

    // accompanist
    implementation(libs.accompanist.appcompat.theme)
    implementation(libs.accompanist.drawablepainter)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.navigation.material)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicator)
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.accompanist.placeholder)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.webview)

    // Compose
    implementation(libs.androidx.compose.bom)
    //implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material)
    //implementation(libs.androidx.compose.material3)

    // TODO : Android Studio Preview support (Build fix)
//    implementation(libs.androidx.compose.ui.tooling.preview)
//    debugImplementation(libs.androidx.compose.ui.tooling)

    // Optional - Integration with activities
    implementation(libs.androidx.activity.compose)
    // Optional - Integration with ViewModels
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraint.layout.compose)
    implementation(libs.lottie.compose)

    // Android Architecture Components
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.okhttp.logging.interceptor)

    // Coil
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    // Timber
    implementation(libs.timber)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation)
}

fun getProperty(file: File, key: String): String = Properties().apply { load(FileInputStream(file)) }.getProperty(key)