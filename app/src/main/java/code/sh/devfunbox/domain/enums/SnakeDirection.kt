package code.sh.devfunbox.domain.enums

enum class SnakeDirection(val dCoordinates: Pair<Int, Int>) {
    UP(dCoordinates = 0 to -1),
    DOWN(dCoordinates = 0 to 1),
    LEFT(dCoordinates = -1 to 0),
    RIGHT(dCoordinates = 1 to 0);

    fun isOpposite(other: SnakeDirection): Boolean {
        return (this == UP && other == DOWN) ||
                (this == DOWN && other == UP) ||
                (this == LEFT && other == RIGHT) ||
                (this == RIGHT && other == LEFT)
    }
}