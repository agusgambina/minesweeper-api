package models

import scala.util.Random

case class Board(
  cells: List[Cell]
) {

  def createGameBoard(columns: Int, rows: Int, mines: Int): Board = copy(cells = createBoardWithMines(columns, rows, mines))

  def createCleanBoard(columns: Int, rows: Int): Board = copy(cells = createWhiteBoard(columns, rows))

  private def createWhiteBoard(columns: Int, rows: Int): List[Cell] = {
    for {
      col <- 1 to columns
      row <- 1 to rows
    } yield Cell(Position(col, row))
  }

  private def createBoardWithMines(columns: Int, rows: Int, mines: Int): List[Cell] = {
    val mines = minesPositions(columns, rows, mines)
    for {
      col <- 1 to columns
      row <- 1 to rows
      isMine <- mines contains Position(col, row)
    } yield Cell(Position(col, row), isMine)
  }

  private def minesPositions(columns: Int, rows: Int, mines: Int): List[Position] = {
    Random.shuffle((for {
      col <- 1 to columns
      row <- 1 to rows
    } yield Position(col, row)).toList).take(mines)
  }
}

object Board {
  import play.api.libs.json._
  implicit val boardFormat = Json.format[Board]
}