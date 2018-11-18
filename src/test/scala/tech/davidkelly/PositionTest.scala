package tech.davidkelly

import org.scalatest.FunSuite

class PositionTest extends FunSuite {

  test("Adding positions (-1, 1) and (0, -1) equals (-1, 0) ."){
    val result = Position.add(Position(-1, 1), Position(0, -1))
    assert(result === Position(-1, 0))
  }

}
