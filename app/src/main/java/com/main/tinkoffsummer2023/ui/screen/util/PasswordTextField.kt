package com.main.tinkoffsummer2023.ui.screen.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String = "",
    label: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit,
) {

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

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
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        shape = RoundedCornerShape(18.dp),
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.lock),
                "",
                Modifier.size(24.dp)
            )
        },
        trailingIcon = {
            IconButton(onClick = {passwordVisible = !passwordVisible}) {
                Icon(
                    painter = painterResource(R.drawable.eye),
                    "",
                    Modifier.size(24.dp)
                )
            }
        },

        colors = TextFieldDefaults.textFieldColors(
            containerColor = CustomTheme.colors.white,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )


}
