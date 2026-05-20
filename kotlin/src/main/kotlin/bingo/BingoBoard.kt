package bingo

/**
 *  source - https://sammancoaching.org/kata_descriptions/bingo.html
 *
 *  The structure of the Bingo Refactoring Kata is similar to the Game of Life kata, but since the focus here is on refactoring, the business rules should be kept as simple as possible. It is not absolutely necessary to understand the business rules in order to complete the task. However, the business rules are:
 *
 * A bingo board must be initialized before it can be used/played
 * A board is initialized when each cell has been assigned a value
 * A cell may not be assigned a value that has already been assigned to another cell
 * When playing, a cell can be marked as selected
 *
 *
 */


class BingoBoard(width: Int, height: Int) {

    private val cells = mutableListOf<Cell>()

    init {
        (1..width).forEachIndexed { xPosition, _ ->
            (1..height).forEachIndexed { yPosition, _ ->
                cells.add(Cell(Coordinate(xPosition, yPosition)))
            }
        }

    }


    fun defineCell(x: Int, y: Int, value: String) {
        defineCell(Coordinate(x, y), value)
    }

    fun defineCell(coordinate: Coordinate, value: String) {

        run {
            check(cells.find { it.coordinate == coordinate }?.value == null) { "cell already defined" }
            val alreadyPresent = cells.find { it.value == value }
            check(alreadyPresent == null) { "$value already present at ${alreadyPresent!!.coordinate.x},${alreadyPresent.coordinate.y}" }
        }

        cells.find { it.coordinate == coordinate }?.value = value
    }

    fun markCell(coordinate: Coordinate) {
        check(isInitialized) { "board not initialized" }
        cells.find { it.coordinate == coordinate }?.marked = true
    }

    fun isMarked(coordinate: Coordinate) = cells.find { it.coordinate == coordinate }?.marked

    val isInitialized: Boolean
        get() = cells.all { it.value != null }
}

data class Cell(
    val coordinate: Coordinate,
    var value: String? = null,
    var marked: Boolean = false
)

data class Coordinate(val x: Int, val y: Int)