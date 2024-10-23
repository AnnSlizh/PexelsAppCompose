package by.slizh.pexelsappcompose.presentation.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")
    object BookmarksScreen : Screen(route = "bookmarks_screen")
    object DetailsScreen : Screen(route = "details_screen/{photoId}") {
        fun createRoute(photoId: Int) = "details_screen/$photoId"
    }
}