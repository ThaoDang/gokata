package gokata.checkers

import gokata.model.Piece.Piece
import gokata.model.{Turn, Piece, Board}
import org.scalatest.{FunSpec, Matchers}

import scala.collection.immutable.Queue

/**
 * Created by thaodang on 16/4/15.
 */
class BoardHistoryCheckerSpec extends FunSpec with Matchers {
  describe("move does not result in a repeat state from the last turn"){
    it("should be valid") {
      val positions1 = Array.ofDim[Piece](5,5)
      positions1(0)(0) = Piece.White
      positions1(1)(0) = Piece.Black
      val board1 = new Board(positions1, Turn.White)

      val positions2 = Array.ofDim[Piece](5,5)
      positions2(0)(0) = Piece.White
      positions2(1)(0) = Piece.Black
      positions2(1)(1) = Piece.White
      val board2 = new Board(positions2, Turn.Black)

      val positions3 = Array.ofDim[Piece](5,5)
      positions3(0)(0) = Piece.White
      positions3(1)(0) = Piece.Black
      positions3(1)(1) = Piece.White
      positions3(1)(4) = Piece.Black
      val board3 = new Board(positions3, Turn.White)

      val boardList = List[Board](board1, board2)
      BoardHistoryChecker(Piece.White, (1,2), board3, boardList).isValid should be(true)
    }
  }
  describe("move results in a repeat state from the last turn"){
    it("should be invalid") {
      val positions1 = Array.ofDim[Piece](5,5)
      positions1(0)(0) = Piece.White
      positions1(1)(0) = Piece.Black
      val board1 = new Board(positions1, Turn.White)

      val positions2 = Array.ofDim[Piece](5,5)
      positions2(0)(0) = Piece.White
      positions2(1)(0) = Piece.Black
      positions2(1)(1) = Piece.White
      val board2 = new Board(positions2, Turn.Black)

      val positions3 = Array.ofDim[Piece](5,5)
      positions3(1)(0) = Piece.Black
      val board3 = new Board(positions3, Turn.White)

      val boardList = List[Board](board1, board2)
      BoardHistoryChecker(Piece.White, (0,0), board3, boardList).isValid should be(false)
    }
  }
}
