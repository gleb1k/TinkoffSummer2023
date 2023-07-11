package com.main.tinkoffsummer2023.ui.screen.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.tinkoffsummer2023.domain.MainRepository
import com.main.tinkoffsummer2023.ui.ViewEvent
import com.main.tinkoffsummer2023.ui.ViewState
import com.main.tinkoffsummer2023.ui.model.MockBackend
import com.main.tinkoffsummer2023.ui.model.Order
import com.main.tinkoffsummer2023.ui.screen.profile.balance.BalanceAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@Immutable
data class OrdersViewState(
    val orders: PersistentList<Order> = MockBackend.ordersDataBase.toPersistentList(),

    override val loading: Boolean = false,
    override val error: String? = null,
) : ViewState

sealed interface OrdersEvent : ViewEvent {

    data class OnOrderItemClick(val id: Int) : OrdersEvent

    object OnBackClick : OrdersEvent
    data class OnLoading(val isLoading: Boolean) : OrdersEvent
    data class OnError(val errorMessage: String?) : OrdersEvent
}

sealed interface OrdersAction {
    data class NavigateToOrder(val categoryId: Int) : OrdersAction
    object NavigateBack : OrdersAction
}

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _state = MutableStateFlow<OrdersViewState>(OrdersViewState())
    val state: StateFlow<OrdersViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<OrdersAction?>()
    val action: SharedFlow<OrdersAction?>
        get() = _action.asSharedFlow()

    fun event(event: OrdersEvent) {
        when (event) {
            is OrdersEvent.OnError -> onError(event)
            is OrdersEvent.OnLoading -> onLoading(event)
            is OrdersEvent.OnOrderItemClick -> onCategoryItemClick(event)
            OrdersEvent.OnBackClick -> onBackClick()
        }
    }

    private fun onBackClick() {
        viewModelScope.launch {
            _action.emit(OrdersAction.NavigateBack)
        }
    }

    private fun onCategoryItemClick(event: OrdersEvent.OnOrderItemClick) {
        viewModelScope.launch {
            _action.emit(OrdersAction.NavigateToOrder(event.id))
        }
    }

    private fun onError(event: OrdersEvent.OnError) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    error = event.errorMessage
                )
            )
        }
    }

    private fun onLoading(event: OrdersEvent.OnLoading) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    loading = event.isLoading
                )
            )
        }
    }
}
