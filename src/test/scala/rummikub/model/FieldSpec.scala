package rummikub.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec with Matchers {
  "A Field" when {
    "new " should {
      val field = Field(null)
      "not have a string" in {
        field.tile should be(null)
      }
      "return a string representation " in {
        field.toString should be(null)
      }
      "be empty" in {
        field.isEmpty should be(true)
      }
      "when unapplied" in{
        Field.unapply(field).get should be(null)
      }
    }
    "set to a specific string " should {
      val nonEmptyField = Field("Yellow5")
      "return that tile" in {
        nonEmptyField.tile startsWith "Yellow5"
      }
      "have a name " in {
        nonEmptyField.toString startsWith "Yellow"
      }
      "not be empty" in {
        nonEmptyField.isEmpty should be(false)
      }
      "when applied" in{
        Field.apply("Yellow5").toString should be("Yellow5")
      }
    }
  }
}
