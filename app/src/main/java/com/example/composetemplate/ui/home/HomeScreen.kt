package com.example.composetemplate.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composetemplate.R
import com.example.composetemplate.ui.home.tab1.Tab1Screen
import com.example.composetemplate.ui.home.tab2.Tab2Screen
import com.example.composetemplate.ui.home.tab3.Tab3Screen
import com.example.composetemplate.ui.home.tab4.Tab4Screen

sealed class Screen(
    @DrawableRes val icon: Int,
    val route: String,
    @StringRes val resourceId: Int,
    val content: (@Composable ()->Unit)
) {
    object Tab1 : Screen(R.drawable.ic_place, "tab1", R.string.tab1, { Tab1Screen() })
    object Tab2 : Screen(R.drawable.ic_chat, "tab2", R.string.tab2, { Tab2Screen() })
    object Tab3 : Screen(R.drawable.ic_camera, "tab3", R.string.tab3, { Tab3Screen() })
    object Tab4 : Screen(R.drawable.ic_payment, "tab4", R.string.tab4, { Tab4Screen() })
}

val items = listOf(Screen.Tab1, Screen.Tab2, Screen.Tab3, Screen.Tab4)

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(
            title = { Text(stringResource(R.string.app_name)) },
            navigationIcon = { IconButton(onClick = { }) {
                Icon(Icons.Default.Menu, "Menu")
            } }
        ) },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(painterResource(screen.icon), null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = Screen.Tab1.route, Modifier.padding(innerPadding)) {
            items.forEach { screen ->
                composable(screen.route) { screen.content.invoke() }
            }
        }
    }
}