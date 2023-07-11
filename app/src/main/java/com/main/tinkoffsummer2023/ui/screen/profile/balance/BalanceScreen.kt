package com.main.tinkoffsummer2023.ui.screen.profile.balance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.main.tinkoffsummer2023.ui.model.MockBackend
import com.main.tinkoffsummer2023.ui.navigation.BottomScreen
import com.main.tinkoffsummer2023.ui.screen.util.BaseGreenButton
import com.main.tinkoffsummer2023.ui.screen.util.BaseInfoColumn
import com.main.tinkoffsummer2023.ui.screen.util.BaseTextFieldUi
import com.main.tinkoffsummer2023.ui.screen.util.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
private fun BalanceScreenActions(
    navController: NavController,
    viewAction: BalanceAction?,
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            BalanceAction.NavigateBack -> navController.navigateUp()
            BalanceAction.NavigateToProfile -> navController.navigateUp()
        }
    }
}

@Composable
fun BalanceScreen(
    navController: NavController,
    viewModel: BalanceViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

//    LaunchedEffect(state.products) {
//        viewModel.event(CatalogEvent.OnLoad)
//    }

    Content(
        state,
        viewModel::event
    )

    BalanceScreenActions(
        navController,
        action
    )
}

@Composable
private fun Content(
    state: BalanceViewState,
    eventHandler: (BalanceEvent) -> Unit,
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            BaseTopAppBar(
                {
                    IconButton(onClick = {
                        eventHandler.invoke(BalanceEvent.OnBackClick)
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                {
                    Text(
                        text = "Пополнение баланса",
                        style = CustomTheme.typography.base,
                    )
                },
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(56.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 24.dp),
            ) {
                BaseInfoColumn(
                    Modifier,
                    content = {
                        Text(
                            text = "Мой баланс",
                            style = CustomTheme.typography.base
                        )
                        Text(
                            text = MockBackend.usersDataBase[0].score.toString(),
                            style = CustomTheme.typography.heading,
                            color = CustomTheme.colors.secondaryBackground,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(CustomTheme.padding.vertical),
                ) {
                    Text(
                        text = "Введите сумму, которую хотите внести",
                        style = CustomTheme.typography.base,
                    )
                    BaseTextFieldUi(
                        value = state.query,
                    ) {
                        eventHandler.invoke(BalanceEvent.OnQueryChange(it))
                    }
                }
                BaseGreenButton(
                    text = "Готово",
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 12.dp)
                        .width(100.dp)
                        .height(50.dp)
                ) {
                    eventHandler.invoke(BalanceEvent.OnAddClick)
                }
            }
        }

    }
}

