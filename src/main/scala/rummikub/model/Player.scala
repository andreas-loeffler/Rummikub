package rummikub.model

case class Player(name1: String, name2: String) {
  override def toString: String = name1 + name2
}
