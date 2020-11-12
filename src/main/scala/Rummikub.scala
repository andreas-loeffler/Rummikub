
import rummikub.model.{GameBoardNet,Player,Field}
import gameboard.TextUI
import scala.io.StdIn.readLine

object Rummikub {
  val gameBoardNet = new GameBoardNet
  gameBoardNet.resetValues()

  def main(args: Array[String]): Unit ={
    gameBoardNet.insertTile(0,0,'Y',2)//valid
    gameBoardNet.insertTile(1,2,'Y',3)//valid
    gameBoardNet.insertTile(1,3,'Y',4)//valid
    gameBoardNet.insertTile(1,4,'B',5)//not valid
    gameBoardNet.insertTile(1,13,'B',4)//valid
    gameBoardNet.insertTile(1,11,'B',4)
    gameBoardNet.printGameboard()

  }

}
