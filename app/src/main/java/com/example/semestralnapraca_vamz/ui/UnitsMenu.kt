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
import androidx.compose.ui.res.stringResource
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
    navController: NavController,
    viewModel: UnitsMenuViewModel
) {
    //val viewModel = remember { UnitsMenuViewModel(context) }
    val viewModel = viewModel
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
                text = stringResource(R.string.upgrade_your_units),
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
                    //navController.popBackStack()
                },
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                Text(text = stringResource(R.string.back), fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFB36800))

        ) {
            val coloredString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White)) {
                    append(stringResource(R.string.gold_2))
                }
                withStyle(style = SpanStyle(color = Color.Yellow)) {
                    append(viewModel.gold.value.toString())
                }
            }
            Text(
                text = coloredString,
                modifier = Modifier
                    .padding(bottom = 8.dp, start = 16.dp)
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
            text = stringResource(R.string.level_type, unitType, unitLevel.value),
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
            buttonLayout(viewModel,unitType, 1, context)
            buttonLayout(viewModel,unitType, 10, context)
            buttonLayout(viewModel,unitType, 100, context)
        }
    }

}

@Composable
fun buttonLayout(
    viewModel: UnitsMenuViewModel,
    unitType: String,
    amountBought: Int,
    context: Context
) {
    if (amountBought == -1) {
        val (price, amount) = viewModel.calculateMaxPurcharsable(unitType)
        Column {
            Button(
                onClick = { viewModel.buyAmountOfUnits(unitType, amount, context) }
            ) {
                Column {
                    Box(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = stringResource(R.string.buy_max, amount),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Box(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = stringResource(R.string.gold_3, price),
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
                text = stringResource(
                    R.string.dps_1,
                    viewModel.calculateDamageIncrease(unitType, 1)
                ),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

    } else {
        Column {
            Button(
                onClick = { viewModel.buyAmountOfUnits(unitType, amountBought, context) }
            ) {
                Column {
                    Box(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = stringResource(R.string.buy_bought, amountBought),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Box(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        val price = viewModel.calculatePriceLoop(unitType, amountBought)
                        Text(
                            text = stringResource(R.string.negativegold, price),
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
                text = stringResource(R.string.dps_2, dps),
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
    val viewModel = UnitsMenuViewModel(context)
    UnitsMenu(
        context,
        isLandscape = false,
        navController,
        viewModel
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun UnitsMenuPreviewLandscape() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val viewModel = UnitsMenuViewModel(context)
    UnitsMenu(
        context,
        isLandscape = true,
        navController,
        viewModel
    )
}