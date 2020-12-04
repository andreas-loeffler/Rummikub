package controller

import model.{GameBoardNet, StatePattern, NumPlayersStrategy, FactoryStrategy}
import util.Observable

class Controller(var gameBoardNet: GameBoardNet) extends Observable {

  def resetGameBoard(): Unit = {
    for (x <- 0 until gameBoardNet.getXSize()) {
      for (y <- 0 until gameBoardNet.getYSize()) {
        gameBoardNet = gameBoardNet.resetValues(x, y)
      }


    }
    gameBoardNet = new GameBoardNet()
    notifyObservers
  }

  def insertTile(row: Int, col: Int, color: Char, value: Int): Unit = {
    gameBoardNet = gameBoardNet.insertTile(row, col, color, value)
    notifyObservers
  }

  def printGameBoard(): String = {
    gameBoardNet.printGameboard()
  }

  def handle(): Unit = {
    val state = StatePattern
    state.handle(gameBoardNet.isEmptyBoard())
  }

  def threePlayerOpt(player1: String, player2: String, player3: String): Unit = {
    gameBoardNet = NumPlayersStrategy.playerN("player3", player1, player2, player3, gameBoardNet)
    notifyObservers
  }

  def twoPlayerOpt(player1: String, player2: String): Unit = {
    gameBoardNet = NumPlayersStrategy.playerN("player2", player1, player2, null, gameBoardNet)
    notifyObservers
  }

  def bigGB(): Unit = {
    gameBoardNet = FactoryStrategy("big")
    notifyObservers
  }

  def smallGB(): Unit = {
    gameBoardNet = FactoryStrategy("small")
    notifyObservers
  }

}
