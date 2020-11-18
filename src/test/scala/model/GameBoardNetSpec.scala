package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class GameBoardNetSpec extends AnyWordSpec with Matchers {
  "A Gameboard" when {
    "new " should {
      val gameBoardNet = GameBoardNet()
      "be empty and return true " in {
        gameBoardNet.resetValues() should be(true)
      }
      "return true" in {
        gameBoardNet.isValidInitPosi(0, 0) should be(true)
        gameBoardNet.isValidLastPosi(0, 0) should be(true)
        gameBoardNet.function1Color(0, 1) should be(true)
        gameBoardNet.function2Color(0, 1) should be(true)
        gameBoardNet.function3Color(0, 1) should be(true)
        gameBoardNet.function4Color(0, 0) should be(true)
        gameBoardNet.function5Color(0, 0) should be(true)
        gameBoardNet.isColorValid(0, 0) should be(true)
        gameBoardNet.allValid(0, 0) should be(true)
      }
      "return false" in {
        gameBoardNet.isValidB2T(0, 0) should be(false)
        gameBoardNet.isValidATNT0(0, 0) should be(false)
      }
      "insert some more tiles " in {
        gameBoardNet.insertTile(0, 1, 'Y', 5).toString startsWith "Y"
        gameBoardNet.insertTile(0, 2, 'Y', 6).toString startsWith "Y"
        gameBoardNet.insertTile(0, 3, 'Y', 8).toString startsWith "Y"
      }
      "then return true " in {
        gameBoardNet.isValidB2T(0, 2) should be(true)
        gameBoardNet.isValidATNT0(0, 3) should be(true)
      }
      "insert then a wrong tile" in {
        gameBoardNet.insertTile(0, 2, 'B', 1).toString startsWith "I"
      }
      "return then false " in {
        gameBoardNet.isColorValid(0, 2) should be(false)
        gameBoardNet.isColorValid(9,14) should be(true)
      }
      "print a gameboard" in {
        gameBoardNet.printGameboard() startsWith "Y"
      }

    }

  }
}
