package models

import reactivemongo.bson.{BSONDocumentReader, BSONDocumentWriter, Macros}

case class Game(
  id: Int,
  player: String,
  columns: Int,
  rows: Int,
  mines: Int,
  isOver: Boolean = false,
  isWinner: Boolean = false,
  timer: Int = 0,
  gameBoard: Board,
  moves: List[Board]
) {
  def playerLoose(moves: List[Board]): Game = {
    copy(isOver = true, isWinner = false, moves = moves)
  }
}

object Game {
  import play.api.libs.json._
  implicit val gameFormat = Json.format[Game]

  implicit val gameReader: BSONDocumentReader[Game] = Macros.reader[Game]
  implicit val gameWriter: BSONDocumentWriter[Game] = Macros.writer[Game]
}