package com.main.tinkoffsummer2023.ui.screen.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.main.tinkoffsummer2023.ui.theme.base.TinkoffSummer2023Theme
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTextFieldUi(
    value: String = "",
    label: String = "",
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {

    TextField(
        value = value,
        onValueChange = { onValueChange.invoke(it) },
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = label,
                style = CustomTheme.typography.hint
            )
        },
        shape = RoundedCornerShape(18.dp),
        singleLine = true,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,

        colors = TextFieldDefaults.textFieldColors(
            containerColor = CustomTheme.colors.white,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )


}


@Preview(showBackground = true)
@Composable
private fun UiPreview() {

    TinkoffSummer2023Theme {
        Column(
            modifier = Modifier.background(
                color = Color.Green
            )
        )
        {
//            BaseTextFieldUi()
        }
    }


}
