package com.main.tinkoffsummer2023.ui.screen.profile.about_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.screen.util.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
fun AboutAppScreen(
    navController: NavController,
) {
    Surface(color = CustomTheme.colors.white) {
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
                    Text(
                        text = "О приложении",
                        style = CustomTheme.typography.base,
                    )
                },
            )
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
                Spacer(modifier = Modifier.padding(24.dp))
                TextAndNumUi(text = "Версия", num = "1.0.0")
                TextAndNumUi(text = "Сборка", num = "1")
                Text(
                    text = "2023 г.", style = CustomTheme.typography.hint,
                    modifier = Modifier.padding(top = 12.dp)
                )

                Text(
                    text = "Пользовательское соглашение",
                    color = CustomTheme.colors.blue,
                    style = CustomTheme.typography.base,
                    modifier = Modifier.padding(top = 32.dp)
                )
            }
        }
    }
}

@Composable
private fun TextAndNumUi(
    text: String,
    num: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(text = text, style = CustomTheme.typography.base)
            Text(text = num, style = CustomTheme.typography.hint)
        }
    }
}
