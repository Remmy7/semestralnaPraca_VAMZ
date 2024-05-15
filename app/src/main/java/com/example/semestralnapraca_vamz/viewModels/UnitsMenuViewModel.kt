package com.example.semestralnapraca_vamz.viewModels

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_vamz.R
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
    private val _gold = mutableIntStateOf(500)
    val gold: MutableState<Int> = _gold


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
        _gold.intValue = sharedPreferencesHelper.getGold(pref)
    }

    fun reloadUnits() {
        loadSavedData()
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

    fun calculateDamageIncrease(unitName: String, levels: Int) : Int {
        var currLevel = getUnitCurrLevel(unitName)
        var multiplier = 1.1
        when(unitName) {
            "wizard" -> multiplier = multiplierWizard
            "archer" -> multiplier = multiplierArcher
            "mystic" -> multiplier = multiplierMystic
            "knight" -> multiplier = multiplierKnight
            "paladin" -> multiplier = multiplierPaladin
        }

        /*var currDamage = 1.0
        var totalDamage = 1.0
        var index = 0
         repeat (currLevel + levels) {
            if (index < currLevel) {
                currDamage = Math.pow(currDamage, multiplier)
            }
            else {
                if (totalDamage == 1.0) totalDamage=currDamage
                totalDamage = Math.pow(totalDamage, multiplier)
            }
            index++
        }
        return (totalDamage-currDamage).toInt()*/
        return (levels*multiplier).toInt() + getUnitCurrLevel(unitName) * 2 * levels
    }

    fun calculatePriceLoop(unitName: String, howMany: Int): Int {
        var returnVal = 0.0
        var currLevel = getUnitCurrLevel(unitName)
        repeat (howMany) {
            returnVal += calculatePriceOneUnit(unitName, currLevel)
            currLevel++
        }
        return returnVal.toInt()
    }
    // Too slow
    fun calculateMaxPurcharsable(unitName: String): Pair<Int, Int> {
        return Pair(1,1)
    }
        /*var totalGold = sharedPreferencesHelper.getGold(pref).toDouble()
        var currentGold = totalGold

        var currLevel = getUnitCurrLevel(unitName)
        var purcharsable = 0
        var totalPrice = 0.0

        while (currentGold > 0) {
            purcharsable++
            totalPrice += calculatePriceOneUnit(unitName, currLevel)
            currLevel++
        }
        if (totalPrice > totalGold ) {
            totalPrice -= calculatePriceOneUnit(unitName, currLevel)
            purcharsable--
        }
        return Pair(totalPrice.toInt(), purcharsable.toInt())
    }*/

    fun buyAmountOfUnits(unitName: String, amount: Int, context: Context) {
        var currentGold = sharedPreferencesHelper.getGold(pref).toDouble()
        var unitLevelCurr = getUnitCurrLevel(unitName)
        var price = calculatePriceLoop(unitName, amount)
        if(currentGold >= price) {
            when(unitName) {
                "wizard" -> {sharedPreferencesHelper.saveWizardLevel(pref, unitLevelCurr + amount); _wizardLevel.intValue += amount}
                "archer" -> {sharedPreferencesHelper.saveArcherLevel(pref, unitLevelCurr + amount); _archerLevel.intValue += amount}
                "mystic" -> {sharedPreferencesHelper.saveMysticLevel(pref, unitLevelCurr + amount); _mysticLevel.intValue += amount}
                "knight" -> {sharedPreferencesHelper.saveKnightLevel(pref, unitLevelCurr + amount); _knightLevel.intValue += amount}
                "paladin" -> {sharedPreferencesHelper.savePaladinLevel(pref, unitLevelCurr + amount); _paladinLevel.intValue += amount}
            }
            sharedPreferencesHelper.saveGold(pref, currentGold.toInt() - price)
            gold.value = currentGold.toInt() - price
        } else {
            val mediaPlayer = MediaPlayer.create(context, R.raw.error_sound)
            mediaPlayer.setOnCompletionListener {
                mediaPlayer.release()
            }
            mediaPlayer.start()
        }

    }

    fun getUnitCurrLevel(unitName: String): Int {
        when(unitName) {
            "wizard" -> {return sharedPreferencesHelper.getWizardLevel(pref)}
            "archer" -> {return sharedPreferencesHelper.getArcherLevel(pref)}
            "mystic" -> {return sharedPreferencesHelper.getMysticLevel(pref)}
            "knight" -> {return sharedPreferencesHelper.getKnightLevel(pref)}
            "paladin" -> {return sharedPreferencesHelper.getPaladinLevel(pref)}
        }
        return 6545
    }
}