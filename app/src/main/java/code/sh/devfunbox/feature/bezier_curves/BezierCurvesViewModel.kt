package code.sh.devfunbox.feature.bezier_curves

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import code.sh.devfunbox.core.ui.base.BaseViewModel
import code.sh.devfunbox.core.ui.util.randomColor
import code.sh.devfunbox.domain.model.CircleModel
import kotlinx.coroutines.flow.update
import kotlin.math.pow

class BezierCurvesViewModel : BaseViewModel<BezierCurvesUiState, BezierCurvesUiEvent>(
    initialState = BezierCurvesUiState.init()
) {

    fun onCanvasAppeared(width: Int, height: Int) {
        launchCoroutine {
            val countPoints = 12

            val listPointsX = (1..countPoints)
                .toList()
                .map { i -> i * (width.toFloat() / countPoints) }
            val listPointsY = (1..countPoints)
                .toList()
                .map { i -> i * (height.toFloat() / countPoints) }

            val circles = listPointsX.zip(listPointsY).mapIndexedNotNull { index, pair ->
                if (index != 0 && index != countPoints - 1) {
                    val circleRadius = if ((index + 1) % 3 != 0) 50f else 25f
                    CircleModel(
                        id = index,
                        color = Color.randomColor(),
                        radius = circleRadius,
                        center = Offset(
                            x = pair.first,
                            y = pair.second
                        )
                    )
                } else null
            }

            mutableState.update { uiState ->
                uiState.copy(circles = circles)
            }
        }
    }

    fun onClickShowPointsButton() {
        launchCoroutine {
            mutableState.update { uiState ->
                uiState.copy(isShowCircle = !uiState.isShowCircle)
            }
        }

    }

    fun onDragStart(startDragOffset: Offset) {
        launchCoroutine {
            val onDragCircle = state.value.circles.find { circle ->
                isDragCircle(
                    startDragOffset = startDragOffset,
                    circleOffset = circle.center,
                    circleRadius = circle.radius
                )
            }

            if (onDragCircle != null) {
                mutableState.update { uiState ->
                    uiState.copy(oDragCircleId = onDragCircle.id)
                }
            }
        }
    }

    fun onDrag(offset: Offset) {
        if (state.value.oDragCircleId != null) {

            val newCircles = state.value.circles.map { circle ->
                if (circle.id != state.value.oDragCircleId) circle
                else circle.copy(center = offset)
            }

            mutableState.update { uiState ->
                uiState.copy(circles = newCircles)
            }

        } else {
            mutableState.update { uiState ->
                uiState.copy(handle = offset)
            }
        }
    }

    fun onDragEnd() {
        launchCoroutine {
            mutableState.update { uiState ->
                uiState.copy(oDragCircleId = null)
            }
        }
    }

    private fun isDragCircle(
        startDragOffset: Offset,
        circleOffset: Offset,
        circleRadius: Float
    ): Boolean {
        return (startDragOffset.x - circleOffset.x)
            .pow(2) + (startDragOffset.y - circleOffset.y)
            .pow(2) <= circleRadius + 100f
            .pow(2)
    }
}