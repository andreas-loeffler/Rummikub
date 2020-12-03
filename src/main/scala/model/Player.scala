package model

case class Player(name: String = null, tiles:Int = 0) {
  override def toString: String = name
}
