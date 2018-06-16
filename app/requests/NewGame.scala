package requests

case class NewGame(
  player: String,
  columns: Option[Int],
  rows: Option[Int],
  mines: Option[Int]
)

object NewGame {
  import play.api.libs.json._
  implicit val newGameFormat = Json.format[NewGame]
}
