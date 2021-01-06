package controller.controllerComponents

import util.Observable

import scala.swing.Publisher

trait ControllerInterface extends Observable with Publisher {

  def resetGameBoard: Unit
  def printGameBoard: String
  def handle: Unit
  def onePlayerOpt(player1: String): Unit
  def twoPlayerOpt(player1: String, player2: String): Unit
  def threePlayerOpt(player1: String, player2: String, player3: String): Unit
  def bigGB:Unit
  def smallGB:Unit
  def set(x: Int, y: Int, colorTile: Char, valueTile: Int): Unit
  def undo:Unit
  def redo:Unit
  def gBxSize:Int
  def gBySize:Int
  def getFieldColor(x: Int,y:Int): Char
  def getFieldValue(x: Int,y:Int): Int


}
