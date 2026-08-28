package com.example.pre37.audio

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.pre37.Pre37Application
import com.example.pre37.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class AudioPlayerService : Service() {

    private val scope = CoroutineScope(Dispatchers.Default)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == START) {
            val audioInteractor = AudioPlayerInteractor(this)

            scope.launch {
                startForeground(NOTIFICATION_ID, notification())
                audioInteractor.invoke()
                delay(2200.milliseconds)
                stopSelf()
            }
        }

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun notification(): Notification = NotificationCompat
        .Builder(this, Pre37Application.NOTIFICATION_CHANNEL)
        .setSmallIcon(android.R.drawable.ic_media_play)
        .setContentTitle(getString(R.string.notification_audio_title))
        .setContentText(getString(R.string.notification_audio_text))
        .setOngoing(true)
        .build()

    companion object {
        const val START = "com.example.pre37.audio.START"

        private const val NOTIFICATION_ID = 37
    }
}