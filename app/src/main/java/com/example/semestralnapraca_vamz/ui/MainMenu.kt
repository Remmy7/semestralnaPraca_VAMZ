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
    isLandscape: Boolean
) {
    landscapeFight = isLandscape
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainActivity.IdleGameScreen.MainMenu.name) {
        composable("mainMenu") {
            //MainMenu(context, landscapeFight) //doesn't work yet
        }
        composable("fightMenu") {
            //FightMenu(context, landscapeFight)
        }
    }

    val viewModel = remember { MainMenuViewModel(context) }

    val level = viewModel.level
    val gold = viewModel.gold
    val legacy = viewModel.legacy



    MainMenuContent(level, gold, legacy, isLandscape, viewModel, context)
}

@Composable
fun MainMenuContent(
    level: MutableState<Int>,
    gold: MutableState<Int>,
    legacy: MutableState<Int>,
    isLandscape: Boolean,
    viewModel: MainMenuViewModel,
    context: Context
) {
    if (isLandscape) {
        LandscapeLayout(level, gold, legacy, viewModel, context)
    } else {
        PortraitLayout(level, gold, legacy, viewModel, context)
    }
}
@Composable
fun LandscapeLayout(

    level: MutableState<Int>,
    gold: MutableState<Int>,
    legacy: MutableState<Int>,
    viewModel: MainMenuViewModel,
    context: Context
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
    legacy: MutableState<Int>,
    viewModel: MainMenuViewModel,
    context: Context
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

@Preview
@Composable
fun MainMenuPreview() {
    val context = LocalContext.current

    MainMenu(
        context,
        isLandscape = false
    )
}



