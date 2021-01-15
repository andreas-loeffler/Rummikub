package rummikub.controller.controllerComponents.controllerBaseImpl

import com.google.inject.{Guice, Inject, Injector}
import rummikub.RummikubModule
import rummikub.controller.controllerComponents.{BigGameboard, ControllerInterface, FieldChanged, PlayersChanged, SmallGameboard}
import rummikub.model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet
import rummikub.model.StrategyComponents.strategyBaseImpl.{FactoryStrategy, NumPlayersStrategy, StatePattern}
import rummikub.model.gameBoardComponents.GameBoardInterface
import rummikub.util.UndoManager


class Controller @Inject()(var gameBoardNet: GameBoardInterface) extends ControllerInterface {

  private val undoManager = new UndoManager
  val injector: Injector = Guice.createInjector(new RummikubModule)

  def resetGameBoard(): Unit = {
    for (x <- 0 until gameBoardNet.getYSize()) {
      for (y <- 0 until gameBoardNet.getXSize()) {
        gameBoardNet = gameBoardNet.resetValues(x, y)
      }


    }
    gameBoardNet = new GameBoardNet()
    publish(new FieldChanged)
  }


  def printGameBoard(): String = {
    gameBoardNet.printGameboard()
  }

  def handle(): Unit = {
    val state = StatePattern
    state.handle(gameBoardNet.isEmptyBoard())
  }

  def onePlayerOpt(player1: String): Unit = {
    gameBoardNet = NumPlayersStrategy.playerN("player1", Some(player1), None, None, gameBoardNet.returnGB)
    publish(new PlayersChanged(player1, "available", "available"))
  }


  def twoPlayerOpt(player1: String, player2: String): Unit = {
    gameBoardNet = NumPlayersStrategy.playerN("player2", Some(player1), Some(player2), None, gameBoardNet.returnGB)
    publish(new PlayersChanged(player1, player2, "available"))
  }

  def threePlayerOpt(player1: String, player2: String, player3: String): Unit = {
    gameBoardNet = NumPlayersStrategy.playerN("player3", Some(player1), Some(player2), Some(player3), gameBoardNet.returnGB)
    publish(new PlayersChanged(player1, player2, player3))
  }


  def bigGB(): Unit = {
    gameBoardNet = FactoryStrategy("big")
    publish(new BigGameboard(22))
  }

  def smallGB(): Unit = {
    gameBoardNet = FactoryStrategy("small")
    publish(new SmallGameboard(11))
  }

  def set(x: Int, y: Int, colorTile: Char, valueTile: Int): Unit = {
    undoManager.doStep(new SetCommand(x, y, colorTile, valueTile, this))
    publish(new FieldChanged)
  }

  def undo: Unit = {
    undoManager.undoStep
    publish(new FieldChanged)
  }

  def redo: Unit = {
    undoManager.redoStep
    publish(new FieldChanged)
  }

  def gBxSize: Int = gameBoardNet.getXSize()

  def gBySize: Int = gameBoardNet.getYSize()

  def getFieldColor(x: Int, y: Int): Char = gameBoardNet.getField(x, y).color

  def getFieldValue(x: Int, y: Int): Int = gameBoardNet.getField(x, y).value

  def getplayer1Name: Option[String] = gameBoardNet.getp1()

  def getplayer2Name: Option[String] = gameBoardNet.getp2()

  def getplayer3Name: Option[String] = gameBoardNet.getp3()

  def saveXml: Unit = {
    import rummikub.model.fileIOComponent.fileIXml.FileIo
    val fIO = new FileIo
    fIO.save(gameBoardNet)
  }

  def loadXml: Unit = {
    import rummikub.model.fileIOComponent.fileIXml.FileIo
    val fIO = new FileIo
    gameBoardNet = fIO.load
    publish(new FieldChanged)
  }

  def saveJson: Unit = {
    import rummikub.model.fileIOComponent.fileIOJson.FileIO
    val fIO = new FileIO
    fIO.save(gameBoardNet)
  }

  def loadJson: Unit = {
    import rummikub.model.fileIOComponent.fileIOJson.FileIO
    val fIO = new FileIO
    gameBoardNet = fIO.load
    publish(new FieldChanged)
  }

}
