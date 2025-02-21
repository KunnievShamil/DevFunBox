package code.sh.devfunbox.feature.tetris

import code.sh.devfunbox.core.ui.base.BaseViewModel

class TetrisViewModel : BaseViewModel<TetrisUiState, TetrisUiEvent>(
    initialState = TetrisUiState.initial()
)