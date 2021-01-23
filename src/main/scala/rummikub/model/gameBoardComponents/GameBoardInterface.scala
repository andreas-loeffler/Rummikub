package Rummikub.model.gameBoardComponents

import Rummikub.model.gameBoardComponents.gameBoardBaseImpl.{Field, GameBoardNet}

trait GameBoardInterface {

  def getField(x: Int, y: Int): Field
  def fillTiles(): List[String]
  def getXSize(): Int
  def getYSize(): Int
  def printGameboard(): String
  def isEmptyBoard(): Boolean
  def resetValues(x: Int, y: Int): GameBoardNet
  def validInsertBase(x: Int, y: Int): Boolean
  def validInsertBetween(x: Int, y: Int): Boolean
  def validInsertATNT0(x: Int, y: Int): Boolean
  def validInsertP0a(x: Int, y: Int): Boolean
  def validInsertP0b(x: Int, y: Int): Boolean
  def isNumberValid(x: Int, y: Int): Boolean
  def colorValidBase(x: Int, y: Int): Boolean
  def colorValidBetween(x: Int, y: Int): Boolean
  def colorValidATNT0(x: Int, y: Int): Boolean
  def colorValidInsPos0a(x: Int, y: Int): Boolean
  def colorValidInsPos0b(x: Int, y: Int): Boolean
  def isColorValid(x: Int, y: Int): Boolean
  def allValid(x: Int, y: Int): Boolean
  def insertTile(x: Int, y: Int, c: Char, v: Int): GameBoardNet
  def insertTileRaw(x: Int, y: Int, c: Char, v: Int): GameBoardNet
  def returnGB: GameBoardNet
  def getp1():Option[String]
  def getp2():Option[String]
  def getp3():Option[String]
  def getAllPlayer(): String
  def renamePlayer1(name:Option[String]):Unit
  def renamePlayer2(name:Option[String]):Unit
  def renamePlayer3(name:Option[String]):Unit
  def removeSingleTile(tile: String): Unit
  def checkTileAvailable(tile: String): Boolean
}
