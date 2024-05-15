package com.example.semestralnapraca_vamz.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import com.example.semestralnapraca_vamz.viewModels.FightMenuViewModel
import com.example.semestralnapraca_vamz.viewModels.SpellUpgradeMenuViewModel
import com.example.semestralnapraca_vamz.viewModels.UnitsMenuViewModel

@Composable
fun SpellUpgradeMenu(
    context: Context,
    isLandscape: Boolean,
    navController: NavController,
    viewModel: SpellUpgradeMenuViewModel
) {
    //val viewModel = remember { SpellUpgradeMenuViewModel(context) }
    val viewModel = viewModel
    SpellUpgradeMenuContent(isLandscape, viewModel, navController, context)
}

@Composable
fun SpellUpgradeMenuContent(
    isLandscape: Boolean,
    viewModel: SpellUpgradeMenuViewModel,
    navController: NavController,
    context: Context
) {
    PortraitLayout(viewModel, navController, context)

}

@Composable
fun PortraitLayout(
    viewModel: SpellUpgradeMenuViewModel,
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
                text = "Upgrade your spells",
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
                    "archer" to viewModel.archerSpellLevel,
                    "wizard" to viewModel.wizardSpellLevel,
                    "mystic" to viewModel.mysticSpellLevel,
                    "knight" to viewModel.knightSpellLevel,
                    "paladin" to viewModel.paladinSpellLevel
                )) { index, (spellType, spellLevel) ->
                    spellBuyLayout(viewModel, context, spellType, spellLevel)
                }
            }

            }
        }
    }


@Composable
fun spellBuyLayout(
    viewModel: SpellUpgradeMenuViewModel,
    context: Context,
    spellType: String,
    spellLevel: MutableState<Int>
) {
    val spellPrice = viewModel.getSpellPrice(spellType)
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(Color.DarkGray)) {
        Text(
            text = "Level ${spellLevel.value} $spellType spell",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Upgrade for $spellPrice",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp),
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            val image1 = viewModel.getDrawable(spellType, 0)
            val image2 = viewModel.getDrawable(spellType, 5)
            Image(
                painter = painterResource(image1),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        viewModel.upgradeSpell(spellType, context)
                    }
            )
            if (image1 != image2) {
                Image(
                    painter = painterResource(R.drawable.arrow_next),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                )
                Image(
                    painter = painterResource(image2),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                )
            }
        }
    }

}

@Composable
fun LandscapeLayout(
    viewModel: SpellUpgradeMenuViewModel,
    navController: NavController,
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB36800))
    ) {
    }

}
@Preview
@Composable
fun SpellUpgradeMenuPreview() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val viewModel = SpellUpgradeMenuViewModel(context)
    SpellUpgradeMenu(
        context,
        isLandscape = false,
        navController,
        viewModel
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun SpellUpgradeMenuPreviewLandscape() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val viewModel = SpellUpgradeMenuViewModel(context)
    SpellUpgradeMenu(
        context,
        isLandscape = true,
        navController,
        viewModel
    )
}