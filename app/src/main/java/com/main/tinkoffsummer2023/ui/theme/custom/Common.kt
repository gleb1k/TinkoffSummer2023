package com.main.tinkoffsummer2023.ui.theme.custom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp


data class Colors(
    val primaryText: Color,
    val primaryBackground: Color,

    val secondaryText: Color,
    val secondaryBackground: Color,

    val tintColor: Color,
    val white: Color,

    val errorColor: Color,

    val purple: Color,
    val red: Color,
    val blue: Color,
    val gray: Color,
)

data class Typography(
    val heading: TextStyle,
    val hint: TextStyle,
    val base: TextStyle,
    val bottom: TextStyle,
)

data class Padding (
    val vertical: Dp,
    val horizontal : Dp,
)

object CustomTheme {

    val colors: Colors
        @Composable
        get() = LocalCustomColors.current

    val typography: Typography
        @Composable
        get() = LocalCustomTypography.current

    val padding : Padding
        @Composable
        get() = LocalCustomPadding.current
}

val LocalCustomColors = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}

val LocalCustomTypography = staticCompositionLocalOf<Typography> {
    error("No font provided")
}

val LocalCustomPadding = staticCompositionLocalOf<Padding> {
    error("No font provided")
}

