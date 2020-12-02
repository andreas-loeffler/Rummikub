package model

case class Field(color: Char, value: Int) {
  def isEmpty: Boolean = (color == ' ' && value == 0)

  override def toString: String = color.toString + value
}
