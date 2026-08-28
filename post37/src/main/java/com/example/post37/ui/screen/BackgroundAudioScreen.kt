package com.example.post37.ui.screen

import android.content.Intent
import android.os.Build
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewWrapper
import androidx.compose.ui.unit.dp
import com.example.post37.R
import com.example.post37.audio.AudioPlayerInteractor
import com.example.post37.audio.AudioPlayerService
import com.example.post37.ui.components.AppBar
import com.example.post37.ui.components.Block
import com.example.post37.ui.navigation.Screen
import com.example.post37.ui.theme.ThemePreviewWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@Preview
@PreviewWrapper(ThemePreviewWrapper::class)
@Composable
fun BackgroundAudioScreen() {
    val context = LocalContext.current
    val activity = LocalActivity.current
    val audioInteractor = remember { AudioPlayerInteractor(context) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { AppBar(name = stringResource(Screen.BackgroundAudio.resourceId)) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { HintsBlock() }

            item {
                BackgroundTestBlock(
                    onClick = {
                        coroutineScope.launch {
                            if (activity == null) return@launch

                            activity.moveTaskToBack(false)
                            delay(7000.milliseconds)
                            audioInteractor.invoke()
                        }
                    }
                )
            }

            item {
                NoWiUTestBlock(
                    onClick = {
                        coroutineScope.launch {
                            if (activity == null) return@launch

                            activity.moveTaskToBack(false)
                            delay(7000.milliseconds)
                            val intent = Intent(activity, AudioPlayerService::class.java)
                            intent.action = AudioPlayerService.START
                            activity.startService(intent)
                        }
                    }
                )
            }
        }
    }
}

@Preview
@PreviewWrapper(ThemePreviewWrapper::class)
@Composable
fun HintsBlock() = Block {
    Text(stringResource(R.string.background_audio_hint))
    Text(stringResource(R.string.background_audio_hint_2))
}

@Composable
fun BackgroundTestBlock(onClick: () -> Unit) = Block {
    Text(stringResource(R.string.background_audio_no_fgs_hint))

    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(stringResource(R.string.background_audio_no_fgs))
    }

    Text(stringResource(
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.BAKLAVA)
            R.string.background_audio_no_fgs_allowed
        else R.string.background_audio_no_fgs_restricted
    ))
}

@Composable
fun NoWiUTestBlock(onClick: () -> Unit) = Block {
    Text(stringResource(R.string.background_audio_no_wiu_hint))

    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(stringResource(R.string.background_audio_no_wiu))
    }
}

@Preview
@PreviewWrapper(ThemePreviewWrapper::class)
@Composable
fun BackgroundTestBlockPreview() {
    BackgroundTestBlock(onClick = {})
}

@Preview
@PreviewWrapper(ThemePreviewWrapper::class)
@Composable
fun NoWiUTestBlockPreview() {
    NoWiUTestBlock(onClick = {})
}