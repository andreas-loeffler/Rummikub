package model

case class Player(name: Option[String] = None, tiles:Int = 0) {
  def isEmpty:Boolean = name.isEmpty
}
