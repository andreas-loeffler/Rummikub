package Rummikub.aview.gui

import Rummikub.controller.controllerComponents._

import java.awt.Color
import scala.swing._
import scala.swing.event._
import scala.util.Try

class CellClicked(val x: Int, val y: Int) extends Event

class SwingGui(controller: ControllerInterface) extends Frame {
  listenTo(controller)
  title = "Rummikub"

  def mainFrame: BorderPanel = new BorderPanel {
    visible = true
    preferredSize = new Dimension(1024, 768)
  }

  var buttons: Array[Array[Button]] = Array.ofDim[Button](controller.gBxSize, controller.gBySize)
  var label1,label2,label3 = new TextArea()
  label1.text = controller.getplayer1Name.toString
  label2.text = controller.getplayer2Name.toString
  label3.text = controller.getplayer3Name.toString
  val optsC = new ComboBox(List('G', 'R', 'B', 'S'))
  val optsV = new ComboBox(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14))
  val doneBtn: Button = new Button("Done"){
    listenTo(mouse.clicks)
    reactions += {
      case e: ButtonClicked => controller.threePlayerOpt(txtplayer1.text,txtplayer2.text,txtplayer3.text)
        label1.text = controller.getplayer1Name.get
        label2.text = controller.getplayer2Name.get
        label3.text = controller.getplayer3Name.get
    }
  }
  var txtplayer1 = new TextField("Enter Player1 Name") {
    listenTo(mouse.clicks)
    reactions += {
      case e: MouseClicked => text = ""}
  }
  var txtplayer2 = new TextField("Enter Player2 Name") {
    listenTo(mouse.clicks)
    reactions += {
      case e: MouseClicked => text = ""}
  }
  var txtplayer3 = new TextField("Enter Player3 Name") {
    listenTo(mouse.clicks)
    reactions += {
      case e: MouseClicked => text = ""}
  }
  val popupMenu: PopupMenu = new PopupMenu {
    contents += doneBtn
    contents += txtplayer1
    contents += txtplayer2
    contents += txtplayer3

  }
  val numberPlayers = new ComboBox(List("1 Player", "2 Player", "3 Player"))


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

  def insert(x:Int,y:Int,c:Char,v:Int): Unit = {

  }


  def createGameboard: GridPanel = new GridPanel(11, 14) {
    for {indexY <- 0 until controller.gBySize} {
      for {index <- 0 until controller.gBxSize} {
        var temp = new Button(controller.getFieldValue(index,indexY).toString + controller.getFieldColor(index,indexY).toString) {
          reactions += {
            case e: ButtonClicked =>
              text = optsC.selection.item + optsV.selection.item.toString
              controller.set(indexY, index, optsC.selection.item, optsV.selection.item)
              background = getTileColor(controller.getFieldColor(index, indexY))
          }
        }
        listenTo(temp.mouse.clicks)

        contents += temp
        buttons(index)(indexY) = temp
      }
    }
  }

  listenTo(optsC.selection)
  listenTo(optsV.selection)
  listenTo(numberPlayers.selection)


  def placeCombos = new GridPanel(2, 1) {
    contents += optsC
    contents += optsV
  }

  def placeMenu: MenuBar = new MenuBar {
    contents += new Menu("Menu") {
      mnemonic = Key.F
      contents += new MenuItem(Action("Reset") {
        controller.resetGameBoard
        close()
        new SwingGui(controller)
      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
      contents += new MenuItem(Action("Create Big") {
        controller.bigGB
      })
      contents += new MenuItem(Action("Create Small") {
        controller.smallGB
      })
      contents += new MenuItem(Action("Undo") {
        controller.undo
      })
      contents += new MenuItem(Action("Redo") {
        controller.redo
      })
      contents += new MenuItem(Action("SaveXml") {
        controller.saveXml
      })
      contents += new MenuItem(Action("LoadXml") {
        controller.loadXml
      })
      contents += new MenuItem(Action("SaveJson") {
        controller.saveJson
      })
      contents += new MenuItem(Action("LoadJson") {
        controller.loadJson
      })
    }
  }


  def createPlayer = new GridPanel(4, 1) {
    reactions += {
      case e: SelectionChanged => popupMenu.show(numberPlayers, 0, numberPlayers.bounds.height)
    }
    contents += numberPlayers
    contents += label1
    contents += label2
    contents += label3
    listenTo(numberPlayers.selection)
  }

  contents = new BorderPanel {
    //add(mainFrame,BorderPanel.Position.North)
    add(createGameboard, BorderPanel.Position.Center)
    add(createPlayer, BorderPanel.Position.East)
    add(placeCombos, BorderPanel.Position.West)
    add(placeMenu, BorderPanel.Position.North)
  }

  visible = true

  reactions += {
    case event: BigGameboard => bigGB(event.newSize)
    case event: SmallGameboard => createGameboard
    case event: FieldChanged => redraw(controller.getplayer1Name.toString, controller.getplayer2Name.toString, controller.getplayer3Name.toString)
    case event: PlayersChanged => redraw(event.newPlayer1, event.newPlayer2, event.newPlayer3)
  }

  def bigGB(gridXSize: Int) = {
    buttons = Array.ofDim[Button](gridXSize, 28)
    contents = new BorderPanel {
      add(createGameboard, BorderPanel.Position.Center)
    }

  }

  def redraw(newPlayer1: String, newPlayer2: String, newPlayer3: String) = {
    label1.text = newPlayer1
    label2.text = newPlayer2
    label3.text = newPlayer3

    for {
      x <- 0 until controller.gBxSize
      y <- 0 until controller.gBySize
    } {
      buttons(x)(y).background = getTileColor(controller.getFieldColor(x, y))
      buttons(x)(y).text = controller.getFieldValue(x, y).toString
    }

    print(newPlayer1)
    print(newPlayer2)
    print(newPlayer3)
  }
}