package code.sh.devfunbox.feature.bezier_curves

import cafe.adriel.voyager.core.screen.Screen
import code.sh.devfunbox.core.ui.base.BaseUiEvent

sealed class BezierCurvesUiEvent : BaseUiEvent {
    data class NavigateTo(val screen: Screen) : BezierCurvesUiEvent()
}