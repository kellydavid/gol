package tech.davidkelly

import scala.collection.immutable.HashSet

trait World {
  val generationIteration: Int

  def nextGeneration: World

  def isEmpty: Boolean
}

case class FiniteWorld(
                        generationIteration: Int,
                        size: Int,
                        environment: HashSet[AliveCell]
                      ) extends World {


  def isEmpty: Boolean = environment.isEmpty

  override def nextGeneration: World = ???
}
