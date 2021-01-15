package rummikub.model.fileIOComponent.fileIOJson

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import play.api.libs.json.Format.GenericFormat
import rummikub.model.fileIOComponent.FileIOInterface
import rummikub.model.gameBoardComponents.GameBoardInterface
import rummikub.RummikubModule
import play.api.libs.json._
import rummikub.model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet

import java.awt.Color
import java.io._
import scala.io.Source


class FileIO extends FileIOInterface {

  override def load: GameBoardInterface = {
    val fileIO = Source.fromFile("gameboard.json").getLines().mkString
    val injector = Guice.createInjector(new RummikubModule)
    var gameBoard: GameBoardInterface = injector.instance[GameBoardInterface](Names.named("normal"))
    val json = Json.parse(fileIO)
    gameBoard.renamePlayer1(Some((json \ "game" \ "player1").as[String]))
    gameBoard.renamePlayer2(Some((json \ "game" \ "player2").as[String]))
    gameBoard.renamePlayer3(Some((json \ "game" \ "player3").as[String]))
    for (index <- 0 until 14*11) {
      val x = (json \\ "yVal") (index).as[Int]
      val y = (json \\ "xVal") (index).as[Int]
      val color = (json \\ "color") (index).toString()
      val value = (json \\ "value") (index).as[Int]
      gameBoard = gameBoard.insertTileRaw(x, y, color.charAt(1), value)
    }
    gameBoard

  }

  override def save(gameboard: GameBoardInterface): Unit = {
    import java.io._
    val printWriter = new PrintWriter(new File("gameboard.json"))
    printWriter.write(Json.prettyPrint(gameboardToJson(gameboard)))
    printWriter.close()
    print("Done")
  }

  def gameboardToJson(game: GameBoardInterface) = {
    Json.obj(
      "game" -> Json.obj(
        "size" -> JsNumber(game.getXSize()),
        "player1" -> Json.toJson(game.getp1().toString),
        "player2" -> Json.toJson(game.getp2().toString),
        "player3" -> Json.toJson(game.getp3().toString),
        "fields" -> Json.toJson(
          for {
            xVal <- 0 until game.getXSize();
            yVal <- 0 until game.getYSize()
          } yield {
            Json.obj(
              "xVal" -> xVal,
              "yVal" -> yVal,
              "color" -> Json.toJson(game.getField(xVal, yVal).color.toString),
              "value" -> Json.toJson(game.getField(xVal, yVal).value)
            )
          }
        )
      )
    )
  }

}
