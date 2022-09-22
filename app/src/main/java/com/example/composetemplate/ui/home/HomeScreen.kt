package com.example.composetemplate.ui

import android.util.Base64
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composetemplate.ui.detail.PhotoDetailScreen
import com.example.composetemplate.ui.home.NestedHomeScreen

@Composable
fun HomeScreen(
    showToast: (String) -> Toast,
    onBack: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            NestedHomeScreen(navigate = { route ->
                  navController.navigate(route)
            }, showToast = showToast, onBack = onBack)
        }
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