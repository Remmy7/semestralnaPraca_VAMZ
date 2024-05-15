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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.semestralnapraca_vamz.R
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

    builder.setTitle(stringResource(R.string.warning))
        .setMessage(stringResource(R.string.you_will_lose_all_your_progress_do_you_still_want_to_proceed))

    builder.setPositiveButton(stringResource(R.string.yes)) { dialog, which ->
        viewModel.resetGame(context)

        dialog.dismiss()
    }

    builder.setNegativeButton(stringResource(R.string.decline)) { dialog, which ->
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
                Text(text = stringResource(R.string.back), fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
                        text = stringResource(R.string.current_legacy) + viewModel.currentLegacy.value,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp)
                            .weight(1f)
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(R.string.legacy_received) + viewModel.legacyReceived.value,
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
                        Text(text = stringResource(R.string.reset_game), fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
                            context.getString(R.string.upgrade_your_game_speed_for) to context.getString(
                                R.string.game_speed
                            ),
                            context.getString(R.string.upgrade_your_total_damage_by_1_for) to context.getString(
                                R.string.total_damage
                            ),
                            context.getString(R.string.upgrade_your_archer_damage_by_1_for) to context.getString(
                                R.string.archer_upgrade
                            ),
                            context.getString(R.string.upgrade_your_wizard_damage_by_1_for) to context.getString(
                                R.string.wizard_upgrade
                            ),
                            context.getString(R.string.upgrade_your_mystic_damage_by_1_for) to context.getString(
                                R.string.mystic_upgrade
                            ),
                            context.getString(R.string.upgrade_your_knight_damage_by_1_for) to context.getString(
                                R.string.knight_upgrade
                            )
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
                Text(text = stringResource(R.string.back), fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
                    text = stringResource(R.string.current_legacy) + viewModel.currentLegacy.value,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .weight(1f)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.legacy_received) + viewModel.legacyReceived.value,
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
                    Text(text = stringResource(R.string.reset_game), fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
                        context.getString(R.string.upgrade_your_game_speed_for) to context.getString(R.string.game_speed),
                        context.getString(R.string.upgrade_your_total_damage_by_1_for) to context.getString(R.string.total_damage),
                        context.getString(R.string.upgrade_your_archer_damage_by_1_for) to context.getString(R.string.archer_upgrade),
                        context.getString(R.string.upgrade_your_wizard_damage_by_1_for) to context.getString(R.string.wizard_upgrade),
                        context.getString(R.string.upgrade_your_mystic_damage_by_1_for) to context.getString(R.string.mystic_upgrade),
                        context.getString(R.string.upgrade_your_knight_damage_by_1_for) to context.getString(R.string.knight_upgrade)
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
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .align(Alignment.CenterHorizontally),
            onClick = { viewModel.legacyUpgrade(upgradeType, context) }
        ) {
            Text(text = stringResource(R.string.upgrade))
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