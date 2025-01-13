package code.sh.devfunbox.feature.root

import androidx.lifecycle.viewModelScope
import code.sh.devfunbox.core.ui.base.BaseViewModel
import code.sh.devfunbox.domain.repository.RootRepository
import code.sh.devfunbox.feature.features.FeaturesScreen
import code.sh.devfunbox.feature.features.FeaturesUiEvent
import kotlinx.coroutines.launch

class RootViewModel(
    private val rootRepository: RootRepository
) : BaseViewModel<Unit, FeaturesUiEvent>(Unit) {

    init {
        navigateTo()
    }

    private fun navigateTo() {
        viewModelScope.launch {
            mutableEvent.send(FeaturesUiEvent.NavigateTo(FeaturesScreen()))
        }
    }
}