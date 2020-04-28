package model

case class Row(cells: Seq[Cell]) {
  def show(): Unit = {
    cells.foreach(_.show())
    print("\n")
  }
}
