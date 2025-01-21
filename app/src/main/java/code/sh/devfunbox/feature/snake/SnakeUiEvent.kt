package code.sh.devfunbox.feature.snake

import cafe.adriel.voyager.core.screen.Screen
import code.sh.devfunbox.core.ui.base.BaseUiEvent

sealed class SnakeUiEvent : BaseUiEvent {
    data class NavigateTo(val screen: Screen) : SnakeUiEvent()
}