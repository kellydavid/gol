package tech.davidkelly

case class Position(x: Int, y: Int)

object Position{

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

  def add(a: Position, b: Position): Position = Position(a.x + b.x, a.y + b.y)
}
