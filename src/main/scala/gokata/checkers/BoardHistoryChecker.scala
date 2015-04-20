package gokata.checkers

import gokata.model.Board
import gokata.model.Piece.Piece

import scala.collection.immutable.Queue

case class BoardHistoryChecker(piece: Piece, position: (Int, Int), board: Board, boardHistory: List[Board]) extends MoveChecker {
  override def isValid: Boolean = {
    board.positions(position._1)(position._2) = piece
    boardHistory(boardHistory.size-2) != board
  }
}
