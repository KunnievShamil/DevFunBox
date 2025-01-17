package code.sh.devfunbox.feature.dvd_screensaver

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import code.sh.devfunbox.core.ui.base.BaseScreen
import kotlinx.coroutines.delay

class DvdSaverScreen : BaseScreen() {
    @Composable
    override fun Content() {

        var canvasSize by remember { mutableStateOf(IntSize(0, 0)) }
        var vector by remember { mutableStateOf(Offset(2f, 2f)) }
        var coordinates by remember { mutableStateOf(Offset(1f, 1f)) }
        var isCollide by remember { mutableStateOf(false) }

        val canvasColor by animateColorAsState(
            targetValue = if (!isCollide) Color.White else Color.Black,
            label = "Dvd Color"
        )

        val dvdColor by animateColorAsState(
            targetValue = if (isCollide) Color.White else Color.Black,
            label = "Dvd Color"
        )

        LaunchedEffect(Unit) {
            while (true) {
                delay(5)

                coordinates = coordinates.copy(
                    x = coordinates.x + vector.x,
                    y = coordinates.y + vector.y
                )

                if (coordinates.x < 0 || coordinates.x > canvasSize.width) {
                    isCollide = !isCollide
                    vector = vector.copy(x = -vector.x)
                }
                if (coordinates.y < 0 || coordinates.y > canvasSize.height) {
                    isCollide = !isCollide
                    vector = vector.copy(y = -vector.y)
                }
            }
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(color = canvasColor)
                .onSizeChanged { size -> canvasSize = size }
        ) {

            drawCircle(
                color = dvdColor,
                center = coordinates,
                radius = 100f
            )
        }
    }
}