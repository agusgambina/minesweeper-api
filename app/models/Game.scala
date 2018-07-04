package models

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
}