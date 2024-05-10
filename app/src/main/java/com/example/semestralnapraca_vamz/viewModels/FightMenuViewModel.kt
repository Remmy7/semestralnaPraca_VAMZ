package com.example.semestralnapraca_vamz.viewModels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_vamz.SharedPreferencesHelper
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._gold
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._legacy
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._level

class FightMenuViewModel(context: Context) : ViewModel() {
    private val _monsterLevel = mutableIntStateOf(1)
    val monsterLevel: MutableState<Int> = _monsterLevel

    private val _monsterHealth = mutableIntStateOf(100)
    val monsterHealth: MutableState<Int> = _monsterHealth

    private val _monsterMaxHealth = mutableIntStateOf(600)
    val monsterMaxHealth: MutableState<Int> = _monsterMaxHealth

    private var _monsterName = "TempBoss"
    val monsterName: String = _monsterName

    private val pref: SharedPreferences
    private val sharedPreferencesHelper: SharedPreferencesHelper

    init {
        sharedPreferencesHelper = SharedPreferencesHelper
        pref = SharedPreferencesHelper.getSharedPreferences(context)
        loadSavedData()
    }

    private fun loadSavedData() {
        _monsterLevel.value = sharedPreferencesHelper.getMonsterLevel(pref)
        _monsterHealth.value = sharedPreferencesHelper.getMonsterHealth(pref)
        _monsterMaxHealth.value = sharedPreferencesHelper.getMonsterMaxHealth(pref)
        _monsterName = sharedPreferencesHelper.getMonsterName(pref).toString()
    }

    fun setMonsterStats(newLevel: Int, newHealth: Int, newMaxHealth: Int, newMonsterName: String) {
        _monsterLevel.value = newLevel
        _monsterHealth.value = newHealth
        _monsterMaxHealth.value = newMaxHealth
        _monsterName = newMonsterName
        sharedPreferencesHelper.saveMonsterLevel(pref, newLevel)
        sharedPreferencesHelper.saveMonsterHealth(pref, newHealth)
        sharedPreferencesHelper.saveMonsterMaxHealth(pref, newMaxHealth)
        sharedPreferencesHelper.saveMonsterName(pref, newMonsterName)
    }


}