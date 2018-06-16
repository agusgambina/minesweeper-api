package controllers

import javax.inject.Inject
import models.{Board, Game}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import requests.NewGame

class GameController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def createGame: Action[AnyContent] = Action { implicit request =>
    val json = request.body.asJson.get
    val newGame = json.as[NewGame]
    Ok(Json.toJson(Game(
      player = newGame.player,
      gameBoard = Board.createGameBoard(newGame.columns.getOrElse(10), newGame.rows.getOrElse(10), newGame.mines.getOrElse(10)),
      moves = List(Board.createWhiteBoard(newGame.columns.getOrElse(10), newGame.rows.getOrElse(10)))
    )))

  }
}
