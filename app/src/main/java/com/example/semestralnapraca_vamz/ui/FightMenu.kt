package com.example.semestralnapraca_vamz.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.semestralnapraca_vamz.viewModels.FightMenuViewModel

var landscape: Boolean = false
@Composable
fun FightMenu(
    context: Context,
    isLandscape: Boolean,
    navController: NavController
) {
    val viewModel = FightMenuViewModel(context)
    landscape = isLandscape
    FightMenuContent(isLandscape, viewModel, navController)
}

@Composable
fun FightMenuContent(
    isLandscape: Boolean,
    viewModel: FightMenuViewModel,
    navController: NavController
) {
    if (isLandscape) {
        LandscapeLayout(viewModel, navController)
    } else {
        PortraitLayout(viewModel, navController)
    }
}

@Composable
fun PortraitLayout(
    viewModel: FightMenuViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB36800))
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "Fight!",
                modifier = Modifier
                    .padding(bottom = 8.dp, top = 8.dp)
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = {
                    navController.navigate("main_menu")
                },
                modifier = Modifier
                    .padding(end = 8.dp)
                ) {
                Text(text = "Back", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }



        Column(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .background(Color(0xFF5C2402)),
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {

        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.15f)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .background(Color(0xFF5C2402)),
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {


        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFF5C2402)),
        )
        {
            monsterStats(viewModel, landscape)
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .background(Color(0xFF5C2402)),
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {


        }


    }
}
@Composable
fun monsterStats(
    viewModel: FightMenuViewModel,
    isLandscape: Boolean
) {
    val monsterName = viewModel.monsterName
    val monsterLevel = viewModel.monsterLevel
    val monsterHealth = viewModel.monsterHealth
    val monsterMaxHealth = viewModel.monsterMaxHealth

    if (isLandscape) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = monsterName,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                    color = Color.White
                )
                Text(
                    text = monsterLevel.toString(),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Column() {

            }
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = monsterName,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                    color = Color.White
                )
                Text(
                    text = monsterLevel.toString(),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )


            }
        }
    }

}
    /*Column(
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
        }*/

@Composable
fun LandscapeLayout(
    viewModel: FightMenuViewModel,
    navController: NavController
) {
}

@Preview
@Composable
fun FightMenuPreview() {
    val context = LocalContext.current
    val navController = rememberNavController()
    FightMenu(
        context,
        isLandscape = false,
        navController
    )
}
