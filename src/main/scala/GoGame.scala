import Piece._

/**
 * Created by thaodang on 5/4/15.
 */
class GoGame {
  def isLegal(piece: Piece, position: (Int, Int), board: Board): Boolean = {
    if(board.isEmpty()) FirstMoveChecker(piece, position, board).isValid
    else SelfCaptureChecker(piece, position, board).isValid
  }

  def startGame(): Board = {
    new Board(Array.ofDim[Piece](5,5))
  }
}
