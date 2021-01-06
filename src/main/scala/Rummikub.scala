
import aview.TextUI
import aview.gui.SwingGui
import controller.FieldChanged
import aview.gui.SwingGui
import controller.controllerComponents.controllerBaseImpl.{Controller, FieldChanged}
import model.StrategyComponents.strategyBaseImpl.StatePattern
import model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet

import scala.io.StdIn.readLine

object Rummikub {
  //var gameBoardNet = new GameBoardNet()
  //gameBoardNet.resetValues()

  val controller = new Controller(new GameBoardNet())
  val textUI = new TextUI(controller)
  val gui = new SwingGui(controller)
  controller.publish(new FieldChanged)

  def main(args: Array[String]): Unit = {
    /*gameBoardNet = gameBoardNet.insertTile(0, 0, 'Y', 5) //valid
    gameBoardNet = gameBoardNet.insertTile(0, 1, 'B', 4) //not valid
    gameBoardNet = gameBoardNet.insertTile(1, 2, 'Y', 3) //valid
    gameBoardNet = gameBoardNet.insertTile(1, 3, 'Y', 4) //valid
    gameBoardNet = gameBoardNet.insertTile(1, 4, 'B', 5) //not valid
    gameBoardNet = gameBoardNet.insertTile(1, 13, 'B', 4) //valid
    gameBoardNet = gameBoardNet.insertTile(1, 11, 'B', 4) //valid
    gameBoardNet = gameBoardNet.insertTile(1, 4, 'Y', 5) //valid
    gameBoardNet = gameBoardNet.insertTile(1, 10, 'B', 4) //not valid
    gameBoardNet = gameBoardNet.insertTile(1, 10, 'B', 3) //valid
    gameBoardNet = gameBoardNet.insertTile(3, 10, 'P', 14) //valid
    gameBoardNet = gameBoardNet.insertTile(0, 1, 'Y', 1)
    print(gameBoardNet.printGameboard())

*/     var input: String = ""
     do {
       print(">>")
       input = readLine()
       textUI.userInput(input)
     } while (input != "quit")


  }

}
