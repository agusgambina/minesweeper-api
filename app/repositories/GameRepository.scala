package repositories

import models.{Game, Position}

import scala.collection.mutable.ListBuffer

object GameRepository {

  var store: ListBuffer[Game] = ListBuffer()

  def saveGame(game: Game): Either[String, Game] = {
    store.append(game)
    Right(game)
  }

  def findGame(gameId: Int): Either[String, Game] = {
    (store.find(game => game.id == gameId)) match {
      case Some(game) => Right(game)
      case e: Any => Left(s"Something went wrong, not found ${e.toString}")
    }
  }

  def updateGame(game: Game): Either[Game, String] = ???



}
