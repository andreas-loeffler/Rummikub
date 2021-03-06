package Rummikub.model.gameBoardComponents.gameBoardBaseImpl

import Rummikub.model.gameBoardComponents.FieldInterface

case class Field(color: Char, value: Int) extends FieldInterface{
  def isEmpty: Boolean = (color == ' ' && value == 0)

  override def toString: String = color.toString + value
}
