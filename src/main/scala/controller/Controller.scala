package controller

import model.GameBoardNet
import util.Observable

class Controller(var gameBoardNet: GameBoardNet) extends Observable {

  def createEmptyGameBoard(): Unit = {
    gameBoardNet = new GameBoardNet
    notifyObservers
  }

  def resetGameBoard(): Unit = {
    gameBoardNet.resetValues()
    notifyObservers
  }

  def insertTile(row: Int, col: Int, color: Char, value: Int): Unit = {
    gameBoardNet.insertTile(row, col, color, value)
    notifyObservers
  }
  def printGameBoard(): String = {
    gameBoardNet.printGameboard()
  }

}
