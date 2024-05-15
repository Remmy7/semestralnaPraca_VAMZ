package com.example.semestralnapraca_vamz.viewModels

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_vamz.R
import com.example.semestralnapraca_vamz.SharedPreferencesHelper
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._archerLevel
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._gold
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._knightLevel
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._legacy
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._level
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._mysticLevel
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._paladinLevel

class SpellUpgradeMenuViewModel(context: Context) : ViewModel() {

    private val _archerSpellLevel = mutableIntStateOf(20)
    val archerSpellLevel: MutableState<Int> = _archerSpellLevel
    private val _wizardSpellLevel = mutableIntStateOf(15)
    val wizardSpellLevel: MutableState<Int> = _wizardSpellLevel
    private val _mysticSpellLevel = mutableIntStateOf(10)
    val mysticSpellLevel: MutableState<Int> = _mysticSpellLevel
    private val _knightSpellLevel = mutableIntStateOf(5)
    val knightSpellLevel: MutableState<Int> = _knightSpellLevel
    private val _paladinSpellLevel = mutableIntStateOf(3)
    val paladinSpellLevel: MutableState<Int> = _paladinSpellLevel
    private val _gold = mutableIntStateOf(500)
    val gold: MutableState<Int> = _gold

    private val _archerSpellUpgradePrice = mutableIntStateOf(100)
    val archerSpellUpgradePrice: MutableState<Int> = _archerSpellUpgradePrice
    private val _wizardSpellUpgradePrice = mutableIntStateOf(150)
    val wizardSpellUpgradePrice: MutableState<Int> = _wizardSpellUpgradePrice
    private val _mysticSpellUpgradePrice = mutableIntStateOf(200)
    val mysticSpellUpgradePrice: MutableState<Int> = _mysticSpellUpgradePrice
    private val _knightSpellUpgradePrice = mutableIntStateOf(250)
    val knightSpellUpgradePrice: MutableState<Int> = _knightSpellUpgradePrice
    private val _paladinSpellUpgradePrice = mutableIntStateOf(300)
    val paladinSpellUpgradePrice: MutableState<Int> = _paladinSpellUpgradePrice


    private val pref: SharedPreferences
    private val sharedPreferencesHelper: SharedPreferencesHelper

    init {
        sharedPreferencesHelper = SharedPreferencesHelper
        pref = SharedPreferencesHelper.getSharedPreferences(context)
        loadSavedData()
    }

    private fun loadSavedData() {
        _archerSpellLevel.intValue = sharedPreferencesHelper.getArcherSpellLevel(pref)
        _wizardSpellLevel.intValue = sharedPreferencesHelper.getWizardSpellLevel(pref)
        _mysticSpellLevel.intValue = sharedPreferencesHelper.getMysticSpellLevel(pref)
        _knightSpellLevel.intValue = sharedPreferencesHelper.getKnightSpellLevel(pref)
        _paladinSpellLevel.intValue = sharedPreferencesHelper.getPaladinSpellLevel(pref)
        _gold.intValue = sharedPreferencesHelper.getGold(pref)

        _archerSpellUpgradePrice.intValue = getSpellPrice("archer")
        _wizardSpellUpgradePrice.intValue = getSpellPrice("wizard")
        _mysticSpellUpgradePrice.intValue = getSpellPrice("mystic")
        _knightSpellUpgradePrice.intValue = getSpellPrice("knight")
        _paladinSpellUpgradePrice.intValue = getSpellPrice("paladin")
    }

    fun reloadSpell() {
        loadSavedData()
    }

