package rummikub.model

case class Field(tile: String) {
  def isEmpty: Boolean = tile == null

  override def toString: String = tile
}
