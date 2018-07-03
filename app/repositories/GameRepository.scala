package repositories

import javax.inject.Inject
import models.Game

import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.json.{JsObject, Json}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json._
import reactivemongo.play.json.collection._

class GameRepository @Inject()(val reactiveMongoApi: ReactiveMongoApi)(implicit ex: ExecutionContext) {

  def games = reactiveMongoApi.database.map(_.collection[JSONCollection]("games"))

  implicit lazy val format = Json.format[Game]

  def save(game: Game): Future[Game] = {
    games.flatMap(_.insert(game)).flatMap {
      _ => Future.successful(game)
    }
  }

  def findGame(gameId: Int): Future[Option[Game]] =
    games.flatMap(_.find(Json.obj("gameId" -> gameId)).one[Game])

}

//class GameRepository(database: MongoDB)(implicit ec: ExecutionContext) extends ReactiveRepository[Game]
//  (database, "Game", testReader, testWriter, ec) {
//
//  override def indexes: Seq[Index] = Seq(
//  Index(Seq("name" -> IndexType.Ascending), unique = true)
//  )
//
//  def saveGame(game: Game) = dbFromConnection(MineSweeperMongoConnection.connection)
//
//
////  {
////    store.indexWhere(x => x.id == game.id) match {
////      case index => {
////        store.updated(index, game)
////        Right(game)
////      }
////      case _ => {
////        Left(s"Something went wrong, not found able to update the game ${game}")
////      }
////    }
////  }
//
//  def findGame(gameId: Int): Either[String, Game] = ???
////  {
////    store.find(game => game.id == gameId) match {
////      case Some(game) => Right(game)
////      case e: Any => Left(s"Something went wrong, not found ${e.toString}")
////    }
////  }
//
//  private def dbFromConnection(connection: MongoConnection): Future[BSONCollection] =
//    connection.database("minesweeper").
//      map(_.collection("games"))
//
//}
