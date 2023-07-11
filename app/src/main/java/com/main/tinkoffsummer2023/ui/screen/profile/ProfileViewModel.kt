package com.main.tinkoffsummer2023.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.tinkoffsummer2023.core.PreferenceManager
import com.main.tinkoffsummer2023.domain.MainRepository
import com.main.tinkoffsummer2023.ui.ViewEvent
import com.main.tinkoffsummer2023.ui.ViewState
import com.main.tinkoffsummer2023.ui.model.User
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
data class ProfileViewState(
    val user: User? = null,

    override val loading: Boolean = false,
    override val error: String? = null,
) : ViewState

sealed interface ProfileEvent : ViewEvent {

    object OnThemeClick : ProfileEvent
    object OnAboutAppClick : ProfileEvent

    object OnBalanceClick : ProfileEvent

    object OnLoad: ProfileEvent

    object OnBackClick : ProfileEvent
    object OnAuthorize: ProfileEvent
    data class OnLoading(val isLoading: Boolean) : ProfileEvent
    data class OnError(val errorMessage: String?) : ProfileEvent
}

sealed interface ProfileAction {
    object NavigateToAboutApp : ProfileAction
    object NavigateTheme : ProfileAction
    object NavigateToBalance : ProfileAction

    object NavigateToLogin: ProfileAction
    object NavigateBack : ProfileAction
}


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: MainRepository,
    private val preferenceManager: PreferenceManager,
) : ViewModel() {

    private val _state = MutableStateFlow<ProfileViewState>(ProfileViewState())
    val state: StateFlow<ProfileViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<ProfileAction?>()
    val action: SharedFlow<ProfileAction?>
        get() = _action.asSharedFlow()

    fun event(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.OnError -> onError(event)
            is ProfileEvent.OnLoading -> onLoading(event)
            is ProfileEvent.OnAboutAppClick -> onAboutAppClick()
            is ProfileEvent.OnThemeClick -> onThemeClick()
            is ProfileEvent.OnBackClick -> onBackClick()
            is ProfileEvent.OnBalanceClick -> onBalanceClick(event)
            ProfileEvent.OnAuthorize -> onAuthorize()
            ProfileEvent.OnLoad -> onLoad()
        }
    }

    private fun onLoad() {
        viewModelScope.launch {
            try {
                onLoading(ProfileEvent.OnLoading(true))
                _state.emit(
                    _state.value.copy(
                        user = repository.getUserInfo(preferenceManager.getUserToken())
                    )
                )
            } catch (throwable: Throwable) {
                onError(ProfileEvent.OnError("Нет соединения"))
            } finally {
                onLoading(ProfileEvent.OnLoading(false))
            }
        }
    }

    private fun onAuthorize() {
        viewModelScope.launch {
            _action.emit(ProfileAction.NavigateToLogin)
        }
    }

    private fun onBackClick() {
        viewModelScope.launch {
            _action.emit(ProfileAction.NavigateBack)
        }
    }

    private fun onBalanceClick(event: ProfileEvent.OnBalanceClick) {
        viewModelScope.launch {
            _action.emit(ProfileAction.NavigateToBalance)
        }
    }

    private fun onAboutAppClick() {
        viewModelScope.launch {
            _action.emit(ProfileAction.NavigateToAboutApp)
        }
    }

    private fun onThemeClick() {
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
