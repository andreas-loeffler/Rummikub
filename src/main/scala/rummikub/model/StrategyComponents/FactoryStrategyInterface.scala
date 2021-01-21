package Rummikub.model.StrategyComponents

import Rummikub.model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet

trait FactoryStrategyInterface {
  def setSize(): GameBoardNet
}
