package gokata.checkers

import gokata.model.Piece.Piece
import gokata.model.{Board, Piece, Turn}
import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by thaodang on 13/4/15.
 */
class TurnCheckerSpec extends FlatSpec with Matchers {
  "TurnChecker" should "return valid if black goes first on an empty board" in {
    val board = new Board(Array.ofDim[Piece](5,5), Turn.Black)
    TurnChecker(Piece.Black, (1,1), board).isValid should be (true)
  }

  "TurnChecker" should "return valid if black goes on black's turn" in {
    val board = new Board(Array.ofDim[Piece](5,5), Turn.Black)
    board.positions(1)(0) = Piece.White
    TurnChecker(Piece.Black, (1,1), board).isValid should be (true)
  }

  "TurnChecker" should "return invalid if black goes on white's turn" in {
    val board = new Board(Array.ofDim[Piece](5,5), Turn.White)
    board.positions(1)(0) = Piece.Black
    board.positions(1)(2) = Piece.Black
    TurnChecker(Piece.Black, (1,1), board).isValid should be (false)
  }
}
