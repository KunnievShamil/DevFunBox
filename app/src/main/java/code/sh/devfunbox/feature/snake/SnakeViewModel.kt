package code.sh.devfunbox.feature.snake

import androidx.lifecycle.viewModelScope
import code.sh.devfunbox.core.ui.base.BaseViewModel
import code.sh.devfunbox.domain.enums.SnakeDirection
import code.sh.devfunbox.domain.model.CellModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SnakeViewModel : BaseViewModel<SnakeUiState, SnakeUiEvent>(
    initialState = SnakeUiState.init()
) {

    var snakeGameEngineJob: Job? = null

    private fun setGameArea() {
        mutableState.update { uiState ->
            val newSnakeCoordinates = listOf(5 to 5)
            val newGameArea = List(10) { verticalIndex ->
                List(10) { horizontalIndex ->
                    CellModel(
                        if (verticalIndex != newSnakeCoordinates.first().first
                            || horizontalIndex != newSnakeCoordinates.first().second
                        ) CellModel.Type.EMPTY
                        else CellModel.Type.SNAKE
                    )
                }
            }
            uiState.copy(
                gameArea = newGameArea,
                snakeCoordinates = newSnakeCoordinates,
                isPlay = true
            )
        }
    }

    fun startGame() {
        snakeGameEngineJob = viewModelScope.launch {

            setGameArea()

            while (true) {
                delay(1000)

                val coordinates = state.value.snakeCoordinates
                val direction = state.value.snakeDirection
                val gameArea = state.value.gameArea

                val newCoordinate = coordinates.first().first + direction.dCoordinates.first to
                        coordinates.first().second + direction.dCoordinates.second

                val newCoordinates = coordinates.toMutableList()
                    .apply {
                        add(0, newCoordinate)
                        removeLast()
                    }.let { it.toList() }

                if (newCoordinates.first().first !in 0..9
                    || newCoordinates.first().second !in 0..9
                ) {
                    mutableState.update { uiState ->
                        uiState.copy(
                            isPlay = false
                        )
                    }
                    this.cancel()
                }

                val newGameArea = gameArea.mapIndexed { verticalIndex, cells ->
                    if (newCoordinates.first().first == verticalIndex) {
                        cells.mapIndexed { horizontalIndex, cell ->
                            if (newCoordinates.first().second == horizontalIndex) {
                                cell.copy(type = CellModel.Type.SNAKE)
                            } else cell
                        }
                    } else cells
                }

                mutableState.update { uiState ->
                    uiState.copy(
                        gameArea = newGameArea,
                        snakeCoordinates = newCoordinates
                    )
                }
            }
        }
    }

    fun changeDirection(direction: SnakeDirection) {
        mutableState.update { uiState ->
            uiState.copy(snakeDirection = direction)
        }
    }
}