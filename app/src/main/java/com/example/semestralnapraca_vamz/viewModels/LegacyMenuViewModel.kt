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
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_vamz.MainActivity
import com.example.semestralnapraca_vamz.R
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
        _legacyReceived.intValue = calculateLegacy(false, context)
        _currentLegacy.intValue = sharedPreferencesHelper.getLegacy(pref)

        if (currentLegacy.value >= getUpgradePrice("Game speed")) {
            val countDownTimerLegacy = object : CountDownTimer(60001,1000) {
                override fun onTick(millisUntilFinished: Long) {
                }
                override fun onFinish() {
                    createNotification(context, context.getString(R.string.don_t_miss_out),
                        context.getString(R.string.you_are_able_to_speed_up_your_game_head_to_the_legacy_menu))
                }
            }
            countDownTimerLegacy.start() // Start the timer
        }

    }
    fun calculateLegacy(resetGame: Boolean, context: Context) : Int {
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
            sharedPreferencesHelper.saveArcherSpellLevel(pref, 1)
            sharedPreferencesHelper.saveWizardSpellLevel(pref, 1)
            sharedPreferencesHelper.saveMysticSpellLevel(pref, 1)
            sharedPreferencesHelper.saveKnightSpellLevel(pref, 1)
            sharedPreferencesHelper.savePaladinSpellLevel(pref, 1)
            reloadLegacy(context)
        }

        return calculatedLeg.toInt()
    }


    fun resetGame(context: Context) {
        calculateLegacy(true, context)
    }

    fun reloadLegacy(context: Context) {
        _currentLegacy.intValue = sharedPreferencesHelper.getLegacy(pref)
        _legacyReceived.intValue = calculateLegacy(false, context)
    }

    fun getUpgradePrice(type: String): Int {
        when(type) {
            "Game speed" -> {return sharedPreferencesHelper.getGameSpeed(pref) * 5}
            "Total Damage" -> {return sharedPreferencesHelper.getLegacyTotal(pref) * 50}
            "Archer upgrade" -> {return sharedPreferencesHelper.getLegacyArcherLevel(pref) * 10}
            "Wizard upgrade" -> {return sharedPreferencesHelper.getLegacyWizardLevel(pref) * 10}
            "Mystic upgrade" -> {return sharedPreferencesHelper.getLegacyMysticLevel(pref) * 15}
            "Knight upgrade" -> {return sharedPreferencesHelper.getLegacyKnightLevel(pref) * 20}
        }
        return sharedPreferencesHelper.getGameSpeed(pref) * 5
    }
    fun legacyUpgrade(type: String, context: Context) {
        val price = getUpgradePrice(type)
        if (currentLegacy.value >= price) {
            sharedPreferencesHelper.saveLegacy(pref, currentLegacy.value - price)
            currentLegacy.value -= price
            when(type) {
                "Game speed" -> {sharedPreferencesHelper.saveGameSpeed(pref, sharedPreferencesHelper.getGameSpeed(pref) + 1)}
                "Total Damage" -> {sharedPreferencesHelper.saveLegacyTotal(pref, sharedPreferencesHelper.getLegacyTotal(pref) + 1)}
                "Archer upgrade" -> {sharedPreferencesHelper.saveLegacyArcherLevel(pref, sharedPreferencesHelper.getLegacyArcherLevel(pref) + 1)}
                "Wizard upgrade" -> {sharedPreferencesHelper.saveLegacyWizardLevel(pref, sharedPreferencesHelper.getLegacyWizardLevel(pref) + 1)}
                "Mystic upgrade" -> {sharedPreferencesHelper.saveLegacyMysticLevel(pref, sharedPreferencesHelper.getLegacyMysticLevel(pref) + 1)}
                "Knight upgrade" -> {sharedPreferencesHelper.saveLegacyKnightLevel(pref, sharedPreferencesHelper.getLegacyKnightLevel(pref) + 1)}
            }
        }
    }

    private var notifNumber = 1
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
        val builder = NotificationCompat.Builder(context, "idlegame_channel2")
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
            val channel = NotificationChannel("idlegame_channel2", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}