package gokata.checkers

import gokata.model.Board
import gokata.model.Piece._

/**
 * Created by thaodang on 16/4/15.
 */
case class OccupiedPointChecker(piece: Piece, position: (Int, Int), board: Board) extends MoveChecker {
  override def isValid: Boolean = {
    board.pieceAt(position) match {
      case None => true
      case _ => false
    }
  }
}
