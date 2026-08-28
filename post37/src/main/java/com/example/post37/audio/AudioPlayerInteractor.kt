package com.example.post37.audio

import android.content.Context
import android.media.AudioFocusRequest
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.sin
import kotlin.time.Duration.Companion.milliseconds

class AudioPlayerInteractor(context: Context) {

    val audioManager: AudioManager = context.getSystemService(AudioManager::class.java)
    val scope = CoroutineScope(Dispatchers.Default)

    fun invoke() = scope.launch {
        val sampleRate = 44_100
        val durationMs = 2_000
        val samples = ShortArray(sampleRate * durationMs / 1_000) { index ->
            val angle = 2.0 * PI * 880.0 * index / sampleRate
            sin(angle).times(Short.MAX_VALUE)
                .times(0.2)
                .toInt()
                .toShort()
        }

        val focusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
            .build()

        val focusResult = audioManager.requestAudioFocus(focusRequest)

        val format = AudioFormat.Builder()
            .setSampleRate(sampleRate)
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
            .build()

        val track = AudioTrack.Builder()
            .setAudioFormat(format)
            .setBufferSizeInBytes(samples.size * Short.SIZE_BYTES)
            .build()

        track.write(samples, 0, samples.size)
        track.play()

        delay(1000.milliseconds)
        var volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        repeat(2) {
            audioManager.setStreamVolume(
                AudioManager.STREAM_MUSIC,
                ++volume,
                0
            )

            delay(200.milliseconds)
        }

        repeat(2) {
            audioManager.setStreamVolume(
                AudioManager.STREAM_MUSIC,
                --volume,
                0
            )

            delay(200.milliseconds)
        }

        delay(200.milliseconds)

        if (focusResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            audioManager.abandonAudioFocusRequest(focusRequest)
        }

        track.release()
    }
}