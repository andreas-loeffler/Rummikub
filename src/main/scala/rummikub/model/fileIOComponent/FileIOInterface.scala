package rummikub.model.fileIOComponent

import rummikub.model.gameBoardComponents.GameBoardInterface

trait FileIOInterface {
  def load: GameBoardInterface
  def save(gameboard: GameBoardInterface): Unit
}
