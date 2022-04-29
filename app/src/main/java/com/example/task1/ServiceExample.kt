package com.example.task1

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi

class ServiceExample : Service() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        mediaPlayer= MediaPlayer.create(this, R.raw.music)
        mediaPlayer.isLooping = true

        val mChannel = NotificationChannel("123", "channel", NotificationManager.IMPORTANCE_DEFAULT)
        mChannel.description = "description"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)

        val notification: Notification = Notification.Builder(this, "123")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Run in foreground")
            .build()

        startForeground(777, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        mediaPlayer.stop()
    }
}