package rummikub.model.gameBoardComponents

import rummikub.model.gameBoardComponents.gameBoardBaseImpl.{Field, GameBoardNet}

trait GameBoardInterface {

  def getField(x: Int, y: Int): Field
  def fillTiles(): List[String]
  def getXSize(): Int
  def getYSize(): Int
  def printGameboard(): String
  def isEmptyBoard(): Boolean
  def resetValues(x: Int, y: Int): GameBoardNet
  def function1(x: Int, y: Int): Boolean
  def validInsertBetween(x: Int, y: Int): Boolean
  def validInsertATNT0(x: Int, y: Int): Boolean
  def validInsertP0a(x: Int, y: Int): Boolean
  def validInsertP0b(x: Int, y: Int): Boolean
  def isNumberValid(x: Int, y: Int): Boolean
  def function1Color(x: Int, y: Int): Boolean
  def function2Color(x: Int, y: Int): Boolean
  def function3Color(x: Int, y: Int): Boolean
  def function4Color(x: Int, y: Int): Boolean
  def function5Color(x: Int, y: Int): Boolean
  def isColorValid(x: Int, y: Int): Boolean
  def allValid(x: Int, y: Int): Boolean
  def insertTile(x: Int, y: Int, c: Char, v: Int): GameBoardNet
  def returnGB: GameBoardNet
  def getp1():Option[String]
  def getp2():Option[String]
  def getp3():Option[String]
}
