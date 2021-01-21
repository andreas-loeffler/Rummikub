package Rummikub.model

import Rummikub.model.StrategyComponents.strategyBaseImpl.FactoryStrategy
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FactoryPatternSpec extends AnyWordSpec with Matchers{
  "A FactoryStrategy " should{
    "create a small Gameboard " in {
      val factoryStrategy = FactoryStrategy("small")
    }
    "create a bigger Gameboard " in{
      val factoryStrategyBig =  FactoryStrategy("big")
    }
  }
}
