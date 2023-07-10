package com.main.tinkoffsummer2023.ui.screen.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.tinkoffsummer2023.domain.MainRepository
import com.main.tinkoffsummer2023.ui.ViewEvent
import com.main.tinkoffsummer2023.ui.ViewState
import com.main.tinkoffsummer2023.ui.model.MockTempConstants
import com.main.tinkoffsummer2023.ui.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
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
data class CatalogViewState(
    val query: String = "",
    val products: PersistentList<Product> = persistentListOf(),

    override val loading: Boolean = false,
    override val error: String? = null,
) : ViewState

sealed interface CatalogEvent : ViewEvent {

    data class OnQueryChange(val query: String) : CatalogEvent

    data class OnProductItemClick(val productId: Int) : CatalogEvent
    data class OnAddToCartClick(val productId: Int) : CatalogEvent

    object OnFilterClick : CatalogEvent
    object OnBackClick : CatalogEvent

    object OnLoad : CatalogEvent

    data class OnLoading(val isLoading: Boolean) : CatalogEvent
    data class OnError(val errorMessage: String?) : CatalogEvent
}

sealed interface CatalogAction {
    data class NavigateToProduct(val productId: Int) : CatalogAction
    object NavigateToFilter : CatalogAction
    object NavigateBack : CatalogAction
}


@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<CatalogViewState>(CatalogViewState())
    val state: StateFlow<CatalogViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<CatalogAction?>()
    val action: SharedFlow<CatalogAction?>
        get() = _action.asSharedFlow()

    fun event(event: CatalogEvent) {
        when (event) {
            is CatalogEvent.OnProductItemClick -> onProductItemClick(event)
            is CatalogEvent.OnError -> onError(event)
            is CatalogEvent.OnLoading -> onLoading(event)
            is CatalogEvent.OnQueryChange -> onQueryChange(event)
            CatalogEvent.OnFilterClick -> onFilterClick()
            is CatalogEvent.OnAddToCartClick -> onAddToCartClick(event)
            CatalogEvent.OnBackClick -> onBackClick()
            CatalogEvent.OnLoad -> onLoad()
        }
    }

    private fun onLoad() {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    //temp
                    products = repository.getProducts().toPersistentList()
                )
            )
        }
    }

    //todo как узнать уже в корзине или нет? хранить всегда состояние корзины? или лезть еще раз в бд? а как отображать состояние корзины на юай?
    private fun onAddToCartClick(event: CatalogEvent.OnAddToCartClick) {
        viewModelScope.launch {
            repository.addProductToCart(event.productId)
        }
    }

    private fun onBackClick() {
        viewModelScope.launch {
            _action.emit(CatalogAction.NavigateBack)
        }
    }

    private fun onFilterClick() {
        viewModelScope.launch {
            _action.emit(CatalogAction.NavigateToFilter)
        }
    }

    private fun onProductItemClick(event: CatalogEvent.OnProductItemClick) {
        viewModelScope.launch {

            //todo
            _action.emit(CatalogAction.NavigateToProduct(event.productId))
        }
    }

    private fun onQueryChange(event: CatalogEvent.OnQueryChange) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    query = event.query
                )
            )
        }
    }

    private fun onError(event: CatalogEvent.OnError) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    error = event.errorMessage
                )
            )
        }
    }

    private fun onLoading(event: CatalogEvent.OnLoading) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    loading = event.isLoading
                )
            )
        }
    }

}
