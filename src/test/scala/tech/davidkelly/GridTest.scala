package tech.davidkelly

import org.scalatest.FunSuite

class GridTest extends FunSuite {

  test("A list of InfiniteGrid positions adjacent to (2, 2) ."){
    val result = InfiniteGrid().adjacentPositions(Position(2, 2))
    println(result)
    assert(result.contains(Position(1, 1)))
    assert(result.contains(Position(2, 1)))
    assert(result.contains(Position(3, 1)))
    assert(result.contains(Position(1, 2)))
    assert(result.contains(Position(3, 2)))
    assert(result.contains(Position(1, 3)))
    assert(result.contains(Position(2, 3)))
    assert(result.contains(Position(3, 3)))
  }


  test("A list of FiniteGrid size 5 positions adjacent to (2, 2) ."){
    val result = FiniteGrid(5).adjacentPositions(Position(2, 2))
    println(result)
    assert(result.contains(Position(1, 1)))
    assert(result.contains(Position(2, 1)))
    assert(result.contains(Position(3, 1)))
    assert(result.contains(Position(1, 2)))
    assert(result.contains(Position(3, 2)))
    assert(result.contains(Position(1, 3)))
    assert(result.contains(Position(2, 3)))
    assert(result.contains(Position(3, 3)))
  }

  test("A FiniteGrid size 5 does not contain position (-1, 1)."){
    val result = FiniteGrid(5).isPositionInsideGrid(Position(-1, 1))
    assert(result === false)
  }

}
