package com.example.semestralnapraca_vamz.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.semestralnapraca_vamz.viewModels.FightMenuViewModel
import com.example.semestralnapraca_vamz.R

var landscape: Boolean = false
@Composable
fun FightMenu(
    context: Context,
    isLandscape: Boolean,
    navController: NavController
) {
    val viewModel = remember { FightMenuViewModel(context) }
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
        spellButtons(viewModel, landscape)
        Column(
            modifier = Modifier
                .fillMaxHeight(0.4f)
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
            Button(
                onClick = {
                }
            ) {

            }

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
    val healthPercentage = (monsterHealth.value.toFloat() / monsterMaxHealth.value.toFloat())


    if (isLandscape) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(

            ) {
                Text(
                    text = viewModel.monsterName.value,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                    color = Color.Red,
                    fontWeight = FontWeight.Bold

                )
                Text(
                    text = monsterLevel.toString(),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                    color = Color.White,
                )
            }
            Column() {

            }
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween


            ) {
                Text(
                    text = viewModel.monsterName.value,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                    color = Color.White
                )
                val levelText = buildAnnotatedString {
                    append("Level: ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(viewModel.monsterLevel.value.toString())
                    }

                }
                Text(
                    text = levelText,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                    color = Color.White,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Gray)
                        .fillMaxWidth()
                        .fillMaxHeight()

                ) {
                    Box(
                        modifier = Modifier
                            .background(Brush.horizontalGradient(listOf(Color(0xFFFF0000),
                                Color(0xFF66FF33))))
                            .fillMaxWidth(healthPercentage)
                            .fillMaxHeight()


                    )
                    Text("${monsterHealth.value} / ${monsterMaxHealth.value}", modifier = Modifier.align(alignment = Alignment.Center), fontSize = 30.sp)
                }


                /*Text(
                    text = "${monsterHealth.value} / ${monsterMaxHealth.value}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )*/
            }
        }

    }

}

@Composable
fun spellButtons(
    viewModel: FightMenuViewModel,
    isLandscape: Boolean
) {
    if (isLandscape) {

    } else {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.15f)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .background(Color(0xFF5C2402)),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {

            Image(
                painter = painterResource(id = R.drawable.archer_spell),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
                    .weight(1f)
                    .clickable { viewModel.castSpell("archer") },
            )
            Image(
                painter = painterResource(id = R.drawable.archer_spell),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
                    .weight(1f)
                    .clickable { viewModel.castSpell("wizard") },
            )
            Image(
                painter = painterResource(id = R.drawable.archer_spell),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
                    .weight(1f)
                    .clickable { viewModel.castSpell("paladin") },
            )
            Image(
                painter = painterResource(id = R.drawable.archer_spell),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
                    .weight(1f)
                    .clickable { viewModel.castSpell("knight") },
            )




        }
    }
}

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
