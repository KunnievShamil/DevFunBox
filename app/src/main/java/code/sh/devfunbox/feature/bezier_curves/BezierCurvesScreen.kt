package code.sh.devfunbox.feature.bezier_curves

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import code.sh.devfunbox.core.ui.base.BaseScreen
import org.koin.compose.viewmodel.koinViewModel

class BezierCurvesScreen : BaseScreen() {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val viewModel: BezierCurvesViewModel = koinViewModel()
        val uiState by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.event.collect { event ->
                when (event) {
                    is BezierCurvesUiEvent.NavigateTo -> navigator.push(event.screen)
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Canvas(modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .onSizeChanged { size -> viewModel.onCanvasAppeared(size.width, size.height) }
                .pointerInput(Unit) { handleDraw(viewModel) }) {
                uiState.circles.chunked(3) { circles ->

                    if (circles.size != 3) return@chunked

                    val bezierCurvePath = bezier(
                        startPoint = circles[0].center,
                        controlPoint = circles[1].center,
                        endPoint = circles[2].center
                    )

                    drawPath(
                        path = bezierCurvePath,
                        style = Stroke(width = 10f),
                        brush = Brush.linearGradient(
                            colors = circles.map { circle -> circle.color }
                        )
                    )

                    if (uiState.isShowCircle) circles.forEach { circle ->
                        drawCircle(
                            color = circle.color,
                            radius = circle.radius,
                            center = circle.center
                        )
                    }
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                onClick = {
                    viewModel.onClickShowPointsButton()
                }) {
                Text(text = "Click")
            }
        }
    }
}

private suspend fun PointerInputScope.handleDraw(viewModel: BezierCurvesViewModel) {
    detectDragGestures(
        onDragStart = { offset ->
            viewModel.onDragStart(offset)
        },
        onDrag = { change, _ ->
            change.consume()
            viewModel.onDrag(
                offset = change.position,
            )
        },
        onDragEnd = {
            viewModel.onDragEnd()
        },
        onDragCancel = {
            viewModel.onDragEnd()
        }
    )
}

/**
 * // Формула для вычисления точки на квадратичной кривой Безье:
 * // B(t) = (1 - t)² * P0 + 2 * (1 - t) * t * P1 + t² * P2
 * // Где:
 * // - P0 — начальная точка (startPoint)
 * // - P1 — контрольная точка (controlPoint)
 * // - P2 — конечная точка (endPoint)
 * // - t — параметр от 0 до 1
 *
 * @param startPoint это p0
 * @param endPoint это p2
 * @param controlPoint это p1
 */
private fun bezier(startPoint: Offset, controlPoint: Offset, endPoint: Offset): Path {
    val offsets = (1..200).map { step ->

        val t = step / 200f

        val resX = (1 - t) * (1 - t) * startPoint.x +
                2 * (1 - t) * t * controlPoint.x +
                t * t * endPoint.x

        val resY = (1 - t) * (1 - t) * startPoint.y +
                2 * (1 - t) * t * controlPoint.y +
                t * t * endPoint.y

        Offset(x = resX, y = resY)
    }

    val bezierCurvePath = Path().apply {
        moveTo(offsets.first().x, offsets.first().y)
        offsets.drop(1).forEach { offset ->
            lineTo(offset.x, offset.y)
        }
    }

    return bezierCurvePath
}