package Rummikub.model.gameBoardComponents.gameBoardBaseImpl

import Rummikub.model.gameBoardComponents.GameBoardInterface
import Rummikub.model.playerComponents.playerBaseImpl.Player

case class GameBoardNet(gameboard: Vector[Vector[Field]]) extends GameBoardInterface {

  def this() = this(Vector.tabulate(11, 14)((x, y) => Field(' ', 0)))

  def this(xS: Int, yS: Int) = this(Vector.tabulate(xS, yS)((x, y) => Field(' ', 0)))

  def getField(x: Int, y: Int): Field = {
    gameboard(y)(x)
  }

  var player1: Player = Player()
  var player2: Player = Player()
  var player3: Player = Player()
  var tiles: List[String] = List()

  def renamePlayer1(name:Option[String]):Unit = {
    val player = new Player(name)
    player1 = player
  }
  def renamePlayer2(name:Option[String]):Unit = {
    val player = new Player(name)
    player2 = player
  }
  def renamePlayer3(name:Option[String]):Unit = {
    val player = new Player(name)
    player3 = player
  }

  def fillTiles(): List[String] = {
    for (x <- 1 until 15) {
      tiles = ("S" + x) :: tiles
      tiles = ("G" + x) :: tiles
      tiles = ("R" + x) :: tiles
      tiles = ("B" + x) :: tiles
    }
    tiles = "J" :: tiles
    tiles = "J" :: tiles
    tiles
  }

  fillTiles()

  def getXSize(): Int = gameboard(1).size

  def getYSize(): Int = gameboard.size


  def printGameboard(): String = {
    val sb = new StringBuilder
    sb.append("Available tiles: ").append(this.tiles).append("\n")
    sb.append("Scores:").append("\n")
    if (this.player1.name.isDefined && this.player2.name.isDefined) {
      sb.append(this.player1.name.get).append(": ").append(this.player1.tiles).append("\n")
      sb.append(this.player2.name.get).append(": ").append(this.player2.tiles).append("\n")
    }
    if (this.player3.name.isDefined)
      sb.append(this.player3.name.get).append(": ").append(this.player3.tiles).append("\n")
    for (x <- gameboard.indices) {
      for (y <- gameboard(1).indices) {
        sb.append(gameboard(x)(y).color).append(gameboard(x)(y).value).append("|")
      }
      sb.append("\n")
    }
    sb.toString()
  }

  def isEmptyBoard(): Boolean = {
    var v: Boolean = true
    for (x <- gameboard.indices) {
      for (y <- 0 until 14) {
        if (gameboard(x)(y).isEmpty)
          v = true
        else {
          v = false
          return v
        }
      }
    }
    v
  }


  def resetValues(x: Int, y: Int): GameBoardNet = copy(gameboard.updated(x, gameboard(x).updated(y, Field(' ', 0))))


  def function1(x: Int, y: Int): Boolean = {
    x >= 0 && x < gameboard.length && y > 0 && y < gameboard(x).length - 1
  }

  def validInsertBetween(x: Int, y: Int): Boolean = (gameboard(x)(y).value < gameboard(x)(y + 1).value) && (gameboard(x)(y).value > gameboard(x)(y - 1).value)

  def validInsertATNT0(x: Int, y: Int): Boolean = (gameboard(x)(y).value >= gameboard(x)(y - 1).value) && gameboard(x)(y + 1).value == 0

  def validInsertP0a(x: Int, y: Int): Boolean = x < gameboard.length && y < gameboard(x).length - 1

  def validInsertP0b(x: Int, y: Int): Boolean = gameboard(x)(y).value >= gameboard(x)(y + 1).value

  def isNumberValid(x: Int, y: Int): Boolean = {
    if (function1(x, y)) {
      if (validInsertBetween(x, y))
        return true
      else if (validInsertATNT0(x, y))
        return true
      else
        return false
    }
    else if (validInsertP0a(x, y)) {
      if (validInsertP0b(x, y))
        return true
    }
    true
  }

  //Color checkers


  def function1Color(x: Int, y: Int): Boolean = x >= 0 && x < gameboard.length && y > 0 && y < gameboard(x).length - 1


  def function2Color(x: Int, y: Int): Boolean = (gameboard(x)(y).color == gameboard(x)(y + 1).color) && (gameboard(x)(y).color == gameboard(x)(y - 1).color) || gameboard(x)(y + 1).color == ' ' && gameboard(x)(y - 1).color == ' '


  def function3Color(x: Int, y: Int): Boolean = (gameboard(x)(y).color.equals(gameboard(x)(y + 1).color) || gameboard(x)(y + 1).color.equals(' ')) && (gameboard(x)(y).color.equals(gameboard(x)(y - 1).color) || gameboard(x)(y - 1).color.equals(' '))

  //check at posi 0
  def function4Color(x: Int, y: Int): Boolean = x < gameboard.length && y < gameboard(x).length - 1

  def function5Color(x: Int, y: Int): Boolean = gameboard(x)(y).color == gameboard(x)(y + 1).color || gameboard(x)(y + 1).color == ' '

  def isColorValid(x: Int, y: Int): Boolean = {
    if (function1Color(x, y)) {
      if (function2Color(x, y))
        return true
      else if (function3Color(x, y))
        return true
      else
        return false
    }
    else if (function4Color(x, y)) {
      if (function5Color(x, y))
        return true
    }
    true
  }


  def allValid(x: Int, y: Int): Boolean = isNumberValid(x, y) && isColorValid(x, y)


  def insertTile(x: Int, y: Int, c: Char, v: Int): GameBoardNet = {
    if (x > gameboard.size && y > gameboard(1).size) {
      println("Invalid position!")
      val gameBoardNetException = copy(gameboard)
      return gameBoardNetException
    }

    val insertedGameboard = copy(gameboard.updated(x, gameboard(x).updated(y, Field(c, v))))
    val insertedWGameboard = copy(gameboard.updated(x, gameboard(x).updated(y, Field(' ', 0))))
    if (!insertedGameboard.allValid(x, y))
      return insertedWGameboard

    insertedGameboard
  }

  override def insertTileRaw(x: Int, y: Int, c: Char, v: Int): GameBoardNet = {
    if (x > gameboard.size && y > gameboard(1).size) {
      println("Invalid position!")
      val gameBoardNetException = copy(gameboard)
      return gameBoardNetException
    }
    val insertedGameboard = copy(gameboard.updated(x, gameboard(x).updated(y, Field(c, v))))

    return insertedGameboard
  }

  override def getp1():Option[String] = player1.name
  override def getp2():Option[String] = player2.name
  override def getp3():Option[String] = player3.name

  override def returnGB(): GameBoardNet = this

  override def getAllPlayer(): String = {
    val names = getp1().get + "," + getp2().get + "," + getp3().get
    names
  }
}
