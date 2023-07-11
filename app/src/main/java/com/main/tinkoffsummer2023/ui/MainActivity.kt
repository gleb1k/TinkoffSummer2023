package com.main.tinkoffsummer2023.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.main.tinkoffsummer2023.core.PreferenceManager
import com.main.tinkoffsummer2023.ui.navigation.CustomNavHost
import com.main.tinkoffsummer2023.ui.navigation.Screen
import com.main.tinkoffsummer2023.ui.screen.profile.settings.LocalSettingsEventBus
import com.main.tinkoffsummer2023.ui.screen.profile.settings.SettingsEventBus
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val settingsEventBus = remember { SettingsEventBus() }

            val currentSettings = settingsEventBus.currentSettings.collectAsState().value

            CustomTheme(
                darkTheme = currentSettings.isDarkMode,
            ) {
                CompositionLocalProvider(
                    LocalSettingsEventBus provides settingsEventBus
                ) {

                    // костыль пипец
                    if (preferenceManager.getUserToken().isNullOrBlank())
                        CustomNavHost(
                            preferenceManager = preferenceManager,
                        )
                    else
                        CustomNavHost(
                            preferenceManager = preferenceManager,
                            startDestination = Screen.Catalog
                        )
                }
            }
        }
    }
}

