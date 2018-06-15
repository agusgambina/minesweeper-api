package models

case class Cell(
  position: Position,
  isMine: Boolean = false,
  mark: Option[String] = None
)

object Cell {
  import play.api.libs.json._
  implicit val cellFormat = Json.format[Cell]
}
