package com.main.tinkoffsummer2023.ui.screen.orders

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.model.Order
import com.main.tinkoffsummer2023.ui.screen.util.BaseGreenButton
import com.main.tinkoffsummer2023.ui.screen.util.BaseInfoColumn
import com.main.tinkoffsummer2023.ui.screen.util.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.screen.util.OrderStatusUi
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
private fun OrdersScreenActions(
    navController: NavController,
    viewAction: OrdersAction?,
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            OrdersAction.NavigateBack -> navController.navigateUp()
            is OrdersAction.NavigateToOrder -> {}
        }
    }
}

@Composable
fun OrdersScreen(
    navController: NavController,
    viewModel: OrdersViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    Content(
        state,
        viewModel::event
    )

    OrdersScreenActions(
        navController,
        action
    )

}

@Composable
private fun Content(
    state: OrdersViewState,
    eventHandler: (OrdersEvent) -> Unit,
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            BaseTopAppBar(
                {
                    IconButton(onClick = {
                        eventHandler.invoke(OrdersEvent.OnBackClick)
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                {
                    Text(
                        text = "Заказы",
                        style = CustomTheme.typography.base,
                    )
                },
            )

            if (state.orders.isEmpty())
                EmptyOrdersScreen(state, eventHandler)
            else
                NotEmptyOrdersScreen(state, eventHandler)
        }
    }
}

@Composable
fun NotEmptyOrdersScreen(
    state: OrdersViewState,
    eventHandler: (OrdersEvent) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(CustomTheme.padding.vertical),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = CustomTheme.padding.vertical)
    ) {
        items(state.orders) {
            OrderItem(order = it)
        }

    }
}

@Composable
fun OrderItem(
    order: Order,
) {
    BaseInfoColumn(
        content = {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(text = "Заказ от ${order.orderTime}", style = CustomTheme.typography.base)
                Text(
                    text = "${order.id}",
                    style = CustomTheme.typography.base,
                    color = CustomTheme.colors.secondaryBackground
                )
                Divider(
                    Modifier.padding(vertical = 8.dp)
                )
                Text(text = "Доставка курьерской службой", style = CustomTheme.typography.base)
                Box(
                    Modifier.padding(vertical = 8.dp)
                ) {
                    OrderStatusUi(text = order.status, color = CustomTheme.colors.blue)
                }
                Text(
                    text = "Ожидаемая дата доставки: ${order.finishTime}",
                    style = CustomTheme.typography.hint
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(order.products) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it.product.images[0])
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.ic_launcher_foreground),
                            error = painterResource(R.drawable.main_icon),
                            contentDescription = "Заказ",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(2.dp)
                                )
                                .size(100.dp)
                                .padding(top = 8.dp),
                        )
                    }
                }


            }
        },
        isClickable = true,
        onClick = {})
}

@Composable
fun EmptyOrdersScreen(
    state: OrdersViewState,
    eventHandler: (OrdersEvent) -> Unit,
) {
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
        androidx.compose.material.Text(
            text = "У вас пока нет заказов",
            style = CustomTheme.typography.base
        )
        BaseGreenButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 46.dp, vertical = 8.dp)
                .height(52.dp),
            text = "Перейти к покупкам"
        ) {
            eventHandler.invoke(OrdersEvent.OnBackClick)
        }
    }
}


