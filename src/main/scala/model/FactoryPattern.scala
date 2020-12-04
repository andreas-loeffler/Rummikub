package model

trait FactoryStrategy {
  def setSize(): GameBoardNet
}

private class BigGameBoardNetStrategy extends FactoryStrategy {
  override def setSize(): GameBoardNet = {
    val gameBoardNet = new GameBoardNet(xS = 22, yS = 28)
    gameBoardNet.fillTiles()
    gameBoardNet
  }
}

private class SmallGameBoardNetStrategy extends FactoryStrategy {
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
