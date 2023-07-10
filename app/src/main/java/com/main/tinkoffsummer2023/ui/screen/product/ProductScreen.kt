package com.main.tinkoffsummer2023.ui.screen.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.main.tinkoffsummer2023.ui.screen.util.BaseInfoColumn
import com.main.tinkoffsummer2023.ui.screen.util.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
private fun ProductScreenActions(
    navController: NavController,
    viewAction: ProductAction?
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
    viewModel: ProductViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

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
    eventHandler: (ProductEvent) -> Unit
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {

            BaseTopAppBar({
                IconButton(onClick = { eventHandler.invoke(ProductEvent.OnBackClick) }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            })
            val pagerState = rememberPagerState()

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
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
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                item {
                    BaseInfoColumn(
                        content = {
                            Text(text = state.product.name, style = CustomTheme.typography.base)
                            Text(
                                text = "${state.product.price} бонуса",
                                style = CustomTheme.typography.heading
                            )
                        }
                    )

                }
                item {
                    BaseInfoColumn(
                        content = {
                            Text(text = "ООО AAA", style = CustomTheme.typography.base)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                repeat(5) {
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "",
                                        Modifier.size(20.dp)
                                    )
                                }
                                Text(
                                    text = "${state.product.rating}",
                                    style = CustomTheme.typography.hint
                                )
                            }
                            Divider()
                            Text(
                                text = "+7 (987) 654 33 21",
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
            }
        }
    }
}

