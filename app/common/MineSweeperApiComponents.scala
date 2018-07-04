package common

import com.softwaremill.macwire.wire
import controllers.{Assets, AssetsComponents, GamesController}
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.routing.Router
import play.filters.HttpFiltersComponents
import repositories.GameRepository
import router.Routes
import services.GameService

class MineSweeperApiComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
  with MongoModule
  with HttpFiltersComponents {

  // Router
  lazy val prefix: String = "/"
  lazy val mineSweeperApiRouter: Router = wire[Routes]
  lazy val router: Router = mineSweeperApiRouter

  // Repos
  lazy val gameRepository = wire[GameRepository]

  // Services
  lazy val gameService = wire[GameService]

  // Controller
  lazy val gameController = wire[GamesController]

}

trait MongoModule {
  lazy val gamesCollection= Mongo.gameCollection
}