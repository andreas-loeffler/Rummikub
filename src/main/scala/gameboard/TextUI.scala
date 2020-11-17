package gameboard


import rummikub.model.{Field, GameBoardNet, Player}


class TextUI {

  val gameBoardNet = new GameBoardNet()
  gameBoardNet.resetValues()

  def userInput(input: String): Unit = {
    val splitinput = input.split(" ")
    try {
      splitinput(0) match {
        case "print" => gameBoardNet.printGameboard()
        case "insert" => gameBoardNet.insertTile(splitinput(1).toInt, splitinput(2).toInt, splitinput(3).charAt(0), splitinput(4).toInt)
        case "quit" => println("Bye!")
        case "reset" => gameBoardNet.resetValues()
    }
  }
  catch
  {
    case e: MatchError => println("Invalid Arguments!")
    case e: IndexOutOfBoundsException => println("Invalid Arguments!")
    case e: NumberFormatException => println("Wrong Format!")
  }

}


}
