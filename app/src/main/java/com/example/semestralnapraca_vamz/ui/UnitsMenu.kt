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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    if (isLandscape) {
        LandscapeLayout(viewModel, navController, context)
    } else {
        PortraitLayout(viewModel, navController, context)
    }
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
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.65f)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .background(Color(0xFF5C2402)),
                verticalArrangement = Arrangement.SpaceEvenly
            )
            {

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

            }
        }
    }

}

@Composable
fun LandscapeLayout(
    viewModel: UnitsMenuViewModel,
    navController: NavController,
    context: Context
) {Column(
    modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFB36800))
) {

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