package com.main.tinkoffsummer2023.ui.screen.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.main.tinkoffsummer2023.ui.navigation.BottomScreen
import com.main.tinkoffsummer2023.ui.screen.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.screen.auth.signIn.SignInAction
import com.main.tinkoffsummer2023.ui.screen.auth.signIn.SignInEvent
import com.main.tinkoffsummer2023.ui.screen.auth.signIn.SignInViewModel
import com.main.tinkoffsummer2023.ui.screen.auth.signIn.SignInViewState
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
private fun ProductScreenActions(
    navController: NavController,
    viewAction: ProductAction?
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
        }
    }
}


@Composable
fun ProductScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    Content(
        state,
        viewModel::event
    )

    ProductScreenActions(
        navController,
        action
    )
}

@Composable
fun Content(
    state: ProductViewState,
    eventHandler: (ProductEvent) -> Unit
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {

            BaseTopAppBar({
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            })
        }
    }
}
