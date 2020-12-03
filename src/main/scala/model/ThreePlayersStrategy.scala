package model

class ThreePlayersStrategy extends NumPlayersStrategy {

  override def defPlayers(player1: String, player2: String, player3: String,gameBoardNet: GameBoardNet): GameBoardNet = {
    //val gameBoardNet = new GameBoardNet()
    gameBoardNet.player1(player1)
    gameBoardNet.player2(player2)
    gameBoardNet.player3(player3)
    gameBoardNet.fillTiles()
    gameBoardNet
  }
}
