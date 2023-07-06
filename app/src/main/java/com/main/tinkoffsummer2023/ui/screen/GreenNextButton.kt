package com.main.tinkoffsummer2023.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme


@Composable
fun GreenNextButton(
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier
            .clip(CircleShape)
            .background(CustomTheme.colors.secondaryBackground)
            .size(56.dp),
        onClick = { },

    ) {
        Icon(
            painterResource(id = R.drawable.arrow_next),
            contentDescription = "",
            tint = CustomTheme.colors.secondaryText,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview
@Composable
fun GreenButtonWithArrowPreview() {
    GreenNextButton({})
}








