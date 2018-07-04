package repositories

import models.Game
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala._
import play.api.libs.json.Json

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

import scala.concurrent.{ExecutionContext, Future}

class GameRepository(collection: MongoCollection[Game])(implicit ec: ExecutionContext) {

  implicit lazy val format = Json.format[Game]

  def save(game: Game): Either[String, Game] = {
    Await.result(collection
      .insertOne(game)
      .head
      .map { _ => game.id.toHexString }.flatMap {
      _ => Future.successful(game)
    } map {
      game => Right(game)
    } recover {
      case error => Left("An error has occurred: " + error.getMessage)
    }, 10 seconds)
  }

  def find(gameId: Int): Either[String, Game] = ???
//  {
//    Await.result(collection
//      .find(Document("id" -> new ObjectId(gameId)))
//      .first
//      .head
//      .map(Option(_)) map {
//      case Some(game: Game) => Right(game)
//      case _ => Left("An error has occurred")
//    }, 10 seconds)
//  }

}
