package code.sh.devfunbox.feature.root

import cafe.adriel.voyager.core.screen.Screen
import code.sh.devfunbox.core.ui.base.BaseUiEvent

sealed class RootUiEvent : BaseUiEvent {
    data class NavigateTo(val screen: Screen) : RootUiEvent()
}