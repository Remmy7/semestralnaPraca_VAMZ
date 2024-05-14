package com.example.semestralnapraca_vamz.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.semestralnapraca_vamz.LogEntry
import com.example.semestralnapraca_vamz.viewModels.FightMenuViewModel
import com.example.semestralnapraca_vamz.R
import kotlin.math.absoluteValue

@Composable
fun FightMenu(
    context: Context,
    isLandscape: Boolean,
    navController: NavController
) {
    val viewModel = remember { FightMenuViewModel(context) }
    FightMenuContent(isLandscape, viewModel, navController, context)
}

@Composable
fun FightMenuContent(
    isLandscape: Boolean,
    viewModel: FightMenuViewModel,
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
    viewModel: FightMenuViewModel,
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
            Box(modifier = Modifier
                .fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.fight_background_2),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        spellButtons(viewModel, false, context)
        Column(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFF5C2402)),
        )
        {
            monsterStats(viewModel, false)
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
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.logEntries.reversed()) { entry ->
                    LogEntryItem(entry)
                }
            }

        }
    }
}
@Composable
fun LogEntryItem(entry: LogEntry) {
    Text(
        text = entry.logText
    )
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
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
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
                            .background(
                                Brush.horizontalGradient(
                                    listOf(
                                        Color(0xFFFF0000),
                                        Color(0xFF66FF33)
                                    )
                                )
                            )
                            .fillMaxWidth(healthPercentage)
                            .fillMaxHeight()


                    )
                    Text("${monsterHealth.value} / ${monsterMaxHealth.value}", modifier = Modifier.align(alignment = Alignment.Center), fontSize = 30.sp)
                }
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
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
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
                            .background(
                                Brush.horizontalGradient(
                                    listOf(
                                        Color(0xFFFF0000),
                                        Color(0xFF66FF33)
                                    )
                                )
                            )
                            .fillMaxWidth(healthPercentage)
                            .fillMaxHeight()


                    )
                    Text("${monsterHealth.value} / ${monsterMaxHealth.value}", modifier = Modifier.align(alignment = Alignment.Center), fontSize = 30.sp)
                }
            }
        }

    }

}

@Composable
fun spellButtons(
    viewModel: FightMenuViewModel,
    isLandscape: Boolean,
    context: Context
) {
    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(end = 16.dp, top=8.dp, bottom=8.dp)
                .background(Color(0xFF5C2402)),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {

            for (spell in viewModel.spells) {
                var isOnCooldown by remember { mutableStateOf(viewModel.isSpellOnCooldown(spell)) }
                var cooldownLeft = viewModel.getCooldownLeft(spell.spellSlot)
                var cooldown = viewModel.getCooldown(spell.spellSlot)
                var progress = cooldownLeft?.value?.toFloat()!! / cooldown?.value?.toFloat()!!

                if (progress == 0f) isOnCooldown = viewModel.isSpellOnCooldown(spell)

                Box(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)) {

                    Image(
                        painter = painterResource(id = spell.drawableResId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                if (!isOnCooldown) {
                                    viewModel.castSpell(spell.spellSlot, context)
                                    isOnCooldown = viewModel.isSpellOnCooldown(spell)
                                }
                            }
                    )
                    if (isOnCooldown) {
                        Box(
                            modifier = Modifier
                                .alpha(0.7f)
                                .background(Color.White)
                                .fillMaxWidth(
                                    cooldownLeft.value.toFloat() / cooldown.value.toFloat()
                                )
                                .fillMaxHeight()
                        ) {
                            Text(
                                text = cooldownLeft.value.toString(),
                                modifier = Modifier.align(alignment = Alignment.Center),
                                fontSize = 30.sp
                            )
                        }
                    }
                } }
        }
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

            for (spell in viewModel.spells) {
                var isOnCooldown by remember { mutableStateOf(viewModel.isSpellOnCooldown(spell)) }
                var cooldownLeft = viewModel.getCooldownLeft(spell.spellSlot)
                var cooldown = viewModel.getCooldown(spell.spellSlot)
                var progress = cooldownLeft?.value?.toFloat()!! / cooldown?.value?.toFloat()!!

                if (progress == 0f) isOnCooldown = viewModel.isSpellOnCooldown(spell)

                Box(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)) {

                    Image(
                        painter = painterResource(id = spell.drawableResId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                if (!isOnCooldown) {
                                    viewModel.castSpell(spell.spellSlot, context)
                                    isOnCooldown = viewModel.isSpellOnCooldown(spell)
                                }
                            }
                    )
                    if (isOnCooldown) {
                        Box(
                            modifier = Modifier
                                .alpha(0.7f)
                                .background(Color.White)
                                .fillMaxWidth(
                                    cooldownLeft.value.toFloat() / cooldown.value.toFloat()
                                )
                                .fillMaxHeight()
                        ) {
                            Text(
                                text = cooldownLeft.value.toString(),
                                modifier = Modifier.align(alignment = Alignment.Center),
                                fontSize = 30.sp
                            )
                        }
                    }
            } }
        }
    }
}

@Composable
fun LandscapeLayout(
    viewModel: FightMenuViewModel,
    navController: NavController,
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB36800))
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.7f),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(start = 16.dp, end = 8.dp, top = 16.dp)
                    .background(Color(0xFF5C2402)),
                verticalArrangement = Arrangement.SpaceEvenly
            )
            {
                Box(modifier = Modifier
                    .fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.fight_background_2),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Column(modifier = Modifier.fillMaxSize()) {
                Row() {
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
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(end = 16.dp)
                        .background(Color(0xFF5C2402)),
                    verticalArrangement = Arrangement.SpaceEvenly
                )
                {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(viewModel.logEntries.reversed()) { entry ->
                            LogEntryItem(entry)
                        }
                    }

                }
            }

        }
        Row() {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
                    .padding(start=16.dp, bottom=8.dp, top = 8.dp, end = 8.dp)
                    .background(Color(0xFF5C2402)),
            )
            {
                monsterStats(viewModel, false)
            }
            spellButtons(viewModel, true, context)

        }

    }
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

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun FightMenuPreviewLandscape() {
    val context = LocalContext.current
    val navController = rememberNavController()
    FightMenu(
        context,
        isLandscape = true,
        navController
    )
}


