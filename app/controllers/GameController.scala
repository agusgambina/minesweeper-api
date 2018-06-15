package controllers

import javax.inject.Inject
import models.Game
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

class GameController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def createGame: Action[AnyContent] = Action { implicit request =>
    Ok(Json.toJson(Game("John")))
  }

}
