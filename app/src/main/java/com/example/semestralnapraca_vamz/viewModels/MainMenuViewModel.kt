package com.example.semestralnapraca_vamz.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainMenuViewModel : ViewModel() {
    private val _level = mutableIntStateOf(1)
    val level: MutableState<Int> = _level

    private val _gold = mutableIntStateOf(600)
    val gold: MutableState<Int> = _gold

    private val _legacy = mutableIntStateOf(0)
    val legacy: MutableState<Int> = _legacy

    fun setLevel(newLevel: Int) {
        _level.value = newLevel
    }

    fun setGold(newGold: Int) {
        _gold.value = newGold
    }

    fun setLegacy(newLegacy: Int) {
        _legacy.value = newLegacy
    }

    fun updateLevel(newLevel: Int) {
        _level.value = newLevel
    }

    fun updateGold(newGold: Int) {
        _gold.value = newGold
    }
}