package com.main.tinkoffsummer2023.ui.screen.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.navigation.BottomScreen
import com.main.tinkoffsummer2023.ui.navigation.Screen
import com.main.tinkoffsummer2023.ui.screen.util.BaseGreenButton
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
private fun StartScreenActions(
    navController: NavController,
    viewAction: StartAction?
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            StartAction.NavigateToSignIn -> navController.navigate(Screen.SignIn.route)
            StartAction.NavigateToSignUp -> navController.navigate(Screen.SignUp.route)
            StartAction.NavigateToCatalog -> navController.navigate(BottomScreen.Category.route)
            null -> Unit
        }
    }
}

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: StartViewModel = hiltViewModel()
) {

    val action by viewModel.action.collectAsStateWithLifecycle(null)

    Content(
        viewModel::event
    )

    StartScreenActions(
        navController,
        action
    )

}

@Composable
fun Content(
    eventHandler: (StartEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.white),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.main_icon),
                contentDescription = "СкороХод",
                modifier = Modifier
                    .size(180.dp)
                    .padding()
            )
            Text(
                text = "CкороХод",
                fontWeight = FontWeight(800),
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontSize = 40.sp,
            )
            Text(
                text = "Место быстрых покупок",
                fontWeight = FontWeight(700),
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontSize = 20.sp
            )

        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, start = 48.dp, end = 48.dp),
        ) {
            Row(
                Modifier
                    .padding(vertical = 24.dp)
            ) {
                ClickableText(
                    text = AnnotatedString("Зарегистрироваться"),
                    onClick = { eventHandler.invoke(StartEvent.OnSignUpClick) },
                    style = CustomTheme.typography.base,
                )
            }
            BaseGreenButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                text = "Авторизоваться"
            ) { eventHandler.invoke(StartEvent.OnSignInClick) }
            Row(
                Modifier
                    .padding(vertical = 24.dp)
            ) {
                ClickableText(
                    text = AnnotatedString("Перейти к каталогу"),
                    onClick = { eventHandler.invoke(StartEvent.OnCatalogClick) },
                    style = CustomTheme.typography.base
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {

//    StartScreen()

}
