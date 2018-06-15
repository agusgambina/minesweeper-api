package models

import scala.util.Random

case class Board(
  columns: Int = 10,
  rows: Int = 10,
  mines: Int = 10,
  cells: List[Cell]
) {

  def createGameBoard(): Board = copy(cells = createBoardWithMines())

  def createCleanBoard(): Board = copy(cells = createWhiteBoard())

  private def createWhiteBoard(): List[Cell] = {
    (for {
      col <- 1 to columns
      row <- 1 to rows
    } yield Cell(Position(col, row))).toList
  }

  private def createBoardWithMines(): List[Cell] = {
    val minesPositions = Random.shuffle((for {
      col <- 1 to columns
      row <- 1 to rows
    } yield Position(col, row)).toList).take(mines)
    (for {
      col <- 1 to columns
      row <- 1 to rows
      isMine = minesPositions contains Position(col, row)
    } yield Cell(Position(col, row), isMine)).toList
  }

}

object Board {
  import play.api.libs.json._
  implicit val boardFormat = Json.format[Board]
}