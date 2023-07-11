package com.animeproject.ui.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.screen.util.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.screen.profile.settings.LocalSettingsEventBus
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
fun SettingsScreen(
    navController: NavController,
) {
    val settingsEventBus = LocalSettingsEventBus.current
    val currentSettings = settingsEventBus.currentSettings.collectAsState().value

    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            BaseTopAppBar(
                {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                {
                    androidx.compose.material.Text(
                        text = "Выбор темы приложения",
                        style = CustomTheme.typography.base,
                    )
                },
            )
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
            ) {
                SettingsItemUi(text = "Как в системе", isChecked = true) {
                    settingsEventBus.updateDarkMode(!currentSettings.isDarkMode)
                }
                SettingsItemUi(text = "Светлая тема", isChecked = false) {
                    settingsEventBus.updateDarkMode(!currentSettings.isDarkMode)
                }
                SettingsItemUi(text = "Темная тема", isChecked = false) {
                    settingsEventBus.updateDarkMode(!currentSettings.isDarkMode)
                }
            }
        }
    }
}

@Composable
private fun SettingsItemUi(
    text: String,
    isChecked: Boolean,
    onClick: () -> Unit = {},
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, style = CustomTheme.typography.base)
        IconButton(onClick = { onClick.invoke()}) {
            if (isChecked)
                Image(
                    painter = painterResource(id = R.drawable.checked),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                ) else
                Image(
                    painter = painterResource(id = R.drawable.circle_white),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
        }
    }
    Divider()

}