    fun getDrawable(spellSlot: String, increaseLevel: Int): Int {
        when(spellSlot) {
            "archer" -> {
                val level = sharedPreferencesHelper.getArcherSpellLevel(pref) + increaseLevel
                if (level < 5) return R.drawable.archer_spell_1
                if (level < 10) return R.drawable.archer_spell_2
                if (level < 15) return R.drawable.archer_spell_3
                if (level < 20) return R.drawable.archer_spell_4
                if (level < 25) return R.drawable.archer_spell_5
                if (level >= 25) return R.drawable.archer_spell_6
            }
            "wizard" -> {
                val level = sharedPreferencesHelper.getWizardSpellLevel(pref) + increaseLevel
                if (level < 5) return R.drawable.wizard_spell_1
                if (level < 10) return R.drawable.wizard_spell_2
                if (level < 15) return R.drawable.wizard_spell_3
                if (level < 20) return R.drawable.wizard_spell_4
                if (level < 25) return R.drawable.wizard_spell_5
                if (level >= 25) return R.drawable.wizard_spell_6
            }
            "mystic" -> {
                val level = sharedPreferencesHelper.getMysticSpellLevel(pref) + increaseLevel
                if (level < 5) return R.drawable.mystic_spell_1
                if (level < 10) return R.drawable.mystic_spell_2
                if (level < 15) return R.drawable.mystic_spell_3
                if (level < 20) return R.drawable.mystic_spell_4
                if (level < 25) return R.drawable.mystic_spell_5
                if (level >= 25) return R.drawable.mystic_spell_6
            }
            "knight" -> {
                val level = sharedPreferencesHelper.getKnightSpellLevel(pref) + increaseLevel
                if (level < 5) return R.drawable.knight_spell_1
                if (level < 10) return R.drawable.knight_spell_2
                if (level < 15) return R.drawable.knight_spell_3
                if (level < 20) return R.drawable.knight_spell_4
                if (level < 25) return R.drawable.knight_spell_5
                if (level >= 25) return R.drawable.knight_spell_6
            }
            "paladin" -> {
                val level = sharedPreferencesHelper.getPaladinSpellLevel(pref) + increaseLevel
                if (level < 5) return R.drawable.paladin_spell_1
                if (level < 10) return R.drawable.paladin_spell_2
                if (level < 15) return R.drawable.paladin_spell_3
                if (level < 20) return R.drawable.paladin_spell_4
                if (level < 25) return R.drawable.paladin_spell_5
                if (level >= 25) return R.drawable.paladin_spell_6
            }
        }
        return R.drawable.enemy_oryx2
    }

    fun getSpellPrice(spellSlot: String): Int {
        when(spellSlot) {
            "archer" -> {
                return sharedPreferencesHelper.getArcherSpellLevel(pref) * 500
            }
            "wizard" -> {
                return sharedPreferencesHelper.getWizardSpellLevel(pref) * 1000
            }
            "mystic" -> {
                return sharedPreferencesHelper.getMysticSpellLevel(pref) * 1000
            }
            "knight" -> {
                return sharedPreferencesHelper.getKnightSpellLevel(pref) * 2000
            }
            "paladin" -> {
                return sharedPreferencesHelper.getPaladinSpellLevel(pref) * 2000
            }

        }
        return sharedPreferencesHelper.getArcherSpellLevel(pref) * 50
    }
    fun upgradeSpell(spellSlot: String, context: Context) {
        val price = getSpellPrice(spellSlot)
        if (price <= _gold.intValue) {
            when (spellSlot) {
                "archer" -> {
                    _gold.intValue -= price
                    _archerSpellLevel.intValue++
                    sharedPreferencesHelper.saveGold(pref, _gold.intValue)
                    sharedPreferencesHelper.saveArcherSpellLevel(pref, _archerSpellLevel.intValue)
                }
                "wizard" -> {
                    _gold.intValue -= price
                    _wizardSpellLevel.intValue++
                    sharedPreferencesHelper.saveGold(pref, _gold.intValue)
                    sharedPreferencesHelper.saveWizardSpellLevel(pref, _wizardSpellLevel.intValue)
                }
                "mystic" -> {
                    _gold.intValue -= price
                    _mysticSpellLevel.intValue++
                    sharedPreferencesHelper.saveGold(pref, _gold.intValue)
                    sharedPreferencesHelper.saveMysticSpellLevel(pref, _mysticSpellLevel.intValue)
                }
                "knight" -> {
                    _gold.intValue -= price
                    _knightSpellLevel.intValue++
                    sharedPreferencesHelper.saveGold(pref, _gold.intValue)
                    sharedPreferencesHelper.saveKnightSpellLevel(pref, _knightSpellLevel.intValue)
                }
                "paladin" -> {
                    _gold.intValue -= price
                    _paladinSpellLevel.intValue++
                    sharedPreferencesHelper.saveGold(pref, _gold.intValue)
                    sharedPreferencesHelper.savePaladinSpellLevel(pref, _paladinSpellLevel.intValue)
                }
            }
        } else {
            val mediaPlayer = MediaPlayer.create(context, R.raw.error_sound)
            mediaPlayer.setOnCompletionListener {
                mediaPlayer.release()
            }
            mediaPlayer.start()
        }
    }
}