package com.main.tinkoffsummer2023.ui.screen.auth.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.tinkoffsummer2023.domain.MainRepository
import com.main.tinkoffsummer2023.ui.ViewEvent
import com.main.tinkoffsummer2023.ui.ViewState
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
data class SignInViewState(
    val queryLogin: String = "",
    val queryPassword: String = "",
    val isAdminChecked : Boolean = false,

    override val loading: Boolean = false,
    override val error: String? = null,
) : ViewState

sealed interface SignInEvent : ViewEvent {

    data class OnQueryLoginChange(val query: String) : SignInEvent
    data class OnQueryPasswordChange(val query: String) : SignInEvent
    data class OnAdminCheckedChange(val isChecked: Boolean) : SignInEvent

    object OnSignInClick : SignInEvent

    data class OnLoading(val isLoading: Boolean) : SignInEvent
    data class OnError(val errorMessage: String?) : SignInEvent
}

sealed interface SignInAction {
    object NavigateToCatalog : SignInAction

}


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SignInViewState>(SignInViewState())
    val state: StateFlow<SignInViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<SignInAction?>()
    val action: SharedFlow<SignInAction?>
        get() = _action.asSharedFlow()

    fun event(event: SignInEvent) {
        when (event) {
            is SignInEvent.OnError -> onError(event)
            is SignInEvent.OnLoading -> onLoading(event)
            is SignInEvent.OnQueryLoginChange -> onQueryLoginChange(event)
            is SignInEvent.OnQueryPasswordChange -> onQueryPasswordChange(event)
            SignInEvent.OnSignInClick -> onSignInClick()
            is SignInEvent.OnAdminCheckedChange -> onAdminCheckedChange(event)
        }
    }

    private fun onAdminCheckedChange(event: SignInEvent.OnAdminCheckedChange) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    isAdminChecked = event.isChecked
                )
            )
        }
    }

    private fun onSignInClick() {
        viewModelScope.launch {
            if (repository.loginUser(_state.value.queryLogin, _state.value.queryPassword)) {
                _action.emit(SignInAction.NavigateToCatalog)
            }
            else
            {
                onError(event = SignInEvent.OnError("Такого пользователя нет"))
            }

        }
    }

    private fun onError(event: SignInEvent.OnError) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    error = event.errorMessage
                )
            )
        }
    }

    private fun onLoading(event: SignInEvent.OnLoading) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    loading = event.isLoading
                )
            )
        }
    }

    private fun onQueryLoginChange(event: SignInEvent.OnQueryLoginChange) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    queryLogin = event.query
                )
            )
        }
    }

    private fun onQueryPasswordChange(event: SignInEvent.OnQueryPasswordChange) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    queryPassword = event.query
                )
            )
        }
    }

}
