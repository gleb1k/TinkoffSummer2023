package com.main.tinkoffsummer2023.ui.screen.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.tinkoffsummer2023.ui.ViewEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

sealed interface StartAction {
    object NavigateToSignIn : StartAction
    object NavigateToSignUp : StartAction
    object NavigateToCatalog : StartAction
}

sealed interface StartEvent : ViewEvent {

    object OnSignInClick : StartEvent
    object OnSignUpClick : StartEvent
    object OnCatalogClick : StartEvent

}

class StartViewModel : ViewModel() {

    private val _action = MutableSharedFlow<StartAction?>()
    val action: SharedFlow<StartAction?>
        get() = _action.asSharedFlow()

    fun event(event: StartEvent) {
        when (event) {
            is StartEvent.OnCatalogClick -> onCatalogClick(event)
            is StartEvent.OnSignInClick -> onSignInClick(event)
            is StartEvent.OnSignUpClick -> onSignUpClick(event)
        }
    }

    private fun onCatalogClick(event: StartEvent.OnCatalogClick) {
        viewModelScope.launch {
            _action.emit(StartAction.NavigateToCatalog)
        }
    }

    private fun onSignInClick(event: StartEvent.OnSignInClick) {
        viewModelScope.launch {
            _action.emit(StartAction.NavigateToSignIn)
        }
    }

    private fun onSignUpClick(event: StartEvent.OnSignUpClick) {
        viewModelScope.launch {
            _action.emit(StartAction.NavigateToSignUp)
        }
    }


}
