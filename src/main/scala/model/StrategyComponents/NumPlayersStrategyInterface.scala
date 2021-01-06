package model.StrategyComponents

import model.gameBoardComponents.gameBoardBaseImpl.GameBoardNet

trait NumPlayersStrategyInterface {
  def playerN(x: String, name1: Option[String], name2: Option[String], name3: Option[String], gameBoardNet: GameBoardNet): GameBoardNet

  def player1(player1: String, gameBoardNet: GameBoardNet): GameBoardNet

  def player2(player1: String, player2: String, gameBoardNet: GameBoardNet): GameBoardNet

  def player3(player1: String, player2: String, player3: String, gameBoardNet: GameBoardNet): GameBoardNet
}



