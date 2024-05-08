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
import com.example.semestralnapraca_vamz.ui.MainViewModel
import com.example.semestralnapraca_vamz.ui.theme.SemestralnaPraca_VAMZTheme
import com.example.semestralnapraca_vamz.viewModels.MainMenuViewModel

class MainActivity : ComponentActivity() {
    private lateinit var preferenceHelper: SharedPreferences
    private lateinit var viewModel: MainMenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceHelper = getSharedPreferences("IdleGame", Context.MODE_PRIVATE)
        setContent {
            SemestralnaPraca_VAMZTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isLandscape = remember {
                        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
                    }
                    MainMenu(viewModel = viewModel, preferenceHelper = preferenceHelper, isLandscape = isLandscape)
                }
            }
        }
    }

    // This object holds all values of SharedPreferences
    // it also sets every string value into a separate variable like _level
    object PreferenceHelper { //https://www.journaldev.com/234/android-sharedpreferences-kotlin
        val _level = "level"
        val _currentExperience = "currentExperience"
        val _levelUpExperience = "levelUpExperience"
        val _gold = "gold"
        val _legacy = "legacy"
        val _monsterLevel = "monsterLevel"
        val _monsterHealth = "monsterHealth"
        val _monsterMaxHealth = "monsterMaxHealth"

        val _wizardLevel = "wizardLevel"
        val _wizardWeaponLevel = "wizardWeaponLevel"
        val _archerLevel = "archerLevel"
        val _archerWeaponLevel = "archerWeaponLevel"
        val _knightLevel = "knightLevel"
        val _knightWeaponLevel = "knightWeaponLevel"
        val _mysticLevel = "mysticLevel"
        val _mysticWeaponLevel = "mysticWeaponLevel"
    }

    object PreferenceHelperLegacy {
        val _legacyWizardLevel = "legacyWizardLevel"
        val _legacyWizardWeaponLevel = "legacyWizardWeaponLevel"
        val _legacyArcherLevel = "legacyArcherLevel"
        val _legacyArcherWeaponLevel = "legacyArcherWeaponLevel"
        val _legacyKnightLevel = "legacyKnightLevel"
        val _legacyKnightWeaponLevel = "legacyKnightWeaponLevel"
        val _legacyMysticLevel = "legacyMysticLevel"
        val _legacyMysticWeaponLevel = "legacyMysticWeaponLevel"
    }
}