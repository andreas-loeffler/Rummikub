package rummikub.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {
  "A player" should {
    "have a name" in {
      Player("testName").name startsWith "testName"
    }
    "have a representation with a String" in {
      Player("testName").toString startsWith "testName"
    }
  }
}
