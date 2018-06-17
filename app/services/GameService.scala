package services

import models.Game
import repositories.GameRepository

object GameService {

  def newGame(game: Game): Either[String, Game] = GameRepository.saveGame(game)

  def findGame(gameId: Int): Either[String, Game] = GameRepository.findGame(gameId)

  def getNextId: Int = GameRepository.store.length + 1
}