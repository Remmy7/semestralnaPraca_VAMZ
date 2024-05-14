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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.semestralnapraca_vamz.ui.FightMenu
import com.example.semestralnapraca_vamz.ui.LegacyMenu
import com.example.semestralnapraca_vamz.ui.MainMenu
import com.example.semestralnapraca_vamz.ui.SpellUpgradeMenu
import com.example.semestralnapraca_vamz.ui.UnitsMenu
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
                    val navController = rememberNavController()
                    val isLandscape = remember {
                        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
                    }
                    val context: Context = this
                    NavHost(navController = navController, startDestination = "main_menu") {
                        composable("main_menu") {
                            MainMenu(context, isLandscape, navController)
                        }
                        composable("fight_menu") {
                            FightMenu(context, isLandscape, navController)
                        }
                        composable("legacy_menu") {
                            LegacyMenu(context, isLandscape, navController)
                        }
                        composable("units_menu") {
                            UnitsMenu(context, isLandscape, navController)
                        }
                        composable("spell_upgrade_menu") {
                            SpellUpgradeMenu(context, isLandscape, navController)
                        }
                    }
                }
            }
        }
    }
    enum class IdleGameScreen {
        MainMenu,
        FightMenu,
        SettingsMenu,
        BlacksmithMenu,
        UnitsMenu,
        LegacyMenu
    }
    fun navigateTo(screen: IdleGameScreen, navController: NavHostController) {
        when (screen) {
            IdleGameScreen.BlacksmithMenu -> navController.navigate("blacksmith_menu")
            IdleGameScreen.FightMenu -> navController.navigate("fight_menu")
            IdleGameScreen.LegacyMenu -> navController.navigate("legacy_menu")
            IdleGameScreen.MainMenu -> navController.navigate("main_menu")
            IdleGameScreen.SettingsMenu -> navController.navigate("settings_menu")
            IdleGameScreen.UnitsMenu -> navController.navigate("units_menu")
        }
    }
}
