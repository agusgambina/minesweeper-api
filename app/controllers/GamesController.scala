package controllers

import javax.inject.Inject
import models.{Game}
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import requests.{GameRequest, MoveRequest}

class GamesController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def createGame: Action[AnyContent] = Action { implicit request =>
    request.body.asJson.get match {
      case s: JsSuccess[GameRequest] => {
        Game.newGame(s.get).fold(
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
        Game.move(jsMove.get).fold(
          error => BadRequest(s"Something went wrong: ${error}"),
          success => Ok(Json.toJson(success))
        )
      }
      case e: JsError => BadRequest("Errors: " + JsError.toJson(e).toString())
    }
  }
}
