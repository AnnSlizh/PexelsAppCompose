package by.slizh.pexelsappcompose.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import by.slizh.pexelsappcompose.R
import by.slizh.pexelsappcompose.domain.model.Photo
import coil.compose.AsyncImage

@Composable
fun PhotoItem(photo: Photo) {
    Column(modifier = Modifier.padding(8.dp)) {
        AsyncImage(
            model = photo.src.original,
            contentDescription = photo.alt,
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)),
            placeholder = painterResource(id = R.drawable.placeholder)
        )
    }
}