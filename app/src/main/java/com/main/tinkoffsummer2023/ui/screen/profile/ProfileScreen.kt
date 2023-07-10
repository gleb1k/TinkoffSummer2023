package com.main.tinkoffsummer2023.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.navigation.Screen
import com.main.tinkoffsummer2023.ui.screen.util.BaseInfoColumn
import com.main.tinkoffsummer2023.ui.screen.util.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.screen.util.CircleGreenButton
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
private fun ProfileScreenActions(
    navController: NavController,
    viewAction: ProfileAction?
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            ProfileAction.NavigateTheme -> navController.navigate(Screen.Settings.route)
            ProfileAction.NavigateToAboutApp -> navController.navigate(Screen.AboutApp.route)
            ProfileAction.NavigateToBalance -> navController.navigate(Screen.Balance.route)
            ProfileAction.NavigateBack -> navController.navigateUp()
        }
    }
}


@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    Content(
        state,
        viewModel::event
    )

    ProfileScreenActions(
        navController,
        action
    )
}

@Composable
private fun Content(
    state: ProfileViewState,
    eventHandler: (ProfileEvent) -> Unit
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            BaseTopAppBar(
                {
                    IconButton(onClick = {
                        eventHandler.invoke(ProfileEvent.OnBackClick)

                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
            )
            Column(
                Modifier
                    .padding(
                        bottom = 16.dp,
                        start = CustomTheme.padding.horizontal,
                        end = CustomTheme.padding.horizontal
                    )
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Мой профиль",
                    style = CustomTheme.typography.heading,
                )
            }
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(CustomTheme.padding.vertical),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = CustomTheme.padding.vertical),
            ) {
                item {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = CustomTheme.padding.horizontal),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Редактировать",
                            style = CustomTheme.typography.base,
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                        )
                    }
                    BaseInfoColumn(
                        content = {
                            Text(text = "Иван Иванович", style = CustomTheme.typography.base)
                            Text(text = "iivan@gmail.ru", style = CustomTheme.typography.hint)
                            Divider()
                            Text(
                                text = "+7 (987) 654 33 21",
                                style = CustomTheme.typography.hint
                            )
                        }
                    )
                }
                item {
                    BaseInfoColumn(
                        content = {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Мой баланс", style = CustomTheme.typography.base)
                                CircleGreenButton(
                                    icon = R.drawable.plus
                                ) {
                                    eventHandler.invoke(ProfileEvent.OnBalanceClick)
                                }
                            }

                            Text(text = "15 350", style = CustomTheme.typography.heading)
                        }

                    )
                }
                item {
                    BaseInfoColumn(
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.settings),
                                        contentDescription = "",
                                        Modifier.size(24.dp)
                                    )
                                    Text(
                                        text = "О приложении",
                                        style = CustomTheme.typography.base,
                                        modifier = Modifier.padding(start = 6.dp)
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.right_arrow_small),
                                        contentDescription = "",
                                        Modifier.size(28.dp)
                                    )
                                }
                            }

                        }, isClickable = true,
                        onClick = {
                            eventHandler.invoke(ProfileEvent.OnAboutAppClick)
                        }
                    )
                }
                item {
                    BaseInfoColumn(
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.home),
                                        contentDescription = "",
                                        Modifier.size(24.dp)
                                    )
                                    Text(
                                        text = "Выбор темы приложения",
                                        style = CustomTheme.typography.base,
                                        modifier = Modifier.padding(start = 6.dp)
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.right_arrow_small),
                                        contentDescription = "",
                                        Modifier.size(28.dp)
                                    )
                                }
                            }
                        }, isClickable = true,
                        onClick = {
                            eventHandler.invoke(ProfileEvent.OnThemeClick)
                        }
                    )
                }

            }

        }
    }
}
