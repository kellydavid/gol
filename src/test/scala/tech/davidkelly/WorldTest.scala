package tech.davidkelly

import org.scalatest.FunSuite
import scala.collection.immutable.HashSet

class WorldTest extends FunSuite {

  test("A world with no alive cells in its environment is empty.") {
    assert(
      FiniteWorld(
        generationIteration = 0,
        size = 5,
        environment = HashSet.empty
      ).isEmpty)
  }

  test("A world with one alive cell in its environment is not empty.") {
    assert(!
      FiniteWorld(
        generationIteration = 0,
        size = 5,
        environment = HashSet(AliveCell(Position(1, 1)))
      ).isEmpty)
  }

}
