package model

case class Cell(alive: Boolean) {
  def show(): Unit = if (alive) print(" O ") else print(" X ")

  def isAliveOnNextGeneration(neighbors: Seq[Cell]): Boolean = {
    val aliveNeighbors = neighbors.filter(_.alive)
    (alive, aliveNeighbors.size) match {
      case (isAlive, x) if isAlive && (x == 2 || x == 3) => true
      case (isAlive, x) if !isAlive && x == 3            => true
      case _                                             => false
    }
  }
}
