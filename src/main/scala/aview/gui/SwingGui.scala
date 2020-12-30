package aview.gui

import controller.{Controller, _}

import java.awt.Color
import scala.swing._
import scala.swing.event._
import scala.util.Try

class CellClicked(val x: Int, val y: Int) extends Event

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)
  title = "Rummikub"

  def mainFrame = new BorderPanel {
    visible = true
    preferredSize = new Dimension(1024, 768)
  }

  var buttons = Array.ofDim[Button](controller.gameBoardNet.getXSize(), controller.gameBoardNet.getYSize())
  var playerNames = Array.ofDim[String](3)
  val optsC = new ComboBox(List('G', 'R', 'B', 'S'))
  val optsV = new ComboBox(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14))

  def getTileColor(c: Char): Color = {
    var color = Color.white
    Try(
      c
      match {
        case 'G' => color = Color.YELLOW
        case 'R' => color = Color.RED
        case 'B' => color = Color.BLUE
        case 'S' => color = Color.BLACK
      }
    )
    color
  }

  def createGameboard = new GridPanel(11, 14) {
    var stanColor = Color.white
    for {indexY <- 0 until controller.gBySize} {
      for {index <- 0 until controller.gBxSize} {
        var temp = new Button(index + "|" + indexY) {
          reactions += {
            case e: ButtonClicked =>
              controller.set(indexY, index, optsC.selection.item, optsV.selection.item)
          }
          if (controller.getFieldColor(index, indexY).equals(' ')) background = Color.white else background = getTileColor(controller.getFieldColor(index, indexY))
        }
        listenTo(temp.mouse.clicks)

        contents += temp
        buttons(index)(indexY) = temp

      }
    }
  }

  listenTo(optsC.selection)
  listenTo(optsV.selection)


  def placeCombos = new GridPanel(2, 1) {
    contents += optsC
    contents += optsV
  }


  def createPlayer = new GridPanel(4, 1) {
    playerNames(0) = "available"
    playerNames(1) = "available"
    playerNames(2) = "available"
    val numberPlayers = new ComboBox(List("1 Player", "2 Player", "3 Player"))
    contents += numberPlayers
    contents += new Label(playerNames(0))
    contents += new Label(playerNames(1))
    contents += new Label(playerNames(2))
    listenTo(numberPlayers.selection)
  }

  contents = new BorderPanel {
    //add(mainFrame,BorderPanel.Position.North)
    add(createGameboard, BorderPanel.Position.Center)
    add(createPlayer, BorderPanel.Position.East)
    add(placeCombos, BorderPanel.Position.West)
  }

  visible = true

  reactions += {
    case event: BigGameboard => bigGB(event.newSize)
    case event: SmallGameboard => createGameboard
    case event: FieldChanged => redraw(controller.gameBoardNet.player1.name.toString, controller.gameBoardNet.player2.name.toString, controller.gameBoardNet.player3.name.toString)
    case event: PlayersChanged => redraw(event.newPlayer1, event.newPlayer2, event.newPlayer3)
  }

  def bigGB(gridXSize: Int) = {
    buttons = Array.ofDim[Button](gridXSize, 28)
    contents = new BorderPanel {
      add(createGameboard, BorderPanel.Position.Center)
    }

  }

  def redraw(newPlayer1: String, newPlayer2: String, newPlayer3: String) = {
    playerNames(0) = newPlayer1
    playerNames(1) = newPlayer2
    playerNames(2) = newPlayer3

    for {
      x <- 0 until controller.gameBoardNet.getXSize()
      y <- 0 until controller.gameBoardNet.getYSize()
    } {
      buttons(x)(y).background = getTileColor(controller.getFieldColor(x, y))
      buttons(x)(y).text = controller.getFieldValue(x, y).toString
    }

    print(newPlayer1)
    print(newPlayer2)
    print(newPlayer3)
  }
}