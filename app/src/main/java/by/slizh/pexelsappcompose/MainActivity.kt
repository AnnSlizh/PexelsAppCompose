package by.slizh.pexelsappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.slizh.pexelsappcompose.presentation.components.BottomNavigationBar
import by.slizh.pexelsappcompose.presentation.components.currentRoute
import by.slizh.pexelsappcompose.presentation.navigation.Screen
import by.slizh.pexelsappcompose.presentation.screens.BookmarksScreen
import by.slizh.pexelsappcompose.presentation.screens.DetailsScreen
import by.slizh.pexelsappcompose.presentation.screens.HomeScreen
import by.slizh.pexelsappcompose.ui.theme.PexelsAppComposeTheme
import by.slizh.pexelsappcompose.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PexelsAppComposeTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = White, bottomBar = {
                    if (currentRoute(navController) != Screen.DetailsScreen.route) {
                        BottomNavigationBar(navController = navController)
                    }
                }) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.HomeScreen.route) { HomeScreen(navController) }
                        composable(Screen.BookmarksScreen.route) { BookmarksScreen() }
                        composable(
                            route = Screen.DetailsScreen.route,
                            arguments = listOf(navArgument("photoId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val photoId = backStackEntry.arguments?.getInt("photoId")
                            DetailsScreen(navController, photoId = photoId)
                        }
                    }
                }
            }
        }
    }
}

