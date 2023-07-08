package com.main.tinkoffsummer2023.ui.theme.custom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


data class Colors(
    val primaryText: Color,
    val primaryBackground: Color,

    val secondaryText: Color,
    val secondaryBackground: Color,

    val tintColor: Color,
    val white: Color,

    val errorColor: Color,
    val tertiaryColor : Color
)

data class Typography(
    val heading: TextStyle,
    val hint: TextStyle,
    val base: TextStyle,
    val bottom: TextStyle,
)

object CustomTheme {

    val colors: Colors
        @Composable
        get() = LocalCustomColors.current

    val typography: Typography
        @Composable
        get() = LocalCustomTypography.current

}

val LocalCustomColors = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}

val LocalCustomTypography = staticCompositionLocalOf<Typography> {
    error("No font provided")
}

