package rummikub.model

import scala.util.{Try, Success, Failure}


case class GameBoardNet() {

  class UpdatableVector2[T](v: Vector[Vector[T]]) {
    def updated2(c1: Int, c2: Int)(newVal: T) =
      v.updated(c1, v(c1).updated(c2, newVal))
  }

  implicit def vectorToUpdatableVector2[T](v: Vector[Vector[T]]) = new UpdatableVector2(v)

  var gameboard = Vector.fill(10, 14)(Field(' ', 0))
  var a = gameboard.updated2(0, 1)(Field('Y', 3))
  println(a)
  a = a.updated2(0, 0)(Field('Y', 2))

  def printGameboard(): Unit = {
    val sb = new StringBuilder
    for (x <- gameboard.indices) {
      for (y <- 0 until 14) {
        sb.append(gameboard(x)(y).color).append(gameboard(x)(y).value).append("|")
      }
      sb.append("\n")
    }
    print(sb)
  }


  def resetValues(): Unit = {
    for (x <- gameboard.indices) {
      for (y <- 0 until 14) {
        gameboard = gameboard.updated2(x, y)(Field(' ', 0))
      }
    }
  }

  //cgeck if insert is bwtween two tiles
  def isValidB2T(x: Int, y: Int): Boolean = {
    if (x >= 0 && x < gameboard.length && y > 0 && y < gameboard(x).length - 1) {
      if (((gameboard(x)(y).value) < (gameboard(x)(y + 1).value)) && ((gameboard(x)(y).value) > (gameboard(x)(y - 1).value)))
        return true
    }
    false
  }

  //check if insert is after tile and next tile is 0
  def isValidATNT0(x: Int, y: Int): Boolean = {
    if (x >= 0 && x < gameboard.length && y > 0 && y < gameboard(x).length - 1) {
      if ((gameboard(x)(y).value >= gameboard(x)(y - 1).value) && gameboard(x)(y + 1).value == 0)
        return true
    }
    false
  }

  //check at posi 0
  def isValidInitPosi(x: Int, y: Int): Boolean = {
    if (x < gameboard.length && y < gameboard(x).length - 1) {
      if (((gameboard(x)(y).value) >= (gameboard(x)(y + 1).value))) {
        return true
      }
    }
    false
  }

  //check at last posi
  def isValidLastPosi(x: Int, y: Int): Boolean = {

    if (((gameboard(x)(y).value) >= (gameboard(x)(y + 1).value))) {
      return true

    }
    false
  }

  //Color checkers
  //if x is greater and less than size and y is greater than 0 and -1 of length
  def function1Color(x: Int, y: Int): Boolean = {
    return x >= 0 && x < gameboard.length && y > 0 && y < gameboard(x).length - 1
  }

  //insert between two tiles if color of next is same and color of before is same
  def function2Color(x: Int, y: Int): Boolean = {
    return ((gameboard(x)(y).color) == (gameboard(x)(y + 1).color)) && ((gameboard(x)(y).color) == (gameboard(x)(y - 1).color)) || gameboard(x)(y + 1).color == (' ') && gameboard(x)(y - 1).color == (' ')
  }

  //insert if color of next is same or none and color of last is same or none
  def function3Color(x: Int, y: Int): Boolean = {
    return (gameboard(x)(y).color.equals(gameboard(x)(y + 1).color) || gameboard(x)(y + 1).color.equals(' ')) && (gameboard(x)(y).color.equals(gameboard(x)(y - 1).color) || gameboard(x)(y - 1).color.equals(' '))
  }

  //check at posi 0
  def function4Color(x: Int, y: Int): Boolean = {
    return x < gameboard.length && y < gameboard(x).length - 1
  }

  def function5Color(x: Int, y: Int): Boolean = {
    return gameboard(x)(y).color == (gameboard(x)(y + 1).color) || gameboard(x)(y + 1).color == (' ')
  }

  def isColorValid(x: Int, y: Int): Boolean = {
    if (function1Color(x, y)) {
      if (function2Color(x, y))
        return true
      else if (function3Color(x, y))
        return true
      else
        return false
    }
    else if (function4Color(x, y)) {
      if (function5Color(x, y))
        return true
    }
    true
  }


  def allValid(x: Int, y: Int): Boolean = {
    if (isValidB2T(x, y) || isValidATNT0(x, y) || isValidInitPosi(x, y) || isValidLastPosi(x, y)) {
      if (isColorValid(x, y))
        return true
    }
    false
  }

  def insertTile(x: Int, y: Int, c: Char, v: Int): Unit = {
    val sb = new StringBuilder
    gameboard = gameboard.updated2(x, y)(Field(c, v))
    Try(
      allValid(x, y)
    ) match {
      case Success(true) => println("Success!")
      case Success(false) => println("Input at this Position " + x + "," + y + " not valid, re-check your move!"); gameboard = gameboard.updated2(x, y)(Field(' ', 0))
      case Failure(exception) => println("Wrong input, position is not valid")
    }


    sb.append(gameboard(x)(y).color).append(gameboard(x)(y).value)
    println(sb.toString())

  }
}