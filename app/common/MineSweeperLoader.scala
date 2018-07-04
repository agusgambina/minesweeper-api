package common

import play.api._
import play.api.ApplicationLoader.Context

class MineSweeperLoader extends ApplicationLoader {

  override def load(context: Context): Application = {
    LoggerConfigurator(context.environment.classLoader).foreach { _.configure(context.environment) }
    new MineSweeperApiComponents(context).application
  }

}
