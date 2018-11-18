package tech.davidkelly

object GridIO {

  def generateOutputString(grid: Grid, markers: Set[Position]): String = {
    val size = grid.asInstanceOf[FiniteGrid].size
    (1 to size)
      .map(x => (1 to size)
        .map(y => {
          (x, y) match {
            case (x, y) if (markers.contains(Position(x, y))) => "X"
            case (_, _) => "O"
          }
        }).mkString).mkString("\n")
  }

}
