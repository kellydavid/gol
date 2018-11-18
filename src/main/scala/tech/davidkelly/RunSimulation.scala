package tech.davidkelly

import scala.collection.immutable.HashSet

object RunSimulation extends App {

  val world = FiniteWorld(
    generationIteration = 0,
    grid = FiniteGrid(5),
    environment = HashSet(
      AliveCell(position = Position(1, 0)),
      AliveCell(position = Position(0, 1)),
      AliveCell(position = Position(2, 1)),
      AliveCell(position = Position(1, 2))
    )
  )

  println(GridIO.generateOutputString(world.grid, world.environment.map(_.position)))

  (1 to 5).foreach(_ => {
    println("\n\n")
    val newWorld = world.nextGeneration
    println(GridIO.generateOutputString(newWorld.grid, newWorld.environment.map(_.position)))
  })

}
