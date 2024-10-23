package by.slizh.pexelsappcompose.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import by.slizh.pexelsappcompose.R
import by.slizh.pexelsappcompose.presentation.navigation.Screen
import by.slizh.pexelsappcompose.ui.theme.Black
import by.slizh.pexelsappcompose.ui.theme.DarkGrayLightTheme
import by.slizh.pexelsappcompose.ui.theme.Red
import by.slizh.pexelsappcompose.ui.theme.White

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(Screen.HomeScreen, Screen.BookmarksScreen)
    val currentRoute = currentRoute(navController)

    NavigationBar(
        containerColor = White,
        contentColor = Black
    ) {
        items.forEach { screen ->
            val isSelected = currentRoute == screen.route
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = when (screen) {
                                Screen.HomeScreen -> R.drawable.home_inactive_button
                                Screen.BookmarksScreen -> R.drawable.bookmark_inactive_button
                                else -> throw IllegalStateException("Screen not supported in BottomNavigationBar")
                            }
                        ),
                        contentDescription = screen.route
                    )
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Red,
                    unselectedIconColor = DarkGrayLightTheme,
                    indicatorColor = White,
                    disabledIconColor = White
                ),
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}