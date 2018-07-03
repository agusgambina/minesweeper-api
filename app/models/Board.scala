package models

import scala.util.Random

case class Board(
  cells: List[Cell]
) {
  def getCell(position: Position): Cell = {
    cells.find(cell => cell.position == position).get
  }
}

object Board {
  import play.api.libs.json._
  implicit val boardFormat = Json.format[Board]

  def createGameBoard(columns: Int, rows: Int, mines: Int): Board = Board({
    val minesPositions = Random.shuffle((for {
      col <- 1 to columns
      row <- 1 to rows
    } yield Position(col, row)).toList).take(mines)
    (for {
      col <- 1 to columns
      row <- 1 to rows
    } yield {
      Cell(Position(col, row), isMine = (minesPositions contains Position(col, row)))
    }).toList
  })

  def createWhiteBoard(columns: Int, rows: Int): Board = Board({
    (for {
      col <- 1 to columns
      row <- 1 to rows
    } yield Cell(Position(col, row))).toList
  })

}