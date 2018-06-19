package requests

import models.Position

case class MoveRequest(
  gameId: Int,
  position: Position
)

object MoveRequest {
  import play.api.libs.json._
  implicit val moveFormat = Json.format[Move]
}

