package tech.davidkelly

trait Grid {

  def adjacentPositions(position: Position): Set[Position]

  def toString(markers: Set[Position]): String

  object Offsets {
    val TOP_LEFT = Position(-1, -1)
    val TOP = Position(0, -1)
    val TOP_RIGHT = Position(1, -1)
    val LEFT = Position(-1, 0)
    val RIGHT = Position(1, 0)
    val BOTTOM_LEFT = Position(-1, 1)
    val BOTTOM = Position(0, 1)
    val BOTTOM_RIGHT = Position(1, 1)
  }

  val offsets: Set[Position] = Set(
    Offsets.TOP_LEFT,
    Offsets.TOP,
    Offsets.TOP_RIGHT,
    Offsets.LEFT,
    Offsets.RIGHT,
    Offsets.BOTTOM_LEFT,
    Offsets.BOTTOM,
    Offsets.BOTTOM_RIGHT
  )
}

case class InfiniteGrid() extends Grid {
  def adjacentPositions(position: Position): Set[Position] =
    offsets.map(offset => Position.add(offset, position))

  def toString(markers: Set[Position]): String = ???
}

case class FiniteGrid(
                       size: Int
                     ) extends Grid {

  def adjacentPositions(position: Position): Set[Position] =
    offsets.map(offset => Position.add(offset, position)).filter(containsPosition)

  def containsPosition(position: Position): Boolean =
    (position.x, position.y) match {
      case (x, _) if x < 0 || x >= size => false
      case (_, y) if y < 0 || y >= size => false
      case (_, _) => true
    }

  def toString(markers: Set[Position]): String =
    (0 until size).map(y => (0 until size).map(x => {
      if(markers.contains(Position(x, y))) "X" else "-"
    }).mkString).mkString("\n")

}
