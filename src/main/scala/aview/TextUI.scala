package aview

import controller.Controller
import util.Observer

import scala.util.{Failure, Success, Try}


class TextUI(controller: Controller) extends Observer {
  controller.add(this)


  def userInput(input: String): Boolean = {
    val splitinput = input.split(" ")
    Try(
      splitinput(0)
      match {
        case "createS" => controller.smallGB()
        case "createB" => controller.bigGB()
        case "print" => print(controller.printGameBoard())
        case "set" => controller.set(splitinput(1).toInt, splitinput(2).toInt, splitinput(3).charAt(0), splitinput(4).toInt)
        case "quit" => print("Bye!")
        case "reset" => controller.resetGameBoard()
        case "status" => controller.handle()
        case "player1" => controller.onePlayerOpt(splitinput(1))
        case "player2" => controller.twoPlayerOpt(splitinput(1),splitinput(2))
        case "player3" => controller.threePlayerOpt(splitinput(1),splitinput(2),splitinput(3))
        case "redo" => controller.redo
        case "undo" => controller.undo
      }
    )
    match {
      case Success(noexception) => true
      case Failure(exception) => println("Invalid Arguments!"); false
    }

  }

  override def update: Unit = println(controller.printGameBoard())
}
