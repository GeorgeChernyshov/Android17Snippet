package com.example.pre37

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pre37.ui.navigation.Screen
import com.example.pre37.ui.screen.BackgroundAudioScreen
import com.example.pre37.ui.theme.Android17SnippetTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { App() }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()

        Android17SnippetTheme {
            NavHost(
                navController = navController,
                startDestination = Screen.BackgroundAudio
            ) {
                composable<Screen.BackgroundAudio> {
                    BackgroundAudioScreen()
                }
            }
        }
    }
}