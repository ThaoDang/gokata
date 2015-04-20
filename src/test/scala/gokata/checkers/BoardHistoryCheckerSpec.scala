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
      val board1 = Board.fromArray(Array(
        "o - - - -",
        "x - - - -",
        "- - - - -",
        "- - - - -",
        "- - - - -"
      ), Turn.White)

      val board2 = Board.fromArray(Array(
        "o - - - -",
        "x o - - -",
        "- - - - -",
        "- - - - -",
        "- - - - -"
      ), Turn.Black)

      val board3 = Board.fromArray(Array(
        "o - - - -",
        "x o - x -",
        "- - - - -",
        "- - - - -",
        "- - - - -"
      ), Turn.White)

      val boardHistory = List[Board](board1, board2)
      BoardHistoryChecker(Piece.White, (1,2), board3, boardHistory).isValid should be(true)
    }
  }
  describe("move results in a repeat state from the last turn"){
    it("should be invalid") {
      val board1 = Board.fromArray(Array(
        "o - - - -",
        "x - - - -",
        "- - - - -",
        "- - - - -",
        "- - - - -"
      ), Turn.White)

      val board2 = Board.fromArray(Array(
        "o - - - -",
        "x o - - -",
        "- - - - -",
        "- - - - -",
        "- - - - -"
      ), Turn.Black)

      val board3 = Board.fromArray(Array(
        "o - - - -",
        "x - - - -",
        "- - - - -",
        "- - - - -",
        "- - - - -"
      ), Turn.White)

      val boardList = List[Board](board1, board2)
      BoardHistoryChecker(Piece.White, (0,0), board3, boardList).isValid should be(false)
    }
  }
}
