package com.main.tinkoffsummer2023.ui.screen.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
fun OrderStatusUi(
    text: String,
    color: Color,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(color)
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 12.dp),
            text = text,
            style = CustomTheme.typography.base,
            color = CustomTheme.colors.secondaryText,
        )
    }
}
