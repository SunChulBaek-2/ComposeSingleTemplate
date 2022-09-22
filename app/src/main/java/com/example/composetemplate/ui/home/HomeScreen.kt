package com.example.composetemplate.ui.home

import android.util.Base64
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composetemplate.ui.detail.PhotoDetailScreen

// 홈화면과 상세화면에 대한 네비게이션 처리
@Composable
fun HomeScreen(
    showToast: (String) -> Toast,
    onBack: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
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
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            val encodedUrl = backStackEntry.arguments?.getString("url")
            val decodedUrl = String(Base64.decode(encodedUrl, 0))
            PhotoDetailScreen(title = title, url = decodedUrl)
        }
    }
}