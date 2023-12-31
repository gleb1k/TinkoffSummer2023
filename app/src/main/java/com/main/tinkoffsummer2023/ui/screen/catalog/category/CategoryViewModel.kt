package com.main.tinkoffsummer2023.ui.screen.catalog.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.tinkoffsummer2023.domain.MainRepository
import com.main.tinkoffsummer2023.ui.ViewEvent
import com.main.tinkoffsummer2023.ui.ViewState
import com.main.tinkoffsummer2023.ui.model.Category
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
data class CategoryViewState(
    val query: String = "",
    val categories: PersistentList<Category> = persistentListOf(),

    override val loading: Boolean = false,
    override val error: String? = null,
) : ViewState

sealed interface CategoryEvent : ViewEvent {

    data class OnQueryChange(val query: String) : CategoryEvent

    data class OnCategoryItemClick(val id: Int) : CategoryEvent
    data class OnSearchClick(val query: String) : CategoryEvent

    object OnLoad: CategoryEvent
    data class OnLoading(val isLoading: Boolean) : CategoryEvent
    data class OnError(val errorMessage: String?) : CategoryEvent
}

sealed interface CategoryAction {
    data class NavigateToCategory(val categoryId: Int) : CategoryAction
    data class NavigateToCatalog(val query: String) : CategoryAction
}


@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CategoryViewState>(CategoryViewState())
    val state: StateFlow<CategoryViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<CategoryAction?>()
    val action: SharedFlow<CategoryAction?>
        get() = _action.asSharedFlow()

    fun event(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.OnCategoryItemClick -> onCategoryItemClick(event)
            is CategoryEvent.OnError -> onError(event)
            is CategoryEvent.OnLoading -> onLoading(event)
            is CategoryEvent.OnQueryChange -> onQueryChange(event)
            is CategoryEvent.OnSearchClick -> onSearchClick(event)
            CategoryEvent.OnLoad -> onLoad()
        }
    }

    private fun onLoad() {
        viewModelScope.launch {
            try {
                onLoading(CategoryEvent.OnLoading(true))
                _state.emit(
                    _state.value.copy(
                        categories = repository.getCategories().toPersistentList()
                    )
                )
            } catch (throwable: Throwable) {
                onError(CategoryEvent.OnError("Нет соединения"))
            } finally {
                onLoading(CategoryEvent.OnLoading(false))
            }
        }
    }

    private fun onSearchClick(event: CategoryEvent.OnSearchClick) {
        viewModelScope.launch {
            _action.emit(CategoryAction.NavigateToCatalog(event.query))
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
