package com.example.semestralnapraca_vamz.viewModels

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.asIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_vamz.MonsterPrefix
import com.example.semestralnapraca_vamz.MonsterSuffix
import com.example.semestralnapraca_vamz.R
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

    private val _monsterName = mutableStateOf("TempBoss")
    val monsterName: MutableState<String> = _monsterName

    private val logScalingFactor = 100
    private val baseMonsterHealth = 50

    //private val contextFightMenu: Context



    private val pref: SharedPreferences
    private val sharedPreferencesHelper: SharedPreferencesHelper
    private val countDownTimer: CountDownTimer

    init {
        sharedPreferencesHelper = SharedPreferencesHelper
        pref = SharedPreferencesHelper.getSharedPreferences(context)
        loadSavedData()
        //contextFightMenu = context

        // Gamecycle timer
        countDownTimer = object : CountDownTimer(Long.MAX_VALUE, 1000) { // 1000 = interval of 1 second
            override fun onTick(millisUntilFinished: Long) {
                updateGame(context);
            }

            override fun onFinish() {

            }
        }
        countDownTimer.start() // Start the timer
    }

    // Clear timer when FightMenu is closed
    override fun onCleared() {
        super.onCleared()
        countDownTimer.cancel()
        /*ssharedPreferencesHelper.saveMonsterLevel(pref, _monsterLevel.intValue)
        sharedPreferencesHelper.saveMonsterHealth(pref, _monsterHealth.intValue)
        sharedPreferencesHelper.saveMonsterMaxHealth(pref, _monsterMaxHealth.intValue)
        sharedPreferencesHelper.saveMonsterName(pref, _monsterName.value)*/
    }

    private fun loadSavedData() {
        _monsterLevel.intValue = sharedPreferencesHelper.getMonsterLevel(pref)
        _monsterHealth.intValue = sharedPreferencesHelper.getMonsterHealth(pref)
        _monsterMaxHealth.intValue = sharedPreferencesHelper.getMonsterMaxHealth(pref)
        _monsterName.value = sharedPreferencesHelper.getMonsterName(pref).toString()
    }

    fun setMonsterStats(newLevel: Int, newHealth: Int, newMaxHealth: Int, newMonsterName: String) {
        _monsterLevel.intValue = newLevel
        _monsterHealth.value = newHealth
        _monsterMaxHealth.value = newMaxHealth
        _monsterName.value = newMonsterName
        sharedPreferencesHelper.saveMonsterLevel(pref, newLevel)
        sharedPreferencesHelper.saveMonsterHealth(pref, newHealth)
        sharedPreferencesHelper.saveMonsterMaxHealth(pref, newMaxHealth)
        sharedPreferencesHelper.saveMonsterName(pref, newMonsterName)
    }

    fun createNewMonster() {
        val newMaxHealth = (baseMonsterHealth * Math.log((_monsterLevel.intValue + 1).toDouble()) * logScalingFactor).toInt()
        setMonsterStats(_monsterLevel.value + 1, newMaxHealth, newMaxHealth, generateMonsterName())
    }



    fun generateMonsterName(): String {
        val prefix = MonsterPrefix.entries.toTypedArray().random().prefix
        val suffix = MonsterSuffix.entries.toTypedArray().random().suffix
        return "$prefix $suffix"
    }

    // Damage calculated every tick, does not overflow to new monster
    fun calculateDamage() : Int {
        return 1
    }

    // Casts a spell specified by the spell slot
    fun castSpell(spellSlot: String, context: Context) {
        when(spellSlot) {
            "archer" -> {
                _monsterHealth.intValue -= 15

            }
            "wizard" -> {
                _monsterHealth.intValue -= 30
            }
            "paladin" -> {
                _monsterHealth.intValue -= 45
            }
            "knight" -> {
                _monsterHealth.intValue -= 20000
            }

        }
        if (_monsterHealth.intValue <= 0) updateGame(context)

    }
    fun updateGame(context: Context) {
        val damage = calculateDamage()
        _monsterHealth.intValue -= damage
        if (_monsterHealth.intValue <= 0) {
            // Monster death, generate a new one
            createNewMonster()
            // Add stats to user
            val addedGold = (_monsterLevel.intValue * Math.log((_monsterLevel.intValue + 1).toDouble()) * logScalingFactor).toInt()
            sharedPreferencesHelper.saveGold(pref, sharedPreferencesHelper.getGold(pref) + addedGold)
            sharedPreferencesHelper.saveLevel(pref, sharedPreferencesHelper.getLevel(pref) + 1)
            //play deathsound
            val mediaPlayer = MediaPlayer.create(context, DeathSounds.entries.toTypedArray().random().resID)
            mediaPlayer.setOnCompletionListener {
                mediaPlayer.release()
            }
            mediaPlayer.start()

        } else {
            sharedPreferencesHelper.saveMonsterHealth(pref, _monsterHealth.intValue)
        }
    }
    enum class DeathSounds(val resID: Int){
        ARCHDEMON(R.raw.archdemon_death),
        CYCLOPS(R.raw.cyclops_death),
        DEMONS(R.raw.demons_death),
        GOBLINS(R.raw.goblins_death),
        MEDUSA(R.raw.medusa_death)

    }
}