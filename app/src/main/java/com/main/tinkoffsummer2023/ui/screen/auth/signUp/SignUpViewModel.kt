package com.main.tinkoffsummer2023.ui.screen.auth.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import javax.inject.Inject


data class SignUpViewState(
    val queryLogin: String = "",
    val queryPassword: String = "",
    val queryPasswordConfirm: String = "",

    override val loading: Boolean = false,
    override val error: String? = null,
) : ViewState

sealed interface SignUpEvent : ViewEvent {

    data class OnQueryLoginChange(val query: String) : SignUpEvent
    data class OnQueryPasswordChange(val query: String) : SignUpEvent
    data class OnQueryPasswordConfirmChange(val query: String) : SignUpEvent

    object OnSignUpClick : SignUpEvent

    data class OnLoading(val isLoading: Boolean) : SignUpEvent
    data class OnError(val errorMessage: String?) : SignUpEvent
}

sealed interface SignUpAction {
    object NavigateToSignIn : SignUpAction

    //data class NavigateToAnime(val animeId: Int) : SearchAction
}


@HiltViewModel
class SignUpViewModel @Inject constructor(
    // private val mainRepository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SignUpViewState>(SignUpViewState())
    val state: StateFlow<SignUpViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<SignUpAction?>()
    val action: SharedFlow<SignUpAction?>
        get() = _action.asSharedFlow()

    fun event(signUpEvent: SignUpEvent) {
        when (signUpEvent) {
            is SignUpEvent.OnError -> onError(signUpEvent)
            is SignUpEvent.OnLoading -> onLoading(signUpEvent)
            is SignUpEvent.OnQueryLoginChange -> onQueryLoginChange(signUpEvent)
            is SignUpEvent.OnQueryPasswordChange -> onQueryPasswordChange(signUpEvent)
            is SignUpEvent.OnQueryPasswordConfirmChange -> onQueryPasswordConfirmChange(signUpEvent)
            SignUpEvent.OnSignUpClick -> {}
        }
    }

    private fun onError(event: SignUpEvent.OnError) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    error = event.errorMessage
                )
            )
        }
    }

    private fun onLoading(event: SignUpEvent.OnLoading) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    loading = event.isLoading
                )
            )
        }
    }

    private fun onQueryLoginChange(event: SignUpEvent.OnQueryLoginChange) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    queryLogin = event.query
                )
            )
        }
    }

    private fun onQueryPasswordConfirmChange(event: SignUpEvent.OnQueryPasswordConfirmChange) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    queryPasswordConfirm = event.query
                )
            )
        }
    }

    private fun onQueryPasswordChange(event: SignUpEvent.OnQueryPasswordChange) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    queryPassword = event.query
                )
            )
        }
    }

}
