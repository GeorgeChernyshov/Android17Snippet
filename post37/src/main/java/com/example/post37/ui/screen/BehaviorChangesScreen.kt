package com.example.post37.ui.screen

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewWrapper
import androidx.compose.ui.unit.dp
import com.example.post37.R
import com.example.post37.ui.components.AppBar
import com.example.post37.ui.theme.ThemePreviewWrapper

@Preview
@PreviewWrapper(ThemePreviewWrapper::class)
@Composable
fun BehaviorChangesScreen() {
    Scaffold(
        topBar = { AppBar(name = stringResource(R.string.label_behavior_changes)) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppMemoryLimitsBlock()
        }
    }
}

@Composable
@Preview(showBackground = true)
@PreviewWrapper(ThemePreviewWrapper::class)
fun AppMemoryLimitsBlock() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CINNAMON_BUN) {
            Text(text = stringResource(R.string.bc_app_memory_limited))
            Text(text = stringResource(R.string.bc_app_memory_limited_hint))
        } else {
            Text(text = stringResource(R.string.bc_app_memory_limited_by_class))
        }
    }
}