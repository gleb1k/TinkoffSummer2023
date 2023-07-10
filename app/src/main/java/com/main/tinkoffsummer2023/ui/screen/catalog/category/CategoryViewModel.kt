package com.main.tinkoffsummer2023.ui.screen.catalog.category

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
data class CategoryViewState(
    val query: String = "",
    val categories: PersistentList<Category> = MockTempConstants.categories,

    override val loading: Boolean = false,
    override val error: String? = null,
) : ViewState

sealed interface CategoryEvent : ViewEvent {

    data class OnQueryChange(val query: String) : CategoryEvent

    data class OnCategoryItemClick(val id: Int) : CategoryEvent

    data class OnLoading(val isLoading: Boolean) : CategoryEvent
    data class OnError(val errorMessage: String?) : CategoryEvent
}

sealed interface CategoryAction {
    data class NavigateToCategory(val categoryId: Int) : CategoryAction
}


@HiltViewModel
class CategoryViewModel @Inject constructor(
    // private val mainRepository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CategoryViewState>(CategoryViewState())
    val state: StateFlow<CategoryViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<CategoryAction?>()
    val action: SharedFlow<CategoryAction?>
        get() = _action.asSharedFlow()

    fun event(categoryEvent: CategoryEvent) {
        when (categoryEvent) {
            is CategoryEvent.OnCategoryItemClick -> onCategoryItemClick(categoryEvent)
            is CategoryEvent.OnError -> onError(categoryEvent)
            is CategoryEvent.OnLoading -> onLoading(categoryEvent)
            is CategoryEvent.OnQueryChange -> onQueryChange(categoryEvent)
        }
    }

    private fun onCategoryItemClick(event: CategoryEvent.OnCategoryItemClick) {
        viewModelScope.launch {
            _action.emit(CategoryAction.NavigateToCategory(event.id))
        }
    }

    private fun onQueryChange(event: CategoryEvent.OnQueryChange) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    query = event.query
                )
            )
        }
    }

    private fun onError(event: CategoryEvent.OnError) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    error = event.errorMessage
                )
            )
        }
    }

    private fun onLoading(event: CategoryEvent.OnLoading) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    loading = event.isLoading
                )
            )
        }
    }

}
