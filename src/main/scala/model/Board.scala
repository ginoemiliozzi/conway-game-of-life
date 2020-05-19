package model
import scala.reflect.ClassTag

case class AdjacentRows(above: Option[BoardRow], below: Option[BoardRow]) {

  def getNeighbors(middleRow: Option[BoardRow], givenIndex: Int) = {
    middleRow match {
      case None => Seq.empty[Cell]
      case Some(boardRow) =>
        boardRow.row.cells.zipWithIndex.collect {
          case (cell, index)
              if (index - givenIndex).abs == 1 || index == givenIndex =>
            cell
        }
    }
  }

  def getAboveNeighbors(givenIndex: Int) = {
    getNeighbors(above, givenIndex)
  }

  def getBelowNeighbors(givenIndex: Int) = {
    getNeighbors(below, givenIndex)
  }
}
case class BoardRow(row: Row, rowIndex: Int) {
  def show(): Unit = row.show()

  def toNeighbors(board: Board): Map[Int, Seq[Cell]] = {
    val lastIndex = row.cells.size - 1
    val nextTo = row.cells.zipWithIndex.sliding(3).flatMap {
      case (leftCell, 0) :: mid :: right :: Nil =>
        List(0 -> Seq(mid._1), mid._2 -> Seq(leftCell, right._1))
      case left :: mid :: (rightCell, idx) :: Nil if (idx == lastIndex) =>
        List(idx -> Seq(mid._1), mid._2 -> Seq(left._1, rightCell))
      case left :: mid :: right :: Nil =>
        List(mid._2 -> Seq(left._1, right._1))
    }

    val adjacentRows = board.adjacentRows(this)
    val result = nextTo.map {
      case (idx, adjacentCells) =>
        val totalNeighbors = adjacentCells ++
          adjacentRows.getAboveNeighbors(idx) ++
          adjacentRows.getBelowNeighbors(idx)

        idx -> totalNeighbors
    }
    result.toMap
  }

  def evolve(board: Board): BoardRow = {
    val neighborsByCell = this.toNeighbors(board)
    copy(row = row.copy(cells = row.cells.zipWithIndex.map {
      case (cell, idx) =>
        Cell(
          cell.isAliveOnNextGeneration(
            neighborsByCell.getOrElse(idx, Seq.empty[Cell])
          )
        )
    }))
  }
}

case class ShowableBoard(rows: Seq[BoardRow])

case class Board(rows: Seq[BoardRow]) {
  def show(): Unit = rows.foreach(_.show())

  def adjacentRows(boardRow: BoardRow): AdjacentRows = {
    val rowReceived = boardRow.rowIndex
    val adjacent = rows.filter(r => (r.rowIndex - rowReceived).abs == 1)
    val aboveRow = adjacent.find(r => (r.rowIndex - rowReceived) < 0)
    val belowRow = adjacent.find(r => (r.rowIndex - rowReceived) > 0)
    AdjacentRows(aboveRow, belowRow)
  }

  def evolve(): Board = { copy(rows = rows.map(br => br.evolve(this))) }

}

object Board {
  // Adding this type parameter because compiler get confused with double def otherwise
  def apply[X: ClassTag](commonRows: Seq[Row]): Board = {

    val boardRows = commonRows.zipWithIndex.map {
      case (row, idx) => BoardRow(row, idx)
    }
    new Board(boardRows)
  }
}
