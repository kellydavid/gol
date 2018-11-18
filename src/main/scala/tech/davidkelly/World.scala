package tech.davidkelly

import scala.collection.immutable.HashSet

trait World {

  val generationIteration: Int

  val environment: HashSet[AliveCell]

  def nextGeneration: World

  def isEmpty: Boolean

}

case class FiniteWorld(
                        generationIteration: Int,
                        size: Int,
                        environment: HashSet[AliveCell]
                      ) extends World {


  def isEmpty: Boolean = environment.isEmpty

  override def nextGeneration: World = {

    def aliveCellsNextGeneration: HashSet[AliveCell] =
      environment
        .map(aliveCell => aliveCell.nextGeneration(findAliveNeighbours(aliveCell)))
        .collect({ case cell: AliveCell => cell })

    def deadCellsNextGeneration: HashSet[AliveCell] =
      environment
        .flatMap(aliveCell => findDeadNeighbours(aliveCell)
          .map(deadCell => deadCell.nextGeneration(findAliveNeighbours(deadCell))))
        .collect({ case cell: AliveCell => cell })

    FiniteWorld(
      generationIteration = generationIteration + 1,
      size = size,
      environment = aliveCellsNextGeneration ++ deadCellsNextGeneration
    )
  }

  def findAliveNeighbours(cell: Cell): Set[AliveCell] = {
    environment
      .filter(aliveCell => FiniteGrid(size).adjacentPositions(cell.position)
        .contains(aliveCell.position))
  }

  def findDeadNeighbours(cell: Cell): Set[DeadCell] = {
    FiniteGrid(size).adjacentPositions(cell.position)
      .filter(deadCellPosition => !findAliveNeighbours(cell).contains(AliveCell(deadCellPosition)))
      .map(DeadCell)
  }
}
