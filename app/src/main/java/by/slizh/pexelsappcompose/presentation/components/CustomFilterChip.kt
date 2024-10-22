package by.slizh.pexelsappcompose.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import by.slizh.pexelsappcompose.domain.model.CollectionModel
import by.slizh.pexelsappcompose.ui.theme.Black
import by.slizh.pexelsappcompose.ui.theme.GrayLightTheme
import by.slizh.pexelsappcompose.ui.theme.Red
import by.slizh.pexelsappcompose.ui.theme.White
import by.slizh.pexelsappcompose.ui.theme.mulishFamily

@Composable
fun CustomFilterChip(
    collection: CollectionModel,
    selectedChip: String?,
    onChipClick: (String) -> Unit,
    query: String
) {
    FilterChip(
        selected = selectedChip == collection.title || query == collection.title,
        onClick = { onChipClick(collection.title) },
        label = {
            Text(
                text = collection.title, fontFamily = mulishFamily,
                fontWeight = FontWeight.Normal
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = GrayLightTheme,
            selectedContainerColor = Red,
            labelColor = Black,
            selectedLabelColor = White
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = selectedChip == collection.title || query == collection.title,
            borderColor = Color.Transparent,
            selectedBorderColor = Color.Transparent
        ),
        modifier = Modifier.padding(end = 11.dp),
        shape = RoundedCornerShape(100.dp)
    )
}