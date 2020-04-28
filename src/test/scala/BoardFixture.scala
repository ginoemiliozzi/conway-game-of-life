import model.{Board, Cell, Row}

object BoardFixture {
  val boardFirstAliveWithoutNeighbors = Board(
    Seq(
      Row(Seq(Cell(true), Cell(false), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false)))
    )
  )

  val boardFirstAliveWithTwoNeighbors = Board(
    Seq(
      Row(Seq(Cell(true), Cell(false), Cell(false), Cell(false))),
      Row(Seq(Cell(true), Cell(true), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false)))
    )
  )

  val boardFirstAliveWithThreeNeighbors = Board(
    Seq(
      Row(Seq(Cell(true), Cell(true), Cell(false), Cell(false))),
      Row(Seq(Cell(true), Cell(true), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false)))
    )
  )

  val boardFirstDeadWithThreeNeighbors = Board(
    Seq(
      Row(Seq(Cell(false), Cell(true), Cell(false), Cell(false))),
      Row(Seq(Cell(true), Cell(true), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false)))
    )
  )

  val boardSecondDeadWithFourNeighbors = Board(
    Seq(
      Row(Seq(Cell(true), Cell(false), Cell(true), Cell(false))),
      Row(Seq(Cell(false), Cell(true), Cell(true), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false)))
    )
  )

  val boardSecondAliveWithFourNeighbors = Board(
    Seq(
      Row(Seq(Cell(true), Cell(true), Cell(true), Cell(false))),
      Row(Seq(Cell(false), Cell(true), Cell(true), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(false), Cell(false)))
    )
  )

  val board4x4AllRowsDifferent = Board(
    Seq(
      Row(Seq(Cell(false), Cell(true), Cell(false), Cell(true))),
      Row(Seq(Cell(true), Cell(true), Cell(false), Cell(false))),
      Row(Seq(Cell(false), Cell(false), Cell(true), Cell(false))),
      Row(Seq(Cell(true), Cell(false), Cell(false), Cell(true)))
    )
  )
}
