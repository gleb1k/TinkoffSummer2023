package com.main.tinkoffsummer2023.ui.screen.catalog.filter

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.model.Category
import com.main.tinkoffsummer2023.ui.model.MockBackend
import com.main.tinkoffsummer2023.ui.screen.util.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
fun FilterScreen(
    navController: NavController,
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            BaseTopAppBar(
                {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                centerItem = {
                    Text(
                        text = "Фильтры",
                        style = CustomTheme.typography.base,
                    )
                },
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
            ) {

                itemsIndexed(MockBackend.categories) { index, category ->
                    if (index == 0)
                        FilterItem(category, true)
                    else
                        FilterItem(category, false)

                    if (index < MockBackend.categories.size)
                        Divider(
                            Modifier
                                .fillMaxWidth()
                        )
                }
            }
        }
    }
}

@Composable
private fun FilterItem(
    category: Category,
    isChecked: Boolean,
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = category.name, style = CustomTheme.typography.base)
        IconButton(onClick = { }) {
            if (isChecked)
                Image(
                    painter = painterResource(id = R.drawable.checked),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                ) else
                Image(
                    painter = painterResource(id = R.drawable.circle_white),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
        }
    }
}

@Composable
fun ImageCheckbox(
    isChecked: Boolean,
    uncheckedImage: Painter,
    checkedImage: Painter,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 30.dp,
) {
    Image(
        painter = if (isChecked) checkedImage else uncheckedImage,
        contentDescription = null,
        modifier = modifier
            .size(size)
            .clickable { onCheckedChange(!isChecked) }
            .padding(4.dp),
    )
}
