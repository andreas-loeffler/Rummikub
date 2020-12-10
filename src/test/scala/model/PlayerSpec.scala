package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {
  "A player" when {
    "new " should {
      val player = Player(Some("testName"))
      "have a name " in {
        player.name.get should be("testName")
      }
      "have a representation with a String" in {
        player.toString should be("testName")
      }
    }
  }
}
