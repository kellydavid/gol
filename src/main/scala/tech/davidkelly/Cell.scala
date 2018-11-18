package tech.davidkelly

trait Cell {
  val position: Position
  def nextGeneration(neighbours: Set[AliveCell]): Cell
}

case class AliveCell(position: Position) extends Cell {

  val MINIMUM_NEIGHBOURS_SURVIVAL: Int = 2
  val MAXIMUM_NEIGHBOURS_SURVIVAL: Int = 3

  def checkSurvival(numberNeighBours: Int): Boolean =
    numberNeighBours >= MINIMUM_NEIGHBOURS_SURVIVAL && numberNeighBours <= MAXIMUM_NEIGHBOURS_SURVIVAL

  override def nextGeneration(neighbours: Set[AliveCell]): Cell = {
    neighbours.size match {
      case survivalNumber if checkSurvival(survivalNumber) => AliveCell(position)
      case _ => DeadCell(position)
    }
  }

}

case class DeadCell(position: Position) extends Cell {

  val NUMBER_NEIGHBOURS_REPRODUCTION: Int = 3

  def checkReproduction(numberNeighbours: Int): Boolean =
    numberNeighbours == NUMBER_NEIGHBOURS_REPRODUCTION

  override def nextGeneration(neighbours: Set[AliveCell]): Cell = {
    neighbours.size match {
      case reproductionNumber if checkReproduction(reproductionNumber) => AliveCell(position)
      case _ => DeadCell(position)
    }
  }
}
