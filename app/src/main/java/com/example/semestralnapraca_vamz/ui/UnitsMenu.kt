package com.example.semestralnapraca_vamz.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.semestralnapraca_vamz.R
import com.example.semestralnapraca_vamz.viewModels.SpellUpgradeMenuViewModel
import com.example.semestralnapraca_vamz.viewModels.UnitsMenuViewModel


@Composable
fun UnitsMenu(
    context: Context,
    isLandscape: Boolean,
    navController: NavController
) {
    val viewModel = remember { UnitsMenuViewModel(context) }
    UnitsMenuContent(isLandscape, viewModel, navController, context)
}

@Composable
fun UnitsMenuContent(
    isLandscape: Boolean,
    viewModel: UnitsMenuViewModel,
    navController: NavController,
    context: Context
) {
    PortraitLayout(viewModel, navController, context)
}

@Composable
fun PortraitLayout(
    viewModel: UnitsMenuViewModel,
    navController: NavController,
    context: Context
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
                text = "Upgrade your units",
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
                .fillMaxSize()
                .background(Color(0xFFB36800))

        ) {
            val coloredString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("Gold: ")
                }
                withStyle(style = SpanStyle(color = Color.Yellow)) {
                    append(viewModel.gold.value.toString())
                }
            }
            Text(
                text = coloredString,
                modifier = Modifier
                    .padding(bottom = 8.dp,start=16.dp)
                    .wrapContentSize(Alignment.Center),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .background(Color(0xFF5C2402)),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                itemsIndexed(listOf(
                    "archer" to viewModel.archerLevel,
                    "wizard" to viewModel.wizardLevel,
                    "mystic" to viewModel.mysticLevel,
                    "knight" to viewModel.knightLevel,
                    "paladin" to viewModel.paladinLevel
                )) { index, (unitType, unitLevel) ->
                    buyLayout(viewModel, context, unitType, unitLevel)
                }
            }

        }
    }
}

@Composable
fun LandscapeLayout(
    viewModel: UnitsMenuViewModel,
    navController: NavController,
    context: Context
) {
        Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB36800)),
            verticalArrangement = Arrangement.SpaceEvenly
    ) {
        buyLayout(viewModel, context, "archer", viewModel.archerLevel)
    }

}

@Composable
fun buyLayout(
    viewModel: UnitsMenuViewModel,
    context: Context,
    unitType: String,
    unitLevel: MutableState<Int>
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(Color.DarkGray)) {
        Text(
            text = "$unitType, level ${unitLevel.value}",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            buttonLayout(viewModel,unitType, 1)
            buttonLayout(viewModel,unitType, 10)
            buttonLayout(viewModel,unitType, 100)
        }
    }

}

@Composable
fun buttonLayout(
    viewModel: UnitsMenuViewModel,
    unitType: String,
    amountBought: Int
) {
    if (amountBought == -1) {
        val (price, amount) = viewModel.calculateMaxPurcharsable(unitType)
        Column {
            Button(
                onClick = { viewModel.buyAmountOfUnits(unitType, amount) }
            ) {
                Column {
                    Box(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Buy MAX($amount)",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Box(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "$price gold",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp, top = 8.dp),
                text = "+${viewModel.calculateDamageIncrease(unitType, 1)} dps",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

    } else {
        Column {
            Button(
                onClick = { viewModel.buyAmountOfUnits(unitType, amountBought) }
            ) {
                Column {
                    Box(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Buy $amountBought",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Box(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        val price = viewModel.calculatePriceLoop(unitType, amountBought)
                        Text(
                            text = "-$price gold",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            val dps = viewModel.calculateDamageIncrease(unitType, amountBought)
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp, top = 8.dp),
                text = "+$dps dps",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }

}

@Preview
@Composable
fun UnitsMenuPreview() {
    val context = LocalContext.current
    val navController = rememberNavController()
    UnitsMenu(
        context,
        isLandscape = false,
        navController
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun UnitsMenuPreviewLandscape() {
    val context = LocalContext.current
    val navController = rememberNavController()
    UnitsMenu(
        context,
        isLandscape = true,
        navController
    )
}