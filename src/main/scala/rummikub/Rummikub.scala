package Rummikub

import com.google.inject.Guice
import aview.TextUI
import aview.gui.SwingGui
import controller.controllerComponents.ControllerInterface

import scala.io.StdIn.readLine

object Rummikub {
  val injector = Guice.createInjector(new RummikubModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val textUI = new TextUI(controller)
  val gui = new SwingGui(controller)
  controller.smallGB

  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      print(">>")
      input = readLine()
      textUI.userInput(input)
    } while (input != "quit")


  }

}
//test