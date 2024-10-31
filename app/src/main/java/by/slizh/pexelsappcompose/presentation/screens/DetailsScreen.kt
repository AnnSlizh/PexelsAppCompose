package by.slizh.pexelsappcompose.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.pexelsappcompose.R
import by.slizh.pexelsappcompose.presentation.components.CustomTopAppBar
import by.slizh.pexelsappcompose.presentation.viewModels.photoDetails.PhotoDetailsEvent
import by.slizh.pexelsappcompose.presentation.viewModels.photoDetails.PhotoDetailsViewModel
import by.slizh.pexelsappcompose.ui.theme.Red
import coil.compose.AsyncImage

@Composable
fun DetailsScreen(
    navController: NavController,
    photoId: Int?,
    photoDetailsViewModel: PhotoDetailsViewModel = hiltViewModel()
) {
    val state by photoDetailsViewModel.state.collectAsState()
    val context = LocalContext.current
    val downloader = hiltViewModel<PhotoDetailsViewModel>().downloader

    LaunchedEffect(key1 = photoId) {
        photoId?.let {
            photoDetailsViewModel.onEvent(PhotoDetailsEvent.LoadPhotoById(it))
            photoDetailsViewModel.onEvent(PhotoDetailsEvent.CheckBookmarkStatus(it))
        }
    }

    Column {
        CustomTopAppBar(
            navController = navController,
            photographer = state.photo?.photographer ?: ""
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 24.dp)
        ) {
            item {
                if (state.isLoading) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        color = Red
                    )
                } else if (state.error != null) {
                    Text(
                        text = state.error.toString(),
                        color = Red
                    )
                } else {
                    state.photo?.let { photo ->
                        AsyncImage(
                            model = photo.src.original,
                            contentDescription = photo.alt,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp)),
                            placeholder = painterResource(id = R.drawable.placeholder),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(48.dp),
                                onClick = {
                                    downloader.downloadFile(photo.src.original)
                                    Toast.makeText(
                                        context,
                                        "Photo download has started",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }) {
                                Image(
                                    painter = painterResource(id = R.drawable.download_button),
                                    contentDescription = "Download image"
                                )
                            }

                            val bookmarkIcon = if (state.isBookmarked) {
                                painterResource(id = R.drawable.save_bookmark_button_active)
                            } else {
                                painterResource(id = R.drawable.save_bookmark_button_inactive)
                            }

                            IconButton(modifier = Modifier
                                .width(48.dp)
                                .height(48.dp),
                                onClick = {
                                    if (state.isBookmarked) photoDetailsViewModel.onEvent(
                                        PhotoDetailsEvent.DeleteBookmark(photo)
                                    )
                                    else photoDetailsViewModel.onEvent(PhotoDetailsEvent.BookmarkPhoto)
                                }) {
                                Image(
                                    painter = bookmarkIcon,
                                    contentDescription = "Save bookmark"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
