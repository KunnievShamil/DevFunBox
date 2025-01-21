package code.sh.devfunbox.feature.snake

import code.sh.devfunbox.domain.enums.SnakeDirection
import code.sh.devfunbox.domain.model.CellModel

data class SnakeUiState(
    val isPlay: Boolean,
    val score: Int,
    val gameArea: List<List<CellModel>>,
    val snakeCoordinates: List<Pair<Int, Int>>,
    val snakeDirection: SnakeDirection,
) {
    companion object {
        fun init() = SnakeUiState(
            isPlay = false,
            score = 0,
            gameArea = emptyList(),
            snakeCoordinates = emptyList(),
            snakeDirection = SnakeDirection.RIGHT
        )
    }
}
