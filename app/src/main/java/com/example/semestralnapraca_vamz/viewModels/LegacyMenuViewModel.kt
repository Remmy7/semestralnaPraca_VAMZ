package com.example.semestralnapraca_vamz.viewModels

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
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

        }
        // Pošli notifikáciu
        if (speedUpgradePrice() < currentLegacy.value) {
            createNotification(context, "Don't miss out!", "You are able to speed up your game,\n head to the Legacy menu!")
        }
        return calculatedLeg.toInt()
    }


    fun resetGame(context: Context) {
        calculateLegacy(true, context)
    }

    fun reloadLegacy() {
        _currentLegacy.intValue = sharedPreferencesHelper.getLegacy(pref)
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
            .setSmallIcon(R.drawable.mystic_spell)
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
            notify(1, builder.build())
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