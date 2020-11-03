package rummikub.model

// not working

import org.scalatest._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class RowSpec extends AnyWordSpec with Matchers {
  "A Row" when {
    "not set any value " should {
      val emptyRow = Row(null)
      "have value 0" in {
        emptyRow.tile should be(null)
      }
    }
    "set to a specific tile " should {
      val nonEmptyRow = Row("Yellow5")
      "return that value" in{
        nonEmptyRow.tile should be("Yellow5")
      }
    }
  }
}
