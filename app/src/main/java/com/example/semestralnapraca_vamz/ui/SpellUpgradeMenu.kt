package com.example.semestralnapraca_vamz.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.example.semestralnapraca_vamz.viewModels.SpellUpgradeMenuViewModel

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
    if (isLandscape) {
        LandscapeLayout(viewModel, navController, context)
    } else {
        PortraitLayout(viewModel, navController, context)
    }
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