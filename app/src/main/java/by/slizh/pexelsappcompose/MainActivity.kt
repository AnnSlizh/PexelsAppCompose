package by.slizh.pexelsappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import by.slizh.pexelsappcompose.presentation.screens.HomeScreen
import by.slizh.pexelsappcompose.presentation.viewModels.PhotoViewModel
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
                Surface(modifier = Modifier.fillMaxSize(), color = White) {
                    HomeScreen()
                }
            }
        }
    }
}
