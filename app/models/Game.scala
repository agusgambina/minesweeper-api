package models

case class Game(
  player: String,
  gameProperties: GameStatus = GameStatus(),
  board: Board = Board(cells = List()).createGameBoard(),
  moves: List[Board] = List()
)

object Game {
  import play.api.libs.json._
  implicit val gameFormat = Json.format[Game]
}
