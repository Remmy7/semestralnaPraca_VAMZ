package com.example.semestralnapraca_vamz

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.semestralnapraca_vamz.ui.MainMenu
import com.example.semestralnapraca_vamz.ui.theme.SemestralnaPraca_VAMZTheme
import com.example.semestralnapraca_vamz.viewModels.MainMenuViewModel

class MainActivity : ComponentActivity() {
    private lateinit var preferenceHelper: SharedPreferences
    private lateinit var viewModel: MainMenuViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SemestralnaPraca_VAMZTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isLandscape = remember {
                        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
                    }
                    MainMenu(this, isLandscape)
                }
            }
        }
    }


}