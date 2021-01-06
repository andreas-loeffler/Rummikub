package controller.controllerComponents.controllerBaseImpl

import util.Command

class SetCommand(x: Int, y: Int, colorTile: Char, valueTile: Int, controller: Controller) extends Command {
  override def doStep: Unit = controller.gameBoardNet = controller.gameBoardNet.insertTile(x, y, colorTile, valueTile)

  override def undoStep: Unit = controller.gameBoardNet = controller.gameBoardNet.insertTile(x, y, ' ', 0)

  override def redoStep: Unit = controller.gameBoardNet = controller.gameBoardNet.insertTile(x, y, colorTile, valueTile)
}
