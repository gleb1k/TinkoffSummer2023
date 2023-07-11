package com.main.tinkoffsummer2023.ui.screen.orders.order_flow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
import com.main.tinkoffsummer2023.ui.model.MockBackend
import com.main.tinkoffsummer2023.ui.navigation.Screen
import com.main.tinkoffsummer2023.ui.screen.util.BaseGreenButton
import com.main.tinkoffsummer2023.ui.screen.util.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme


@Composable
fun OrderingScreen(
    navController: NavController,
    viewModel: OrderingFlowViewModel = hiltViewModel(),
) {
    val order = MockBackend.ordersDataBase[0]

    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            BaseTopAppBar(
                leftItem = {
                    IconButton({
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                centerItem = {
                    Text(
                        text = "Оформление заказа",
                        style = CustomTheme.typography.base,
                    )
                },
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
            ) {
                Column() {
                    Text(
                        text = "Ваш заказ / ${order.products.size}",
                        style = CustomTheme.typography.boldBig
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(CustomTheme.padding.horizontal)
                    ) {
                        items(order.products) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(it.product.images[0])
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
                                    .size(100.dp)
                                    .clickable {
                                        navController.navigate("product/${it.product.id}")
                                    }
                            )
                        }
                    }
                }
                Column() {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Доставка",
                            style = CustomTheme.typography.baseBold
                        )
                        Text(
                            text = "Изменить",
                            style = CustomTheme.typography.hint,
                            color = CustomTheme.colors.secondaryBackground
                        )
                    }
                    Text(
                        text = order.address,
                        style = CustomTheme.typography.base,
                    )
                }
                Column(
                    Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Ожидаемая дата доставки",
                        style = CustomTheme.typography.baseBold
                    )
                    Text(
                        text = order.finishTime,
                        style = CustomTheme.typography.base,
                    )
                }
                Column() {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Способ оплаты",
                            style = CustomTheme.typography.baseBold
                        )
                        Text(
                            text = "Изменить",
                            style = CustomTheme.typography.hint,
                            color = CustomTheme.colors.secondaryBackground
                        )
                    }
                    Text(
                        text = "Картой при получении",
                        style = CustomTheme.typography.base,
                    )

                }
                BaseGreenButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    text = "Оформить",
                    textStyle = CustomTheme.typography.baseWhite,
                ) {
                    navController.navigate(Screen.OrderIsProcessed.route)
                }
            }
        }
    }
}
