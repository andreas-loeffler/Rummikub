package rummikub.model

import rummikub.model.StrategyComponents.strategyBaseImpl.StatePattern
import rummikub.model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StatePatternSpec extends AnyWordSpec with Matchers {
  "A StatePattern " should {
    val gameBoardNet = new GameBoardNet()
    "be offState " in {
      StatePattern.handle(gameBoardNet.isEmptyBoard()).toString startsWith ("N")
    }
    "insert a tile " in {
      gameBoardNet.insertTile(0, 0, 'Y', 3)
    }
    "print " in {
      StatePattern.handle(false).toString startsWith "2"
    }
  }


}
