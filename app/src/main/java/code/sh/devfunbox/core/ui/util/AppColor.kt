package code.sh.devfunbox.core.ui.util

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun Color.Companion.randomColor(): Color {
    return Color(
        red = Random.nextFloat(),
        green = Random.nextFloat(),
        blue = Random.nextFloat()
    )
}