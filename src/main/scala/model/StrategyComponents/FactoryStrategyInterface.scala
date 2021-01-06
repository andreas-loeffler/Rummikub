package model.StrategyComponents

import model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet

trait FactoryStrategyInterface {
  def setSize(): GameBoardNet
}
