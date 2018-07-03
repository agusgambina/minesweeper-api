package repositories

import reactivemongo.api.MongoConnection
import reactivemongo.api.collections.bson.BSONCollection

import scala.concurrent.Future

object MineSweeperMongoConnection {

  val driver = new reactivemongo.api.MongoDriver
  val connection: MongoConnection = driver.connection(List("localhost"))

}
