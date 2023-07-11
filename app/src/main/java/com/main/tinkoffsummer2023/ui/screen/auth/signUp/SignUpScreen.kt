package com.main.tinkoffsummer2023.ui.screen.auth.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.navigation.Screen
import com.main.tinkoffsummer2023.ui.screen.util.BaseTextFieldUi
import com.main.tinkoffsummer2023.ui.screen.util.CircleGreenButton
import com.main.tinkoffsummer2023.ui.screen.util.PasswordTextField
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme
import com.main.tinkoffsummer2023.ui.theme.custom.baseLightPalette


@Composable
private fun SignUpScreenActions(
    navController: NavController,
    viewAction: SignUpAction?,
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            SignUpAction.NavigateToSignIn -> navController.navigate(Screen.SignIn.route)
            null -> Unit
        }
    }
}


@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    Content(
        state,
        viewModel::event
    )

    SignUpScreenActions(
        navController,
        action
    )
}

@Composable
private fun Content(
    state: SignUpViewState,
    eventHandler: (SignUpEvent) -> Unit,
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {

            TopAppBar(
                backgroundColor = CustomTheme.colors.secondaryBackground,
                elevation = 8.dp,
            ) {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            ) {
                // Создание аккаунта
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp),
                ) {
                    Text(
                        text = "Создание аккаунта",
                        style = CustomTheme.typography.heading
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                ) {
                    BaseTextFieldUi(
                        label = "Номер телефона или Email",
                        value = state.queryLogin,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.user_filled),
                                "",
                                Modifier.size(30.dp)
                            )
                        },
                    ) { eventHandler.invoke(SignUpEvent.OnQueryLoginChange(it)) }
                }
                // password
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                ) {
                    PasswordTextField(
                        label = "Пароль",
                        value = state.queryPassword,
                    ) { eventHandler.invoke(SignUpEvent.OnQueryPasswordChange(it)) }
                }
                // password2
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                ) {
                    PasswordTextField(
                        label = "Подтверждение пароля",
                        value = state.queryPasswordConfirm,
                    ) { eventHandler.invoke(SignUpEvent.OnQueryPasswordConfirmChange(it)) }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Checkbox(
                            checked = state.isAdminChecked,
                            onCheckedChange = { eventHandler.invoke(SignUpEvent.OnAdminCheckedChange(it)) },
                            colors = CheckboxDefaults.colors(
                                checkedColor = CustomTheme.colors.secondaryBackground
                            )
                        )

                        Text(
                            text = "Я продавец",
                            style = CustomTheme.typography.base,
                            color = CustomTheme.colors.secondaryBackground
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Зарегистрироваться",
                            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = baseLightPalette.primaryText,
                            modifier = Modifier.weight(2f)
                        )
                        CircleGreenButton(
                            isGreen = state.queryLogin.isNotBlank() && state.queryPassword.isNotBlank() && state.queryPasswordConfirm.isNotBlank()
                        ) {
                            eventHandler.invoke(SignUpEvent.OnSignUpClick)
                        }
                    }
                }

            }
        }
    }
}


