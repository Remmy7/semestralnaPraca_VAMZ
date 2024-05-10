package com.example.semestralnapraca_vamz.viewModels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_vamz.MainActivity
import com.example.semestralnapraca_vamz.SharedPreferencesHelper


class MainMenuViewModel(context: Context) : ViewModel() {
    private val _level = mutableIntStateOf(1)
    val level: MutableState<Int> = _level

    private val _gold = mutableIntStateOf(600)
    val gold: MutableState<Int> = _gold

    private val _legacy = mutableIntStateOf(0)
    val legacy: MutableState<Int> = _legacy

    private val pref: SharedPreferences
    private val sharedPreferencesHelper: SharedPreferencesHelper

    init {
        sharedPreferencesHelper = SharedPreferencesHelper
        pref = SharedPreferencesHelper.getSharedPreferences(context)
        loadSavedData()
    }

    private fun loadSavedData() {
        _level.value = sharedPreferencesHelper.getLevel(pref)
        _gold.value = sharedPreferencesHelper.getGold(pref)
        _legacy.value = sharedPreferencesHelper.getLegacy(pref)
    }

    fun setLevel(newLevel: Int) {
        _level.value = newLevel
        sharedPreferencesHelper.saveLevel(pref, newLevel)
    }

    fun setGold(newGold: Int) {
        _gold.value = newGold
        sharedPreferencesHelper.saveGold(pref, newGold)
    }

    fun setLegacy(newLegacy: Int) {
        _legacy.value = newLegacy
        sharedPreferencesHelper.saveLegacy(pref, newLegacy)
    }
}