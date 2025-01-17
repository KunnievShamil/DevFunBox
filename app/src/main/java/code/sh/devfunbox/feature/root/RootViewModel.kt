package code.sh.devfunbox.feature.root

import code.sh.devfunbox.core.ui.base.BaseViewModel
import code.sh.devfunbox.domain.repository.RootRepository
import code.sh.devfunbox.feature.features.FeaturesScreen

class RootViewModel(
    private val rootRepository: RootRepository
) : BaseViewModel<Unit, RootUiEvent>(Unit) {

    init {
        navigateToFeaturesScreen()
    }

    private fun navigateToFeaturesScreen() {
        val screens = rootRepository.getScreens()
        launchCoroutine {
            mutableEvent.send(RootUiEvent.NavigateTo(FeaturesScreen(screens)))
        }
    }
}