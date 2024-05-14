package com.example.semestralnapraca_vamz.viewModels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_vamz.SharedPreferencesHelper

class UnitsMenuViewModel(context: Context) : ViewModel() {
//    private val _level = mutableIntStateOf(1)
//    val level: MutableState<Int> = _level
//
//    private val _gold = mutableIntStateOf(600)
//    val gold: MutableState<Int> = _gold
//
//    private val _legacy = mutableIntStateOf(0)
//    val legacy: MutableState<Int> = _legacy

    private val _archerLevel = mutableIntStateOf(20)
    val archerLevel: MutableState<Int> = _archerLevel
    private val _wizardLevel = mutableIntStateOf(15)
    val wizardLevel: MutableState<Int> = _wizardLevel
    private val _mysticLevel = mutableIntStateOf(10)
    val mysticLevel: MutableState<Int> = _mysticLevel
    private val _knightLevel = mutableIntStateOf(5)
    val knightLevel: MutableState<Int> = _knightLevel
    private val _paladinLevel = mutableIntStateOf(3)
    val paladinLevel: MutableState<Int> = _paladinLevel

    private val pref: SharedPreferences
    private val sharedPreferencesHelper: SharedPreferencesHelper

    private val basePriceUnit = 2;
    private val multiplierWizard = 1.1;
    private val multiplierArcher = 1.3;
    private val multiplierMystic = 1.45;
    private val multiplierKnight = 1.8;
    private val multiplierPaladin = 2.1;

    init {
        sharedPreferencesHelper = SharedPreferencesHelper
        pref = SharedPreferencesHelper.getSharedPreferences(context)
        loadSavedData()
    }

    private fun loadSavedData() {
//        _level.value = sharedPreferencesHelper.getLevel(pref)
//        _gold.value = sharedPreferencesHelper.getGold(pref)
//        _legacy.value = sharedPreferencesHelper.getLegacy(pref)
        _archerLevel.intValue = sharedPreferencesHelper.getArcherLevel(pref)
        _wizardLevel.intValue = sharedPreferencesHelper.getWizardLevel(pref)
        _mysticLevel.intValue = sharedPreferencesHelper.getMysticLevel(pref)
        _knightLevel.intValue = sharedPreferencesHelper.getKnightLevel(pref)
        _paladinLevel.intValue = sharedPreferencesHelper.getPaladinLevel(pref)
    }

    fun calculatePriceOneUnit(unitName: String, unitLevel: Int): Double {
        when(unitName) {
            "wizard" -> {return basePriceUnit + multiplierWizard * unitLevel}
            "archer" -> {return basePriceUnit + multiplierArcher * unitLevel}
            "mystic" -> {return basePriceUnit + multiplierMystic * unitLevel}
            "knight" -> {return basePriceUnit + multiplierKnight * unitLevel}
            "paladin" -> {return basePriceUnit + multiplierPaladin * unitLevel}
        }
        return 999999.9
    }

    fun calculatePriceLoop(unitName: String, unitLevel: Int, howMany: Int): Int {
        var returnVal = 0.0
        var currLevel = unitLevel
        repeat (howMany) {
            returnVal += calculatePriceOneUnit(unitName, currLevel)
            currLevel++
        }
        return returnVal.toInt()
    }
    fun calculateMaxPurcharsable(unitName: String, unitLevel: Int): Pair<Int, Int> {
        var currentGold = sharedPreferencesHelper.getGold(pref).toDouble()
        var currLevel = unitLevel
        var purcharsable = 0
        var totalPrice = 0.0

        while (currentGold < totalPrice) {
            purcharsable++
            totalPrice += calculatePriceOneUnit(unitName, currLevel)
            currLevel++
        }
        if (totalPrice != currentGold) {
            totalPrice -= calculatePriceOneUnit(unitName, currLevel-1)
            purcharsable--
            currLevel--
        }
        return Pair(totalPrice.toInt(), purcharsable.toInt())
    }
}