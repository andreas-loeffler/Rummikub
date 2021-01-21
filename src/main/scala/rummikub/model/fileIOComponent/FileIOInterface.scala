package Rummikub.model.fileIOComponent

import Rummikub.model.gameBoardComponents.GameBoardInterface

trait FileIOInterface {
  def load: GameBoardInterface
  def save(gameboard: GameBoardInterface): Unit
}
