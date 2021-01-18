package rummikub.model.StrategyComponents

trait StateInterface {
  def handle(x: Boolean): Unit

  def onState: Unit

  def offState: Unit


}
