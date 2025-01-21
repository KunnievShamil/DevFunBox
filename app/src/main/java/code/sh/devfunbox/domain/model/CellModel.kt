package code.sh.devfunbox.domain.model

import androidx.compose.ui.graphics.Color

data class CellModel(
    val type: Type
) {
    enum class Type(val color: Color) {
        EMPTY(color = Color.Transparent),
        SNAKE(color = Color.Black),
        FOOD(color = Color.Red)
    }
}
