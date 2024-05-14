package com.example.semestralnapraca_vamz.viewModels

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.asIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_vamz.LogEntry
import com.example.semestralnapraca_vamz.MonsterPrefix
import com.example.semestralnapraca_vamz.MonsterSuffix
import com.example.semestralnapraca_vamz.R
import com.example.semestralnapraca_vamz.SharedPreferencesHelper
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._gold
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._legacy
import com.example.semestralnapraca_vamz.SharedPreferencesHelper.PreferenceHelper._level
import com.example.semestralnapraca_vamz.Spell

class FightMenuViewModel(context: Context) : ViewModel() {
    private val _monsterLevel = mutableIntStateOf(1)
    val monsterLevel: MutableState<Int> = _monsterLevel

    private val _monsterHealth = mutableIntStateOf(100)
    val monsterHealth: MutableState<Int> = _monsterHealth

    private val _monsterMaxHealth = mutableIntStateOf(600)
    val monsterMaxHealth: MutableState<Int> = _monsterMaxHealth

    private val _monsterName = mutableStateOf("TempBoss")
    val monsterName: MutableState<String> = _monsterName



    private val logScalingFactor = 25
    private val exponentialScalingFactor = 1.7
    private val baseMonsterHealth = 10
    private var gameSpeed = 1

    private val cooldowns = mutableStateMapOf<String, MutableState<Long>>()
    private val cooldownLefts = mutableStateMapOf<String, MutableState<Long>>()



    //private val contextFightMenu: Context

    val spells = listOf(
        Spell(R.drawable.archer_spell, 5L, "archer"),
        Spell(R.drawable.wizard_spell, 10L, "wizard"),
        Spell(R.drawable.mystic_spell, 15L, "mystic"),
        Spell(R.drawable.knight_spell, 0L, "knight"),
        Spell(R.drawable.paladin_spell, 25L, "paladin")
        //Spell(R.drawable.knight_spell, 5000L, "knight")
    )


    val logEntries = SnapshotStateList<LogEntry>()


    private val pref: SharedPreferences
    private val sharedPreferencesHelper: SharedPreferencesHelper
    private val countDownTimer: CountDownTimer

