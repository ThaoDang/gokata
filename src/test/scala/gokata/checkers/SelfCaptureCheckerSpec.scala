package gokata.checkers

import gokata.model.Piece._
import gokata.model.{Board, Piece, Turn}
import org.scalatest.{FunSpec, Matchers}

class SelfCaptureCheckerSpec extends FunSpec with Matchers {
  describe("not a self capture") {
    it("should allow black to go there") {
      val board = Board.fromArray(Array(
        "- - - - -",
        "- - - - -",
        "- - - o o",
        "- - o x -",
        "- - - - o"
      ), Turn.White)
      SelfCaptureChecker(Piece.Black, (3, 4), board).isValid should be(true)
    }
  }
  describe("A single spot surrounded by whites") {
    it("should not allow black to go there") {
      val board = Board.fromArray(Array(
        "- o - - -",
        "o - o - -",
        "- o - - -",
        "- - - - -",
        "- - - - -"
      ), Turn.Black)
      SelfCaptureChecker(Piece.Black, (1, 1), board).isValid should be(false)
    }
  }
  describe("A spot connected to another surrounded black") {
    it("should not allow black to go there") {
      val board = Board.fromArray(Array(
      "- - - - -",
      "- - o o -",
      "- o - x o",
      "- - o o -",
      "- - - - -"), Turn.Black)
      SelfCaptureChecker(Piece.Black, (2, 2), board).isValid should be(false)
    }
  }
  describe("connected to another black in the corner") {
    it("should not allow black to go there") {
      val board = Board.fromArray(Array(
        "- - - - -",
        "- - - - -",
        "- - - o o",
        "- - o x -",
        "- - - o o"
      ), Turn.Black)
      SelfCaptureChecker(Piece.Black, (3, 4), board).isValid should be(false)
    }
  }
  describe("surrounded by white and can capture a white") {
    it("should allow black to go there") {
      val board = Board.fromArray(Array(
        "- o x - -",
        "o - o x -",
        "- o x - -",
        "- - - - -",
        "- - - - -"
      ), Turn.Black)
      SelfCaptureChecker(Piece.Black, (1, 1), board).isValid should be(true)
    }
  }
}
