package controller

import model.{FactoryStrategy, GameBoardNet, NumPlayersStrategy, StatePattern}
import util.{Observable, UndoManager}

class Controller(var gameBoardNet: GameBoardNet) extends Observable {

  private val undoManager = new UndoManager

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

  def onePlayerOpt(player1: String): Unit = {
    gameBoardNet = NumPlayersStrategy.playerN("player1", Some(player1), None, None, gameBoardNet)
  }


  def twoPlayerOpt(player1: String, player2: String): Unit = {
    gameBoardNet = NumPlayersStrategy.playerN("player2", Some(player1), Some(player2), None, gameBoardNet)
    notifyObservers
  }

  def threePlayerOpt(player1: String, player2: String, player3: String): Unit = {
    gameBoardNet = NumPlayersStrategy.playerN("player3", Some(player1), Some(player2), Some(player3), gameBoardNet)
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

  def set(x: Int, y: Int, colorTile: Char, valueTile: Int): Unit = {
    undoManager.doStep(new SetCommand(x, y, colorTile, valueTile, this))
    notifyObservers
  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
  }

}
