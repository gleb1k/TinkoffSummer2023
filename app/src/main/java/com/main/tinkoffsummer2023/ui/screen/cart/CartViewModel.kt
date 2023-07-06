package com.main.tinkoffsummer2023.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.tinkoffsummer2023.ui.ViewEvent
import com.main.tinkoffsummer2023.ui.ViewState
import com.main.tinkoffsummer2023.ui.model.ProductInCart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CartViewState(
    val cartProducts: PersistentList<ProductInCart> = persistentListOf(),

    override val loading: Boolean = false,
    override val error: String? = null,
) : ViewState

sealed interface CartEvent : ViewEvent {
    object OnDeleteAllClick : CartEvent
    data class OnProductClick(val productId: Int) : CartEvent
    data class OnProductPlusClick(val productId: Int) : CartEvent
    data class OnProductMinusClick(val productId: Int) : CartEvent
    object OnCheckoutOrderClick : CartEvent

    data class OnLoading(val isLoading: Boolean) : CartEvent
    data class OnError(val errorMessage: String?) : CartEvent
}

sealed interface CartAction {
    data class NavigateToProduct(val categoryId: Int) : CartAction
}

@HiltViewModel
class CartViewModel @Inject constructor(
    // private val mainRepository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CartViewState>(CartViewState())
    val state: StateFlow<CartViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<CartAction?>()
    val action: SharedFlow<CartAction?>
        get() = _action.asSharedFlow()

    fun event(cartEvent: CartEvent) {
        when (cartEvent) {
            CartEvent.OnCheckoutOrderClick -> TODO()
            CartEvent.OnDeleteAllClick -> TODO()
            is CartEvent.OnError -> onError(cartEvent)
            is CartEvent.OnLoading -> onLoading(cartEvent)
            is CartEvent.OnProductMinusClick -> TODO()
            is CartEvent.OnProductPlusClick -> TODO()
            is CartEvent.OnProductClick -> onProductClick(cartEvent)
        }
    }

    private fun onProductClick(event: CartEvent.OnProductClick) {
        viewModelScope.launch {
            _action.emit(CartAction.NavigateToProduct(event.productId))
        }
    }

    private fun onError(event: CartEvent.OnError) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    error = event.errorMessage
                )
            )
        }
    }

    private fun onLoading(event: CartEvent.OnLoading) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    loading = event.isLoading
                )
            )
        }
    }

}
