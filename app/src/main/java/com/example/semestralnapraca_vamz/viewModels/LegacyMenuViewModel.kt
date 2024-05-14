package com.example.semestralnapraca_vamz.viewModels

import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_vamz.SharedPreferencesHelper
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._monsterLevel

class LegacyMenuViewModel(context: Context) : ViewModel() {
    private val pref: SharedPreferences
    private val sharedPreferencesHelper: SharedPreferencesHelper

    private val _legacyReceived = mutableIntStateOf(1)
    val legacyReceived: MutableState<Int> = _legacyReceived

    init {
        sharedPreferencesHelper = SharedPreferencesHelper
        pref = SharedPreferencesHelper.getSharedPreferences(context)
        _legacyReceived.intValue = calculateLegacy(false)
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
            sharedPreferencesHelper.getWizardLevel(pref)) / baseCalc - 1

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

        }
        return calculatedLeg.toInt()
    }
    fun resetGame() {
        calculateLegacy(true)
    }
}