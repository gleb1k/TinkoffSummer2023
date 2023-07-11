package com.main.tinkoffsummer2023.ui.screen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.model.CartProduct
import com.main.tinkoffsummer2023.ui.navigation.BottomScreen
import com.main.tinkoffsummer2023.ui.navigation.Screen
import com.main.tinkoffsummer2023.ui.screen.util.BaseCounter
import com.main.tinkoffsummer2023.ui.screen.util.BaseGreenButton
import com.main.tinkoffsummer2023.ui.screen.util.BaseInfoColumn
import com.main.tinkoffsummer2023.ui.screen.util.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme
import com.main.tinkoffsummer2023.ui.theme.custom.baseLightPalette

@Composable
private fun CartScreenActions(
    navController: NavController,
    viewAction: CartAction?,
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            is CartAction.NavigateToProduct -> navController.navigate("product/${viewAction.productId}")
            CartAction.NavigateBack -> navController.navigateUp()
            CartAction.NavigateToCatalog -> navController.navigateUp()
            CartAction.NavigateToOrderAddress -> navController.navigate(Screen.OrderAddress.route)
        }
    }
}

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    CartScreenActions(
        navController,
        action
    )

    LaunchedEffect(state.cartProducts) {
        viewModel.event(CartEvent.OnLoad)
    }

    if (state.cartProducts.isEmpty())
        EmptyCartScreen(state, viewModel::event)
    else
        NotEmptyCartScreen(state, viewModel::event)

}

@Composable
fun NotEmptyCartScreen(
    state: CartViewState,
    eventHandler: (CartEvent) -> Unit,
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            BaseTopAppBar(
                {
                    IconButton(onClick = {
                        eventHandler.invoke(CartEvent.OnBackClick)
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                {
                    Text(
                        text = "Корзина",
                        style = CustomTheme.typography.base,
                    )
                },
                {
                    IconButton(onClick = {
                        eventHandler.invoke(CartEvent.OnDeleteAllClick)
                    }) {
                        Icon(
                            painterResource(id = R.drawable.trash),
                            "backIcon",
                            tint = CustomTheme.colors.purple,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter,
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    item {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = CustomTheme.padding.horizontal),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Товары (${state.cartProducts.size})",
                                style = CustomTheme.typography.boldBig,
                            )
                        }
                    }
                    itemsIndexed(state.cartProducts) { index, product ->
                        CartItem(product, eventHandler)

                        if (index < state.cartProducts.size)
                            Divider(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = CustomTheme.padding.horizontal)
                            )
                    }
                    item {
                        Spacer(modifier = Modifier.height(90.dp))
                    }
                }
                Box(
                    modifier = Modifier.padding(
                        vertical = CustomTheme.padding.horizontal
                    ),
                ) {
                    BaseInfoColumn(
                        content = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                ) {
                                    Text(
                                        text = "Итоговая стоимость",
                                        style = CustomTheme.typography.hint,
                                    )
                                    Text(
                                        text = "${state.cartProducts.sumOf { it.product.price }} бонуса",
                                        style = CustomTheme.typography.baseBold,
                                    )
                                }
                                BaseGreenButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                        .padding(start = 24.dp),
                                    text = "Оформить",
                                    textStyle = CustomTheme.typography.baseWhite,
                                ) {
                                    eventHandler.invoke(CartEvent.OnCheckoutOrderClick)
                                }
                            }
                        }
                    )

                }
            }
        }
    }

}


@Composable
private fun CartItem(
    cartProduct: CartProduct,
    eventHandler: (CartEvent) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = CustomTheme.padding.horizontal,
                horizontal = CustomTheme.padding.horizontal
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(cartProduct.product.images[0])
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.main_icon),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(2.dp)
                )
                .size(128.dp)
                .clickable {
                         eventHandler.invoke(CartEvent.OnProductClick(cartProduct.product.id))
                },
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp)
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = cartProduct.product.name,
                style = CustomTheme.typography.base,
            )
            BaseGreenButton(
                modifier = Modifier
                    .height(36.dp)
                    .width(150.dp),
                text = "${cartProduct.product.price} бонуса",
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = baseLightPalette.secondaryText
                ),
                onClick = { eventHandler.invoke(CartEvent.OnProductClick(cartProduct.product.id))}
            )
            Box(modifier = Modifier.align(Alignment.End)) {
                BaseCounter(count = cartProduct.count)
            }

        }
    }

}


@Composable
fun EmptyCartScreen(
    state: CartViewState,
    eventHandler: (CartEvent) -> Unit,
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            BaseTopAppBar(
                {
                    IconButton(onClick = {
                        eventHandler.invoke(CartEvent.OnBackClick)
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                {
                    Text(
                        text = "Корзина",
                        style = CustomTheme.typography.base,
                    )
                },
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 56.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.empty_cart),
                    contentDescription = "",
                    modifier = Modifier
                        .size(180.dp)
                        .padding()
                )
                Text(
                    text = "В корзине пока пусто",
                    style = CustomTheme.typography.base
                )
                BaseGreenButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 46.dp, vertical = 8.dp)
                        .height(52.dp),
                    text = "Перейти в каталог"
                ) {
                    eventHandler.invoke(CartEvent.OnCatalogClick)
                }
            }
        }
    }
}

