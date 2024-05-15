package com.example.semestralnapraca_vamz.ui

import android.app.AlertDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.semestralnapraca_vamz.viewModels.FightMenuViewModel
import com.example.semestralnapraca_vamz.viewModels.LegacyMenuViewModel

@Composable
fun LegacyMenu(
    context: Context,
    isLandscape: Boolean,
    navController: NavController,
    viewModel: LegacyMenuViewModel
) {
    //val viewModel = remember { LegacyMenuViewModel(context) }
    val viewModel = viewModel
    val builder = AlertDialog.Builder(context)

    builder.setTitle("Warning!")
        .setMessage("You will lose all your progress. Do you still want to proceed?")

    builder.setPositiveButton("YES") { dialog, which ->
        viewModel.resetGame(context)

        dialog.dismiss()
    }

    builder.setNegativeButton("Decline") { dialog, which ->
        dialog.dismiss()
    }
    LegacyMenuContent(isLandscape, viewModel, navController, builder, context)

}

@Composable
fun LegacyMenuContent(
    isLandscape: Boolean,
    viewModel: LegacyMenuViewModel,
    navController: NavController,
    builder: AlertDialog.Builder,
    context: Context
) {
    if (isLandscape) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFB36800))
        ) {
            Button(
                onClick = {
                    navController.navigate("main_menu")
                    //navController.popBackStack()
                },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.End)
            ) {
                Text(text = "Back", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.3f)
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .background(Color(0xFF5C2402)),
                    verticalArrangement = Arrangement.SpaceEvenly
                )
                {
                    Text(
                        text = "Current legacy: " + viewModel.currentLegacy.value,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp)
                            .weight(1f)
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Legacy received: " + viewModel.legacyReceived.value,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp)
                            .weight(1f)
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = {
                            val dialog = builder.create()
                            dialog.show()
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(bottom = 8.dp)

                    ) {
                        Text(text = "RESET GAME", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight(1f)
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .background(Color(0xFF5C2402)),
                    verticalArrangement = Arrangement.SpaceEvenly
                )
                {
                    itemsIndexed(
                        listOf(
                            "Upgrade your game speed for: " to "Game speed",
                            "Upgrade your total damage by 1% for: " to "Total Damage",
                            "Upgrade your archer damage by 1% for: " to "Archer upgrade",
                            "Upgrade your wizard damage by 1% for: " to "Wizard upgrade",
                            "Upgrade your mystic damage by 1% for: " to "Mystic upgrade",
                            "Upgrade your knight damage by 1% for: " to "Knight upgrade"
                        )
                    ) { index, (customText, upgradeType) ->
                        legacyBuyLayout(viewModel, context, customText, upgradeType)
                    }
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFB36800))
        ) {
            Button(
                onClick = {
                    navController.navigate("main_menu")
                    //navController.popBackStack()
                },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.End)
            ) {
                Text(text = "Back", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .background(Color(0xFF5C2402)),
                verticalArrangement = Arrangement.SpaceEvenly
            )
            {
                Text(
                    text = "Current legacy: " + viewModel.currentLegacy.value,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .weight(1f)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Legacy received: " + viewModel.legacyReceived.value,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .weight(1f)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = {
                        val dialog = builder.create()
                        dialog.show()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = 8.dp)

                ) {
                    Text(text = "RESET GAME", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .background(Color(0xFF5C2402)),
                verticalArrangement = Arrangement.SpaceEvenly
            )
            {
                itemsIndexed(
                    listOf(
                        "Upgrade your game speed for: " to "Game speed",
                        "Upgrade your total damage by 1% for: " to "Total Damage",
                        "Upgrade your archer damage by 1% for: " to "Archer upgrade",
                        "Upgrade your wizard damage by 1% for: " to "Wizard upgrade",
                        "Upgrade your mystic damage by 1% for: " to "Mystic upgrade",
                        "Upgrade your knight damage by 1% for: " to "Knight upgrade"
                    )
                ) { index, (customText, upgradeType) ->
                    legacyBuyLayout(viewModel, context, customText, upgradeType)
                }
            }
        }
    }
}

@Composable
fun legacyBuyLayout(
    viewModel: LegacyMenuViewModel,
    context: Context,
    customText: String,
    upgradeType: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.DarkGray)
    ) {
        Text(
            text = upgradeType,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "$customText ${viewModel.getUpgradePrice(upgradeType)}",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Button(
            modifier = Modifier.fillMaxWidth(0.5f).
            align(Alignment.CenterHorizontally),
            onClick = { viewModel.legacyUpgrade(upgradeType, context) }
        ) {
            Text(text = "Upgrade")
        }
    }
}


@Preview
@Composable
fun LegacyMenuPreview() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val viewModel = LegacyMenuViewModel(context)
    LegacyMenu(
        context,
        isLandscape = false,
        navController,
        viewModel
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun LegacyMenuPreviewLandscape() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val viewModel = LegacyMenuViewModel(context)

    LegacyMenu(
        context,
        isLandscape = true,
        navController,
        viewModel
    )
}