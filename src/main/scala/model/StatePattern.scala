package model

object State {
  def handle(x: Boolean): Unit = {
      x match {
        case true => onState
        case false => offState
      }
  }

  def onState = println("No current game")

  def offState = println("1 Game live, pls wait")
}