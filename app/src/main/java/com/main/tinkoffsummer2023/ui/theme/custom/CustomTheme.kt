package com.main.tinkoffsummer2023.ui.theme.custom

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.main.tinkoffsummer2023.R

@Composable
fun CustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when {
        darkTheme -> baseDarkPalette
        else -> baseLightPalette
    }

    val typography = Typography(
        heading = TextStyle(
            fontFamily = FontFamily(Font(R.font.montserrat_extrabold)),
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            color = baseLightPalette.primaryText
        ),
        hint = TextStyle(
            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = baseLightPalette.tintColor
        ),
        base = TextStyle(
            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = baseLightPalette.primaryText
        ),
        bottom = TextStyle(
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.secondaryBackground.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalCustomColors provides colors,
        LocalCustomTypography provides typography,
        content = content
    )
}
