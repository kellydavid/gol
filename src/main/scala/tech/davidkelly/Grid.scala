package tech.davidkelly

import tech.davidkelly.Position.Offsets

trait Grid {
  def adjacentPositions(position: Position): List[Position]
}

case class InfiniteGrid() extends Grid {
  def adjacentPositions(position: Position): List[Position] = {
    List(
      Offsets.TOP_LEFT,
      Offsets.TOP,
      Offsets.TOP_RIGHT,
      Offsets.LEFT,
      Offsets.RIGHT,
      Offsets.BOTTOM_LEFT,
      Offsets.BOTTOM,
      Offsets.BOTTOM_RIGHT
    ).map(offset => Position.add(offset, position))
  }
}

case class FiniteGrid(
                       size: Int
                     ) extends Grid{

  def adjacentPositions(position: Position): List[Position] = {
    List(
      Offsets.TOP_LEFT,
      Offsets.TOP,
      Offsets.TOP_RIGHT,
      Offsets.LEFT,
      Offsets.RIGHT,
      Offsets.BOTTOM_LEFT,
      Offsets.BOTTOM,
      Offsets.BOTTOM_RIGHT
    ).map(offset => Position.add(offset, position)).filter(isPositionInsideGrid)
  }

  def isPositionInsideGrid(position: Position): Boolean = {
    (position.x, position.y) match {
      case (x, _) if x < 0 || x >= size => false
      case (_, y) if y < 0 || y >= size => false
      case (_, _) => true
    }
  }
}
