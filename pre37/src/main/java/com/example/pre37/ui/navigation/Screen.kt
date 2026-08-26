package com.example.pre37.ui.navigation

import androidx.annotation.StringRes
import com.example.pre37.R
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String, @StringRes val resourceId: Int) {

    @Serializable
    data object BackgroundAudio : Screen(
        route = "backgroundAudio",
        resourceId = R.string.label_background_audio
    )
}