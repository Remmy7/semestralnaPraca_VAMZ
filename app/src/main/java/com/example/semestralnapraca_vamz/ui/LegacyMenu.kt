package com.example.semestralnapraca_vamz.ui

import android.app.AlertDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.semestralnapraca_vamz.viewModels.LegacyMenuViewModel

@Composable
fun LegacyMenu(
    context: Context,
    isLandscape: Boolean,
    navController: NavController
) {
    val viewModel = remember { LegacyMenuViewModel(context) }
    val builder = AlertDialog.Builder(context)

    builder.setTitle("Warning!")
        .setMessage("You will lose all your progress. Do you still want to proceed?")

    builder.setPositiveButton("YES") { dialog, which ->
        viewModel.resetGame()
        dialog.dismiss()
        navController.navigate("main_menu")
    }

    builder.setNegativeButton("Decline") { dialog, which ->
        dialog.dismiss()
    }
    LegacyMenuContent(isLandscape, viewModel, navController, builder)

}

@Composable
fun LegacyMenuContent(
    isLandscape: Boolean,
    viewModel: LegacyMenuViewModel,
    navController: NavController,
    builder: AlertDialog.Builder
) {
    if(isLandscape) {

    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFB36800))
        ) {
            Button(
                onClick = {
                    navController.navigate("main_menu")
                },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.End)
            ) {
                Text(text = "Back", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .background(Color(0xFF5C2402)),
                verticalArrangement = Arrangement.SpaceEvenly
            )
            {

                Text(
                    text = "Legacy received: " + viewModel.legacyReceived.value,
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 8.dp, top = 8.dp)
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
                        .fillMaxHeight(0.5f)
                        .padding(bottom = 8.dp, top = 8.dp)

                ) {
                    Text(text = "RESET GAME", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .background(Color(0xFF5C2402)),
                verticalArrangement = Arrangement.SpaceEvenly
            )
            {

            }
        }
    }
}


@Preview
@Composable
fun LegacyMenuPreview() {
    val context = LocalContext.current
    val navController = rememberNavController()
    LegacyMenu(
        context,
        isLandscape = false,
        navController
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun LegacyMenuPreviewLandscape() {
    val context = LocalContext.current
    val navController = rememberNavController()
    LegacyMenu(
        context,
        isLandscape = true,
        navController
    )
}