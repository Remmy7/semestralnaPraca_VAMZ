package com.example.semestralnapraca_vamz.ui
import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.semestralnapraca_vamz.MainActivity
import com.example.semestralnapraca_vamz.ui.theme.SemestralnaPraca_VAMZTheme

@Composable
fun MainMenu(
    viewModel: MainViewModel = viewModel(),
    preferenceHelper: SharedPreferences?
) {
    // Load preferenceHelper when starting app
    preferenceHelper?.let { prefs ->
        val level = viewModel.level
        val gold = viewModel.gold
        val legacy = viewModel.legacy

        // Retrieve values from SharedPreferences
        val savedLevel = prefs.getInt(MainActivity.PreferenceHelper._level, 1)
        val savedGold = prefs.getInt(MainActivity.PreferenceHelper._gold, 600)
        val savedLegacy = prefs.getInt(MainActivity.PreferenceHelper._legacy, 0)

        // Save values to ViewModel
        viewModel.setLevel(savedLevel)
        viewModel.setGold(savedGold)
        viewModel.setLegacy(savedLegacy)


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color(0xFFB36800))
        ) {
            Text(
                text = "Idle Game",
                modifier = Modifier.padding(vertical = 16.dp),
                //style = MaterialTheme.typography.h4,
                color = Color.White
            )

            StatsLayout(level = level, gold = gold, legacy = legacy)

            ButtonsLayout()
        }
    }
}

@Composable
fun StatsLayout(level: MutableState<Int>, gold: MutableState<Int>, legacy: MutableState<Int>) {
    Column {
        StatItem(name = "Level", value = level)
        StatItem(name = "Gold", value = gold)
        StatItem(name = "Legacy", value = legacy)
    }
}

@Composable
fun StatItem(name: String, value: MutableState<Int>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$name: ",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
        Text(
            text = value.value.toString(),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
    }
}

@Composable
fun ButtonsLayout() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Button(
            onClick = { /* TODO() */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            //colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE21030))
        ) {
            Text(text = "Fight")
        }

        Button(
            onClick = { /* TODO() */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            //colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF127891))
        ) {
            Text(text = "Blacksmith")
        }

        Button(
            onClick = { /* TODO() */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            //colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF966304))
        ) {
            Text(text = "Units")
        }

        Button(
            onClick = { /* TODO() */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            //colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF8F804))
        ) {
            Text(text = "Legacy")
        }

        Button(
            onClick = { /* TODO() */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            //colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF696262))
        ) {
            Text(text = "Settings")
        }
    }
}

class MainViewModel : ViewModel() {
    private val _level = mutableStateOf(1)
    val level: MutableState<Int> = _level

    private val _gold = mutableStateOf(600)
    val gold: MutableState<Int> = _gold

    private val _legacy = mutableStateOf(0)
    val legacy: MutableState<Int> = _legacy

    fun setLevel(newLevel: Int) {
        _level.value = newLevel
    }

    fun setGold(newGold: Int) {
        _gold.value = newGold
    }

    fun setLegacy(newLegacy: Int) {
        _legacy.value = newLegacy
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuPreview() {
    SemestralnaPraca_VAMZTheme {
        val viewModel = MainViewModel()


        val mockPreferenceHelper = object : SharedPreferences {

            override fun getAll(): MutableMap<String, *> {
                return mutableMapOf<String, Any?>()
            }

            override fun getString(key: String?, defValue: String?): String? {
                return null
            }

            override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? {
                return null
            }

            override fun getInt(key: String?, defValue: Int): Int {
                return 0
            }

            override fun getLong(key: String?, defValue: Long): Long {
                return 0L
            }

            override fun getFloat(key: String?, defValue: Float): Float {
                return 0f
            }

            override fun getBoolean(key: String?, defValue: Boolean): Boolean {
                return false
            }

            override fun contains(key: String?): Boolean {
                return false
            }

            override fun edit(): SharedPreferences.Editor {
                throw NotImplementedError("Mock SharedPreferences doesn't support editing")
            }

            override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {}

            override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {}
        }

        MainMenu(viewModel = viewModel, preferenceHelper = mockPreferenceHelper)
    }
}


