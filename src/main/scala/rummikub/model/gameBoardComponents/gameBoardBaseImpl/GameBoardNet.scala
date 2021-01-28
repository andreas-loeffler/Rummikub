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
  var removedTiles: List[String] = List()

  def renamePlayer1(name: Option[String]): Unit = {
    val player = new Player(name)
    player1 = player
  }

  def renamePlayer2(name: Option[String]): Unit = {
    val player = new Player(name)
    player2 = player
  }

  def renamePlayer3(name: Option[String]): Unit = {
    val player = new Player(name)
    player3 = player
  }

  def fillTiles(): List[String] = {
    for (x <- 1 until 14) {
      tiles = ("S" + x) :: tiles
      tiles = ("G" + x) :: tiles
      tiles = ("R" + x) :: tiles
      tiles = ("B" + x) :: tiles
    }
    tiles = "J" :: tiles
    tiles
  }


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
      for (y <- 0 until 13) {
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


  def validInsertBase(x: Int, y: Int): Boolean = {
    x >= 0 && x < gameboard.length && y > 0 && y < gameboard(x).length - 1
  }

  def validInsertBetween(x: Int, y: Int): Boolean = (gameboard(x)(y).value < gameboard(x)(y + 1).value) && (gameboard(x)(y).value > gameboard(x)(y - 1).value)

  def validInsertATNT0(x: Int, y: Int): Boolean = (gameboard(x)(y).value >= gameboard(x)(y - 1).value) && gameboard(x)(y + 1).value == 0

  def validInsertP0a(x: Int, y: Int): Boolean = x < gameboard.length && y < gameboard(x).length - 1

  def validInsertP0b(x: Int, y: Int): Boolean = gameboard(x)(y).value >= gameboard(x)(y + 1).value

  def isNumberValid(x: Int, y: Int): Boolean = {
    if (validInsertBase(x, y)) {
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
  def colorValidBase(x: Int, y: Int): Boolean = x >= 0 && x < gameboard.length && y > 0 && y < gameboard(x).length - 1


  def colorValidBetween(x: Int, y: Int): Boolean = (gameboard(x)(y).color == gameboard(x)(y + 1).color) && (gameboard(x)(y).color == gameboard(x)(y - 1).color) || gameboard(x)(y + 1).color == ' ' && gameboard(x)(y - 1).color == ' '


  def colorValidATNT0(x: Int, y: Int): Boolean = (gameboard(x)(y).color.equals(gameboard(x)(y + 1).color) || gameboard(x)(y + 1).color.equals(' ')) && (gameboard(x)(y).color.equals(gameboard(x)(y - 1).color) || gameboard(x)(y - 1).color.equals(' '))

  //check at posi 0
  def colorValidInsPos0a(x: Int, y: Int): Boolean = x < gameboard.length && y < gameboard(x).length - 1

  def colorValidInsPos0b(x: Int, y: Int): Boolean = gameboard(x)(y).color == gameboard(x)(y + 1).color || gameboard(x)(y + 1).color == ' '

  def isColorValid(x: Int, y: Int): Boolean = {
    if (colorValidBase(x, y)) {
      if (colorValidBetween(x, y))
        return true
      else if (colorValidATNT0(x, y))
        return true
      else
        return false
    }
    else if (colorValidInsPos0a(x, y)) {
      if (colorValidInsPos0b(x, y))
        return true
    }
    true
  }


  def allValid(x: Int, y: Int): Boolean = ((isNumberValid(x, y) && isColorValid(x, y)) || isSameNumberdifColor(x, y))

  def isSameNumberdifColor(x: Int, y: Int): Boolean = {
    if (y > 1) {
      if (gameboard(x)(y - 1).value != gameboard(x)(y).value || !checkPrevPrev(x, y)) {
        return false
      }
      else if (gameboard(x)(y - 1) == null) {
        return true
      }
    } else if (y == 0) {
      return true
    } else if (y == 1) {
      return gameboard(x)(y - 1).value == gameboard(x)(y).value
    }
    true

  }

  def checkPrevPrev(x: Int, y: Int): Boolean = {
    if (gameboard(x)(y - 2).isEmpty) {
      return true
    }
    else if (gameboard(x)(y - 2).value == gameboard(x)(y).value) {
      return true
    }
    false
  }


  def checkTileAvailable(tile: String): Boolean = {
    if (this.tiles.contains(tile)) {
      removeSingleTile(tile)
      return true
    }
    false
  }

  def removeSingleTile(tile: String): Unit = {
    var index: Int = tiles.indexOf(tile)
    this.tiles = tiles.patch(index, None, 1)
    this.removedTiles = tile :: removedTiles
  }

  def redoRemove(tile: String): Unit = {
    var index = removedTiles.indexOf(tile)
    this.removedTiles = this.removedTiles.patch(index, None, 1)
    this.tiles = tile :: tiles
  }


  def insertTile(x: Int, y: Int, c: Char, v: Int): GameBoardNet = {
    if (x > gameboard.size && y > gameboard(1).size) {
      println("Invalid position!")
      val gameBoardNetException = copy(gameboard)
      return gameBoardNetException
    }

    val insertedGameboard = copy(gameboard.updated(x, gameboard(x).updated(y, Field(c, v))))
    val insertedWGameboard = copy(gameboard.updated(x, gameboard(x).updated(y, Field(' ', 0))))
    insertedGameboard.player1 = this.player1
    insertedGameboard.player2 = this.player2
    insertedGameboard.player3 = this.player3
    insertedGameboard.tiles = this.tiles
    insertedGameboard.removedTiles = this.removedTiles
    insertedWGameboard.player1 = this.player1
    insertedWGameboard.player2 = this.player2
    insertedWGameboard.player3 = this.player3
    insertedWGameboard.tiles = this.tiles
    insertedWGameboard.removedTiles = this.removedTiles
    if (!insertedGameboard.allValid(x, y)) {
      insertedWGameboard.redoRemove(tile = c + v.toString)
      return insertedWGameboard
    }

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

  override def getp1(): Option[String] = player1.name

  override def getp2(): Option[String] = player2.name

  override def getp3(): Option[String] = player3.name

  override def returnGB(): GameBoardNet = this

  override def getAllPlayer(): String = {
    val names = getp1().get + "," + getp2().get + "," + getp3().get
    names
  }
}
