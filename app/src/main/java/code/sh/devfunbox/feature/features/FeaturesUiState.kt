package code.sh.devfunbox.feature.features

import code.sh.devfunbox.domain.model.ScreenModel

data class FeaturesUiState(
    val screens: List<ScreenModel>
) {
    companion object {
        fun init() = FeaturesUiState(
            screens = emptyList()
        )
    }
}