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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import com.example.semestralnapraca_vamz.viewModels.FightMenuViewModel
import com.example.semestralnapraca_vamz.viewModels.LegacyMenuViewModel
import com.example.semestralnapraca_vamz.viewModels.MainMenuViewModel
import com.example.semestralnapraca_vamz.viewModels.SpellUpgradeMenuViewModel
import com.example.semestralnapraca_vamz.viewModels.UnitsMenuViewModel

class MainActivity : ComponentActivity() {
    private lateinit var preferenceHelper: SharedPreferences
    private lateinit var viewModelMainMenu: MainMenuViewModel
    private lateinit var viewModelFight: FightMenuViewModel
    private lateinit var viewModelLegacy: LegacyMenuViewModel
    private lateinit var viewModelSpellUpgrade: SpellUpgradeMenuViewModel
    private lateinit var viewModelUnits: UnitsMenuViewModel


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
                    viewModelMainMenu = MainMenuViewModel(context)
                    viewModelFight = FightMenuViewModel(context)
                    viewModelLegacy = LegacyMenuViewModel(context)
                    viewModelSpellUpgrade = SpellUpgradeMenuViewModel(context)
                    viewModelUnits = UnitsMenuViewModel(context)
                    NavHost(navController = navController, startDestination = "main_menu") {
                        composable("main_menu") {
                            MainMenu(context, isLandscape, navController, viewModelMainMenu)
                            reloadAllViewModels(context)
                        }
                        composable("fight_menu") {
                            FightMenu(context, isLandscape, navController, viewModelFight)
                            reloadAllViewModels(context)
                        }
                        composable("legacy_menu") {
                            LegacyMenu(context, isLandscape, navController, viewModelLegacy)
                            reloadAllViewModels(context)
                        }
                        composable("units_menu") {
                            UnitsMenu(context, isLandscape, navController, viewModelUnits)
                            reloadAllViewModels(context)
                        }
                        composable("spell_upgrade_menu") {
                            SpellUpgradeMenu(context, isLandscape, navController, viewModelSpellUpgrade)
                            reloadAllViewModels(context)
                        }
                    }
                }
            }
        }
    }
    fun navigateBack(navController: NavHostController) {
        navController.popBackStack()
    }

    fun reloadAllViewModels(context: Context) {
        viewModelFight.reloadFight()
        viewModelMainMenu.reloadMain()
        viewModelUnits.reloadUnits()
        viewModelSpellUpgrade.reloadSpell()
        viewModelLegacy.reloadLegacy()

    }
//    enum class IdleGameScreen {
//        MainMenu,
//        FightMenu,
//        SettingsMenu,
//        BlacksmithMenu,
//        UnitsMenu,
//        LegacyMenu
//    }
//    fun navigateTo(screen: IdleGameScreen, navController: NavHostController) {
//        when (screen) {
//            IdleGameScreen.BlacksmithMenu -> navController.navigate("blacksmith_menu")
//            IdleGameScreen.FightMenu -> navController.navigate("fight_menu")
//            IdleGameScreen.LegacyMenu -> navController.navigate("legacy_menu")
//            IdleGameScreen.MainMenu -> navController.navigate("main_menu")
//            //IdleGameScreen.SettingsMenu -> navController.navigate("settings_menu")
//            IdleGameScreen.UnitsMenu -> navController.navigate("units_menu")
//        }
//    }
}
