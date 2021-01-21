package controller

import Rummikub.controller.controllerComponents.controllerBaseImpl.Controller
import Rummikub.model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import Rummikub.util.Observer

class ControllerSpec extends AnyWordSpec with Matchers{
"A Controller " when{
  "observer by an Observer" should{
    val gameBoardNet =  new GameBoardNet()
    val controller = new Controller(gameBoardNet)
    val observer =  new Observer {
      var updated: Boolean = false
      def isUpdated: Boolean = updated
      override def update: Unit = updated = true
    }
    "notify Observer after" in{
      controller.resetGameBoard()
      controller.printGameBoard()
    }
    "return x and y Value " in{
      controller.gBxSize.toString should be ("14")
      controller.gBySize.toString should be ("11")
    }
    "return playerNames " in {
      controller.getplayer1Name.toString should be ("None")
      controller.getplayer2Name.toString should be ("None")
      controller.getplayer3Name.toString should be ("None")
    }
    "return field color and value " in {
      controller.getFieldColor(0,0).toString should be(" ")
      controller.getFieldValue(0,0).toString should be("0")
    }
    "save a game " in {
      controller.saveXml
      controller.saveJson
    }
    "load a game " in {
      controller.loadXml
      controller.loadJson
    }
  }
}
}
