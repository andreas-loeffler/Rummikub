package controller.controllerComponents.controllerBaseImpl

import controller.controllerComponents.ControllerInterface
import model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet
import model.StrategyComponents.strategyBaseImpl.StatePattern
import model.StrategyComponents.strategyBaseImpl.{FactoryStrategy, NumPlayersStrategy, StatePattern}
import util.UndoManager


class Controller(var gameBoardNet: GameBoardNet) extends ControllerInterface {

  private val undoManager = new UndoManager

  def resetGameBoard(): Unit = {
    for (x <- 0 until gameBoardNet.getYSize()) {
      for (y <- 0 until gameBoardNet.getXSize()) {
        gameBoardNet = gameBoardNet.resetValues(x, y)
      }


    }
    gameBoardNet = new GameBoardNet()
    publish(new FieldChanged)
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
    publish(new PlayersChanged(player1, "available", "available"))
  }


  def twoPlayerOpt(player1: String, player2: String): Unit = {
    gameBoardNet = NumPlayersStrategy.playerN("player2", Some(player1), Some(player2), None, gameBoardNet)
    publish(new PlayersChanged(player1, player2, "available"))
  }

  def threePlayerOpt(player1: String, player2: String, player3: String): Unit = {
    gameBoardNet = NumPlayersStrategy.playerN("player3", Some(player1), Some(player2), Some(player3), gameBoardNet)
    publish(new PlayersChanged(player1, player2, player3))
  }


  def bigGB(): Unit = {
    gameBoardNet = FactoryStrategy("big")
    publish(new BigGameboard(22))
  }

  def smallGB(): Unit = {
    gameBoardNet = FactoryStrategy("small")
    publish(new SmallGameboard(11))
  }

  def set(x: Int, y: Int, colorTile: Char, valueTile: Int): Unit = {
    undoManager.doStep(new SetCommand(x, y, colorTile, valueTile, this))
    publish(new FieldChanged)
  }

  def undo: Unit = {
    undoManager.undoStep
    publish(new FieldChanged)
  }

  def redo: Unit = {
    undoManager.redoStep
    publish(new FieldChanged)
  }

  def gBxSize: Int = gameBoardNet.getXSize()

  def gBySize: Int = gameBoardNet.getYSize()

  def getFieldColor(x: Int, y: Int): Char = gameBoardNet.getField(x, y).color

  def getFieldValue(x: Int, y: Int): Int = gameBoardNet.getField(x, y).value

}
