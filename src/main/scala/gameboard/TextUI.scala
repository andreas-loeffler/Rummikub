package gameboard


import rummikub.model.{Field, GameBoardNet, Player}

import scala.util.{Try, Success, Failure}


class TextUI {

  val gameBoardNet = new GameBoardNet()
  gameBoardNet.resetValues()

  def userInput(input: String): Boolean = {
    val splitinput = input.split(" ")
    Try(
      splitinput(0)
      match {
        case "print" => gameBoardNet.printGameboard()
        case "insert" => gameBoardNet.insertTile(splitinput(1).toInt, splitinput(2).toInt, splitinput(3).charAt(0), splitinput(4).toInt)
        case "quit" => println("Bye!")
        case "reset" => gameBoardNet.resetValues()
      }
    )
    match {
      case Success(noexception) => return true
      case Failure(exception) => println("Invalid Arguments!"); false
    }

  }


}
