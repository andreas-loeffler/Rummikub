package aview

import model.GameBoardNet

import scala.util.{Failure, Success, Try}


class TextUI {

  val gameBoardNet: GameBoardNet = GameBoardNet()
  gameBoardNet.resetValues()

  def userInput(input: String): Boolean = {
    val splitinput = input.split(" ")
    Try(
      splitinput(0)
      match {
        case "print" => print(gameBoardNet.printGameboard())
        case "insert" => gameBoardNet.insertTile(splitinput(1).toInt, splitinput(2).toInt, splitinput(3).charAt(0), splitinput(4).toInt)
        case "quit" => println("Bye!")
        case "reset" => gameBoardNet.resetValues()
      }
    )
    match {
      case Success(noexception) => true
      case Failure(exception) => println("Invalid Arguments!"); false
    }

  }


}
