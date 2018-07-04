package controllers

import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import requests.{GameRequest, MoveRequest}
import services.GameService

class GamesController(val controllerComponents: ControllerComponents,
  gameService: GameService) extends BaseController {

  def createGame: Action[JsValue] = Action(parse.json) { implicit request =>
    val result = request.body.validate[GameRequest]
    result.fold(
      errors => BadRequest(s"Errors: ${errors}"),
      gameRequest => gameService.newGame(gameRequest).fold(
        error => BadRequest(s"Something went wrong: ${error}"),
        success => Ok(Json.toJson(success))
      )
    )
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
