package code.sh.devfunbox.feature.tictactoe

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import code.sh.devfunbox.core.ui.base.BaseScreen

class TicTacToeScreen : BaseScreen() {
    @Composable
    override fun Content() {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

        }
    }

    @Composable
    fun PlayingField(
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {

            drawLine(
                color = Color.Blue,
                start = Offset(0f, 0f),
                end = Offset(x = size.width, y = size.height)
            )

            drawLine(
                color = Color.Blue,
                start = Offset(size.width, 0f),
                end = Offset(x = 0f, y = size.height)
            )

        }
    }

    @Preview
    @Composable
    fun Preview() {
        PlayingField()
    }
}