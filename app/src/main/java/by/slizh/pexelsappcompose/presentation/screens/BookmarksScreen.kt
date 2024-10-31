package by.slizh.pexelsappcompose.presentation.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.pexelsappcompose.presentation.components.BookmarkItem
import by.slizh.pexelsappcompose.presentation.navigation.Screen
import by.slizh.pexelsappcompose.presentation.viewModels.bookmarks.BookmarksEvent
import by.slizh.pexelsappcompose.presentation.viewModels.bookmarks.BookmarksViewModel
import by.slizh.pexelsappcompose.ui.theme.Red
import by.slizh.pexelsappcompose.ui.theme.mulishFamily

@Composable
fun BookmarksScreen(
    navController: NavController,
    bookmarksViewModel: BookmarksViewModel = hiltViewModel()
) {

    val state by bookmarksViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        bookmarksViewModel.onEvent(BookmarksEvent.LoadBookmarks)
    }

    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Bookmarks",
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        if (state.isLoading) LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            color = Red
        ) else if (state.error != null) {
            Text(
                text = state.error.toString(),
                color = Red
            )
        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2), verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp)
            ) {
                items(state.bookmarks) { bookmark ->
                    BookmarkItem(
                        bookmark = bookmark,
                        showDetailsPhoto = {
                            navController.navigate(
                                Screen.DetailsScreen.createRoute(
                                    bookmark.id
                                )
                            )
                        })
                }
            }
        }
    }
}
