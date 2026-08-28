package com.example.post37.ui.screen

import android.content.Intent
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewWrapper
import androidx.compose.ui.unit.dp
import com.example.post37.R
import com.example.post37.ui.components.AppBar
import com.example.post37.ui.components.Block
import com.example.post37.ui.navigation.Screen
import com.example.post37.ui.theme.ThemePreviewWrapper

@Composable
fun BehaviorChangesScreen(onNextClick: () -> Unit) {
    val context = LocalContext.current

    Scaffold(
        topBar = { AppBar(name = stringResource(Screen.BehaviorChanges.resourceId)) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppMemoryLimitsBlock()
            Button(onClick = {
                context.startActivity(
                    Intent(context, ImeVisibilityActivity::class.java)
                )
            }) {
                Text(stringResource(R.string.bc_ime_visibility_nav))
            }

            Button(onClick = onNextClick) {
                Text(stringResource(R.string.button_next))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@PreviewWrapper(ThemePreviewWrapper::class)
fun AppMemoryLimitsBlock() = Block {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CINNAMON_BUN) {
        Text(text = stringResource(R.string.bc_app_memory_limited))
        Text(text = stringResource(R.string.bc_app_memory_limited_hint))
    } else {
        Text(text = stringResource(R.string.bc_app_memory_limited_by_class))
    }
}

@Preview
@PreviewWrapper(ThemePreviewWrapper::class)
@Composable
fun BehaviorChangesScreenPreview() {
    BehaviorChangesScreen(onNextClick = {})
}
