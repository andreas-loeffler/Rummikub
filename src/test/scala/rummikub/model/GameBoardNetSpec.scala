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
      "insert a tile in the middle" in {
        gameBoardNet.insertTile(0, 1, 'Y', 6)
        gameBoardNet.insertTile(0, 2, 'Y', 7)
      }
      "return true aswell" in {
        gameBoardNet.isValid(0, 1) should be(true)
        gameBoardNet.isColorValid(0, 1) should be(true)
      }
      "re-insert a tile" in {
        gameBoardNet.insertTile(0, 1, 'Y', 6)
      }
      "return true too" in {
        gameBoardNet.isValid(0, 1) should be(true)
        gameBoardNet.isColorValid(0, 1) should be(true)
      }
      "insert a tile at the end" in {
        gameBoardNet.insertTile(0, 13, 'B', 4)
      }
      "return also true" in {
        gameBoardNet.isValid(0, 13) should be(true)
        gameBoardNet.isColorValid(0, 13) should be(true)
      }
      "insert a tile wrong" in {
        gameBoardNet.insertTile(0, 12, 'B', 6).toString startsWith "I"
      }
      "insert another tile wrong" in {
        gameBoardNet.insertTile(0, 12, 'Y', 3).toString startsWith "I"
      }
      "print gameboard" in {
        gameBoardNet.printGameboard().toString startsWith "0"
      }
      "insert a wrong tile" in {
        gameBoardNet.insertTile(14, 14, 'A', 77).toString startsWith "W"
      }
    }
  }

}
