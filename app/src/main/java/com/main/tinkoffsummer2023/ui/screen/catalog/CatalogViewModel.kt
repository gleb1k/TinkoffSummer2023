package com.main.tinkoffsummer2023.ui.screen.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.tinkoffsummer2023.ui.ViewEvent
import com.main.tinkoffsummer2023.ui.ViewState
import com.main.tinkoffsummer2023.ui.model.Category
import com.main.tinkoffsummer2023.ui.model.MockTempConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
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
    val categories: PersistentList<Category> = MockTempConstants.categories,

    override val loading: Boolean = false,
    override val error: String? = null,
) : ViewState

sealed interface CatalogEvent : ViewEvent {

    data class OnQueryChange(val query: String) : CatalogEvent

    data class OnCategoryItemClick(val id: Int) : CatalogEvent

    data class OnLoading(val isLoading: Boolean) : CatalogEvent
    data class OnError(val errorMessage: String?) : CatalogEvent
}

sealed interface CatalogAction {
    data class NavigateToCategory(val categoryId: Int) : CatalogAction
}


@HiltViewModel
class CatalogViewModel @Inject constructor(
    // private val mainRepository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CatalogViewState>(CatalogViewState())
    val state: StateFlow<CatalogViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<CatalogAction?>()
    val action: SharedFlow<CatalogAction?>
        get() = _action.asSharedFlow()

    fun event(catalogEvent: CatalogEvent) {
        when (catalogEvent) {
            is CatalogEvent.OnCategoryItemClick -> onCategoryItemClick(catalogEvent)
            is CatalogEvent.OnError -> onError(catalogEvent)
            is CatalogEvent.OnLoading -> onLoading(catalogEvent)
            is CatalogEvent.OnQueryChange -> onQueryChange(catalogEvent)
        }
    }

    private fun onCategoryItemClick(event: CatalogEvent.OnCategoryItemClick) {
        viewModelScope.launch {
            _action.emit(CatalogAction.NavigateToCategory(event.id))
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
