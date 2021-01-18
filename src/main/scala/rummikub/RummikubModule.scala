package rummikub

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import rummikub.controller.controllerComponents.ControllerInterface
import rummikub.model.fileIOComponent.FileIOInterface
import rummikub.model.gameBoardComponents.GameBoardInterface
import rummikub.model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet

class RummikubModule extends AbstractModule with ScalaModule{

  val defaultXsize:Int = 11;
  val defaultYsize:Int = 14;

  override def configure() = {
    bindConstant().annotatedWith(Names.named("DefaultXSize")).to(defaultXsize)
    bindConstant().annotatedWith(Names.named("DefaultYSize")).to(defaultYsize)
    bind[GameBoardInterface].to[GameBoardNet]
    bind[ControllerInterface].to[rummikub.controller.controllerComponents.controllerBaseImpl.Controller]
    bind[GameBoardInterface].annotatedWithName("big").toInstance(new GameBoardNet(22,28))
    bind[GameBoardInterface].annotatedWithName("normal").toInstance(new GameBoardNet(11,14))
    bind[FileIOInterface].to[rummikub.model.fileIOComponent.fileIOJson.FileIO]
    //bind[FileIOInterface].to[rummikub.model.fileIOComponent.fileIXml.FileIo]

  }

}
