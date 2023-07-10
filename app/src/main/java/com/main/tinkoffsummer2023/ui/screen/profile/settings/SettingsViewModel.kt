package com.main.tinkoffsummer2023.ui.screen.profile.settings

import androidx.lifecycle.ViewModel
import com.main.tinkoffsummer2023.ui.ViewEvent
import com.main.tinkoffsummer2023.ui.ViewState
import com.main.tinkoffsummer2023.ui.model.Category
import com.main.tinkoffsummer2023.ui.model.MockTempConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import javax.annotation.concurrent.Immutable
import javax.inject.Inject


@Immutable
data class SettingsViewState(
    val categories: PersistentList<Category> = MockTempConstants.categories,

    override val loading: Boolean = false,
    override val error: String? = null,
) : ViewState

sealed interface ProfileEvent : ViewEvent {

    object OnThemeClick : ProfileEvent
    object OnAboutAppClick : ProfileEvent
    object OnBackClick : ProfileEvent
    object OnBalanceClick : ProfileEvent

    data class OnLoading(val isLoading: Boolean) : ProfileEvent
    data class OnError(val errorMessage: String?) : ProfileEvent
}

sealed interface ProfileAction {
    object NavigateToAboutApp : ProfileAction
    object NavigateTheme : ProfileAction
    object NavigateToBalance : ProfileAction

    object NavigateBack : ProfileAction
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
) : ViewModel()

