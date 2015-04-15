import Piece.Piece
import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by thaodang on 13/4/15.
 */
class TurnCheckerSpec extends FlatSpec with Matchers {
  "FirstMoveChecker" should "return valid if black goes first on an empty board" in {
    val board = new Board(Array.ofDim[Piece](5,5), Turn.Black)
    TurnChecker(Piece.Black, (1,1), board).isValid should be (true)
  }

  "FirstMoveChecker" should "return invalid if white goes first on an empty board" in {
    val board = new Board(Array.ofDim[Piece](5,5), Turn.White)
    TurnChecker(Piece.White, (1,1), board).isValid should be (false)
  }

  "FirstMoveChecker" should "return valid if black goes on black's turn" in {
    val board = new Board(Array.ofDim[Piece](5,5), Turn.Black)
    board.positions(1)(0) = Piece.White
    TurnChecker(Piece.Black, (1,1), board).isValid should be (true)
  }

  "FirstMoveChecker" should "return invalid if black goes on white's turn" in {
    val board = new Board(Array.ofDim[Piece](5,5), Turn.White)
    board.positions(1)(0) = Piece.Black
    TurnChecker(Piece.White, (1,1), board).isValid should be (false)
  }
}
