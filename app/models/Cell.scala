package models

import reactivemongo.bson.{BSONDocumentReader, BSONDocumentWriter, Macros}

case class Cell(
  position: Position,
  isRevealed: Boolean = false,
  isMine: Boolean = false,
  mark: Option[String] = None
)

object Cell {
  import play.api.libs.json._
  implicit val cellFormat = Json.format[Cell]

  implicit val cellReader: BSONDocumentReader[Cell] = Macros.reader[Cell]
  implicit val cellWriter: BSONDocumentWriter[Cell] = Macros.writer[Cell]
}
