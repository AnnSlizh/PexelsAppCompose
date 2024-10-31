package by.slizh.pexelsappcompose.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.pexelsappcompose.presentation.components.CustomFilterChip
import by.slizh.pexelsappcompose.presentation.components.CustomSearchBar
import by.slizh.pexelsappcompose.presentation.components.PhotoItem
import by.slizh.pexelsappcompose.presentation.navigation.Screen
import by.slizh.pexelsappcompose.presentation.viewModels.homePhotos.PhotoEvent
import by.slizh.pexelsappcompose.presentation.viewModels.homePhotos.PhotoViewModel
import by.slizh.pexelsappcompose.ui.theme.Red


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, photoViewModel: PhotoViewModel = hiltViewModel()) {
    val state by photoViewModel.state.collectAsState()
    var selectedChip by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        CustomSearchBar(
            query = state.query,
            onQueryChange = { query ->
                photoViewModel.onEvent(PhotoEvent.SearchPhotos(query))
                if (query.isEmpty()) selectedChip = null
            },
            onSearch = { photoViewModel.onEvent(PhotoEvent.SearchPhotos(state.query)) },
            onClear = {
                photoViewModel.onEvent(PhotoEvent.SearchPhotos(""))
                selectedChip = null
            }
        )
        if (state.featuredCollection != null) {
            LazyRow(modifier = Modifier.padding(start = 24.dp)) {
                items(state.featuredCollection!!.collections) { collection ->
                    CustomFilterChip(
                        collection = collection,
                        selectedChip = selectedChip,
                        onChipClick = { chipTitle ->
                            selectedChip = chipTitle
                            photoViewModel.onEvent(PhotoEvent.SelectFeatureCollection(chipTitle))
                        },
                        query = state.query
                    )
                }
            }
        }
        if (state.isLoading) LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            color = Red
        )
//            if (state.error != null) {
//                Text(
//                    text = state.error.toString(),
//                    color = Color.Red,
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//            }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2), verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp)
        ) {
            items(state.photos) { photo ->
                PhotoItem(
                    photo = photo,
                    showDetailsPhoto = {
                        navController.navigate(
                            Screen.DetailsScreen.createRoute(
                                photo.id
                            )
                        )
                    })

            }
        }
    }
}

