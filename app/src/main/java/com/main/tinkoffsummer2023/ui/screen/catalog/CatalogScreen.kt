package com.main.tinkoffsummer2023.ui.screen.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.model.Category
import com.main.tinkoffsummer2023.ui.model.MockTempConstants
import com.main.tinkoffsummer2023.ui.model.Product
import com.main.tinkoffsummer2023.ui.screen.BuyGreenButton
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme


@Composable
private fun CatalogScreenActions(
    navController: NavController,
    viewAction: CatalogAction?
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            //todo huynya
            is CatalogAction.NavigateToCategory -> TODO()
        }
    }
}


@Composable
fun CatalogScreen(
    navController: NavController,
    viewModel: CatalogViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    Content(
        state,
        viewModel::event
    )

    CatalogScreenActions(
        navController,
        action
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
    state: CatalogViewState,
    eventHandler: (CatalogEvent) -> Unit
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {

            TopAppBar(
                backgroundColor = CustomTheme.colors.secondaryBackground,
                elevation = 8.dp,
            ) {
                TextField(
                    value = "",
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    label = {
                        Text(
                            text = "Искать на СкороХод",
                            style = CustomTheme.typography.hint
                        )
                    },
                    shape = RoundedCornerShape(18.dp),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search_green),
                            contentDescription = "",
                            modifier = Modifier.size(18.dp),
                            tint = CustomTheme.colors.secondaryBackground
                        )
                    },

                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = CustomTheme.colors.white,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp),
                ) {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 0.dp),
                            ) {
                                Text(
                                    text = "Каталог",
                                    style = CustomTheme.typography.heading
                                )
                            }
                        }
                        items(MockTempConstants.categories) {
                            CatalogCategoryItem(it)
                        }
                        items(MockTempConstants.products) {
                            CatalogProductItem(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CatalogCategoryItem(
    category: Category
) {
    Column() {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(category.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.main_icon),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(
                RoundedCornerShape(16.dp)
            )
        )
        Text(
            text = category.name,
            style = CustomTheme.typography.base,
        )
    }
}

@Composable
fun CatalogProductItem(
    product: Product,
) {
    Column() {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(
                RoundedCornerShape(2.dp)
            )
        )
        Text(
            text = product.name,
            style = CustomTheme.typography.base,
        )
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row() {
                repeat(5) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "",
                        Modifier.size(20.dp)
                    )
                }
            }
            Text(
                text = product.rating.toString(),
                style = CustomTheme.typography.base,
            )
        }
        BuyGreenButton(onClick = {}, onclickparam = 1)
    }
}

