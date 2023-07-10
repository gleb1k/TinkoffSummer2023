package com.main.tinkoffsummer2023.ui.screen.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme


@Composable
fun BaseGreenButton(
    modifier: Modifier = Modifier,
    text: String = "",
    textStyle: TextStyle = TextStyle(
        fontWeight = FontWeight(700),
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontSize = 20.sp,
        color = CustomTheme.colors.secondaryText
    ),
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Button(
            onClick = { onClick.invoke() },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = CustomTheme.colors.secondaryBackground
            )
        ) {
            Text(
                text = text,
                style = textStyle,
            )
        }
    }

}
