package by.slizh.pexelsappcompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import by.slizh.pexelsappcompose.R
import by.slizh.pexelsappcompose.ui.theme.DarkGrayLightTheme
import by.slizh.pexelsappcompose.ui.theme.GrayLightTheme
import by.slizh.pexelsappcompose.ui.theme.mulishFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onClear: () -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            Text(
                text = "Search",
                color = DarkGrayLightTheme,
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Normal
            )
        },
        leadingIcon = {
            Image(
                painterResource(id = R.drawable.search_icon),
                contentDescription = null,
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear",
                        tint = DarkGrayLightTheme
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 15.dp, end = 24.dp, bottom = 24.dp)
            .background(
                GrayLightTheme,
                shape = RoundedCornerShape(50.dp)
            ),
        singleLine = true,
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = GrayLightTheme,
            unfocusedBorderColor = GrayLightTheme,
            cursorColor = DarkGrayLightTheme
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
            }
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        )
    )
}
