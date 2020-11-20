package controller

import model.GameBoardNet
import util.Observable

class Controller(var gameBoardNet: GameBoardNet) extends Observable {

  def resetGameBoard(): Unit = {
    for (x <- 0 until 10) {
      for (y <- 0 until 14) {
        gameBoardNet = gameBoardNet.resetValues(x, y)
      }

    }
    notifyObservers
  }

  def createGameboard(): Unit = {
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

}
