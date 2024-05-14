package com.example.semestralnapraca_vamz

import androidx.compose.ui.text.AnnotatedString

data class LogEntry(
    val logText: AnnotatedString,
    val monsterName: String,
    val xpAmount: Int,
    val goldAmount: Int
)