package controller

import scala.swing.event.Event


class FieldChanged extends Event
case class BigGameboard(newSize: Int) extends Event
case class SmallGameboard(newSize: Int) extends Event
case class PlayersChanged (newPlayer1: String,newPlayer2: String,newPlayer3: String) extends Event
class CandidatesChanged extends Event
