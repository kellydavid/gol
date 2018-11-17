package tech.davidkelly

import org.scalatest.FunSuite
import tech.davidkelly.World.{AliveCell, Position}

import scala.collection.immutable.HashSet

class WorldTest extends FunSuite {

  test("A world with no alive cells in its environment is empty."){
    assert(World(environment = HashSet.empty).isEmpty)
  }

  test("A world with one alive cell in its environment is not empty."){
    assert(!World(environment = HashSet(AliveCell(Position(1, 1)))).isEmpty)
  }

}
