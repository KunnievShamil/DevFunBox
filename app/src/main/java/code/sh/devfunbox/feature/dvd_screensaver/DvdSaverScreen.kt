package code.sh.devfunbox.feature.dvd_screensaver

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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import code.sh.devfunbox.R
import code.sh.devfunbox.core.ui.base.BaseScreen
import kotlinx.coroutines.delay

class DvdSaverScreen : BaseScreen() {

    @Composable
    override fun Content() {
        val dvdPainter: Painter = painterResource(id = R.drawable.ic_dvd)

        val dvdSize = remember { Size(200.dp.value, 200.dp.value) }
        var canvasSize by remember { mutableStateOf(IntSize.Zero) }
        var coordinates by remember { mutableStateOf(Offset(1f, 1f)) }
        var vector by remember { mutableStateOf(Offset(2f, 2f)) }
        var isCollide by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            animateDvd(dvdSize = dvdSize,
                canvasSize = { canvasSize },
                coordinates = { coordinates },
                updateCoordinates = { newCoordinates -> coordinates = newCoordinates },
                vector = { vector },
                updateVector = { newVector -> vector = newVector },
                onCollide = { isCollide = !isCollide })
        }

        DvdCanvas(dvdPainter = dvdPainter,
            dvdSize = dvdSize,
            coordinates = coordinates,
            onCanvasSizeChanged = { newCanvasSize -> canvasSize = newCanvasSize })
    }
}

@Composable
fun DvdCanvas(
    dvdSize: Size,
    dvdPainter: Painter,
    coordinates: Offset,
    onCanvasSizeChanged: (IntSize) -> Unit
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .onSizeChanged(onCanvasSizeChanged)
    ) {
        with(dvdPainter) {
            translate(left = coordinates.x, top = coordinates.y) {
                draw(size = dvdSize)
            }
        }
    }
}

suspend fun animateDvd(
    dvdSize: Size,
    canvasSize: () -> IntSize,
    coordinates: () -> Offset,
    updateCoordinates: (Offset) -> Unit,
    vector: () -> Offset,
    updateVector: (Offset) -> Unit,
    onCollide: () -> Unit
) {
    while (true) {

        delay(5)

        val currentCoordinates = coordinates()
        val currentVector = vector()
        val canvasBounds = canvasSize()

        val newCoordinates = currentCoordinates + currentVector
        updateCoordinates(newCoordinates)

        val isCollideX =
            (newCoordinates.x < 1) || (newCoordinates.x + dvdSize.width > canvasBounds.width)
        val isCollideY =
            (newCoordinates.y < 1) || (newCoordinates.y + dvdSize.height > canvasBounds.height)

        if (isCollideX || isCollideY) {
            if (isCollideX) {
                updateVector(currentVector.copy(x = -currentVector.x))
            }
            if (isCollideY) {
                updateVector(currentVector.copy(y = -currentVector.y))
            }
            onCollide()
        }
    }
}