    init {
        sharedPreferencesHelper = SharedPreferencesHelper
        pref = SharedPreferencesHelper.getSharedPreferences(context)
        loadSavedData()
        //contextFightMenu = context
        for (spell in spells) {
            cooldowns[spell.spellSlot] = mutableStateOf(spell.cooldown)
            cooldownLefts[spell.spellSlot] = mutableStateOf(spell.cooldownLeft)
        }

        // Gamecycle timer
        countDownTimer = object : CountDownTimer(Long.MAX_VALUE, (1000 / gameSpeed).toLong()) { // 1000 = interval of 1 second
            override fun onTick(millisUntilFinished: Long) {
                updateGame(context);
                updateSpellCooldowns(spells)

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
        gameSpeed = sharedPreferencesHelper.getGameSpeed(pref)

    }

    fun setMonsterStats(newLevel: Int, newHealth: Int, newMaxHealth: Int, newMonsterName: String) {
        _monsterLevel.intValue = newLevel
        _monsterHealth.intValue = newHealth
        _monsterMaxHealth.intValue = newMaxHealth
        _monsterName.value = newMonsterName
        sharedPreferencesHelper.saveMonsterLevel(pref, newLevel)
        sharedPreferencesHelper.saveMonsterHealth(pref, newHealth)
        sharedPreferencesHelper.saveMonsterMaxHealth(pref, newMaxHealth)
        sharedPreferencesHelper.saveMonsterName(pref, newMonsterName)
    }

    fun createNewMonster() {
        //val newMaxHealth = (baseMonsterHealth * Math.log((_monsterLevel.intValue + 1).toDouble()) * logScalingFactor).toInt()
        val newMaxHealth = Math.pow(baseMonsterHealth * (_monsterLevel.intValue + 1).toDouble(), exponentialScalingFactor).toInt()
        setMonsterStats(_monsterLevel.intValue + 1, newMaxHealth, newMaxHealth, generateMonsterName())
    }



    fun generateMonsterName(): String {
        val prefix = MonsterPrefix.entries.toTypedArray().random().prefix
        val suffix = MonsterSuffix.entries.toTypedArray().random().suffix
        return "$prefix $suffix"
    }

    // Damage calculated every tick, does not overflow to new monster
    fun calculateDamage() : Int {
        val multiplierWizard = 1.1;
        val multiplierArcher = 1.3;
        val multiplierMystic = 1.45;
        val multiplierKnight = 1.8;
        val multiplierPaladin = 2.1;
        var returnVal = 1.0
        returnVal +=
            sharedPreferencesHelper.getArcherLevel(pref) * multiplierArcher +
            sharedPreferencesHelper.getKnightLevel(pref) * multiplierKnight +
            sharedPreferencesHelper.getMysticLevel(pref) * multiplierMystic +
            sharedPreferencesHelper.getWizardLevel(pref) * multiplierWizard +
            sharedPreferencesHelper.getPaladinLevel(pref) * multiplierPaladin
        returnVal *= 2
        returnVal *= 1 + (sharedPreferencesHelper.getLevel(pref)/100)
        return returnVal.toInt()
        //        return (levels*multiplier).toInt() + getUnitCurrLevel(unitName) * 2 * levels
    }

    // Casts a spell specified by the spell slot
    fun castSpell(spellSlot: String, context: Context) {
        val spell = spells.find {it.spellSlot == spellSlot}
        if (spell != null) {
            spell.cooldownLeft = spell.cooldown
        }
        when(spellSlot) {
            "archer" -> {
                _monsterHealth.intValue -= 15
            }
            "wizard" -> {
                _monsterHealth.intValue -= 30
            }
            "mystic" -> {
                _monsterHealth.intValue -= 45
            }
            "paladin" -> {
                _monsterHealth.intValue -= 45
            }
            "knight" -> {
                _monsterHealth.intValue -= 9999999
            }

        }
        if (_monsterHealth.intValue <= 0) updateGame(context)


    }
    fun updateGame(context: Context) {
        val damage = calculateDamage()
        _monsterHealth.intValue -= damage
        if (_monsterHealth.intValue <= 0) {
            // Add stats to user
            val addedGold = (_monsterLevel.intValue * Math.log((_monsterLevel.intValue + 1).toDouble()) * logScalingFactor).toInt()
            sharedPreferencesHelper.saveGold(pref, sharedPreferencesHelper.getGold(pref) + addedGold)
            sharedPreferencesHelper.saveLevel(pref, sharedPreferencesHelper.getLevel(pref) + 1)
            // Log monster death
            val xpAmount = 50 + _monsterLevel.intValue
            val coloredString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Red)) {
                    append(monsterName.value)
                }
                withStyle(style = SpanStyle(color = Color.White)) {
                    append(", received ")
                }
                withStyle(style = SpanStyle(color = Color.Green)) {
                    append("$xpAmount xp ")
                }
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("and ")
                }
                withStyle(style = SpanStyle(color = Color.Yellow)) {
                    append("$addedGold gold")
                }
            }
            val entry = LogEntry(coloredString, monsterName.value, xpAmount, addedGold)
            addLogEntry(entry)
            // Monster death, generate a new one
            createNewMonster()
            // Play deathsound
            val mediaPlayer = MediaPlayer.create(context, DeathSounds.entries.toTypedArray().random().resID)
            mediaPlayer.setOnCompletionListener {
                mediaPlayer.release()
            }
            mediaPlayer.start()

        } else {
            if (_monsterHealth.intValue > _monsterMaxHealth.intValue) _monsterHealth.intValue = _monsterMaxHealth.intValue
            sharedPreferencesHelper.saveMonsterHealth(pref, _monsterHealth.intValue)
        }
    }

    enum class DeathSounds(val resID: Int){
        ARCHDEMON(R.raw.archdemon_death),
        CYCLOPS(R.raw.cyclops_death),
        DEMONS(R.raw.demons_death),
        MEDUSA(R.raw.medusa_death),
        GOBLINS(R.raw.goblins_death)
    }

    fun isSpellOnCooldown(spell: Spell): Boolean {
        return spell.cooldownLeft > 0L
    }
    fun updateSpellCooldowns(spells: List<Spell>) {
        for (spell in spells) {
            if (spell.cooldownLeft != 0L)  spell.cooldownLeft--

            cooldownLefts[spell.spellSlot]?.value = spell.cooldownLeft
        }
    }
    fun addLogEntry(entry: LogEntry) {
        synchronized(logEntries) {
            logEntries.add(entry)
        }
    }

    fun getCooldown(spellSlot: String): MutableState<Long>? {
        return cooldowns[spellSlot]
    }

    fun getCooldownLeft(spellSlot: String): MutableState<Long>? {
        return cooldownLefts[spellSlot]
    }
}