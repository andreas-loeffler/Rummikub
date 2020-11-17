package rummikub.model


case class GameBoardNet() {
  val gameboard: Vector[Vector[Field]] = Vector.fill(10, 14)(Field(' ', 0))

  def resetValues(): Unit = {
    for (x <- gameboard.indices) {
      for (y <- 0 until 14) {
        gameboard(x)(y).setField(new Field(' ',0))
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
    //if x is greater and less than size and y is greater than 0 and less than  -1 of length
    if (x >= 0 && x < gameboard.length && y > 0 && y < gameboard(x).length - 1) {
      //insert between two tiles
      if (((gameboard(x)(y).value) < (gameboard(x)(y + 1).value)) && ((gameboard(x)(y).value) > (gameboard(x)(y - 1).value))) {
        return true
      } //insert after tile and next tile is 0
      else if ((gameboard(x)(y).value >= gameboard(x)(y - 1).value) && gameboard(x)(y + 1).value == 0) {
        return true
      } //return false if nothing works
      else {
        return false
      }
    } //check at posi 0
    else if (x < gameboard.length && y < gameboard(x).length - 1) {
      if (((gameboard(x)(y).value) >= (gameboard(x)(y + 1).value))) {
        return true
      }
    } //check at last posi
    /*else {
      if (((gameboard(x)(y).value) >= (gameboard(x)(y - 1).value))) {
        return true
      }
      }*/

    true
  }


  def isColorValid(x: Int, y: Int): Boolean = {
    //if x is greater and less than size and y is greater than 0 and -1 of length
    if (x >= 0 && x < gameboard.length && y > 0 && y < gameboard(x).length - 1) {
      //insert between two tiles if color of next is same and color of before is same
      if (((gameboard(x)(y).color) == (gameboard(x)(y + 1).color)) && ((gameboard(x)(y).color) == (gameboard(x)(y - 1).color)) || gameboard(x)(y + 1).color == (' ') && gameboard(x)(y - 1).color == (' ')) {
        return true
      } //insert if color of next is same or none and color of last is same or none
      else if ((gameboard(x)(y).color.equals(gameboard(x)(y + 1).color) || gameboard(x)(y + 1).color.equals(' ')) && (gameboard(x)(y).color.equals(gameboard(x)(y - 1).color) || gameboard(x)(y - 1).color.equals(' '))) {
        return true
      } else {
        return false
      }
    } //check at posi 0
    else if (x < gameboard.length && y < gameboard(x).length - 1) {
      if (gameboard(x)(y).color == (gameboard(x)(y + 1).color) || gameboard(x)(y + 1).color == (' ')) {
        return true
      }
    } //check at last posi
    /*else {
      if (gameboard(x)(y).color.equals(gameboard(x)(y - 1).color) || gameboard(x)(y - 1).color.equals(' ')) {
        return true
      }
    }*/
    true
  }


  def insertTile(x: Int, y: Int, c: Char, v: Int): Unit = {
    val sb = new StringBuilder
    try {
      val field: Field =  new Field(c,v)
      gameboard(x)(y).copy(c,v)
    } catch {
      case e: ArrayIndexOutOfBoundsException => println("Wrong input, position is not valid")
        return
    }
    if (!isValid(x, y)) {
      println("Input at this Position " + x + "," + y + " not valid, re-check your move!")
      gameboard(x)(y).setField(new Field(' ',0))
      return
    }
    if (!isColorValid(x, y)) {
      println("Input at this Position " + x + "," + y + " not valid, re-check your move!")
      gameboard(x)(y).setField(new Field(' ',0))
      return
    }
    sb.append("Valid move: ")
    sb.append(gameboard(x)(y).color).append(gameboard(x)(y).value)
    println(sb.toString())


  }
}