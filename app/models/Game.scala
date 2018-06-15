package models

case class Game(
  player: String,
  gameProperties: GameProperties,
  board: Board,
  moves: List[Board]
)

object Game {
  import play.api.libs.json._
  implicit val gameFormat = Json.format[Game]
}
