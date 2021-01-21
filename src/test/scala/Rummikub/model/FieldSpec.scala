package Rummikub.model

import Rummikub.model.gameBoardComponents.gameBoardBaseImpl.Field
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec with Matchers {
  "A Field" when {
    "new " should {
      val field = Field(' ',0)
      "not have a string" in {
        field.color should be(' ')
      }
      "return a string representation " in {
        field.toString should be(" 0")
      }
      "be empty" in {
        field.isEmpty should be(true)
      }
      "when unapplied" in{
        Field.unapply(field).get should be(' ',0)
      }
    }
    "set to a specific string " should {
      val nonEmptyField = Field('Y',5)
      "return that tile" in {
        nonEmptyField.color should be ('Y')
      }
      "have a name " in {
        nonEmptyField.toString should be("Y5")
      }
      "not be empty" in {
        nonEmptyField.isEmpty should be(false)
      }
      "when applied" in{
        Field.apply('Y',5).toString should be("Y5")
      }
    }
  }
}
