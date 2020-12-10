package model

object NumPlayersStrategy {

  def playerN(x: String, name1: Option[String], name2: Option[String], name3: Option[String], gameBoardNet: GameBoardNet): GameBoardNet = x match {
    case "player1" => player1(name1.get, gameBoardNet)
    case "player2" => player2(name1.get,name2.get,gameBoardNet)
    case "player3" => player3(name1.get,name2.get,name3.get,gameBoardNet)
  }


  def player1(player1: String, gameBoardNet: GameBoardNet): GameBoardNet = {
    gameBoardNet.player1 = Player(Some(player1))
    gameBoardNet
  }

  def player2(player1: String, player2: String, gameBoardNet: GameBoardNet): GameBoardNet = {
    gameBoardNet.player1 = Player(Some(player1))
    gameBoardNet.player2 = Player(Some(player2))
    gameBoardNet
  }

  def player3(player1: String, player2: String, player3: String, gameBoardNet: GameBoardNet): GameBoardNet = {
    gameBoardNet.player1 = Player(Some(player1))
    gameBoardNet.player2 = Player(Some(player2))
    gameBoardNet.player3 = Player(Some(player3))
    gameBoardNet
  }
}
