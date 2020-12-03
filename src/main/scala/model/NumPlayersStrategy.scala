package model

object NumPlayersStrategy {

  def playerN(x: String, name1: String, name2: String, name3: String, gameBoardNet: GameBoardNet): GameBoardNet = x match {
    case "player1" => player1(name1, gameBoardNet)
    case "player2" => player2(name1,name2,gameBoardNet)
    case "player3" => player3(name1,name2,name3,gameBoardNet)
  }


  def player1(player1: String, gameBoardNet: GameBoardNet): GameBoardNet = {
    gameBoardNet.player1 = Player(player1)
    gameBoardNet
  }

  def player2(player1: String, player2: String, gameBoardNet: GameBoardNet): GameBoardNet = {
    gameBoardNet.player1 = Player(player1)
    gameBoardNet.player2 = Player(player2)
    gameBoardNet
  }

  def player3(player1: String, player2: String, player3: String, gameBoardNet: GameBoardNet): GameBoardNet = {
    gameBoardNet.player1 = Player(player1)
    gameBoardNet.player2 = Player(player2)
    gameBoardNet.player3 = Player(player3)
    gameBoardNet
  }
}
