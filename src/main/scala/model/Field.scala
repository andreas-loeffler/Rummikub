package model

case class Field(color: Char, value: Int) {
  def isEmpty: Boolean = color == ' '

  def setField(f: Field): Field = if (!isEmpty) {
    copy(f.color, f.value)
  } else this

  override def toString: String = color.toString + value


}
