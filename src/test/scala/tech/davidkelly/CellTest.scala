package tech.davidkelly

import org.scalatest.FunSuite

class CellTest extends FunSuite {

  val testPosition = Position(0, 0)

  def CreateTestAliveCells(number: Int): Set[AliveCell] =
    (1 to number).map(offset => AliveCell(Position.add(testPosition, Position(offset, offset)))).toSet

  test("An Alive Cell with less than two alive neighbours dies from underpopulation."){
    val testCell: AliveCell = AliveCell(position = testPosition)

    val result1 = testCell.nextGeneration(Set.empty)
    assert(result1 === DeadCell(position = testPosition))

    val result2 = testCell.nextGeneration(CreateTestAliveCells(1))
    assert(result2 === DeadCell(position = testPosition))
  }

  test("An Alive Cell with two or three alive neighbours survives to the next generation."){
    val testCell: AliveCell = AliveCell(position = testPosition)
    val result1 = testCell.nextGeneration(CreateTestAliveCells(2))
    assert(result1 === AliveCell(position = testCell.position))

    val result2 = testCell.nextGeneration(CreateTestAliveCells(3))
    assert(result2 === AliveCell(position = testCell.position))
  }

  test("An Alive Cell with more than three neighbours dies by overpopulation."){
    val testCell: AliveCell = AliveCell(position = testPosition)

    val result1 = testCell.nextGeneration(CreateTestAliveCells(4))
    assert(result1 === DeadCell(position = testCell.position))

    val result2 = testCell.nextGeneration(CreateTestAliveCells(5))
    assert(result2 === DeadCell(position = testCell.position))

    val result3 = testCell.nextGeneration(CreateTestAliveCells(6))
    assert(result3 === DeadCell(position = testCell.position))
  }

  test("A Dead Cell with exactly three neighbours becomes alive through reproduction."){
    val testCell: DeadCell = DeadCell(position = testPosition)
    val result = testCell.nextGeneration(CreateTestAliveCells(3))
    assert(result === AliveCell(position = testCell.position))
  }

}
