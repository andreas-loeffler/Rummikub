package rummikub.model.StrategyComponents

import rummikub.model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet

trait FactoryStrategyInterface {
  def setSize(): GameBoardNet
}
