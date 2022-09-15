plugins {
    id("java-platform")
    id("maven-publish")
}

val activityCompose = "1.5.1"
val coil = "2.2.1"
val compose = Versions.COMPOSE
val coreKtx = "1.8.0"
val hilt = Versions.HILT_AGP
val lifecycle = "2.5.1"
val okhttp = "4.10.0"
val retrofit = "2.9.0"
val timber = "5.0.1"

dependencies {
    constraints {
        api("${Libs.ACTIVITY_COMPOSE}:$activityCompose")
        api("${Libs.COIL}:$coil")
        api("${Libs.COIL_COMPOSE}:$coil")
        api("${Libs.COMPOSE_MATERIAL}:$compose")
        api("${Libs.COMPOSE_UI}:$compose")
        api("${Libs.COMPOSE_UI_TOOLING}:$compose")
        api("${Libs.CORE_KTX}:$coreKtx")
        api("${Libs.HILT_ANDROID}:$hilt")
        api("${Libs.HILT_COMPILER}:$hilt")
        api("${Libs.LIFECYCLE_RUNTIME_KTX}:$lifecycle")
        api("${Libs.OKHTTP_LOGGING_INTERCEPTOR}:$okhttp")
        api("${Libs.RETROFIT}:$retrofit")
        api("${Libs.RETROFIT_GSON_CONVERTER}:$retrofit")
        api("${Libs.TIMBER}:$timber")
    }
}