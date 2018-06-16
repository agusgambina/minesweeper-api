package controllers

import javax.inject.Inject
import models.{Board, Game}
import play.api.Configuration
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import requests.NewGame

class GameController @Inject()(cc: ControllerComponents, config: Configuration) extends AbstractController(cc) {

  val defaultColumns = 10
  val defaultRows = 10
  val defaultMines = 10

  def createGame: Action[AnyContent] = Action { implicit request =>
    val json = request.body.asJson.get
    val newGame = json.as[NewGame]
    Ok(Json.toJson(Game(
      player = newGame.player,
      columns = defaultColumns,
      rows = defaultRows,
      mines = defaultMines,
      gameBoard = Board.createGameBoard(newGame.columns.getOrElse(defaultColumns), newGame.rows.getOrElse(defaultRows), newGame.mines.getOrElse(defaultMines)),
      moves = List(Board.createWhiteBoard(newGame.columns.getOrElse(defaultColumns), newGame.rows.getOrElse(defaultRows)))
    )))

  }
}
