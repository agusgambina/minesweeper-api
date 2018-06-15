package models

case class GameProperties(
  columns: Int = 10,
  rows: Int = 10,
  mines: Int = 10,
  isOver: Boolean = false,
  isWinner: Boolean = false,
  timer: Int = 0
)

object GameProperties {
  import play.api.libs.json._
  implicit val gamePropertiesFormat = Json.format[GameProperties]
}