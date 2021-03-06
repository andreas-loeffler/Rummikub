package Rummikub.model.StrategyComponents.strategyBaseImpl

import Rummikub.model.StrategyComponents.StateInterface

object StatePattern extends StateInterface {
  def handle(x: Boolean): Unit = {
    x match {
      case true => offState
      case false => onState
    }
  }

  def onState = println("1 Game live, pls wait")

  def offState = println("No current game")
}
