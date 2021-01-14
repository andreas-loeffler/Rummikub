package rummikub

import com.google.inject.Guice
import rummikub.aview.TextUI
import rummikub.aview.gui.SwingGui
import rummikub.controller.controllerComponents._
import rummikub.model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet
import rummikub.controller.controllerComponents.controllerBaseImpl.{Controller, FieldChanged}

import scala.io.StdIn.readLine

object Rummikub {
  //var gameBoardNet = new GameBoardNet()
  //gameBoardNet.resetValues()

  val injector = Guice.createInjector(new RummikubModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
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

*/ var input: String = ""
    do {
      print(">>")
      input = readLine()
      textUI.userInput(input)
    } while (input != "quit")


  }

}
