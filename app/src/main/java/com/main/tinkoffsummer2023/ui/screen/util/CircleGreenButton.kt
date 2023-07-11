package com.main.tinkoffsummer2023.ui.screen.util

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme


@Composable
fun CircleGreenButton(
    @DrawableRes
    icon: Int = R.drawable.arrow_next,
    isGreen: Boolean = true,
    onClick: () -> Unit,
) {
    if (isGreen)
        IconButton(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(CustomTheme.colors.secondaryBackground),
            onClick = { onClick.invoke() },
            enabled = isGreen

            ) {
            Icon(
                painterResource(id = icon),
                contentDescription = "",
                tint = CustomTheme.colors.secondaryText,
                modifier = Modifier.size(20.dp)
            )
        }
    else
        IconButton(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(CustomTheme.colors.gray),
            onClick = { onClick.invoke() },
            enabled = isGreen
            ) {
            Icon(
                painterResource(id = icon),
                contentDescription = "",
                tint = CustomTheme.colors.secondaryText,
                modifier = Modifier.size(20.dp)
            )
        }
}








