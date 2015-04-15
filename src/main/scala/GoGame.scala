import Piece._

class GoGame {
  def isLegal(piece: Piece, position: (Int, Int), board: Board): Boolean = {
    TurnChecker(piece, position, board).isValid &&
      OccupiedPointChecker(piece, position, board).isValid &&
        SelfCaptureChecker(piece, position, board).isValid
  }

  def startGame(): Board = {
    new Board(Array.ofDim[Piece](5,5), Turn.Black)
  }
}
