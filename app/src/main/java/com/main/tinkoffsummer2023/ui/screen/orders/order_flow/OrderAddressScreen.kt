package com.main.tinkoffsummer2023.ui.screen.orders.order_flow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.main.tinkoffsummer2023.ui.model.Order
import com.main.tinkoffsummer2023.ui.navigation.Screen
import com.main.tinkoffsummer2023.ui.screen.util.BaseGreenButton
import com.main.tinkoffsummer2023.ui.screen.util.BaseTextFieldUi
import com.main.tinkoffsummer2023.ui.screen.util.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme
import kotlin.random.Random

@Composable
fun OrderAddressScreen(
    navController: NavController,
    viewModel: OrderingFlowViewModel = hiltViewModel(),
) {
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
                        text = "Адрес доставки",
                        style = CustomTheme.typography.base,
                    )
                },
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
            ) {
                BaseTextFieldUi(
                    label = "Страна",
                    value = viewModel.countryQuery.collectAsStateWithLifecycle().value,
                ) {
                    run { viewModel.onCountryQueryChange(it) }
                }
                BaseTextFieldUi(
                    label = "Город/населенный пункт",
                    value = viewModel.cityQuery.collectAsStateWithLifecycle().value,
                ) {
                    run { viewModel.onCityQueryChange(it) }
                }
                BaseTextFieldUi(
                    label = "Улица",
                    value = viewModel.streetQuery.collectAsStateWithLifecycle().value,
                ) {
                    run { viewModel.onStreetQueryChange(it) }
                }
                BaseTextFieldUi(
                    label = "Строение/корпус",
                    value = viewModel.buildingQuery.collectAsStateWithLifecycle().value,
                ) {
                    run { viewModel.onBuildingQueryChange(it) }
                }

                BaseGreenButton(
                    text = "Далее",
                    textStyle = CustomTheme.typography.baseWhite,
                    modifier = Modifier
                        .align(Alignment.End)
                        .width(100.dp)
                        .height(50.dp)
                ) {
                    MockBackend.ordersDataBase.add(
                        Order(
                            id = Random(2).nextInt(),
                            orderTime = "12 июля",
                            finishTime = "19 июля",
                            address = "${viewModel.countryQuery.value}, г. ${viewModel.cityQuery.value}, ${viewModel.streetQuery.value} ${viewModel.buildingQuery.value}",
                            status = "В сборке",
                            products = MockBackend.cartProductDataBase
                        )
                    )
                    navController.navigate(Screen.Ordering.route)
                }

            }
        }

    }

}
