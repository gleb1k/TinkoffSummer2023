package com.main.tinkoffsummer2023.ui.screen.profile

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
data class ProfileViewState(
    val categories: PersistentList<Category> = MockTempConstants.categories,

    override val loading: Boolean = false,
    override val error: String? = null,
) : ViewState

sealed interface ProfileEvent : ViewEvent {

    object OnThemeClick : ProfileEvent
    object OnAboutAppClick : ProfileEvent

    data class OnLoading(val isLoading: Boolean) : ProfileEvent
    data class OnError(val errorMessage: String?) : ProfileEvent
}

sealed interface ProfileAction {
    object NavigateToAboutApp : ProfileAction
    object NavigateTheme: ProfileAction
}


@HiltViewModel
class ProfileViewModel @Inject constructor(
) : ViewModel() {

    private val _state = MutableStateFlow<ProfileViewState>(ProfileViewState())
    val state: StateFlow<ProfileViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<ProfileAction?>()
    val action: SharedFlow<ProfileAction?>
        get() = _action.asSharedFlow()

    fun event(catalogEvent: ProfileEvent) {
        when (catalogEvent) {
            is ProfileEvent.OnError -> onError(catalogEvent)
            is ProfileEvent.OnLoading -> onLoading(catalogEvent)
            ProfileEvent.OnAboutAppClick -> TODO()
            ProfileEvent.OnThemeClick -> TODO()
        }
    }

    private fun onAboutAppClick(event: ProfileEvent.OnAboutAppClick) {
        viewModelScope.launch {
            _action.emit(ProfileAction.NavigateToAboutApp)
        }
    }

    private fun onThemeClick(event: ProfileEvent.OnThemeClick) {
        viewModelScope.launch {
            _action.emit(ProfileAction.NavigateTheme)
        }
    }


    private fun onError(event: ProfileEvent.OnError) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    error = event.errorMessage
                )
            )
        }
    }

    private fun onLoading(event: ProfileEvent.OnLoading) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    loading = event.isLoading
                )
            )
        }
    }

}
