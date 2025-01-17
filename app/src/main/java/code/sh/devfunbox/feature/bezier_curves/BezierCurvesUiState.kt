package code.sh.devfunbox.feature.bezier_curves

import androidx.compose.ui.geometry.Offset
import code.sh.devfunbox.domain.model.CircleModel

data class BezierCurvesUiState(
    val handle: Offset,
    val circles: List<CircleModel>,
    val oDragCircleId: Int?,
    val isShowCircle: Boolean
) {
    companion object {
        fun init() = BezierCurvesUiState(
            handle = Offset.Zero,
            circles = emptyList(),
            oDragCircleId = null,
            isShowCircle = true
        )
    }
}