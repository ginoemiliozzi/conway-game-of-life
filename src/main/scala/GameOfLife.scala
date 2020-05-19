import model.{Board, Cell, Row}

object GameOfLife extends App {
  val board = Board(
    Seq(
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(true), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(true), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(true), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false), Cell(false)))
    )
  )
  (1 to 100).foldLeft(board)((currentBoard, i) => {
    println(s"===ITERATION ${i}==")
    currentBoard.show()
    println(s"================")
    currentBoard.evolve()
  })
}
