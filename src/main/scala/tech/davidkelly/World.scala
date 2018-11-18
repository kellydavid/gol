package tech.davidkelly

import tech.davidkelly.World.AliveCell

import scala.collection.immutable.HashSet

object World {

  trait Cell {
    val position: Position
  }

  case class AliveCell(position: Position) extends Cell

  case class DeadCell(position: Position) extends Cell



}

case class World(
                  environment: HashSet[AliveCell]
                )
{
  def nextGeneration: World = {
    ???
  }

  def isEmpty: Boolean = environment.isEmpty
}
