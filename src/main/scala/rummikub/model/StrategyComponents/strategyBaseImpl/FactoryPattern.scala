package Rummikub.model.StrategyComponents.strategyBaseImpl

import Rummikub.model.StrategyComponents.FactoryStrategyInterface
import Rummikub.model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet


private class BigGameBoardNetStrategy extends FactoryStrategyInterface {
  override def setSize(): GameBoardNet = {
    val gameBoardNet = new GameBoardNet(xS = 22, yS = 28)
    gameBoardNet.fillTiles()
    gameBoardNet
  }
}

private class SmallGameBoardNetStrategy extends FactoryStrategyInterface {
  override def setSize(): GameBoardNet = {
    val gameBoardNet = new GameBoardNet(xS = 11, yS = 14)
    gameBoardNet.fillTiles()
    gameBoardNet
  }
}

object FactoryStrategy {
  def apply(kind: String): GameBoardNet = kind match {
    case "big" => new BigGameBoardNetStrategy().setSize()
    case "small" => new SmallGameBoardNetStrategy().setSize()
  }
}
