package rummikub.model

case class Field(color: Char, value:Int) {
  def isEmpty: Boolean = color.equals(null)

  override def toString: String = color.toString + value


}
