package rummikub.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest._

class PlayerSpec extends AnyWordSpec with Matchers {
  "A player" should {
    "have a name" in {
      Player("testName").name should be("testName")
    }
    "have a representation with a String" in {
      Player("testName").toString should be("testName")
    }
  }
}
