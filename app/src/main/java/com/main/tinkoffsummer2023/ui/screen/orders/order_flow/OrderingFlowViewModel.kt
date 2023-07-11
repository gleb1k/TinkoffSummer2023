package com.main.tinkoffsummer2023.ui.screen.orders.order_flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.tinkoffsummer2023.domain.MainRepository
import com.main.tinkoffsummer2023.ui.model.MockBackend
import com.main.tinkoffsummer2023.ui.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class OrderingFlowViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    private val _order = MutableStateFlow<Order?>(Order())
    val order: StateFlow<Order?>
        get() = _order.asStateFlow()

    private val _countryQuery = MutableStateFlow<String>("")
    val countryQuery: StateFlow<String>
        get() = _countryQuery.asStateFlow()

    private val _cityQuery = MutableStateFlow<String>("")
    val cityQuery: StateFlow<String>
        get() = _cityQuery.asStateFlow()

    private val _streetQuery = MutableStateFlow<String>("")
    val streetQuery: StateFlow<String>
        get() = _streetQuery.asStateFlow()

    private val _buildingQuery = MutableStateFlow<String>("")
    val buildingQuery: StateFlow<String>
        get() = _buildingQuery.asStateFlow()

    fun onCountryQueryChange(query: String) {
        viewModelScope.launch {
            _countryQuery.emit(query)
        }
    }

    fun onCityQueryChange(query: String) {
        viewModelScope.launch {
            _cityQuery.emit(query)
        }
    }

    fun onStreetQueryChange(query: String) {
        viewModelScope.launch {
            _streetQuery.emit(query)
        }
    }

    fun onBuildingQueryChange(query: String) {
        viewModelScope.launch {
            _buildingQuery.emit(query)
        }
    }

    fun onAddressNextClick() {
        viewModelScope.launch {
            _order.emit(
                Order(
                    id = Random(2).nextInt(),
                    orderTime = "",
                    finishTime = "19 июля",
                    address = "${countryQuery.value} ${cityQuery.value} ${streetQuery.value} ${buildingQuery.value}",
                    status = "В сборке",
                    products = MockBackend.cartProductDataBase
                )
            )
        }
    }

}
