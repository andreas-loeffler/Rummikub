package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StatePatternSpec extends AnyWordSpec with Matchers {
  "A StatePattern " should {
    val gameBoardNet = new GameBoardNet()
    "be offState " in {
      State.handle(gameBoardNet.isEmptyBoard()).toString startsWith ("N")
    }
    "insert a tile " in {
      gameBoardNet.insertTile(0, 0, 'Y', 3)
    }
    "print " in {
      State.handle(false).toString startsWith "2"
    }
  }


}
