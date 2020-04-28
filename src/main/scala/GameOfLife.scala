import model.{Board, Cell, Row}

object GameOfLife extends App {
  val board = Board(
    Seq(
      Row(Seq(Cell(true), Cell(true), Cell(false), Cell(true), Cell(false))),
      Row(Seq(Cell(true), Cell(true), Cell(true), Cell(true), Cell(true))),
      Row(Seq(Cell(true), Cell(false), Cell(true), Cell(false), Cell(true))),
      Row(Seq(Cell(true), Cell(true), Cell(false), Cell(true), Cell(true))),
      Row(Seq(Cell(true), Cell(true), Cell(true), Cell(true), Cell(false)))
    )
  )
}
