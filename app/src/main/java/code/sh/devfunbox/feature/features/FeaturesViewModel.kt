package code.sh.devfunbox.feature.features

import cafe.adriel.voyager.core.screen.Screen
import code.sh.devfunbox.core.ui.base.BaseViewModel
import code.sh.devfunbox.domain.model.ScreenModel
import kotlinx.coroutines.flow.update

class FeaturesViewModel(
    private val screens: List<ScreenModel>
) : BaseViewModel<FeaturesUiState, FeaturesUiEvent>(
    initialState = FeaturesUiState.init()
) {

    init {
        setScreens()
    }

    private fun setScreens() {
        launchCoroutine {
            mutableState.update { uiState ->
                uiState.copy(
                    screens = screens
                )
            }
        }
    }

    fun onClickScreen(screen: Screen) {
        launchCoroutine {
            mutableEvent.send(
                FeaturesUiEvent.NavigateTo(
                    screen
                )
            )
        }
    }
}