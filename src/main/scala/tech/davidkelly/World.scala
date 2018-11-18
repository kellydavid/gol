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

  override def nextGeneration: World =
    FiniteWorld(
      generationIteration = generationIteration + 1,
      size = size,
      environment = (
        environment
          .map(aliveCell => aliveCell.nextGeneration(findAliveNeighbours(aliveCell)))
          .collect({ case cell: AliveCell => cell })
          ++
          environment
            .flatMap(aliveCell => findDeadNeighbours(aliveCell)
              .map(deadCell => deadCell.nextGeneration(findAliveNeighbours(deadCell))))
            .collect({ case cell: AliveCell => cell })
        )
    )


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

  //  def findNeighbours(cell: AliveCell): (Set[AliveCell], Set[DeadCell]) = {
  //
  //    def adjacentPositions: Set[Position] = FiniteGrid(size).adjacentPositions(cell.position)
  //    val aliveCells = environment.filter(aliveCell => adjacentPositions.contains(aliveCell.position))
  //    val deadCells = adjacentPositions
  //      .filter(deadCellPosition => !aliveCells.contains(AliveCell(deadCellPosition)))
  //      .map(DeadCell(_))
  //    (aliveCells, deadCells)
  //  }
}
