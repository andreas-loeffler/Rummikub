package controller

import model.GameBoardNet
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import util.Observer

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
    controller.add(observer)
    "notify Observer after" in{
      controller.resetGameBoard()
      controller.createGameboard()
      controller.insertTile(0,4,'Y',2)
      controller.printGameBoard()
    }
  }
}
}
