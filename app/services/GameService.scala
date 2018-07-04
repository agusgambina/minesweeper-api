package services

import models.{Board, Cell, Game, Position}
import repositories.GameRepository
import requests.{GameRequest, MoveRequest}

class GameService(val gameRepository: GameRepository) {

  val defaultColumns = 10
  val defaultRows = 10
  val defaultMines = 10

  def newGame(gameRequest: GameRequest): Either[String, Game] = {
    gameRepository.save(Game(
      id = getNextId,
      player = gameRequest.player,
      columns = defaultColumns,
      rows = defaultRows,
      mines = defaultMines,
      gameBoard = Board.createGameBoard(gameRequest.columns.getOrElse(defaultColumns), gameRequest.rows.getOrElse(defaultRows), gameRequest.mines.getOrElse(defaultMines)),
      moves = List(Board.createWhiteBoard(gameRequest.columns.getOrElse(defaultColumns), gameRequest.rows.getOrElse(defaultRows)))
    ))
  }

  def move(moveRequest: MoveRequest): Either[String, Game] = {
    for {
      game <- gameRepository.find(moveRequest.gameId).right
      _ <- Some(if (isValidPosition(moveRequest.position, game)) true else None).toRight(s"The column #${moveRequest.position} does not inside the board ").right
      cell <- Some(game.moves.last.getCell(moveRequest.position)).toRight(s"Can not get the cell at the position (${moveRequest.position.column},${moveRequest.position.row})").right
      _ <- Some(if (!cell.isRevealed) true else false).toRight(s"The cell ${cell.position} is already revealed").right
      _ <- Some(if (cell.mark.isEmpty) true else None).toRight(s"The cell (${cell.position}) has a mark ${cell.mark}").right
      reveal <- reveal(cell, game).right
    } yield reveal
  }

  private def reveal(moveRequest: MoveRequest): Either[String, Game] = ???

  // TODO this would change when persistence is implemented
  val r = scala.util.Random
  private def getNextId: Int = r.nextInt

  private def isValidPosition(position: Position, game: Game): Boolean =
    0 >= position.column && position.column <= game.columns && 0 >= position.row && position.row <= game.rows


  private def reveal(cell: Cell, game: Game): Either[String, Game] = {
    val auxiliaryBoard = Board(game.moves.last.cells.updated(game.moves.last.cells.indexOf(cell), cell.copy(isRevealed = true)))
    if (cell.isMine) {
      val moveList = auxiliaryBoard :: game.moves
      game.copy(isOver = true, isWinner = false, moves = moveList) match {
        case newGame: Game => gameRepository.save(newGame)
        case _ => Left(s"Something went wrong revealing the $cell in the game $game")
      }
    } else {
      val revealedBoard = revealNeighbors(cell, auxiliaryBoard, game)
      if (revealedBoard.cells.forall(cell => !cell.isMine && cell.isRevealed)) {
        gameRepository.save(game.copy(isOver = true, isWinner = true, moves = revealedBoard :: game.moves))
      } else {
        gameRepository.save(game.copy(moves = revealedBoard :: game.moves))
      }
    }
  }

  private def revealNeighbors(cell: Cell, auxiliaryBoard: Board, game: Game): Board = {
    for(col <- cell.position.column - 1 to  cell.position.column + 1) {
      for(row <- cell.position.row - 1 to  cell.position.column + 1) {
        val auxiliaryCell = auxiliaryBoard.getCell(Position(col, row))
        if(isValidPosition(auxiliaryCell.position, game) && auxiliaryCell.mark.isEmpty && !auxiliaryCell.isMine){
          auxiliaryBoard.cells.indexWhere(x => x.position == Position(col, row)) match {
            case index: Int => auxiliaryBoard.cells.updated(index, auxiliaryCell.copy(isRevealed = true, mark = Some(countMines(auxiliaryCell.position, auxiliaryBoard, game).toString)))
          }
        }
      }
    }
    auxiliaryBoard
  }

  private def countMines(position: Position, auxiliaryBoard: Board, game: Game): Int = {
    var mines: Int = 0
    for (col <- position.column - 1 to position.column + 1) {
      for (row <- position.row - 1 to position.column + 1) {
        if (isValidPosition(Position(col, row), game)) {
          if (auxiliaryBoard.getCell(Position(col, row)).isMine) {
            mines += 1
          }
        }
      }
    }
    mines
  }

}
