
import rummikub.model.{GameBoardNet,Player,Field}
import gameboard.TextUI
import scala.io.StdIn.readLine

object Rummikub {
  //val gameBoardNet = new GameBoardNet(10,14)
  //gameBoardNet.resetValues()
  val tui = new TextUI

  def main(args: Array[String]): Unit ={
    /*gameBoardNet.insertTile(0,0,'Y',5)//valid
    gameBoardNet.insertTile(0, 1, 'B', 4)
    gameBoardNet.insertTile(1,2,'Y',3)//valid
    gameBoardNet.insertTile(1,3,'Y',4)//valid
    gameBoardNet.insertTile(1,4,'B',5)//not valid
    gameBoardNet.insertTile(1,13,'B',4)//valid
    gameBoardNet.insertTile(1,11,'B',4)//valid
    gameBoardNet.insertTile(1,4,'Y',5)//valid
    gameBoardNet.insertTile(1,10,'B',4)//not valid
    gameBoardNet.insertTile(1,10,'B',3)//valid
    gameBoardNet.insertTile(3,10,'P',14)//valid
    gameBoardNet.insertTile(3,100,'P',14)//valid
    gameBoardNet.printGameboard()
*/
    var input: String = ""
    do{
      print(">>")
      input = readLine()
      tui.userInput(input)
    }while (input != "quit")


  }

}
