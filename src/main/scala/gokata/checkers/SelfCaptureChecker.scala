package gokata.checkers

import gokata.model.Board
import gokata.model.Piece._

/**
 * Created by thaodang on 16/4/15.
 */
case class SelfCaptureChecker(piece: Piece, positionToPut: (Int, Int), board: Board) extends MoveChecker {
  override def isValid: Boolean = !isCaptured(piece, positionToPut, List(positionToPut))._1 || isCapturingOpponent(positionToPut, List(positionToPut))

  def isCaptured(currentPiece: Piece, currentPosition: (Int, Int), visited: List[(Int, Int)]): (Boolean, List[(Int, Int)]) = {
    def isSurrounded(position: (Int, Int)): (Boolean, List[(Int, Int)]) = {
      if (visited.contains(position)) return (true, visited)
      board.pieceAt(position) match {
        case None => (false, visited)
        case Some(p) if p != currentPiece => (true, visited)
        case _ => isCaptured(currentPiece, position, visited.::(position))
      }
    }
    surroundingPositions(currentPosition).foldLeft[(Boolean, List[(Int, Int)])](true, visited)((soFar, position) => {
      val (isSurroundedSoFar, visitedSoFar) = soFar
      if (isSurroundedSoFar) isSurrounded(position) else soFar
    })
  }

  def isCapturingOpponent(position: (Int, Int), visited: List[(Int, Int)]): Boolean = {
    surroundingPositions(position).foldLeft[(Boolean, List[(Int, Int)])](false, visited)((soFar, currentPosition) => {
      val (isCapturedSoFar, visitedSoFar) = soFar
      if (!isCapturedSoFar) isCaptured(piece.opponent, currentPosition, visitedSoFar) else soFar
    })._1
  }

  private def surroundingPositions(current: (Int, Int)): List[(Int, Int)] = {
    List((current._1 - 1, current._2),
      (current._1 + 1, current._2),
      (current._1, current._2 - 1),
      (current._1, current._2 + 1)).filter(position => !board.isOffEdge(position))
  }
}
