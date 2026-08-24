package com.example.post37.ui.screen

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.post37.R

class ImeVisibilityActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ime_visibility)

        findViewById<TextView>(R.id.imeVisibilityResultText).setText(
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CINNAMON_BUN) {
                R.string.bc_ime_visibility_gone
            } else {
                R.string.bc_ime_visibility_stayed
            }
        )

        findViewById<Button>(R.id.backButton).setOnClickListener { finish() }
    }
}
