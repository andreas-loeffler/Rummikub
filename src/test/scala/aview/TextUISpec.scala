package aview

import controller.Controller
import model.GameBoardNet
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TextUISpec extends AnyWordSpec with Matchers {
  "A TextUI" when {
    "new " should {
      val controller = new Controller(new GameBoardNet)
      val txt = new TextUI(controller)
      "print the gameboard" in {
        txt.userInput("print") should be(true)
      }
      "insert a tile" in {
        txt.userInput("insert 0 0 Y 5") should be(true)
      }
      "quit" in {
        txt.userInput("quit").toString startsWith "B"
      }
      "reset the values" in {
        txt.userInput("reset") should be(true)
      }
      "throw an error" in {
        txt.userInput("test").toString startsWith ("I")
      }
      "throw another error" in {
        txt.userInput("insert 3 4").toString startsWith ("I")
      }
      "throw also an error" in {
        txt.userInput("insert a b Y 4").toString startsWith ("I")
      }
      "return true " in{
        txt.userInput("create") should be(true)
      }

    }

  }
}