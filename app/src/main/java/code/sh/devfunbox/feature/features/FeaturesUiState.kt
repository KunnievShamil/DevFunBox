package code.sh.devfunbox.feature.features

data class FeaturesUiState(
    val title: String
) {
    companion object {
        fun init() = FeaturesUiState(
            title = "Features Screen"
        )
    }
}
