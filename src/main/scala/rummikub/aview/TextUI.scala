package Rummikub.aview

import Rummikub.controller.controllerComponents.{BigGameboard, CandidatesChanged, ControllerInterface, FieldChanged, PlayersChanged}
import scala.swing.Reactor
import scala.util.{Failure, Success, Try}

class TextUI(controller: ControllerInterface) extends Reactor {

  listenTo(controller)

  def userInput(input: String): Boolean = {
    val splitinput = input.split(" ")
    Try(
      splitinput(0)
      match {
        case "createS" => controller.smallGB
        case "createB" => controller.bigGB
        case "print" => print(controller.printGameBoard)
        case "set" => controller.set(splitinput(1).toInt, splitinput(2).toInt, splitinput(3).charAt(0), splitinput(4).toInt)
        case "quit" => print("Bye!")
        case "reset" => controller.resetGameBoard
        case "status" => controller.handle
        case "player1" => controller.onePlayerOpt(splitinput(1))
        case "player2" => controller.twoPlayerOpt(splitinput(1), splitinput(2))
        case "player3" => controller.threePlayerOpt(splitinput(1), splitinput(2), splitinput(3))
        case "redo" => controller.redo
        case "undo" => controller.undo
        case "save" => controller.saveXml
      }
    )
    match {
      case Success(noexception) => true
      case Failure(exception) => println("Invalid Arguments!"); false
    }

  }

  reactions += {
    case event: BigGameboard => printTui
    case event: FieldChanged => printTui
    case event: CandidatesChanged => printCandidates
    case event: PlayersChanged => printTui
  }

  def printTui: Unit = {
    println(controller.printGameBoard)
    //println(GameStatus.message(controller.gameStatus))
  }

  def printCandidates: Unit = {
    println("Candidates: ")
    /*for (row <- 0 until size; col <- 0 until size) {
      if (controller.isShowCandidates(row, col)) println("("+row+","+col+"):"+controller.available(row, col).toList.sorted)
    */
  }

  //override def update: Unit = println(controller.printGameBoard())
}
