package com.example.semestralnapraca_vamz.viewModels

import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_vamz.SharedPreferencesHelper
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._monsterLevel

class LegacyMenuViewModel(context: Context) : ViewModel() {
    private val pref: SharedPreferences
    private val sharedPreferencesHelper: SharedPreferencesHelper

    private val _currentLegacy = mutableIntStateOf(1)
    val currentLegacy: MutableState<Int> = _currentLegacy

    private val _legacyReceived = mutableIntStateOf(1)
    val legacyReceived: MutableState<Int> = _legacyReceived

    init {
        sharedPreferencesHelper = SharedPreferencesHelper
        pref = SharedPreferencesHelper.getSharedPreferences(context)
        _legacyReceived.intValue = calculateLegacy(false)
        _currentLegacy.value = sharedPreferencesHelper.getLegacy(pref)
    }
    fun calculateLegacy(resetGame: Boolean) : Int {
        var calculatedLeg = 1.0f
        val baseCalc = 100
        calculatedLeg +=
            sharedPreferencesHelper.getGold(pref)/(baseCalc*10000) +
                    (sharedPreferencesHelper.getArcherLevel(pref) +
            sharedPreferencesHelper.getKnightLevel(pref) +
            sharedPreferencesHelper.getLevel(pref) +
            sharedPreferencesHelper.getMonsterLevel(pref) +
            sharedPreferencesHelper.getMysticLevel(pref) +
            sharedPreferencesHelper.getWizardLevel(pref) +
            sharedPreferencesHelper.getPaladinLevel(pref)) / baseCalc - 1

        if (resetGame) {
            sharedPreferencesHelper.saveMonsterLevel(pref, 1)
            sharedPreferencesHelper.saveMonsterHealth(pref, 100)
            sharedPreferencesHelper.saveMonsterMaxHealth(pref, 100)
            sharedPreferencesHelper.saveGold(pref, 0)
            sharedPreferencesHelper.saveLevel(pref, 1)
            sharedPreferencesHelper.saveLegacy(pref, sharedPreferencesHelper.getLegacy(pref) + calculatedLeg.toInt())
            sharedPreferencesHelper.saveArcherLevel(pref, 1)
            sharedPreferencesHelper.saveWizardLevel(pref, 1)
            sharedPreferencesHelper.saveMysticLevel(pref, 1)
            sharedPreferencesHelper.saveKnightLevel(pref, 1)
            sharedPreferencesHelper.savePaladinLevel(pref, 1)

        }
        return calculatedLeg.toInt()
    }
    fun resetGame() {
        calculateLegacy(true)
    }

    fun speedUpgradePrice(): Int {
        return sharedPreferencesHelper.getGameSpeed(pref) * 5
    }
    fun speedUpgrade() {
        val price = speedUpgradePrice()
        if (currentLegacy.value > price) {
            currentLegacy.value -= price
            sharedPreferencesHelper.saveLegacy(pref, sharedPreferencesHelper.getLegacy(pref) + 1)
        }
    }
}