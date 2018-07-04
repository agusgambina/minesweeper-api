package common

import com.softwaremill.macwire.wire
import controllers.{Assets, AssetsComponents, GamesController}
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.routing.Router
import play.filters.HttpFiltersComponents
import play.modules.reactivemongo.{DefaultReactiveMongoApi, ReactiveMongoApi}
import reactivemongo.api.MongoConnection
import repositories.GameRepository
import router.Routes
import services.GameService

class MineSweeperApiComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
    with HttpFiltersComponents
    with AssetsComponents {

  // DB
  val driver = new reactivemongo.api.MongoDriver
  val connection: MongoConnection = driver.connection(List("localhost"))
  lazy val reactiveMongoApi: ReactiveMongoApi = wire[DefaultReactiveMongoApi]

  // Repos
  lazy val gameRepository = wire[GameRepository]

  // Services
  lazy val gameService = wire[GameService]

  // Controller
  lazy val gameController = wire[GamesController]

  // Router
  override lazy val assets = wire[Assets]
  lazy val prefix: String = "/"
  lazy val mineSweeperApiRouter: Router = wire[Routes]
  lazy val router: Router = mineSweeperApiRouter

}
