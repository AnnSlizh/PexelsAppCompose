package by.slizh.pexelsappcompose.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.pexelsappcompose.R
import by.slizh.pexelsappcompose.domain.model.Photo
import by.slizh.pexelsappcompose.ui.theme.Black
import by.slizh.pexelsappcompose.ui.theme.White
import by.slizh.pexelsappcompose.ui.theme.mulishFamily
import coil.compose.AsyncImage


@Composable
fun BookmarkItem(bookmark: Photo, showDetailsPhoto: () -> Unit) {

    Column(modifier = Modifier
        .padding(8.dp)
        .clickable { showDetailsPhoto() }) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
        ) {
            AsyncImage(
                model = bookmark.src.original,
                contentDescription = bookmark.alt,
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = painterResource(id = R.drawable.picture)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Black.copy(alpha = 0.4f))
                    .fillMaxWidth()
                    .padding(top = 6.dp, bottom = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = bookmark.photographer, fontFamily = mulishFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = White
                )
            }
        }
    }
}
