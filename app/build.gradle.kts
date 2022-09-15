plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    val buildProp = file(rootProject.file("build.properties"))
    compileSdk = Versions.getProperty(buildProp, "compileSdk").toInt()

    defaultConfig {
        applicationId = Versions.getProperty(buildProp, "applicationId")
        minSdk = Versions.getProperty(buildProp, "minSdk").toInt()
        targetSdk = Versions.getProperty(buildProp, "targetSdk").toInt()
        versionCode = Versions.getProperty(buildProp, "versionCode").toInt()
        versionName = Versions.getProperty(buildProp, "versionName")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
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
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))
    androidTestApi(platform(project(":depconstraints")))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}")

    // Compose
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_UI_TOOLING_PREVIEW)
    implementation(Libs.ACTIVITY_COMPOSE)
    debugImplementation(Libs.COMPOSE_UI_TOOLING)

    // Android Architecture Components
    implementation(Libs.LIFECYCLE_RUNTIME_KTX)

    // Retrofit
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_GSON_CONVERTER)
    implementation(Libs.OKHTTP_LOGGING_INTERCEPTOR)

    // Coil
    implementation(Libs.COIL)
    implementation(Libs.COIL_COMPOSE)

    // Timber
    implementation(Libs.TIMBER)

    // Hilt
    implementation(Libs.HILT_ANDROID)
    kapt(Libs.HILT_COMPILER)
}