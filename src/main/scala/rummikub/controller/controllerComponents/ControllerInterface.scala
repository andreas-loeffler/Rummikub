package Rummikub.controller.controllerComponents

import Rummikub.util.Observable

import scala.swing.Publisher

trait ControllerInterface extends Observable with Publisher {

  def saveXml: Unit

  def loadXml: Unit

  def saveJson: Unit

  def loadJson: Unit

  def resetGameBoard: Unit

  def printGameBoard: String

  def handle: Unit

  def onePlayerOpt(player1: String): Unit

  def twoPlayerOpt(player1: String, player2: String): Unit

  def threePlayerOpt(player1: String, player2: String, player3: String): Unit

  def bigGB: Unit

  def smallGB: Unit

  def set(x: Int, y: Int, colorTile: Char, valueTile: Int): Unit

  def undo: Unit

  def redo: Unit

  def gBxSize: Int

  def gBySize: Int

  def getFieldColor(x: Int, y: Int): Char

  def getFieldValue(x: Int, y: Int): Int

  def getplayer1Name: Option[String]

  def getplayer2Name: Option[String]

  def getplayer3Name: Option[String]

  def checkAvailable(tile: String): Boolean

}

import scala.swing.event.Event


class FieldChanged extends Event

case class BigGameboard(newSize: Int) extends Event

case class SmallGameboard(newSize: Int) extends Event

case class PlayersChanged(newPlayer1: String, newPlayer2: String, newPlayer3: String) extends Event


