import Piece.Piece

/**
 * Created by thaodang on 5/4/15.
 */
abstract class MoveChecker {
  def isValid: Boolean
}

case class FirstMoveChecker(piece: Piece, position: (Int, Int), board: Board) extends MoveChecker {
  override def isValid: Boolean = {
    if (!board.isEmpty) return true
    piece match {
      case Piece.Black => true
      case _ => false
    }
  }
}

case class SelfCaptureChecker(piece: Piece, position: (Int, Int), board: Board) extends MoveChecker {

  private def isCaptured(current: (Int, Int), visitedList: List[(Int, Int)]): Boolean = {

    def isSurrounded(i: Int, j: Int, visitedList: List[(Int, Int)]): Boolean = {
      if (board.isOffEdge(i, j)) return true
      if (visitedList.contains(current)) return true

      board.pieceAt(i, j) match {
        case null => false
        case p if p != piece => true
        case _ => isCaptured((i, j), visitedList.::((i, j)))
      }
    }

    isSurrounded(current._1 - 1, current._2, visitedList.::(current)) &&
      isSurrounded(current._1 + 1, current._2, visitedList.::(current)) &&
      isSurrounded(current._1, current._2 - 1, visitedList.::(current)) &&
      isSurrounded(current._1, current._2 + 1, visitedList.::(current))
  }

  override def isValid: Boolean = {
    !isCaptured(position, List())
  }
}
