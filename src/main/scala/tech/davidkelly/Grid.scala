package tech.davidkelly

trait Grid {

  def adjacentPositions(position: Position): Set[Position]

  def toString(markers: Set[Position]): String

  val offsets: Set[Position] =
    (-1 to 1).flatMap(x => (-1 to 1).map(y => Position(x, y)))
    .toSet.filter(pos => pos.x != 0 || pos.y != 0)
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
