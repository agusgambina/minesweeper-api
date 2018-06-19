package repositories

import models.{Game, Position}

import scala.collection.mutable.ListBuffer

object GameRepository {

  var store: List[Game] = List()

  def saveGame(game: Game): Either[String, Game] = {
    store.indexWhere(x => x.id == game.id) match {
      case index => {
        store.updated(index, game)
        Right(game)
      }
      case _ => {
        Left(s"Something went wrong, not found able to update the game ${game}")
      }
    }
  }

  def findGame(gameId: Int): Either[String, Game] = {
    store.find(game => game.id == gameId) match {
      case Some(game) => Right(game)
      case e: Any => Left(s"Something went wrong, not found ${e.toString}")
    }
  }

}
