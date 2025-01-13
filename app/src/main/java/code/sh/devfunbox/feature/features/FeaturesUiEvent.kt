package code.sh.devfunbox.feature.features

import cafe.adriel.voyager.core.screen.Screen
import code.sh.devfunbox.core.ui.base.BaseUiEvent

sealed class FeaturesUiEvent : BaseUiEvent {
    data class NavigateTo(val screen: Screen) : FeaturesUiEvent()
}