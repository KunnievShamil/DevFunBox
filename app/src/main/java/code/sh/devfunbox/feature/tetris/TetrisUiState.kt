package code.sh.devfunbox.feature.tetris

data class TetrisUiState(
    val isPlay: Boolean
) {
    companion object {
        fun initial() = TetrisUiState(
            isPlay = false
        )
    }
}
