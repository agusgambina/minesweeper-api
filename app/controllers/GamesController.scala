package controllers

import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import requests.{GameRequest, MoveRequest}
import services.GameService

class GamesController(val controllerComponents: ControllerComponents,
  gameService: GameService) extends BaseController {

  def createGame: Action[AnyContent] = Action { implicit request =>
    request.body.asJson.get match {
      case s: JsSuccess[GameRequest] => {
        gameService.newGame(s.get).fold(
          error => BadRequest(s"Something went wrong: ${error}"),
          success => Ok(Json.toJson(success))
        )
      }
      case e: JsError => BadRequest("Errors: " + JsError.toJson(e).toString())
    }
  }

  def move: Action[AnyContent] = Action { implicit request =>
    request.body.asJson.get match {
      case jsMove: JsSuccess[MoveRequest] => {
        gameService.move(jsMove.get).fold(
          error => BadRequest(s"Something went wrong: ${error}"),
          success => Ok(Json.toJson(success))
        )
      }
      case e: JsError => BadRequest("Errors: " + JsError.toJson(e).toString())
    }
  }
}
