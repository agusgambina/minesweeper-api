package models

case class GameStatus(
  isOver: Boolean = false,
  isWinner: Boolean = false,
  timer: Int = 0
)

object GameStatus {
  import play.api.libs.json._
  implicit val gamePropertiesFormat = Json.format[GameStatus]
}