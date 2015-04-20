package gokata.model

import gokata.checkers.{BoardHistoryChecker, SelfCaptureChecker, OccupiedPointChecker, TurnChecker}
import gokata.model.Piece._

import scala.collection.immutable.Queue

class GoGame {
  def isLegalMove(piece: Piece, position: (Int, Int), board: Board, boardHistory: List[Board]): Boolean = {
    TurnChecker(piece, position, board).isValid &&
      OccupiedPointChecker(piece, position, board).isValid &&
        SelfCaptureChecker(piece, position, board).isValid &&
          BoardHistoryChecker(piece, position, board, boardHistory).isValid
  }

  def startGame(): Board = {
    new Board(Array.ofDim[Piece](5,5), Turn.Black)
  }
}
