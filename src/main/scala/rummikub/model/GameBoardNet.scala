package rummikub.model


case class GameBoardNet() {
  var gameboard = Array.ofDim[Field](10, 14)

  def resetValues(): Unit = {
    for (x <- gameboard.indices) {
      for (y <- 0 until 14) {
        gameboard(x)(y) = new Field(' ', 0)
      }
    }
  }

  def printGameboard(): Unit = {
    val sb = new StringBuilder
    for (x <- gameboard.indices) {
      for (y <- 0 until 14) {
        sb.append(gameboard(x)(y)).append("|")
      }
      sb.append("\n")
    }
    print(sb)
  }

  def isValid(x: Int, y: Int): Boolean = {
    if (x > 0 && x < gameboard.length && y > 0 && y < gameboard(x).length - 1) {
      if (((gameboard(x)(y).value) < (gameboard(x)(y + 1).value)) && ((gameboard(x)(y).value) > (gameboard(x)(y - 1).value))) {
        return true
      }
      else if ((gameboard(x)(y).value >= gameboard(x)(y - 1).value) && gameboard(x)(y + 1).value == 0) {
        return true
      } else {
        print(gameboard(x)(y).value, gameboard(x)(y - 1).value, gameboard(x)(y + 1).value)
        return false
      }
    } //check at posi 0
    else if (x < gameboard.length && y < gameboard(x).length - 1) {
      if (((gameboard(x)(y).value) >= (gameboard(x)(y + 1).value))) {
        return true
      }
    } //check at last posi
    else {
      if (((gameboard(x)(y).value) >= (gameboard(x)(y - 1).value))) {
        return true
      }
    }
    false
  }


  def isColorValid(x: Int, y: Int): Boolean = {
    if (x > 0 && x < gameboard.length && y > 0 && y < gameboard(x).length - 1) {
      if (((gameboard(x)(y).color) == (gameboard(x)(y + 1).color)) && ((gameboard(x)(y).color) == (gameboard(x)(y - 1).color)) || gameboard(x)(y + 1).color.equals(' ') || gameboard(x)(y - 1).color.equals(' ')) {
        return true
      }
      else if ((gameboard(x)(y).color.equals(gameboard(x)(y + 1).color) || gameboard(x)(y + 1).color.equals(' ')) && (gameboard(x)(y).color.equals(gameboard(x)(y - 1).color) || gameboard(x)(y - 1).color.equals(' '))) {
        return true
      } else {
        print(gameboard(x)(y).color, gameboard(x)(y - 1).color, gameboard(x)(y + 1).color)
        return false
      }
    } //check at posi 0
    else if (x < gameboard.length && y < gameboard(x).length - 1) {
      if (gameboard(x)(y).color.equals(gameboard(x)(y + 1).color) || gameboard(x)(y + 1).color.equals(' ')) {
        return true
      }
    } //check at last posi
    else {
      if (gameboard(x)(y).color.equals(gameboard(x)(y - 1).color) || gameboard(x)(y - 1).color.equals(' ')) {
        return true
      }
    }
    false
  }


  def insertTile(x: Int, y: Int, c: Char, v: Int): Unit = {
    val sb = new StringBuilder
    gameboard(x)(y) = Field(c, v)
    if (!isValid(x, y)) {
      if (!isColorValid(x, y)) {
        println("Input at this Position " + x + "," + y + " not valid, re-check your move!")
        gameboard(x)(y) = Field(' ', 0)

      }
      return
    }

    sb.append("Valid move: ")
    sb.append(gameboard(x)(y).color).append(gameboard(x)(y).value)
    println(sb.toString())
  }

}
