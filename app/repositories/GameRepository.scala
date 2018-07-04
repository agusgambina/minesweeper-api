package repositories

import models.Game

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json._
import reactivemongo.play.json.collection._

class GameRepository(val reactiveMongoApi: ReactiveMongoApi) {

  def games = reactiveMongoApi.database.map(_.collection[JSONCollection]("games"))

  implicit lazy val format = Json.format[Game]

  def save(game: Game): Either[String, Game] = ???
//  {
//    games.flatMap(_.insert(game)).flatMap {
//      _ => Future.successful(game)
//    } map {
//      game => Right(game)
//    } recover {
//      case error => Left("An error has occurred: " + error.getMessage)
//    }
//  }

  def find(gameId: Int): Either[String, Game] = ???
//  {
//    Await(games.flatMap(_.find(Json.obj("gameId" -> gameId)).one[Game]), 10 seconds) match {
//      case Some(game: Game) => Right(game)
//      case error => Left(s"An error has occurred find gameId #${gameId}, ${error}")
//    }
//  }

}
