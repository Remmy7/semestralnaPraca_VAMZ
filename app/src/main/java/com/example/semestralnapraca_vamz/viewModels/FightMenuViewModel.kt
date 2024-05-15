package com.example.semestralnapraca_vamz.viewModels

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
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
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_vamz.LogEntry
import com.example.semestralnapraca_vamz.MainActivity
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

    private val _monsterName = mutableStateOf(context.getString(R.string.tempboss))
    val monsterName: MutableState<String> = _monsterName

    private val _monsterSprite = mutableIntStateOf(R.drawable.enemy_medusa)
    val monsterSprite: MutableState<Int> = _monsterSprite



    private val logScalingFactor = 25
    private val exponentialScalingFactor = 1.7
    private val baseMonsterHealth = 10
    private var gameSpeed = 1

    private val cooldowns = mutableStateMapOf<String, MutableState<Long>>()
    private val cooldownLefts = mutableStateMapOf<String, MutableState<Long>>()






    val logEntries = SnapshotStateList<LogEntry>()

    val spells: List<Spell>

    private val pref: SharedPreferences
    private val sharedPreferencesHelper: SharedPreferencesHelper
    private val countDownTimer: CountDownTimer





    init {
        sharedPreferencesHelper = SharedPreferencesHelper
        pref = SharedPreferencesHelper.getSharedPreferences(context)
        loadSavedData()



        spells = listOf(
            Spell(getDrawable("archer"), 5L, "archer"),
            Spell(getDrawable("wizard"), 10L, "wizard"),
            Spell(getDrawable("mystic"), 15L, "mystic"),
            Spell(getDrawable("knight"), 0L, "knight"),
            Spell(getDrawable("paladin"), 25L, "paladin")
        )

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

    fun getDrawable(spellSlot: String): Int {
        when(spellSlot) {
            "archer" -> {
                val level = sharedPreferencesHelper.getArcherSpellLevel(pref)
                if (level < 5) return R.drawable.archer_spell_1
                if (level < 10) return R.drawable.archer_spell_2
                if (level < 15) return R.drawable.archer_spell_3
                if (level < 20) return R.drawable.archer_spell_4
                if (level < 25) return R.drawable.archer_spell_5
                if (level >= 25) return R.drawable.archer_spell_6
            }
            "wizard" -> {
                val level = sharedPreferencesHelper.getWizardSpellLevel(pref)
                if (level < 5) return R.drawable.wizard_spell_1
                if (level < 10) return R.drawable.wizard_spell_2
                if (level < 15) return R.drawable.wizard_spell_3
                if (level < 20) return R.drawable.wizard_spell_4
                if (level < 25) return R.drawable.wizard_spell_5
                if (level >= 25) return R.drawable.wizard_spell_6
            }
            "mystic" -> {
                val level = sharedPreferencesHelper.getMysticSpellLevel(pref)
                if (level < 5) return R.drawable.mystic_spell_1
                if (level < 10) return R.drawable.mystic_spell_2
                if (level < 15) return R.drawable.mystic_spell_3
                if (level < 20) return R.drawable.mystic_spell_4
                if (level < 25) return R.drawable.mystic_spell_5
                if (level >= 25) return R.drawable.mystic_spell_6
            }
            "knight" -> {
                val level = sharedPreferencesHelper.getKnightSpellLevel(pref)
                if (level < 5) return R.drawable.knight_spell_1
                if (level < 10) return R.drawable.knight_spell_2
                if (level < 15) return R.drawable.knight_spell_3
                if (level < 20) return R.drawable.knight_spell_4
                if (level < 25) return R.drawable.knight_spell_5
                if (level >= 25) return R.drawable.knight_spell_6
            }
            "paladin" -> {
                val level = sharedPreferencesHelper.getPaladinSpellLevel(pref)
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

    fun reloadFight() {
        loadSavedData()
    }


    override fun onCleared() {
        super.onCleared()
        countDownTimer.cancel()

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

        _monsterSprite.intValue = returnRandomSprite()
    }

    fun createNewMonster(context: Context) {
        val newMaxHealth = Math.pow(baseMonsterHealth * (_monsterLevel.intValue + 1).toDouble(), exponentialScalingFactor).toInt()
        setMonsterStats(_monsterLevel.intValue + 1, newMaxHealth, newMaxHealth, generateMonsterName())
        // Play deathsound
        val mediaPlayer = MediaPlayer.create(context, DeathSounds.entries.toTypedArray().random().resID)
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
        }
        mediaPlayer.start()
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
            ((sharedPreferencesHelper.getArcherLevel(pref) * multiplierArcher) * (1 + (sharedPreferencesHelper.getLegacyArcherLevel(pref)/100)) +
            (sharedPreferencesHelper.getKnightLevel(pref) * multiplierKnight) * (1 + (sharedPreferencesHelper.getLegacyKnightLevel(pref)/100)) +
            (sharedPreferencesHelper.getMysticLevel(pref) * multiplierMystic) * (1 + (sharedPreferencesHelper.getLegacyMysticLevel(pref)/100)) +
            (sharedPreferencesHelper.getWizardLevel(pref) * multiplierWizard) * (1 + (sharedPreferencesHelper.getLegacyWizardLevel(pref)/100)) +
            (sharedPreferencesHelper.getPaladinLevel(pref) * multiplierPaladin)) * (1 + (sharedPreferencesHelper.getLegacyTotal(pref) / 100))
        returnVal *= 2
        returnVal *= 1 + (sharedPreferencesHelper.getLevel(pref)/100)
        return returnVal.toInt()
    }

    // Casts a spell specified by the spell slot
    fun castSpell(spellSlot: String, context: Context) {
        val spell = spells.find {it.spellSlot == spellSlot}
        if (spell != null) {
            spell.cooldownLeft = spell.cooldown
        }
        when(spellSlot) {
            "archer" -> {
                _monsterHealth.intValue -=
                    (sharedPreferencesHelper.getArcherSpellLevel(pref) * (1 + sharedPreferencesHelper.getLegacyArcherLevel(pref)/100))*15
            }
            "wizard" -> {
                _monsterHealth.intValue -=
                    (sharedPreferencesHelper.getWizardSpellLevel(pref) * (1 + sharedPreferencesHelper.getLegacyWizardLevel(pref)/100))*30
            }
            "mystic" -> {
                _monsterHealth.intValue -=
                    (sharedPreferencesHelper.getMysticSpellLevel(pref) * (1 + sharedPreferencesHelper.getLegacyMysticLevel(pref)/100))*45
            }
            "paladin" -> {
                _monsterHealth.intValue -=
                    (sharedPreferencesHelper.getPaladinSpellLevel(pref))*60
            }
            "knight" -> {
                _monsterHealth.intValue -=
                    (sharedPreferencesHelper.getKnightSpellLevel(pref) * (1 + sharedPreferencesHelper.getLegacyKnightLevel(pref)/100))*120
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
                    append(context.getString(R.string.received_3))
                }
                withStyle(style = SpanStyle(color = Color.Green)) {
                    append(context.getString(R.string.xp_2, xpAmount.toString()))
                }
                withStyle(style = SpanStyle(color = Color.White)) {
                    append(context.getString(R.string.and_5))
                }
                withStyle(style = SpanStyle(color = Color.Yellow)) {
                    append(context.getString(R.string.gold_fight, addedGold.toString()))
                }
            }
            val entry = LogEntry(coloredString, monsterName.value, xpAmount, addedGold)
            addLogEntry(entry)
            // send a notification
            createNotification(context,
                context.getString(R.string.you_killed_a_monster), coloredString.toString())
            // Monster death, generate a new one
            createNewMonster(context)


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

    enum class EnemySprites(val resID: Int) {
        Oryx(R.drawable.enemy_oryx),
        Oryx2(R.drawable.enemy_oryx2),
        Medusa(R.drawable.enemy_medusa),
        Beholder(R.drawable.enemy_beholder),
        Umi(R.drawable.enemy_umi)
    }

    fun returnRandomSprite() : Int {
        return EnemySprites.entries.toTypedArray().random().resID
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

    private var notifNumber = 5000
    fun createNotification(context: Context, title: String, content: String) {
        // Create the notification channel
        createNotificationChannel(context)

        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Build the notification
        val builder = NotificationCompat.Builder(context, "idlegame_channel")
            .setSmallIcon(R.drawable.mystic_spell_6)
            .setContentTitle(title)
            .setContentText(content)
            .setStyle(NotificationCompat.BigTextStyle().bigText(content))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        // Show the notification
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notifNumber++
            notify(notifNumber, builder.build())
        }
    }
    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "idlegame_channel"
            val descriptionText = "My Notification Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("idlegame_channel", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}