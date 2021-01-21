package Rummikub.model.playerComponents.playerBaseImpl

import Rummikub.model.playerComponents.PlayerInterface

case class Player(name: Option[String] = None, tiles: Int = 0) extends PlayerInterface{
  def isEmpty: Boolean = name.isEmpty

  override def toString: String = name.get
}
