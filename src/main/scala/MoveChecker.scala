import Piece.Piece
import Piece._

abstract class MoveChecker {
  def isValid: Boolean
}

case class TurnChecker(piece: Piece, position: (Int, Int), board: Board) extends MoveChecker {
  override def isValid: Boolean = {
    piece match {
      case piece if(piece == board.turn) => true
      case _ => false
    }
  }
}

case class OccupiedPointChecker(piece: Piece, position: (Int, Int), board: Board) extends MoveChecker {
  override def isValid: Boolean = {
    board.pieceAt(position) match {
      case None => true
      case _ => false
    }
  }
}

case class SelfCaptureChecker(piece: Piece, positionToPut: (Int, Int), board: Board) extends MoveChecker {
  override def isValid: Boolean = !isCaptured(piece, positionToPut, List(positionToPut)) || isCapturingOpponent(positionToPut, List(positionToPut))

  def isCaptured(currentPiece: Piece, currentPosition: (Int, Int), visited: List[(Int, Int)]) : Boolean = {
    def isSurrounded(position: (Int, Int)): Boolean = {
      if(visited.contains(position)) return true
      board.pieceAt(position) match {
        case None => false
        case Some(p) if p != currentPiece => true
        case _ => isCaptured(currentPiece, position, visited.::(position))
      }
    }
    surroundingPositions(currentPosition).forall(position => isSurrounded(position))
  }

  def isCapturingOpponent(position: (Int, Int), visited: List[(Int, Int)]): Boolean = {
    surroundingPositions(position).exists(currentPosition => isCaptured(piece.opponent, currentPosition, visited))
  }

  private def surroundingPositions(current: (Int, Int)): List[(Int, Int)] = {
    List((current._1 - 1, current._2),
      (current._1 + 1, current._2),
      (current._1, current._2 - 1),
      (current._1, current._2 + 1)).filter(position => !board.isOffEdge(position))
  }
}