package rummikub.model.fileIOComponent.fileIOJson

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import play.api.libs.json.Format.GenericFormat
import rummikub.model.fileIOComponent.FileIOInterface
import rummikub.model.gameBoardComponents.GameBoardInterface
import rummikub.RummikubModule
import play.api.libs.json._

import java.awt.Color
import java.io._
import scala.io.Source


class FileIO extends FileIOInterface{
  override def load: GameBoardInterface = {

  }

  override def save(gameboard: GameBoardInterface): Unit = {
    import java.io._
    val printWriter = new PrintWriter(new File("gameboard.json"))
    printWriter.write(Json.prettyPrint(gameboardToJson(gameboard)))
  }

  def gameboardToJson(game: GameBoardInterface) = {
    Json.obj(
      "game" -> Json.obj(
        "size" -> JsNumber(game.getXSize()),
        "player" -> Json.toJson(game.getAllPlayer()),
        "fields" -> Json.toJson(
          for{
              xVal <- 0 until game.getXSize();
              yVal <- 0 until game.getYSize()
          } yield {
            Json.obj(
              "xVal" -> xVal,
              "yVal" -> yVal,
              "field"-> Json.toJson(game.getField(xVal,yVal))
            )
          }
        )
      )
    )
  }

}
