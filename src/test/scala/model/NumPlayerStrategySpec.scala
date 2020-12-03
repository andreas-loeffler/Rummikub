package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class NumPlayerStrategySpec extends AnyWordSpec with Matchers {
  "A NumPlayerStrategySpec " when {
    "new " should {
      var gameBoardNet = new GameBoardNet()
      NumPlayersStrategy.playerN("player1", "Josef", "Dieter", "Heinz", gameBoardNet)

      "have a player " in {
        gameBoardNet.player1.name should be ("Josef")
      }
      "insert another player " in{
        NumPlayersStrategy.playerN("player2", "Josef", "Dieter", "Heinz", gameBoardNet)
      }
      "have a second player " in{
        gameBoardNet.player2.name should be("Dieter")
      }
      "insert a third player " in{
        NumPlayersStrategy.playerN("player3", "Josef", "Dieter", "Heinz", gameBoardNet)
      }
      "have a third player " in{
        gameBoardNet.player3.name should be("Heinz")
      }
    }
  }
}
