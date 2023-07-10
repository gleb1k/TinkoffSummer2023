package com.main.tinkoffsummer2023.ui.screen.util

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
fun BaseInfoColumn(
    @SuppressLint("ModifierParameter") outsideModifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = CustomTheme.padding.horizontal),
    content: @Composable () -> Unit = {
        IconButton(
            onClick = { },
            enabled = false
        ) {}
    },
    isClickable: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = outsideModifier
    ) {
        if (isClickable)
            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .clickable {
                        onClick.invoke()
                    }
                    .background(CustomTheme.colors.white)
                    .padding(16.dp)
            ) {
                content()
            }
        else
            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(CustomTheme.colors.white)
                    .padding(16.dp)
            ) {
                content()
            }
    }
}


