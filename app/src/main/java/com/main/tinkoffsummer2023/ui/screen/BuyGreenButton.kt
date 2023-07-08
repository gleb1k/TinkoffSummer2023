package com.main.tinkoffsummer2023.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
fun BuyGreenButton(
    price: Int = 99,
    onClick: (Int) -> Unit,
    onclickparam: Int
) {
    Button(
        onClick = { onClick.invoke(onclickparam) },
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = CustomTheme.colors.secondaryBackground
        )
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$price бонуса",
                fontWeight = FontWeight(700),
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontSize = 13.sp,
                color = CustomTheme.colors.secondaryText
            )
            Icon(
                painter = painterResource(id = R.drawable.bag),
                contentDescription = "Купить",
                tint = CustomTheme.colors.secondaryText,
            )
        }
    }
}
