package com.example.composetemplate.ui

import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.composetemplate.ui.detail.PhotoDetailScreen
import com.example.composetemplate.ui.home.*
import com.example.composetemplate.ui.splash.SplashScreen
import com.example.composetemplate.ui.widget.DefaultSnackbar
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.launch

fun defaultEnterTransition(): EnterTransition = slideInVertically(
    initialOffsetY = { fullHeight -> fullHeight }
)

fun defaultExitTransition(): ExitTransition = slideOutVertically(
    targetOffsetY = { fullHeight -> fullHeight }
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberAnimatedNavController(),
    showToast: (String) -> Toast,
    onBack: () -> Unit
) {
    BackHandler {
        if (!navController.popBackStack()) {
            if (System.currentTimeMillis() > backKeyPressedTime + BACK_PRESS_DELAY_TIME) {
                backKeyPressedTime = System.currentTimeMillis()
                toast = showToast("\'뒤로\' 버튼 한번 더 누르시면 종료됩니다.")
                return@BackHandler
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + BACK_PRESS_DELAY_TIME) {
                toast?.cancel()
                onBack.invoke()
            }
        }
    }

    val scaffoldState = rememberScaffoldState()
    val showBottomBar = navController.currentBackStackEntryAsState().value?.destination?.route in tabs.map { it.route }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    tabs.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(painterResource(screen.icon), null) },
                            label = { Text(stringResource(screen.resourceId)) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                val from = currentDestination?.route
                                val to = screen.route
                                if (from != to) {
                                    navController.navigate(screen.route) {
                                        popUpTo(0) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AnimatedNavHost(navController = navController, startDestination = "splash") {
                // 스플래시
                composable("splash") {
                    SplashScreen {
                        navController.navigate("home") {
                            popUpTo(0)
                        }
                    }
                }
                // 홈
                navigation(route = "home", startDestination = Screen.Tab1.route) {
                    tabs.forEach { screen ->
                        composable(
                            route = screen.route,
                            enterTransition = {
                                try {
                                    val from = initialState.destination.route?.substring(3)?.toInt() ?: 0
                                    val to = screen.route.substring(3).toInt()
                                    slideInHorizontally(initialOffsetX = { fullWidth -> if (from < to) fullWidth else -fullWidth })
                                } catch (e: Exception) {
                                    slideInHorizontally(initialOffsetX = { 0 })
                                }
                            },
                            exitTransition = {
                                try {
                                    val from = screen.route.substring(3).toInt()
                                    val to = targetState.destination.route?.substring(3)?.toInt() ?: 0
                                    slideOutHorizontally(targetOffsetX = { fullWidth -> if (from < to) -fullWidth else fullWidth })
                                } catch (e: Exception) {
                                    slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth })
                                }
                            }
                        ) {
                            screen.content.invoke(
                                // showSnackbar
                                { text ->
                                    scope.launch {
                                        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                        scaffoldState.snackbarHostState.showSnackbar(message = text)
                                    }
                                },
                                // 상세화면 네비게이션
                                { route -> navController.navigate(route) }
                            )
                        }
                    }
                }
                // 상세
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
            DefaultSnackbar(
                snackbarHostState = scaffoldState.snackbarHostState,
                onDismiss = { scaffoldState.snackbarHostState.currentSnackbarData?.dismiss() },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}