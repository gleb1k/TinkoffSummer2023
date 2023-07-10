package com.main.tinkoffsummer2023.ui.screen.profile.balance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.tinkoffsummer2023.domain.MainRepository
import com.main.tinkoffsummer2023.ui.ViewEvent
import com.main.tinkoffsummer2023.ui.ViewState
import com.main.tinkoffsummer2023.ui.screen.catalog.CatalogEvent
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
data class BalanceViewState(
    val query: String = "",

    override val loading: Boolean = false,
    override val error: String? = null,
) : ViewState

sealed interface BalanceEvent : ViewEvent {

    data class OnQueryChange(val query: String) : BalanceEvent

    object OnAddClick : BalanceEvent

    object OnBackClick : BalanceEvent
    object OnLoad : BalanceEvent

    data class OnLoading(val isLoading: Boolean) : BalanceEvent
    data class OnError(val errorMessage: String?) : BalanceEvent
}

sealed interface BalanceAction {
    object NavigateToProfile : BalanceAction

    object NavigateBack : BalanceAction
}

@HiltViewModel
class BalanceViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<BalanceViewState>(BalanceViewState())
    val state: StateFlow<BalanceViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<BalanceAction?>()
    val action: SharedFlow<BalanceAction?>
        get() = _action.asSharedFlow()

    fun event(event: BalanceEvent) {
        when (event) {
            is BalanceEvent.OnError -> onError(event)
            is BalanceEvent.OnLoading -> onLoading(event)
            BalanceEvent.OnBackClick -> onBackClick()
            BalanceEvent.OnLoad -> onLoad()
            BalanceEvent.OnAddClick -> onAddClick()
            is BalanceEvent.OnQueryChange -> onQueryChange(event)
        }
    }

    private fun onLoad() {
//        viewModelScope.launch {
//            _state.emit(
//                _state.value.copy(
//                    //temp
//                    products = repository.getProducts().toPersistentList()
//                )
//            )
//        }
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

    private fun onAddClick() {
        viewModelScope.launch {
            _action.emit(BalanceAction.NavigateToProfile)
        }
    }

    private fun onBackClick() {
        viewModelScope.launch {
            _action.emit(BalanceAction.NavigateBack)
        }
    }

    private fun onQueryChange(event: BalanceEvent.OnQueryChange) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    query = event.query
                )
            )
        }
    }

    private fun onError(event: BalanceEvent.OnError) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    error = event.errorMessage
                )
            )
        }
    }

    private fun onLoading(event: BalanceEvent.OnLoading) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    loading = event.isLoading
                )
            )
        }
    }
}
