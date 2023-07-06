package com.animeproject.ui.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.main.tinkoffsummer2023.ui.screen.settings.LocalSettingsEventBus
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
fun SettingsScreen(
) {
    val settingsEventBus = LocalSettingsEventBus.current
    val currentSettings = settingsEventBus.currentSettings.collectAsState().value

    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
        }
    }
}

@Preview
@Composable
fun SettingsPreview() {
    SettingsScreen()
}
