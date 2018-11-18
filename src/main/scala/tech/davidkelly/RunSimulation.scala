package tech.davidkelly

import scala.annotation.tailrec
import scala.collection.immutable.HashSet

object RunSimulation extends App {

  val startingWorld = FiniteWorld(
    grid = FiniteGrid(5),
    environment = HashSet(
      AliveCell(position = Position(1, 0)),
      AliveCell(position = Position(0, 1)),
      AliveCell(position = Position(2, 1)),
      AliveCell(position = Position(1, 2)),
      AliveCell(position = Position(1, 1))
    )
  )

  val startingWorld2 = FiniteWorld(
    grid = FiniteGrid(10),
    environment = HashSet(
      AliveCell(position = Position(0, 0)),
      AliveCell(position = Position(0, 1)),
      AliveCell(position = Position(1, 0))
    )
  )

  val startingWorld3 = FiniteWorld(
    grid = FiniteGrid(20),
    environment = HashSet(
      AliveCell(position = Position(4, 3)),
      AliveCell(position = Position(3, 4)),
      AliveCell(position = Position(5, 4)),
      AliveCell(position = Position(4, 5)),
      AliveCell(position = Position(4, 4))
    )
  )

  val startingWorld4 = FiniteWorld(
    grid = FiniteGrid(20),
    environment = HashSet(
      AliveCell(position = Position(4, 3)),
      AliveCell(position = Position(3, 4)),
      AliveCell(position = Position(5, 4)),
      AliveCell(position = Position(4, 5)),
      AliveCell(position = Position(4, 4)),
      AliveCell(position = Position(6, 4)),
      AliveCell(position = Position(7, 4))
    )
  )

  val glider = FiniteWorld(
    grid = FiniteGrid(30),
    environment = HashSet(
      AliveCell(position = Position(5, 5)),
      AliveCell(position = Position(6, 5)),
      AliveCell(position = Position(7, 5)),
      AliveCell(position = Position(7, 4)),
      AliveCell(position = Position(6, 3))
    )
  )

  def run(startingWorld: World, iterations: Int): Unit = {

    @tailrec
    def simulate(world: World, iterations: Int, history: List[World]): List[World] = {
      iterations match {
        case it if it > 0 => simulate(world.nextGeneration, iterations - 1, history ++ List(world))
        case _ => history
      }
    }

    simulate(startingWorld, iterations, List.empty).map(world => {
      println("\n\n")
      world.printWorld
      Thread.sleep(500)
    })
  }

  run(glider, 20)
}
