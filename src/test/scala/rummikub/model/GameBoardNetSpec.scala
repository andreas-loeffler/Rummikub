package rummikub.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class GameBoardNetSpec extends AnyWordSpec with Matchers {
  "A Gameboard" when {
    "new " should {
      val gameBoardNet = GameBoardNet(10, 14)
      gameBoardNet.resetValues()
      "insert a tile " in {
        gameBoardNet.insertTile(0, 0, 'Y', 5)
      }
      "return true " in {
        gameBoardNet.isValid(0, 0) should be(true)
        gameBoardNet.isColorValid(0, 0) should be(true)
      }
      "insert a 2nd tile " in {
        gameBoardNet.insertTile(0, 2, 'Y', 7)
      }
      "return true too" in {
        gameBoardNet.isValid(0, 2) should be(true)
        gameBoardNet.isColorValid(0, 2) should be(true)
      }
      "insert a tile between tiles" in {
        gameBoardNet.insertTile(0, 1, 'Y', 6)
      }
      "return true aswell" in {
        gameBoardNet.isValid(0, 1) should be(true)
        gameBoardNet.isColorValid(0, 1) should be(true)
      }
      "insert a tile if next is none" in {
        gameBoardNet.insertTile(0, 3, 'Y', 7)
      }
      "return valid" in {
        gameBoardNet.isValid(0, 3) should be(true)
        gameBoardNet.isColorValid(0, 3) should be(true)
      }
      "insert a tile at the end" in {
        gameBoardNet.insertTile(0, 13, 'B', 8)
      }
      "return truee" in {
        gameBoardNet.isValid(0, 13) should be(true)
        gameBoardNet.isColorValid(0, 13) should be(true)
      }
      "insert a wrong color tile " in {
        gameBoardNet.insertTile(0, 4, 'B', 9)
      }
      "return true also " in {
        gameBoardNet.isValid(0, 4) should be(false)
        gameBoardNet.isColorValid(0, 4) should be(false)
      }
      "insert a wrong value tile " in {
        gameBoardNet.insertTile(0, 4, 'Y', 3)
      }
      "return false " in {
        gameBoardNet.isValid(0, 4) should be(false)
        gameBoardNet.isColorValid(0, 4) should be(false)
      }
      "inserting at outOfBounds" in{
        gameBoardNet.insertTile(55,55,'B',6).toString startsWith("Wrong")
      }
      "should be print a gameboard" in{
        gameBoardNet.printGameboard().toString startsWith("Y")
      }
    }


  }

}
