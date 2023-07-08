package com.main.tinkoffsummer2023.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme
import com.main.tinkoffsummer2023.ui.theme.custom.baseLightPalette

@Composable
fun BaseCounter(
    count: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(id = R.drawable.minus),
            "minus",
            tint = CustomTheme.colors.tertiaryColor,
            modifier = Modifier.size(18.dp),
        )
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = "$count", style = TextStyle(
                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = baseLightPalette.tertiaryColor
            )
        )
        Icon(
            painterResource(id = R.drawable.plus),
            "plus", tint = CustomTheme.colors.tertiaryColor,
            modifier = Modifier.size(18.dp),
        )

    }
}
