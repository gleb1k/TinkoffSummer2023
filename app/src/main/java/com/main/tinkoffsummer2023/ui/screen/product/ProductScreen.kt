package com.main.tinkoffsummer2023.ui.screen.product

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.screen.util.BaseGreenButton
import com.main.tinkoffsummer2023.ui.screen.util.BaseInfoColumn
import com.main.tinkoffsummer2023.ui.screen.util.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
private fun ProductScreenActions(
    navController: NavController,
    viewAction: ProductAction?,
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            ProductAction.NavigateBack -> navController.navigateUp()
        }
    }
}


@Composable
fun ProductScreen(
    navController: NavController,
    productId: Int?,
    viewModel: ProductViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    if (productId != null) {
        LaunchedEffect(Unit) {
            viewModel.event(ProductEvent.OnLoad(productId))
        }
    } else {
        viewModel.event(ProductEvent.OnError("There is nothing to load, id==null"))
    }

    Content(
        state,
        viewModel::event
    )

    ProductScreenActions(
        navController,
        action
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Content(
    state: ProductViewState,
    eventHandler: (ProductEvent) -> Unit,
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {

            BaseTopAppBar({
                IconButton(onClick = { eventHandler.invoke(ProductEvent.OnBackClick) }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            })

            state.product?.let {
                val pagerState = rememberPagerState()
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(CustomTheme.padding.vertical),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        item {
                            HorizontalPager(
                                pageCount = state.product.images.size,
                                state = pagerState,
                                key = { state.product.images[it] },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(state.product.images[it])
                                        .crossfade(true)
                                        .build(),
                                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                                    error = painterResource(R.drawable.main_icon),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                        item {
                            BaseInfoColumn(
                                content = {
                                    Text(
                                        text = state.product.name,
                                        style = CustomTheme.typography.baseBold
                                    )
                                    Text(
                                        text = "${state.product.price} бонуса",
                                        style = CustomTheme.typography.boldBig,
                                        color = CustomTheme.colors.secondaryBackground,
                                    )
                                }
                            )

                        }
                        item {
                            BaseInfoColumn(
                                content = {
                                    Text(text = "ООО \"Иван Иванов\"", style = CustomTheme.typography.baseBold)
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        repeat(state.product.rating.toInt()) {
                                            Icon(
                                                Icons.Default.Star,
                                                contentDescription = "",
                                                Modifier.size(24.dp)
                                            )
                                        }
                                        Text(
                                            text = "${state.product.rating}",
                                            style = CustomTheme.typography.baseBold,
                                            modifier = Modifier.padding(horizontal = 8.dp)
                                        )
                                    }
                                    Divider(Modifier.padding(top = 4.dp, bottom = 8.dp))
                                    Text(
                                        text = "+7 (800) 555 35 35",
                                        style = CustomTheme.typography.hint
                                    )
                                }
                            )
                        }
                        item {
                            BaseInfoColumn(
                                content = {
                                    Text(
                                        text = state.product.description,
                                        style = CustomTheme.typography.base
                                    )
                                }
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(56.dp))
                        }
                    }
                    Box(
                        modifier = Modifier.padding(
                            horizontal = CustomTheme.padding.horizontal,
                            vertical = CustomTheme.padding.horizontal
                        ),
                    ) {
                        BaseGreenButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            text = "Добавить в корзину",
                            textStyle = CustomTheme.typography.baseWhite,
                        ) {
                            eventHandler.invoke(ProductEvent.OnAddToCartClick)
                        }
                    }
                }
            }
        }
    }
}

