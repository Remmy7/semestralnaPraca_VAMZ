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
        val _wizardWeaponLevel = "wizardWeaponLevel"
        val _archerLevel = "archerLevel"
        val _archerWeaponLevel = "archerWeaponLevel"
        val _knightLevel = "knightLevel"
        val _knightWeaponLevel = "knightWeaponLevel"
        val _mysticLevel = "mysticLevel"
        val _mysticWeaponLevel = "mysticWeaponLevel"

    }

    object PreferenceHelperLegacy {
        val _legacyWizardLevel = "legacyWizardLevel"
        val _legacyWizardWeaponLevel = "legacyWizardWeaponLevel"
        val _legacyArcherLevel = "legacyArcherLevel"
        val _legacyArcherWeaponLevel = "legacyArcherWeaponLevel"
        val _legacyKnightLevel = "legacyKnightLevel"
        val _legacyKnightWeaponLevel = "legacyKnightWeaponLevel"
        val _legacyMysticLevel = "legacyMysticLevel"
        val _legacyMysticWeaponLevel = "legacyMysticWeaponLevel"
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

    fun saveWizardWeaponLevel(preferenceHelper: SharedPreferences, wizardWeaponLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._wizardWeaponLevel, wizardWeaponLevel).apply()
    }

    fun saveArcherLevel(preferenceHelper: SharedPreferences, archerLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._archerLevel, archerLevel).apply()
    }

    fun saveArcherWeaponLevel(preferenceHelper: SharedPreferences, archerWeaponLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._archerWeaponLevel, archerWeaponLevel).apply()
    }

    fun saveKnightLevel(preferenceHelper: SharedPreferences, knightLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._knightLevel, knightLevel).apply()
    }

    fun saveKnightWeaponLevel(preferenceHelper: SharedPreferences, knightWeaponLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._knightWeaponLevel, knightWeaponLevel).apply()
    }

    fun saveMysticLevel(preferenceHelper: SharedPreferences, mysticLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._mysticLevel, mysticLevel).apply()
    }

    fun saveMysticWeaponLevel(preferenceHelper: SharedPreferences, mysticWeaponLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelper._mysticWeaponLevel, mysticWeaponLevel).apply()
    }

    fun saveLegacyWizardLevel(preferenceHelper: SharedPreferences, legacyWizardLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelperLegacy._legacyWizardLevel, legacyWizardLevel).apply()
    }

    fun saveLegacyWizardWeaponLevel(preferenceHelper: SharedPreferences, legacyWizardWeaponLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelperLegacy._legacyWizardWeaponLevel, legacyWizardWeaponLevel).apply()
    }

    fun saveLegacyArcherLevel(preferenceHelper: SharedPreferences, legacyArcherLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelperLegacy._legacyArcherLevel, legacyArcherLevel).apply()
    }

    fun saveLegacyArcherWeaponLevel(preferenceHelper: SharedPreferences, legacyArcherWeaponLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelperLegacy._legacyArcherWeaponLevel, legacyArcherWeaponLevel).apply()
    }

    fun saveLegacyKnightLevel(preferenceHelper: SharedPreferences, legacyKnightLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelperLegacy._legacyKnightLevel, legacyKnightLevel).apply()
    }

    fun saveLegacyKnightWeaponLevel(preferenceHelper: SharedPreferences, legacyKnightWeaponLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelperLegacy._legacyKnightWeaponLevel, legacyKnightWeaponLevel).apply()
    }

    fun saveLegacyMysticLevel(preferenceHelper: SharedPreferences, legacyMysticLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelperLegacy._legacyMysticLevel, legacyMysticLevel).apply()
    }

    fun saveLegacyMysticWeaponLevel(preferenceHelper: SharedPreferences, legacyMysticWeaponLevel: Int) {
        preferenceHelper.edit().putInt(PreferenceHelperLegacy._legacyMysticWeaponLevel, legacyMysticWeaponLevel).apply()
    }

    // Get methods
    fun getLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._level, 1)
    }

    fun getCurrentExperience(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._currentExperience, 0)
    }

    fun getLevelUpExperience(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._levelUpExperience, 0)
    }

    fun getGold(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._gold, 600)
    }

    fun getLegacy(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._legacy, 0)
    }

    fun getMonsterLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._monsterLevel, 0)
    }

    fun getMonsterHealth(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._monsterHealth, 0)
    }

    fun getMonsterMaxHealth(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._monsterMaxHealth, 0)
    }
    fun getMonsterName(preferenceHelper: SharedPreferences): String? {
        return preferenceHelper.getString(PreferenceHelper._monsterName, "TempBoss")
    }

    fun getWizardLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._wizardLevel, 0)
    }

    fun getWizardWeaponLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._wizardWeaponLevel, 0)
    }

    fun getArcherLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._archerLevel, 0)
    }

    fun getArcherWeaponLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._archerWeaponLevel, 0)
    }

    fun getKnightLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._knightLevel, 0)
    }

    fun getKnightWeaponLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._knightWeaponLevel, 0)
    }

    fun getMysticLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._mysticLevel, 0)
    }

    fun getMysticWeaponLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelper._mysticWeaponLevel, 0)
    }

    fun getLegacyWizardLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelperLegacy._legacyWizardLevel, 0)
    }

    fun getLegacyWizardWeaponLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelperLegacy._legacyWizardWeaponLevel, 0)
    }

    fun getLegacyArcherLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelperLegacy._legacyArcherLevel, 0)
    }

    fun getLegacyArcherWeaponLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelperLegacy._legacyArcherWeaponLevel, 0)
    }

    fun getLegacyKnightLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelperLegacy._legacyKnightLevel, 0)
    }

    fun getLegacyKnightWeaponLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelperLegacy._legacyKnightWeaponLevel, 0)
    }

    fun getLegacyMysticLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelperLegacy._legacyMysticLevel, 0)
    }

    fun getLegacyMysticWeaponLevel(preferenceHelper: SharedPreferences): Int {
        return preferenceHelper.getInt(PreferenceHelperLegacy._legacyMysticWeaponLevel, 0)
    }
}