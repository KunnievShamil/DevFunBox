package code.sh.devfunbox.feature.tictactoe

data class TicTacToeUiState(
    val isStart: Boolean
) {
    companion object {
        fun init() = TicTacToeUiState(
            isStart = false
        )
    }
}
