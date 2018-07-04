package models

import reactivemongo.bson.{BSONDocumentReader, BSONDocumentWriter, Macros}

case class Position(
  column: Int,
  row: Int
)

object Position {
  import play.api.libs.json._
  implicit val positionFormat = Json.format[Position]

  implicit val positionReader: BSONDocumentReader[Position] = Macros.reader[Position]
  implicit val positionWriter: BSONDocumentWriter[Position] = Macros.writer[Position]
}
