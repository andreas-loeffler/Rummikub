package rummikub.model

object GameBoar{
  def main(args: Array[String]): Unit = {
    println("Wanna play a game?\n")
    println("yeah")
    println(gameBoard("Andreas", "Pascal", 104))
  }

  case class gameBoard(name1: String, name2: String, tokenStartAmount: Int) {
    override def toString: String = "(" + tokenStartAmount + ")" + sb

    val value: Array[Array[Int]] = Array.ofDim[Int](10, 14)
    val sb = new StringBuilder()
    sb.append("\n")
    sb.append("Player 01:").append(name1).append("\n")
    sb.append("Player 02:").append(name2).append("\n")
    for (x <- 0 to 14) {
      sb.append(x).append("\t")
    }
    sb.append("\n")
    for (i <- value.indices) {
      sb.append((i + 1) + "\t")
      for (j <- 0 to 13) {
        sb.append(value(i)(j) + "\t")
      }
      sb.append("\n")
    }
    sb.toString()

  }


}
