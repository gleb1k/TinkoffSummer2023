package com.main.tinkoffsummer2023.ui.screen.orders.order_flow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.main.tinkoffsummer2023.ui.model.MockBackend
import com.main.tinkoffsummer2023.ui.navigation.BottomScreen
import com.main.tinkoffsummer2023.ui.screen.util.BaseGreenButton
import com.main.tinkoffsummer2023.ui.screen.util.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
fun OrderIsProcessedScreen(
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
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
            ) {
                Column(
                    Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Заказ оформлен",
                        style = CustomTheme.typography.boldBig,
                    )
                }
                Column(Modifier.fillMaxWidth()) {

                        Text(
                            text = "Доставка",
                            style = CustomTheme.typography.baseBold
                        )

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
                Column(Modifier.fillMaxWidth()) {

                    Text(
                        text = "Способ оплаты",
                        style = CustomTheme.typography.baseBold
                    )

                    Text(
                        text = "Картой при получении",
                        style = CustomTheme.typography.base,
                    )

                }
                BaseGreenButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    text = "Хорошо",
                    textStyle = CustomTheme.typography.baseWhite,
                ) {
                    navController.navigate(BottomScreen.Orders.route)
                }
            }
        }
    }
}
