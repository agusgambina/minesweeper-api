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
)

object Game {
  import play.api.libs.json._
  implicit val gameFormat = Json.format[Game]
}
