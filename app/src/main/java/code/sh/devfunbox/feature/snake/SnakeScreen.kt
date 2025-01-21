package code.sh.devfunbox.feature.snake

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import code.sh.devfunbox.core.ui.base.BaseScreen
import code.sh.devfunbox.domain.enums.SnakeDirection
import code.sh.devfunbox.domain.model.CellModel
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.abs

class SnakeScreen : BaseScreen() {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val viewModel: SnakeViewModel = koinViewModel()
        val uiState by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.event.collect { event ->
                when (event) {
                    is SnakeUiEvent.NavigateTo -> navigator.push(event.screen)
                }
            }
        }

        UI(
            viewModel = viewModel,
            uiState = uiState
        )
    }
}

@Composable
fun UI(
    viewModel: SnakeViewModel,
    uiState: SnakeUiState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDirectionChange {
                    viewModel.changeDirection(it)
                }
            }
    ) {
        if (uiState.isPlay) {
            GameArea(
                modifier = Modifier.align(Alignment.Center),
                gameArea = uiState.gameArea
            )
        } else {
            SnakeGameLaunch(
                onClick = viewModel::startGame
            )
        }
    }
}

@Composable
private fun BoxScope.SnakeGameLaunch(
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.align(alignment = Alignment.Center),
        onClick = onClick
    ) {
        Text(text = "Start")
    }
}

@Composable
private fun GameArea(
    modifier: Modifier = Modifier,
    gameArea: List<List<CellModel>>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(1f)
    ) {
        gameArea.forEach { cells ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                cells.forEach { cell ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .border(
                                BorderStroke(
                                    width = 1.dp,
                                    color = Color.Black
                                )
                            )
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(1.dp)
                                .background(
                                    color = cell.type.color
                                )
                        )
                    }
                }
            }
        }
    }
}

private suspend fun PointerInputScope.detectDirectionChange(onDirectionChanged: (SnakeDirection) -> Unit) {
    detectDragGestures { change, dragAmount ->
        change.consume()
        val (dx, dy) = dragAmount
        when {
            dx > 0 && abs(dx) > abs(dy) -> onDirectionChanged(SnakeDirection.DOWN)
            dx < 0 && abs(dx) > abs(dy) -> onDirectionChanged(SnakeDirection.UP)
            dy > 0 && abs(dy) > abs(dx) -> onDirectionChanged(SnakeDirection.RIGHT)
            dy < 0 && abs(dy) > abs(dx) -> onDirectionChanged(SnakeDirection.LEFT)
        }
    }
}
