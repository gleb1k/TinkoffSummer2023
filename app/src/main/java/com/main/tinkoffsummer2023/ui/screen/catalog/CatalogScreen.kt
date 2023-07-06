package com.main.tinkoffsummer2023.ui.screen.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
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
import com.main.tinkoffsummer2023.ui.screen.BaseTextFieldUi
import com.main.tinkoffsummer2023.ui.screen.auth.signUp.SignUpAction
import com.main.tinkoffsummer2023.ui.screen.auth.signUp.SignUpEvent
import com.main.tinkoffsummer2023.ui.screen.auth.signUp.SignUpViewModel
import com.main.tinkoffsummer2023.ui.screen.auth.signUp.SignUpViewState
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme


@Composable
private fun CatalogScreenActions(
    navController: NavController,
    viewAction: CatalogAction?
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            //todo huynya
            is CatalogAction.NavigateToCategory -> TODO()
        }
    }
}


@Composable
fun CatalogScreen(
    navController: NavController,
    viewModel: CatalogViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    Content(
        state,
        viewModel::event
    )

    CatalogScreenActions(
        navController,
        action
    )
}

@Composable
fun Content(
    state: CatalogViewState,
    eventHandler: (CatalogEvent) -> Unit
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {

            TopAppBar(
                backgroundColor = CustomTheme.colors.secondaryBackground,
                elevation = 8.dp,
            ) {
                Text(
                    text = "Каталог",
                    style = CustomTheme.typography.heading,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                ) {
                    BaseTextFieldUi(
                        label = "Искать на СкороХод",
                        value = "",
                        //todo спросить у илюхи
//                    onValueChange = eventHandler.invoke(SignUpEvent.OnQueryLoginChange),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.search),
                                "",
                                Modifier.size(30.dp)
                            )
                        },
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp),
                ) {
                    Text(
                        text = "Каталог",
                        style = CustomTheme.typography.heading
                    )
                }

                LazyColumn(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(CustomTheme.colors.primaryBackground),
                ) {
                    item {

                    }
                }
            }

        }
    }
}
