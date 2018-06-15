package models

case class Position(
  column: Int,
  row: Int
)

object Position {
  import play.api.libs.json._
  implicit val positionFormat = Json.format[Position]
}
