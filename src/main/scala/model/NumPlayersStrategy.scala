package model

trait NumPlayersStrategy {

  def defPlayers(player1: String, player2: String, player3: String, gameBoardNet: GameBoardNet): GameBoardNet
}
