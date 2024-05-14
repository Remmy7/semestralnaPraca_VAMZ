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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.semestralnapraca_vamz.MainActivity
import com.example.semestralnapraca_vamz.ui.theme.SemestralnaPraca_VAMZTheme
import com.example.semestralnapraca_vamz.viewModels.MainMenuViewModel

var landscapeFight: Boolean = false

@Composable
fun MainMenu(
    context: Context,
    isLandscape: Boolean,
    navController: NavController
) {
    landscapeFight = isLandscape


    val viewModel = remember { MainMenuViewModel(context) }

    val level = viewModel.level
    val gold = viewModel.gold
    val legacy = viewModel.legacy



    MainMenuContent(level, gold, legacy, isLandscape, viewModel, context, navController)
}

@Composable
fun MainMenuContent(
    level: MutableState<Int>,
    gold: MutableState<Int>,
    legacy: MutableState<Int>,
    isLandscape: Boolean,
    viewModel: MainMenuViewModel,
    context: Context,
    navController: NavController
) {
    if (isLandscape) {
        LandscapeLayout(level, gold, legacy, viewModel, context, navController)
    } else {
        PortraitLayout(level, gold, legacy, viewModel, context, navController)
    }
}
@Composable
fun LandscapeLayout(

    level: MutableState<Int>,
    gold: MutableState<Int>,
    legacy: MutableState<Int>,
    viewModel: MainMenuViewModel,
    context: Context,
    navController: NavController
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
                  viewModel.setLevel(level.value + 1)
                },
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
                    onClick = {
                        navController.navigate("units_menu")
                    },
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
                    onClick = {
                        navController.navigate("spell_upgrade_menu")
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.2f)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF127891),
                        contentColor = Color.Black
                    ),
                ) {
                    Text(text = "Spells", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }


                Button(
                    onClick = {
                        navController.navigate("legacy_menu")
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.23f)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF8F804),
                        contentColor = Color.Black
                    ),

                    ) {
                    Text(text = "Legacy", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
@Composable
fun PortraitLayout(
    level: MutableState<Int>,
    gold: MutableState<Int>,
    legacy: MutableState<Int>,
    viewModel: MainMenuViewModel,
    context: Context,
    navController: NavController
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

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFF5C2402)),
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {

            Button(
                onClick = {
                    navController.navigate("fight_menu")
                },
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
                onClick = {
                    navController.navigate("units_menu")
                },
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
                onClick = {
                    navController.navigate("spell_upgrade_menu")
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.15f)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF127891),
                    contentColor = Color.Black
                ),

                ) {
                Text(text = "Spells", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }


            Button(
                onClick = {
                    navController.navigate("legacy_menu")
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.18f)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF8F804),
                    contentColor = Color.Black
                ),

                ) {
                Text(text = "Legacy", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
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


@Preview
@Composable
fun MainMenuPreview() {
    val context = LocalContext.current
    val navController = rememberNavController()

    MainMenu(
        context,
        isLandscape = false,
        navController
    )
}
@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun MainMenuPreviewLandscape() {
    val context = LocalContext.current
    val navController = rememberNavController()

    MainMenu(
        context,
        isLandscape = true,
        navController
    )
}



