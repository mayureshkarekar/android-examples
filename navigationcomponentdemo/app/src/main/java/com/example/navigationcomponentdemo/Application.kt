package com.example.navigationcomponentdemo

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

const val NOTIFICATION_CHANNEL_ID = "defaultChannel"

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val defaultNotificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Default",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                defaultNotificationChannel
            )
        }
    }
}