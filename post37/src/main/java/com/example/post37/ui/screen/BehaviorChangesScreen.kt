package com.example.post37.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
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
        ) {}
    }
}