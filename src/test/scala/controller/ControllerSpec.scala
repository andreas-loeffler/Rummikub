package controller

import rummikub.controller.controllerComponents.controllerBaseImpl.Controller
import rummikub.model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import rummikub.util.Observer

class ControllerSpec extends AnyWordSpec with Matchers{
"A Controller " when{
  "osberver by an Observer" should{
    val gameBoardNet =  new GameBoardNet()
    val controller = new Controller(gameBoardNet)
    val observer =  new Observer {
      var updated: Boolean = false
      def isUpdated: Boolean = updated
      override def update: Unit = updated = true
    }

    //controller.add(observer)
    "notify Observer after" in{
      controller.resetGameBoard()
      //controller.insertTile(0,4,'Y',2)
      controller.printGameBoard()
    }
  }
}
}
