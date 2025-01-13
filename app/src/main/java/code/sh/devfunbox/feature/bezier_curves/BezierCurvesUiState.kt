package code.sh.devfunbox.feature.bezier_curves

data class BezierCurvesUiState(
    val title: String
) {
    companion object {
        fun init() = BezierCurvesUiState(
            title = "Кривые безье"
        )
    }
}