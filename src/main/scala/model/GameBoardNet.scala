package model

import scala.util.{Failure, Success, Try}

case class GameBoardNet() {

  class UpdatableVector2[T](v: Vector[Vector[T]]) {
    def updated2(c1: Int, c2: Int)(newVal: T) =
      v.updated(c1, v(c1).updated(c2, newVal))
  }

  implicit def vectorToUpdatableVector2[T](v: Vector[Vector[T]]) = new UpdatableVector2(v)

  var gameboard: Vector[Vector[Field]] = Vector.fill(10, 14)(Field(' ', 0))

  def printGameboard(): String = {
    val sb = new StringBuilder
    for (x <- gameboard.indices) {
      for (y <- 0 until 14) {
        sb.append(gameboard(x)(y).color).append(gameboard(x)(y).value).append("|")
      }
      sb.append("\n")
    }
    sb.toString()
  }


  def resetValues(): Boolean = {
    for (x <- gameboard.indices) {
      for (y <- 0 until 14) {
        gameboard = gameboard.updated2(x, y)(Field(' ', 0))
      }
    }
    true
  }

  def function1(x: Int, y: Int): Boolean = {
    x >= 0 && x < gameboard.length && y > 0 && y < gameboard(x).length - 1
  }

  def validInsertBetween(x: Int, y: Int): Boolean = {
    (gameboard(x)(y).value < gameboard(x)(y + 1).value) && (gameboard(x)(y).value > gameboard(x)(y - 1).value)
  }

  def validInsertATNT0(x: Int, y: Int): Boolean = {
    (gameboard(x)(y).value >= gameboard(x)(y - 1).value) && gameboard(x)(y + 1).value == 0
  }

  def validInsertP0a(x: Int, y: Int): Boolean = {
    x < gameboard.length && y < gameboard(x).length - 1
  }

  def validInsertP0b(x: Int, y: Int): Boolean = {
    gameboard(x)(y).value >= gameboard(x)(y + 1).value
  }

  def isNumberValid(x: Int, y: Int): Boolean = {
    if (function1(x, y)) {
      if (validInsertBetween(x, y))
        return true
      else if (validInsertATNT0(x, y))
        return true
      else
        return false
    }
    else if (validInsertP0a(x, y)) {
      if (validInsertP0b(x, y))
        return true
    }
    true
  }

  //Color checkers


  def function1Color(x: Int, y: Int): Boolean = {
    x >= 0 && x < gameboard.length && y > 0 && y < gameboard(x).length - 1
  }


  def function2Color(x: Int, y: Int): Boolean = {
    (gameboard(x)(y).color == gameboard(x)(y + 1).color) && (gameboard(x)(y).color == gameboard(x)(y - 1).color) || gameboard(x)(y + 1).color == ' ' && gameboard(x)(y - 1).color == ' '
  }


  def function3Color(x: Int, y: Int): Boolean = {
    (gameboard(x)(y).color.equals(gameboard(x)(y + 1).color) || gameboard(x)(y + 1).color.equals(' ')) && (gameboard(x)(y).color.equals(gameboard(x)(y - 1).color) || gameboard(x)(y - 1).color.equals(' '))
  }

  //check at posi 0
  def function4Color(x: Int, y: Int): Boolean = {
    x < gameboard.length && y < gameboard(x).length - 1
  }

  def function5Color(x: Int, y: Int): Boolean = {
    gameboard(x)(y).color == gameboard(x)(y + 1).color || gameboard(x)(y + 1).color == ' '
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
    if (isNumberValid(x, y) && isColorValid(x, y)) {
      return true
    }
    false
  }

  def insertTile(x: Int, y: Int, c: Char, v: Int): Boolean = {
    val sb = new StringBuilder
    if (x > 9 && y > 13) {
      println("Invalid position!")
      return false
    }

    gameboard = gameboard.updated2(x, y)(Field(c, v))

    Try(
      allValid(x, y)
    ) match {
      case Success(true) => println("Success!")
      case Success(false) => println("Input at this Position " + x + "," + y + " not valid, re-check your move!"); gameboard = gameboard.updated2(x, y)(Field(' ', 0)); return false
    }


    sb.append(gameboard(x)(y).color).append(gameboard(x)(y).value)
    println(sb.toString())
    true
  }
}
