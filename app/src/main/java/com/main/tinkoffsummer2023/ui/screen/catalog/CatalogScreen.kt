package com.main.tinkoffsummer2023.ui.screen.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.model.Product
import com.main.tinkoffsummer2023.ui.navigation.Screen
import com.main.tinkoffsummer2023.ui.screen.util.BaseGreenButton
import com.main.tinkoffsummer2023.ui.screen.util.BuyGreenButton
import com.main.tinkoffsummer2023.ui.screen.util.CustomTextField
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme


@Composable
private fun CatalogScreenActions(
    navController: NavController,
    viewAction: CatalogAction?,
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            CatalogAction.NavigateToFilter -> navController.navigate(Screen.Filter.route)
            // todo kostyl
            is CatalogAction.NavigateToProduct -> navController.navigate("product/${viewAction.productId}")
            CatalogAction.NavigateBack -> navController.navigateUp()
        }
    }
}


@Composable
fun CatalogScreen(
    navController: NavController,
    query: String? = null,
    viewModel: CatalogViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    // kostyl' криво работает... я устал
    LaunchedEffect(query) {
        if (query != null) {
            viewModel.event(CatalogEvent.OnQueryChange(query))
            viewModel.event(CatalogEvent.OnSearchClick(state.query))
        } else
            viewModel.event(CatalogEvent.OnSearchClick(state.query))
    }

    Content(
        state,
        viewModel::event
    )

    CatalogScreenActions(
        navController,
        action
    )
}

@Composable
private fun Content(
    state: CatalogViewState,
    eventHandler: (CatalogEvent) -> Unit,
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                backgroundColor = CustomTheme.colors.secondaryBackground,
                elevation = 8.dp,
            ) {
                IconButton(onClick = {
                    eventHandler.invoke(CatalogEvent.OnBackClick)
                }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
                Box(
                    Modifier
                        .weight(1.0f)
                ) {
                    CustomTextField(
                        trailingIcon = null,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colors.surface,
                                RoundedCornerShape(percent = 40)
                            )
                            .padding(4.dp)
                            .height(30.dp),
                        fontSize = 16.sp,
                        placeholderText = "Искать на СкороХод",
                        value = state.query,
                        onValueChange = { eventHandler.invoke(CatalogEvent.OnQueryChange(it)) },
                        keyboardOptions = KeyboardOptions().copy(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                eventHandler.invoke(CatalogEvent.OnSearchClick(state.query))
                            }
                        ),
                    )
                }
                //какая-то херня происходит с размерами
                IconButton(onClick = {
                    eventHandler.invoke(CatalogEvent.OnFilterClick)
                }) {
                    Icon(
                        painterResource(id = R.drawable.filter),
                        null,
                        modifier = Modifier
                            .size(46.dp)
                            .padding(horizontal = 12.dp)
                    )
                }
            }
            if (state.products.isEmpty())
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    EmptyCatalogScreen(eventHandler)
                }
            else {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 0.dp),
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                        ) {

                            items(state.products, key = { it.id }) {
                                ProductItem(
                                    it,
                                    { eventHandler.invoke(CatalogEvent.OnProductItemClick(it.id)) },
                                    { eventHandler.invoke(CatalogEvent.OnAddToCartClick(it.id)) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductItem(
    product: Product,
    onImageClick: () -> Unit,
    onButtonClick: () -> Unit,
) {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.images[0])
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(2.dp)
                )
                .clickable {
                    onImageClick.invoke()
                }
                .fillMaxWidth()
                .size(160.dp)
        )
        Text(
            text = product.name,
            style = CustomTheme.typography.base,
        )
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                repeat(product.rating.toInt()) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        Modifier.size(20.dp)
                    )
                }
            }
            Text(
                text = product.rating.toString(),
                style = CustomTheme.typography.base,
            )
        }
        BuyGreenButton(product.price) { onButtonClick.invoke() }
    }
}

@Composable
fun EmptyCatalogScreen(
    eventHandler: (CatalogEvent) -> Unit,
) {
    Surface(color = CustomTheme.colors.secondaryText) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 56.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.box2),
                    contentDescription = "",
                    modifier = Modifier
                        .size(180.dp)
                        .padding()
                )
                Text(
                    text = "Простите, по Вашему запросу товаров сейчас нет",
                    style = CustomTheme.typography.base,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                )
                BaseGreenButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 46.dp, vertical = 8.dp)
                        .height(52.dp),
                    text = "Перейти в каталог"
                ) {
                    eventHandler.invoke(CatalogEvent.OnBackClick)
                }
            }
        }
    }
}


