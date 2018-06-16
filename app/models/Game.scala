package models

import scala.util.Random

case class Game(
  player: String,
  columns: Int = 10,
  rows: Int = 10,
  mines: Int = 10,
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
