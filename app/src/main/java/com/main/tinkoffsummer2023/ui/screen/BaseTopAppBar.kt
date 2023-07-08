package com.main.tinkoffsummer2023.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
fun BaseTopAppBar(
    leftItem: @Composable () -> Unit = {
        IconButton(
            onClick = { },
            enabled = false
        ) {}
    },
    centerItem: @Composable () -> Unit = {
        Text(text = "")
    },
    rightItem: @Composable () -> Unit = {
        IconButton(
            onClick = { },
            enabled = false
        ) {}
    },
) {
    TopAppBar(
        backgroundColor = CustomTheme.colors.secondaryBackground,
        elevation = 8.dp,
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            leftItem()
            centerItem()
            rightItem()
        }

    }
}
