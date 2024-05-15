package com.example.semestralnapraca_vamz

import android.content.Context
import android.content.SharedPreferences

// This object holds all values of SharedPreferences
// it also sets every string value into a separate variable like _level
// https://www.journaldev.com/234/android-sharedpreferences-kotlin
object SharedPreferencesHelper {

    // This object holds all values of SharedPreferences
    // it also sets every string value into a separate variable like _level
    object PreferenceHelper { //https://www.journaldev.com/234/android-sharedpreferences-kotlin
        val _level = "level"
        val _currentExperience = "currentExperience"
        val _levelUpExperience = "levelUpExperience"
        val _gold = "gold"
        val _legacy = "legacy"
        val _monsterLevel = "monsterLevel"
        val _monsterHealth = "monsterHealth"
        val _monsterMaxHealth = "monsterMaxHealth"
        val _monsterName = "monsterName"

        val _wizardLevel = "wizardLevel"
        val _wizardSpellLevel = "wizardSpellLevel"
        val _archerLevel = "archerLevel"
        val _archerSpellLevel = "archerSpellLevel"
        val _knightLevel = "knightLevel"
        val _knightSpellLevel = "knightSpellLevel"
        val _mysticLevel = "mysticLevel"
        val _mysticSpellLevel = "mysticSpellLevel"
        val _paladinLevel = "paladinLevel"
        val _paladinSpellLevel = "paladinSpellLevel"


        val _legacyWizardLevel = "legacyWizardLevel"
        val _legacyWizardSpellLevel = "legacyWizardSpellLevel"
        val _legacyArcherLevel = "legacyArcherLevel"
        val _legacyArcherSpellLevel = "legacyArcherSpellLevel"
        val _legacyKnightLevel = "legacyKnightLevel"
        val _legacyKnightSpellLevel = "legacyKnightSpellLevel"
        val _legacyMysticLevel = "legacyMysticLevel"
        val _legacyMysticSpellLevel = "legacyMysticSpellLevel"

        val _gameSpeed = "gameSpeed"
        val _legacyTotal = "totalLegacy"
    }

    fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("PreferenceHelper", Context.MODE_PRIVATE)
    }

    // Save methods
    fun saveLevel(preferenceHelper: SharedPreferences, level: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._level, level).apply()
    }

    fun saveCurrentExperience(preferenceHelper: SharedPreferences, currentExperience: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._currentExperience, currentExperience).apply()
    }

    fun saveLevelUpExperience(preferenceHelper: SharedPreferences, levelUpExperience: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._levelUpExperience, levelUpExperience).apply()
    }

    fun saveGold(preferenceHelper: SharedPreferences, gold: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._gold, gold).apply()
    }

    fun saveLegacy(preferenceHelper: SharedPreferences, legacy: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._legacy, legacy).apply()
    }

    fun saveMonsterLevel(preferenceHelper: SharedPreferences, monsterLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._monsterLevel, monsterLevel).apply()
    }

    fun saveMonsterHealth(preferenceHelper: SharedPreferences, monsterHealth: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._monsterHealth, monsterHealth).apply()
    }

    fun saveMonsterMaxHealth(preferenceHelper: SharedPreferences, monsterMaxHealth: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._monsterMaxHealth, monsterMaxHealth).apply()
    }
    fun saveMonsterName(preferenceHelper: SharedPreferences, monsterName: String) {
        preferenceHelper.edit().putString(PreferenceHelper._monsterName, monsterName).apply()
    }

    fun saveWizardLevel(preferenceHelper: SharedPreferences, wizardLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._wizardLevel, wizardLevel).apply()
    }

    fun saveWizardSpellLevel(preferenceHelper: SharedPreferences, wizardSpellLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._wizardSpellLevel, wizardSpellLevel).apply()
    }

    fun saveArcherLevel(preferenceHelper: SharedPreferences, archerLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._archerLevel, archerLevel).apply()
    }

    fun saveArcherSpellLevel(preferenceHelper: SharedPreferences, archerSpellLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._archerSpellLevel, archerSpellLevel).apply()
    }

    fun saveKnightLevel(preferenceHelper: SharedPreferences, knightLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._knightLevel, knightLevel).apply()
    }

    fun saveKnightSpellLevel(preferenceHelper: SharedPreferences, knightSpellLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._knightSpellLevel, knightSpellLevel).apply()
    }

    fun savePaladinLevel(preferenceHelper: SharedPreferences, knightLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._paladinLevel, knightLevel).apply()
    }

    fun savePaladinSpellLevel(preferenceHelper: SharedPreferences, knightSpellLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._paladinSpellLevel, knightSpellLevel).apply()
    }

    fun saveMysticLevel(preferenceHelper: SharedPreferences, mysticLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._mysticLevel, mysticLevel).apply()
    }

    fun saveMysticSpellLevel(preferenceHelper: SharedPreferences, mysticSpellLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._mysticSpellLevel, mysticSpellLevel).apply()
    }

    fun saveLegacyWizardLevel(preferenceHelper: SharedPreferences, legacyWizardLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._legacyWizardLevel, legacyWizardLevel).apply()
    }

    fun saveLegacyWizardSpellLevel(preferenceHelper: SharedPreferences, legacyWizardSpellLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._legacyWizardSpellLevel, legacyWizardSpellLevel).apply()
    }

    fun saveLegacyArcherLevel(preferenceHelper: SharedPreferences, legacyArcherLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._legacyArcherLevel, legacyArcherLevel).apply()
    }

    fun saveLegacyArcherSpellLevel(preferenceHelper: SharedPreferences, legacyArcherSpellLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._legacyArcherSpellLevel, legacyArcherSpellLevel).apply()
    }

    fun saveLegacyKnightLevel(preferenceHelper: SharedPreferences, legacyKnightLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._legacyKnightLevel, legacyKnightLevel).apply()
    }

    fun saveLegacyKnightSpellLevel(preferenceHelper: SharedPreferences, legacyKnightSpellLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._legacyKnightSpellLevel, legacyKnightSpellLevel).apply()
    }

    fun saveLegacyMysticLevel(preferenceHelper: SharedPreferences, legacyMysticLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._legacyMysticLevel, legacyMysticLevel).apply()
    }

    fun saveLegacyMysticSpellLevel(preferenceHelper: SharedPreferences, legacyMysticSpellLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._legacyMysticSpellLevel, legacyMysticSpellLevel).apply()
    }
    fun saveGameSpeed(preferenceHelper: SharedPreferences, gameSpeed: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._gameSpeed, gameSpeed).apply()
    }
    fun saveLegacyTotal(preferenceHelper: SharedPreferences, legacyTotal: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._legacyTotal, legacyTotal).apply()
    }

    // Get methods
    fun getLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._level, 1)
    }

    fun getCurrentExperience(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._currentExperience, 1)
    }

    fun getLevelUpExperience(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._levelUpExperience, 1)
    }

    fun getGold(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._gold, 600)
    }

    fun getLegacy(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._legacy, 1)
    }

    fun getMonsterLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._monsterLevel, 15)
    }

    fun getMonsterHealth(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._monsterHealth, 300)
    }

    fun getMonsterMaxHealth(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._monsterMaxHealth, 600)
    }
    fun getMonsterName(preferenceHelper: SharedPreferences): String? {
        return preferenceHelper.getString(PreferenceHelper._monsterName, "TempBoss")
    }

    fun getWizardLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._wizardLevel, 1)
    }

    fun getWizardSpellLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._wizardSpellLevel, 1)
    }

    fun getArcherLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._archerLevel, 1)
    }

    fun getArcherSpellLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._archerSpellLevel, 1)
    }

    fun getKnightLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._knightLevel, 1)
    }
    fun getPaladinLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._paladinLevel, 1)
    }

    fun getKnightSpellLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._knightSpellLevel, 1)
    }
    fun getPaladinSpellLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._paladinSpellLevel, 1)
    }

    fun getMysticLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._mysticLevel, 1)
    }

    fun getMysticSpellLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._mysticSpellLevel, 1)
    }

    fun getLegacyWizardLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._legacyWizardLevel, 1)
    }

    fun getLegacyWizardSpellLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._legacyWizardSpellLevel, 1)
    }

    fun getLegacyArcherLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._legacyArcherLevel, 1)
    }

    fun getLegacyArcherSpellLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._legacyArcherSpellLevel, 1)
    }

    fun getLegacyKnightLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._legacyKnightLevel, 1)
    }

    fun getLegacyKnightSpellLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._legacyKnightSpellLevel, 1)
    }

    fun getLegacyMysticLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._legacyMysticLevel, 1)
    }

    fun getLegacyMysticSpellLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._legacyMysticSpellLevel, 1)
    }
    fun getGameSpeed(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._gameSpeed, 1)
    }
    fun getLegacyTotal(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._legacyTotal, 1)
    }
}