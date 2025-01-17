package code.sh.devfunbox.domain.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class CircleModel(
    val id: Int,
    val color: Color = Color.Black,
    val center: Offset,
    val radius: Float
)