package com.main.tinkoffsummer2023.ui.screen.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.tinkoffsummer2023.ui.ViewEvent
import com.main.tinkoffsummer2023.ui.ViewState
import com.main.tinkoffsummer2023.ui.model.MockTempConstants
import com.main.tinkoffsummer2023.ui.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
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
data class ProductViewState(
    val query: String = "",
    // todo
    val product: Product = MockTempConstants.products[1],

    override val loading: Boolean = false,
    override val error: String? = null,
) : ViewState

sealed interface ProductEvent : ViewEvent {

    data class OnQueryChange(val query: String) : ProductEvent

    object OnCartClick : ProductEvent

    object OnBackClick : ProductEvent
    data class OnLoading(val isLoading: Boolean) : ProductEvent
    data class OnError(val errorMessage: String?) : ProductEvent
}

sealed interface ProductAction {
    object NavigateBack : ProductAction
}


@HiltViewModel
class ProductViewModel @Inject constructor(
    // private val mainRepository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ProductViewState>(ProductViewState())
    val state: StateFlow<ProductViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<ProductAction?>()
    val action: SharedFlow<ProductAction?>
        get() = _action.asSharedFlow()

    fun event(event: ProductEvent) {
        when (event) {
            ProductEvent.OnCartClick -> onCartClick()
            is ProductEvent.OnError -> onError(event)
            is ProductEvent.OnLoading -> onLoading(event)
            is ProductEvent.OnQueryChange -> onQueryChange(event)
            ProductEvent.OnBackClick -> onBackClick()
        }
    }

    private fun onBackClick() {
        viewModelScope.launch {
            _action.emit(ProductAction.NavigateBack)
        }
    }


    private fun onQueryChange(event: ProductEvent.OnQueryChange) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    query = event.query
                )
            )
        }
    }


    private fun onCartClick() {
        viewModelScope.launch {
            // todo
        }
    }

    private fun onError(event: ProductEvent.OnError) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    error = event.errorMessage
                )
            )
        }
    }

    private fun onLoading(event: ProductEvent.OnLoading) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    loading = event.isLoading
                )
            )
        }
    }
}
