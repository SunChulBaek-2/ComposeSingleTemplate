package com.example.composetemplate.ui.home

import android.util.Base64
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.composetemplate.ui.detail.PhotoDetailScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

fun defaultEnterTransition(): EnterTransition = slideInVertically(
    initialOffsetY = { fullHeight -> fullHeight }
)

fun defaultExitTransition(): ExitTransition = slideOutVertically(
    targetOffsetY = { fullHeight -> fullHeight }
)

// 홈화면과 상세화면에 대한 네비게이션 처리
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    showToast: (String) -> Toast,
    onBack: () -> Unit
) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = "home") {
        // 홈화면
        composable("home") {
            NestedHomeScreen(navigate = { route ->
                  navController.navigate(route)
            }, showToast = showToast, onBack = onBack)
        }
        // 상세화면
        composable(
            route = "photo/{title}/{url}",
            arguments = listOf(
                navArgument("url" ) { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            ),
            enterTransition = { defaultEnterTransition() },
            exitTransition = { defaultExitTransition() }
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            val encodedUrl = backStackEntry.arguments?.getString("url")
            val decodedUrl = String(Base64.decode(encodedUrl, 0))
            PhotoDetailScreen(title = title, url = decodedUrl)
        }
    }
}