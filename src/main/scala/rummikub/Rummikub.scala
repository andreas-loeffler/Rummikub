package Rummikub

import com.google.inject.Guice
import aview.TextUI
import aview.gui.SwingGui
import controller.controllerComponents.ControllerInterface

import scala.io.StdIn.readLine

object Rummikub {
  var UI_Type: Boolean = if (System.getenv("UI_TYPE").equals("tui")) true else false

  val injector = Guice.createInjector(new RummikubModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val textUI = new TextUI(controller)
  controller.smallGB

  def main(args: Array[String]): Unit = {
      if (!UI_Type) {
        val gui = new SwingGui(controller)
      }
    var input: String = ""
    do {
      print(">>")
      input = readLine()
      textUI.userInput(input)
    } while (input != "quit")


  }

}

//test