package gokata.checkers

import gokata.model.{Board, Piece}
import gokata.model.Piece.Piece



case class TurnChecker(piece: Piece, position: (Int, Int), board: Board) extends MoveChecker {
  override def isValid = {
    piece == board.turn
  }
}



