package Rummikub.model

import Rummikub.model.StrategyComponents.strategyBaseImpl.NumPlayersStrategy
import Rummikub.model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class NumPlayerStrategySpec extends AnyWordSpec with Matchers {
  "A NumPlayerStrategySpec " when {
    "new " should {
      var gameBoardNet = new GameBoardNet()
      NumPlayersStrategy.playerN("player1", Some("Josef"), Some("Dieter"), Some("Heinz"), gameBoardNet)

      "have a player " in {
        gameBoardNet.player1.name.get should be("Josef")
      }
      "insert a n=1 palyer" in {
        NumPlayersStrategy.playerN("player1",Some("Günther"),None,None,gameBoardNet)
      }
      "insert another player " in {
        NumPlayersStrategy.playerN("player2", Some("Josef"), Some("Dieter"), Some("Heinz"), gameBoardNet)
      }
      "have a second player " in {
        gameBoardNet.player2.name.get should be("Dieter")
      }
      "insert a third player " in {
        NumPlayersStrategy.playerN("player3", Some("Josef"), Some("Dieter"), Some("Heinz"), gameBoardNet)
      }
      "have a third player " in {
        gameBoardNet.player3.name.get should be("Heinz")
      }
    }
  }
}
