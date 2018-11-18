package tech.davidkelly

import scala.collection.immutable.HashSet

trait World {

  val generationIteration: Int

  val grid: Grid

  val environment: HashSet[AliveCell]

  def isEmpty: Boolean

  def nextGeneration: World

  def printWorld(): Unit
}

case class FiniteWorld(
                        generationIteration: Int,
                        grid: FiniteGrid,
                        environment: HashSet[AliveCell]
                      ) extends World {


  def isEmpty: Boolean = environment.isEmpty

  override def nextGeneration: FiniteWorld = {

    def aliveCellsNextGeneration: HashSet[AliveCell] =
      environment
        .map(aliveCell => aliveCell.nextGeneration(findAliveNeighbours(aliveCell)))
        .collect({ case cell: AliveCell => cell })

    def deadCellsNextGeneration: HashSet[AliveCell] =
      environment
        .flatMap(aliveCell => findDeadNeighbours(aliveCell)
          .map(deadCell => deadCell.nextGeneration(findAliveNeighbours(deadCell))))
        .collect({ case cell: AliveCell => cell })

    copy(
      generationIteration = generationIteration + 1,
      environment = aliveCellsNextGeneration ++ deadCellsNextGeneration
    )
  }

  def findAliveNeighbours(cell: Cell): Set[AliveCell] = {
    environment
      .filter(aliveCell => grid.adjacentPositions(cell.position)
        .contains(aliveCell.position))
  }

  def findDeadNeighbours(cell: Cell): Set[DeadCell] = {
    grid.adjacentPositions(cell.position)
      .filter(deadCellPosition => !findAliveNeighbours(cell).contains(AliveCell(deadCellPosition)))
      .map(DeadCell)
  }

  override def printWorld(): Unit = {
    println(grid.toString(environment.map(_.position)))
  }
}
