package com.example.semestralnapraca_vamz.ui
import android.content.Context
import android.content.SharedPreferences
import android.provider.CalendarContract.Colors
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.semestralnapraca_vamz.MainActivity
import com.example.semestralnapraca_vamz.ui.theme.SemestralnaPraca_VAMZTheme
import com.example.semestralnapraca_vamz.viewModels.MainMenuViewModel



@Composable
fun MainMenu(
    viewModel: MainMenuViewModel = viewModel(),
    preferenceHelper: SharedPreferences,
    isLandscape: Boolean


) {
    println("ok2")
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
        println("ok3")

        MainMenuContent(level, gold, legacy, isLandscape)
    }
}
@Composable
fun MainMenuContent(
    level: MutableState<Int>,
    gold: MutableState<Int>,
    legacy: MutableState<Int>,
    isLandscape: Boolean
) {
    if (isLandscape) {
        LandscapeLayout(level, gold, legacy)
    } else {
        PortraitLayout(level, gold, legacy)
    }
}
@Composable
fun LandscapeLayout(
    level: MutableState<Int>,
    gold: MutableState<Int>,
    legacy: MutableState<Int>
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB36800))
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)

        ) {
            Text(
                text = "Idle Game",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .background(Color(0xFF5C2402))
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            ) {
                StatsLayout(level = level, gold = gold, legacy = legacy)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    /*val level = viewModel.level
                    viewModel.setLevel(level.value + 1);
                          */},
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE21030),
                    contentColor = Color.Black
                ),
            ) {
                Text(text = "Fight", fontSize = 50.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Right Column
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .fillMaxHeight()
                .fillMaxWidth()

        ) {
            Column(
                modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color(0xFF5C2402))
                ,
                verticalArrangement = Arrangement.SpaceEvenly,

                ) {
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.15f)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF127891),
                        contentColor = Color.Black
                    ),
                ) {
                    Text(text = "Blacksmith", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.2f)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF966304),
                        contentColor = Color.Black
                    ),
                ) {
                    Text(text = "Units", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }


                Button(
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.2f)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF8F804),
                        contentColor = Color.Black
                    ),

                    ) {
                    Text(text = "Legacy", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxHeight(0.25f)
                        .fillMaxWidth(0.9f)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF696262),
                        contentColor = Color.Black
                    ),
                ) {
                    Text(text = "Settings", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
@Composable
fun PortraitLayout(
    level: MutableState<Int>,
    gold: MutableState<Int>,
    legacy: MutableState<Int>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB36800))
    ) {
        Text(
            text = "Idle Game",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxHeight(0.2f)
                .background(Color(0xFF5C2402))
        ) {
            StatsLayout(level = level, gold = gold, legacy = legacy)
        }

        ButtonsLayout(isLandscape = false)
    }
}

@Composable
fun StatsLayout(level: MutableState<Int>, gold: MutableState<Int>, legacy: MutableState<Int>) {
    Column {
        StatItem(name = "Level", value = level, Color.White, Color.Green)
        StatItem(name = "Gold", value = gold, Color.White, Color.Yellow)
        StatItem(name = "Legacy", value = legacy, Color.White, Color.White)
    }
}

@Composable
fun StatItem(name: String, value: MutableState<Int>, color1: Color, color2: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$name: ",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            color = color1,

        )
        Text(
            text = value.value.toString(),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            color = color2,
            fontWeight = FontWeight.Bold
        )
    }
}



@Composable
fun ButtonsLayout(isLandscape: Boolean) {
    val buttonModifierPortrait = Modifier
        .fillMaxWidth(0.7f)
        .heightIn(min = 48.dp)
    val buttonModifierLandscape = Modifier
        .fillMaxWidth(0.7f)
        .heightIn(min = 48.dp)
    if (isLandscape) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFF5C2402)),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF127891),
                        contentColor = Color.Black
                    ),


                    ) {
                    Text(text = "Blacksmith")
                }

                Button(
                    onClick = {  },
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF966304),
                        contentColor = Color.Black
                    ),

                    ) {
                    Text(text = "Units")
                }
            }

        }

    } else {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFF5C2402)),
            verticalArrangement = Arrangement.SpaceEvenly)
        {

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.20f)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE21030),
                    contentColor = Color.Black
                ),


                ) {
                Text(text = "Fight", fontSize = 40.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.15f)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF127891),
                    contentColor = Color.Black
                ),

                ) {
                Text(text = "Blacksmith", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.15f)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF966304),
                    contentColor = Color.Black
                ),
            ) {
                Text(text = "Units", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }


            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.15f)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF8F804),
                    contentColor = Color.Black
                ),

                ) {
                Text(text = "Legacy", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.18f)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF696262),
                    contentColor = Color.Black
                ),
            ) {
                Text(text = "Settings", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
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

        MainMenu(viewModel = MainMenuViewModel(), preferenceHelper = mockPreferenceHelper, true)
    }
}


