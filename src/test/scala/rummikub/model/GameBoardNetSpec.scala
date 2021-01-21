package Rummikub.model

import Rummikub.model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet
import Rummikub.model.playerComponents.playerBaseImpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class GameBoardNetSpec extends AnyWordSpec with Matchers {
  "A Gameboard" when {
    "new " should {
      var gameBoardNet = new GameBoardNet()
      gameBoardNet = gameBoardNet.insertTile(0, 1, 'Y', 2)
      gameBoardNet = gameBoardNet.insertTile(0, 2, 'B', 6)
      gameBoardNet = gameBoardNet.insertTile(0, 3, 'Y', 4)
      gameBoardNet = gameBoardNet.insertTile(0, 4, 'Y', 5)
      gameBoardNet = gameBoardNet.insertTile(0, 5, 'Y', 6)
      gameBoardNet = gameBoardNet.insertTile(0, 12, 'B', 8)
      gameBoardNet = gameBoardNet.insertTile(0, 13, 'B', 8)

      "be empty and return true " in {
        gameBoardNet.resetValues(0, 0) should be(gameBoardNet)
      }
      "return true" in {
        gameBoardNet.isNumberValid(0, 4) should be(true)
        gameBoardNet.function1Color(0, 1) should be(true)
        gameBoardNet.function2Color(0, 1) should be(true)
        gameBoardNet.function3Color(0, 1) should be(true)
        gameBoardNet.function4Color(0, 0) should be(true)
        gameBoardNet.function5Color(0, 0) should be(false)
        gameBoardNet.function5Color(0,1) should be (true)
        gameBoardNet.function1(0, 1) should be(true)
        gameBoardNet.validInsertATNT0(0, 1) should be(true)
        gameBoardNet.validInsertP0a(0, 0) should be(true)
        gameBoardNet.validInsertP0b(0, 0) should be(false)
        gameBoardNet = gameBoardNet.insertTile(0, 0, 'Y', 2)
        gameBoardNet.validInsertP0b(0, 0) should be(true)
        gameBoardNet.isColorValid(0, 0) should be(true)

        gameBoardNet.allValid(0, 0) should be(true)
      }

      "insert some more tiles " in {
        gameBoardNet.insertTile(0, 1, 'Y', 5).toString startsWith "Y"
        gameBoardNet.insertTile(0, 2, 'Y', 6).toString startsWith "Y"
        gameBoardNet.insertTile(0, 3, 'Y', 8).toString startsWith "Y"
        gameBoardNet.insertTile(0, 13, 'B', 8).toString startsWith "Y"
      }
      "should not be empty" in{
        gameBoardNet.isEmptyBoard() should be(false)
      }
      "should be valid/true " in {
        gameBoardNet.isNumberValid(0, 2) should be(false)
        gameBoardNet.isNumberValid(0, 13) should be(true)
      }

      "insert wrong tiles" in {
        gameBoardNet.insertTile(10, 13, 'B', 5).toString startsWith "I"
      }
      "return then false " in {
        gameBoardNet.isColorValid(0, 2) should be(false)
        gameBoardNet.isColorValid(9, 14) should be(true)
        gameBoardNet.validInsertBetween(0, 2) should be(false)
      }
      "print a gameboard" in {
        gameBoardNet.printGameboard() startsWith "Y"
      }

    }

  }
  "Another GameBoard" when {
    "new " should {
      var gameBoardNet = new GameBoardNet(11, 14)
      "set tiles" in {
        gameBoardNet.fillTiles()
      }
      "be empty" in{
        gameBoardNet.isEmptyBoard() should be (true)
      }
      "return x size" in{
        gameBoardNet.getXSize() should be(14)
      }
      "return y size" in{
        gameBoardNet.getYSize() should be(11)
      }
      "add players in" in{
        gameBoardNet.player1 = Player(Some("Adam"))
        gameBoardNet.player2 = Player(Some("Josef"))
      }
      "print the score" in{
        gameBoardNet.printGameboard() startsWith "Adam"
      }
      "add another player " in{
        gameBoardNet.player3 =Player(Some("Eva"))
      }
      "print the score again" in{
        gameBoardNet.printGameboard() startsWith("Adam")
      }
      "insert a tile at wrong posi " in {
        gameBoardNet.insertTile(12,15,'Y',6).toString startsWith(" ")
      }
      "return the name of players" in{
        gameBoardNet.getp1().toString startsWith("A")
        gameBoardNet.getp2().toString startsWith("J")
        gameBoardNet.getp3().toString startsWith("E")
      }
      "return a field " in{
        gameBoardNet.getField(2,2).toString should be(" 0")
      }
      "insert a tile raw " in{
        gameBoardNet.insertTileRaw(0,0,'S',3)
      }
      "print a error if wrong raw insert " in{
        gameBoardNet.insertTileRaw(122,1999,'G',3).toString startsWith("I")
      }
    }
  }
}
