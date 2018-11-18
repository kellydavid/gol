package tech.davidkelly

import org.scalatest.FunSuite

class GridTest extends FunSuite {

  test("A list of InfiniteGrid positions adjacent to (2, 2) ."){
    val result = InfiniteGrid().adjacentPositions(Position(2, 2))
    assert(result === Set(
      Position(1, 1),
      Position(2, 1),
      Position(3, 1),
      Position(1, 2),
      Position(3, 2),
      Position(1, 3),
      Position(2, 3),
      Position(3, 3)
    ))
  }


  test("A list of FiniteGrid size 5 positions adjacent to (2, 2) ."){
    val result = FiniteGrid(5).adjacentPositions(Position(2, 2))
    assert(result === Set(
      Position(1, 1),
      Position(2, 1),
      Position(3, 1),
      Position(1, 2),
      Position(3, 2),
      Position(1, 3),
      Position(2, 3),
      Position(3, 3)
    ))
  }

  test("A FiniteGrid size 5 does not contain position (-1, 1)."){
    val result = FiniteGrid(5).containsPosition(Position(-1, 1))
    assert(result === false)
  }

  test("A FiniteGrid size 5 contains position (2,2)."){
    val result = FiniteGrid(5).containsPosition(Position(2, 2))
    assert(result === true)
  }

  test("A FiniteGrid size 5 contains position (0,0)."){
    val result = FiniteGrid(5).containsPosition(Position(2, 2))
    assert(result === true)
  }

  test("A FiniteGrid size 5 contains position (4,4)."){
    val result = FiniteGrid(5).containsPosition(Position(4, 4))
    assert(result === true)
  }

  test("A FiniteGrid size 5 does not contain position (5,5)."){
    val result = FiniteGrid(5).containsPosition(Position(5, 5))
    assert(result === false)
  }

  test("A list of FiniteGrid size 5 positions adjacent to (0, 0) ."){
    val result = FiniteGrid(5).adjacentPositions(Position(0, 0))
    assert(result === Set(
      Position(0, 1),
      Position(1, 0),
      Position(1, 1)
    ))
  }

  test("A list of FiniteGrid size 5 positions adjacent to (1, 0) ."){
    val result = FiniteGrid(5).adjacentPositions(Position(1, 0))
    assert(result === Set(
      Position(0, 0),
      Position(2, 0),
      Position(0, 1),
      Position(1, 1),
      Position(2, 1)
    ))
  }

  test("A list of FiniteGrid size 1 positions adjacent to (0, 0) is empty ."){
    val result = FiniteGrid(1).adjacentPositions(Position(0, 0))
    assert(result === Set.empty)
  }

}
