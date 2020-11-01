package rummikub.model

case class Row(tile: String) {
  def isEmpty: Boolean = tile != null

}
