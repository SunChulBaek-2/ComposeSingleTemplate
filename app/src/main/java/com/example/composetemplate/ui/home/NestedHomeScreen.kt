package com.example.composetemplate.ui.home

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composetemplate.R
import com.example.composetemplate.ui.home.tab1.Tab1Screen
import com.example.composetemplate.ui.home.tab2.Tab2Screen
import com.example.composetemplate.ui.home.tab3.Tab3Screen
import com.example.composetemplate.ui.home.tab4.Tab4Screen
import com.example.composetemplate.ui.widget.DefaultSnackbar
import kotlinx.coroutines.launch

sealed class Screen(
    @DrawableRes val icon: Int,
    val route: String,
    @StringRes val resourceId: Int,
    val content: (@Composable ((String) -> Unit, (String) -> Unit) -> Unit)
) {
    object Tab1 : Screen(R.drawable.ic_place, "tab1", R.string.tab1, { showSnackbar, navigate ->
        Tab1Screen(showSnackbar = showSnackbar, navigate = navigate)
    })
    object Tab2 : Screen(R.drawable.ic_chat, "tab2", R.string.tab2, { showSnackbar, navigate ->
        Tab2Screen(showSnackbar = showSnackbar)
    })
    object Tab3 : Screen(R.drawable.ic_camera, "tab3", R.string.tab3, { showSnackbar, navigate ->
        Tab3Screen(showSnackbar = showSnackbar)
    })
    object Tab4 : Screen(R.drawable.ic_payment, "tab4", R.string.tab4, { showSnackbar, navigate ->
        Tab4Screen(showSnackbar = showSnackbar)
    })
}

val tabs = listOf(Screen.Tab1, Screen.Tab2, Screen.Tab3, Screen.Tab4)

const val BACK_PRESS_DELAY_TIME: Long = 2000
var backKeyPressedTime: Long = 0
var toast: Toast? = null

@Composable
fun NestedHomeScreen(
    navigate: (String) -> Unit,
    showToast: (String) -> Toast,
    onBack: () -> Unit
) {
    val navController = rememberNavController()
    val showTopBottomBar = navController.currentBackStackEntryAsState().value?.destination?.route in tabs.map { it.route}
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
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (showTopBottomBar) {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Menu, "Menu")
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (showTopBottomBar) {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    tabs.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(painterResource(screen.icon), null) },
                            label = { Text(stringResource(screen.resourceId)) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(0)
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = Screen.Tab1.route
            ) {
                // 하단탭
                tabs.forEach { screen ->
                    composable(screen.route) { backStackEntry ->
                        screen.content.invoke(
                            // showSnackbar
                            { text ->
                                scope.launch {
                                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                    scaffoldState.snackbarHostState.showSnackbar(message = text)
                                }
                            },
                            // navigate
                            { route ->
                                //navController.navigate(route)
                                navigate.invoke(route)
                            })
                    }
                }
                // 2-Depth
//                composable(
//                    route = "photo/{title}/{url}",
//                    arguments = listOf(
//                        navArgument("url" ) { type = NavType.StringType },
//                        navArgument("title") { type = NavType.StringType }
//                    )
//                ) { backStackEntry ->
//                    val title = backStackEntry.arguments?.getString("title")
//                    val encodedUrl = backStackEntry.arguments?.getString("url")
//                    val decodedUrl = String(Base64.decode(encodedUrl, 0))
//                    PhotoDetailScreen(title = title, url = decodedUrl)
//                }
            }
            DefaultSnackbar(
                snackbarHostState = scaffoldState.snackbarHostState,
                onDismiss = { scaffoldState.snackbarHostState.currentSnackbarData?.dismiss() },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}