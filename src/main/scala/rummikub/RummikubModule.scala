package Rummikub

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import controller.controllerComponents.ControllerInterface
import model.fileIOComponent.FileIOInterface
import model.gameBoardComponents.GameBoardInterface
import model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet

class RummikubModule extends AbstractModule with ScalaModule{

  val defaultXsize:Int = 11;
  val defaultYsize:Int = 14;

  override def configure() = {
    bindConstant().annotatedWith(Names.named("DefaultXSize")).to(defaultXsize)
    bindConstant().annotatedWith(Names.named("DefaultYSize")).to(defaultYsize)
    bind[GameBoardInterface].to[GameBoardNet]
    bind[ControllerInterface].to[controller.controllerComponents.controllerBaseImpl.Controller]
    bind[GameBoardInterface].annotatedWithName("big").toInstance(new GameBoardNet(22,28))
    bind[GameBoardInterface].annotatedWithName("normal").toInstance(new GameBoardNet(11,14))
    bind[FileIOInterface].to[model.fileIOComponent.fileIOJson.FileIO]
    //bind[FileIOInterface].to[rummikub.model.fileIOComponent.fileIXml.FileIo]

  }

}
