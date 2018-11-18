package tech.davidkelly

import org.scalatest.FunSuite
import scala.collection.immutable.HashSet

class WorldTest extends FunSuite {

  test("A world with no alive cells in its environment is empty.") {
    assert(
      FiniteWorld(
        generationIteration = 0,
        grid = FiniteGrid(5),
        environment = HashSet.empty
      ).isEmpty)
  }

  test("A world with one alive cell in its environment is not empty.") {
    assert(!
      FiniteWorld(
        generationIteration = 0,
        grid = FiniteGrid(5),
        environment = HashSet(AliveCell(Position(1, 1)))
      ).isEmpty)
  }

  test("A finite world which is empty will produce an empty world in the next generation.") {
    val testWorld = FiniteWorld(
      generationIteration = 0,
      grid = FiniteGrid(5),
      environment = HashSet.empty
    )
    val result = testWorld.nextGeneration
    assert(result === FiniteWorld(
      generationIteration = 1,
      grid = FiniteGrid(5),
      environment = HashSet.empty
    ))
  }

  test("A finite world will transition to the next generation with underpopulation.") {
    val testWorld = FiniteWorld(
      generationIteration = 0,
      grid = FiniteGrid(5),
      environment = HashSet(
        AliveCell(Position(0, 0)),
        AliveCell(Position(0, 1)),
        AliveCell(Position(3, 3))
      )
    )
    val result = testWorld.nextGeneration
    assert(result === FiniteWorld(
      generationIteration = 1,
      grid = FiniteGrid(5),
      environment = HashSet.empty
    ))
  }

  test("A finite world will transition to the next generation with survival.") {
    val testWorld = FiniteWorld(
      generationIteration = 0,
      grid = FiniteGrid(5),
      environment = HashSet(
        AliveCell(Position(1, 0)),
        AliveCell(Position(0, 1)),
        AliveCell(Position(2, 1)),
        AliveCell(Position(1, 2))
      )
    )
    val result = testWorld.nextGeneration
    assert(result === FiniteWorld(
      generationIteration = 1,
      grid = FiniteGrid(5),
      environment = HashSet(
        AliveCell(Position(1, 0)),
        AliveCell(Position(0, 1)),
        AliveCell(Position(2, 1)),
        AliveCell(Position(1, 2))
      )
    ))
  }

  test("A finite world will transition to the next generation with underpopulation and reproduction.") {
    val testWorld = FiniteWorld(
        generationIteration = 0,
      grid = FiniteGrid(5),
        environment = HashSet(
          AliveCell(Position(0, 0)),
          AliveCell(Position(0, 1)),
          AliveCell(Position(2, 2))
      )
    )
    val result = testWorld.nextGeneration
    assert(result === FiniteWorld(
        generationIteration = 1,
        grid = FiniteGrid(5),
        environment = HashSet(
          AliveCell(Position(1, 1))
      )
    ))
  }

  test("A finite world will transition to the next generation with overpopulation and reproduction.") {
    val testWorld = FiniteWorld(
      generationIteration = 0,
      grid = FiniteGrid(5),
      environment = HashSet(
        AliveCell(Position(1, 0)),
        AliveCell(Position(0, 1)),
        AliveCell(Position(2, 1)),
        AliveCell(Position(1, 2)),
        AliveCell(Position(1, 1))
      )
    )
    val result = testWorld.nextGeneration
    assert(result === FiniteWorld(
      generationIteration = 1,
      grid = FiniteGrid(5),
      environment = HashSet(
        AliveCell(Position(0, 0)),
        AliveCell(Position(1, 0)),
        AliveCell(Position(2, 0)),
        AliveCell(Position(0, 1)),
        AliveCell(Position(2, 1)),
        AliveCell(Position(0, 2)),
        AliveCell(Position(1, 2)),
        AliveCell(Position(2, 2))
      )
    ))
  }
}
