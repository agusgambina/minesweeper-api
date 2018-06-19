package requests

case class GameRequest(
  player: String,
  columns: Option[Int],
  rows: Option[Int],
  mines: Option[Int]
)

object GameRequest {
  import play.api.libs.json._
  implicit val gameRequestFormat = Json.format[GameRequest]
}
