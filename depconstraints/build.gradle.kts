plugins {
    id("java-platform")
    id("maven-publish")
}

val accompanist = "0.25.1"
val activityCompose = "1.5.1"
val coil = "2.2.1"
val compose = Versions.COMPOSE
val composeMaterial3 = "1.0.0-beta03"
val constraintLayoutCompose = "1.0.1"
val coreKtx = "1.8.0"
val hilt = Versions.HILT_AGP
val hiltNavigation = "1.0.0"
val lifecycle = "2.5.1"
val navigation = "2.5.2"
val okhttp = "4.10.0"
val retrofit = "2.9.0"
val timber = "5.0.1"

dependencies {
    constraints {
        api("${Libs.ACCOMPANIST_SYSTEM_UI_CONTROLLER}:$accompanist")
        api("${Libs.ACCOMPANIST_APPCOMPAT_THEME}:$accompanist")
        api("${Libs.ACCOMPANIST_PAGER}:$accompanist")
        api("${Libs.ACCOMPANIST_PAGER_INDICATOR}:$accompanist")
        api("${Libs.ACCOMPANIST_PERMISSIONS}:$accompanist")
        api("${Libs.ACCOMPANIST_PLACEHOLDER_MATERIAL}:$accompanist")
        api("${Libs.ACCOMPANIST_PLACEHOLDER}:$accompanist")
        api("${Libs.ACCOMPANIST_FLOW_LAYOUT}:$accompanist")
        api("${Libs.ACCOMPANIST_NAVIGATION_ANIMATION}:$accompanist")
        api("${Libs.ACCOMPANIST_NAVIGATION_MATERIAL}:$accompanist")
        api("${Libs.ACCOMPANIST_DRAWABLE_PAINTER}:$accompanist")
        api("${Libs.ACCOMPANIST_SWIPE_TO_REFRESH}:$accompanist")
        api("${Libs.ACTIVITY_COMPOSE}:$activityCompose")
        api("${Libs.COIL}:$coil")
        api("${Libs.COIL_COMPOSE}:$coil")
        api("${Libs.COMPOSE_MATERIAL}:$compose")
        api("${Libs.COMPOSE_MATERIAL3}:$composeMaterial3")
        api("${Libs.COMPOSE_UI}:$compose")
        api("${Libs.COMPOSE_UI_TOOLING}:$compose")
        api("${Libs.COMPOSE_UI_TOOLING_PREVIEW}:$compose")
        api("${Libs.CONSTRAINT_LAYOUT_COMPOSE}:$constraintLayoutCompose")
        api("${Libs.CORE_KTX}:$coreKtx")
        api("${Libs.HILT_ANDROID}:$hilt")
        api("${Libs.HILT_COMPILER}:$hilt")
        api("${Libs.HILT_NAVIGATION}:$hiltNavigation")
        api("${Libs.LIFECYCLE_RUNTIME_KTX}:$lifecycle")
        api("${Libs.LIFECYCLE_VIEW_MODEL_COMPOSE}:$lifecycle")
        api("${Libs.NAVIGATION_COMPOSE}:$navigation")
        api("${Libs.OKHTTP_LOGGING_INTERCEPTOR}:$okhttp")
        api("${Libs.RETROFIT}:$retrofit")
        api("${Libs.RETROFIT_GSON_CONVERTER}:$retrofit")
        api("${Libs.TIMBER}:$timber")
    }
}