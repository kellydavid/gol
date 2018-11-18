package tech.davidkelly

case class Position(x: Int, y: Int)

object Position{
  def add(a: Position, b: Position): Position = Position(a.x + b.x, a.y + b.y)
}
