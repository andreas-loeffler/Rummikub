package rummikub.model.fileIOComponent.fileIXml

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import play.api.libs.json
import play.api.libs.json.JsValue
import rummikub.RummikubModule
import rummikub.model.fileIOComponent.FileIOInterface
import rummikub.model.gameBoardComponents.GameBoardInterface
import rummikub.model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet

import scala.xml.{Elem, PrettyPrinter}
import java.io._

class FileIo extends FileIOInterface {

  override def load: GameBoardInterface = {
    val file = scala.xml.XML.loadFile("save.xml")
    val injector = Guice.createInjector(new RummikubModule)
    var game: GameBoardInterface = injector.instance[GameBoardInterface](Names.named("normal"))
    game.renamePlayer1(Some((file \\ "player1").toString))
    game.renamePlayer2(Some((file \\ "player2").toString))
    game.renamePlayer3(Some((file \\ "player3").toString))
    val fielNodes = (file \\ "field")
    for (field <- fielNodes) {
      val x = (field \ "@yVal").text.toInt
      val y = (field \ "@xVal").text.toInt
      val color = (field \ "@color").text.toString
      val valueTile = (field \ "@val").text.toInt
      game = game.insertTileRaw(x, y, color.charAt(0), valueTile)
    }
    game

  }

  override def save(gameboard: GameBoardInterface): Unit = {
    val printW = new PrintWriter(new File("save.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gameBoardToXml(gameboard))
    printW.write(xml)
    printW.close
  }

  def gameBoardToXml(game: GameBoardInterface) = {
    <game xSize={game.getXSize.toString}>
      {for {
      //xVal max -> 13, yVal max -> 10
      xVal <- 0 until game.getXSize
      yVal <- 0 until game.getYSize
    } yield fieldToXml(game, xVal, yVal)}
    </game>
  }

  def fieldToXml(interface: GameBoardInterface, xVal: Int, yVal: Int) = {
    <field xVal={xVal.toString} yVal={yVal.toString} color={interface.getField(xVal, yVal).color.toString}
           val={interface.getField(xVal, yVal).value.toString}>
      {interface.getField(xVal, yVal).value}
    </field>

      <player1 p1={interface.getp1.toString}>
    </player1>

      <player2 p2={interface.getp2.toString}>
    </player2>

    <player3 p3={interface.getp3.toString}>
    </player3>
  }
}
