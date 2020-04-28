import org.scalatest._
import org.scalatest.matchers.should.Matchers
import BoardFixture._
import model.{AdjacentRows, BoardRow, Cell, Row}

class BoardSpec extends FlatSpec with Matchers {

  "Rules" should "let survive a cell with two neighbors" in {

    val firstCell = boardFirstAliveWithTwoNeighbors.rows.head.row.cells.head
    firstCell shouldBe Cell(true)

    val evolvedBoard = boardFirstAliveWithTwoNeighbors.evolve()

    val firstCellEvolved = evolvedBoard.rows.head.row.cells.head
    firstCellEvolved shouldBe Cell(true)
  }

  it should "let survive a cell with three neighbors" in {

    val firstCell = boardFirstAliveWithThreeNeighbors.rows.head.row.cells.head
    firstCell shouldBe Cell(true)

    val evolvedBoard = boardFirstAliveWithThreeNeighbors.evolve()

    val firstCellEvolved = evolvedBoard.rows.head.row.cells.head
    firstCellEvolved shouldBe Cell(true)
  }

  it should "become alive a dead cell with three neighbors as if by reproduction" in {

    val firstCell = boardFirstDeadWithThreeNeighbors.rows.head.row.cells.head
    firstCell shouldBe Cell(false)

    val evolvedBoard = boardFirstAliveWithThreeNeighbors.evolve()

    val firstCellEvolved = evolvedBoard.rows.head.row.cells.head
    firstCellEvolved shouldBe Cell(true)
  }

  it should "kill a cell with four neighbors as if by overpopulation" in {
    val secondCell = boardSecondAliveWithFourNeighbors.rows.head.row.cells(1)
    secondCell shouldBe Cell(true)

    val evolvedBoard = boardSecondAliveWithFourNeighbors.evolve()

    val secondCellEvolved = evolvedBoard.rows.head.row.cells(1)
    secondCellEvolved shouldBe Cell(false)
  }

  it should "let dead a cell with four neighbors" in {
    val secondCell = boardSecondDeadWithFourNeighbors.rows.head.row.cells(1)
    secondCell shouldBe Cell(false)

    val evolvedBoard = boardSecondDeadWithFourNeighbors.evolve()

    val secondCellEvolved = evolvedBoard.rows.head.row.cells(1)
    secondCellEvolved shouldBe Cell(false)
  }

  it should "kill a cell without neighbors as if by underpopulation" in {
    val firstCell = boardFirstAliveWithoutNeighbors.rows.head.row.cells.head
    firstCell shouldBe Cell(true)

    val evolvedBoard = boardFirstAliveWithoutNeighbors.evolve()

    val firstCellEvolved = evolvedBoard.rows.head.row.cells.head
    firstCellEvolved shouldBe Cell(false)
  }

  "Board" should "calculate adjacent rows for the first row" in {
    board4x4AllRowsDifferent.rows match {
      case x1 :: x2 :: _ =>
        val result = board4x4AllRowsDifferent.adjacentRows(x1)
        val expectedResult = AdjacentRows(above = None, below = Some(x2))
        result shouldBe expectedResult
      case _ => false
    }
  }

  it should "calculate adjacent rows for other row" in {
    board4x4AllRowsDifferent.rows match {
      case x1 :: x2 :: x3 :: _ =>
        val result = board4x4AllRowsDifferent.adjacentRows(x2)
        val expectedResult = AdjacentRows(above = Some(x1), below = Some(x3))
        result shouldBe expectedResult
      case _ => false
    }
  }

}
