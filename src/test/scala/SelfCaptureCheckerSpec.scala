import Piece._
import org.scalatest.{Matchers, FunSpec}

class SelfCaptureCheckerSpec extends FunSpec with Matchers {
  describe("not a self capture") {
    it("should allow black to go there") {
      val positions = Array.ofDim[Piece](5, 5)
      positions(3)(2) = Piece.White
      positions(2)(3) = Piece.White
      positions(3)(3) = Piece.Black
      positions(2)(4) = Piece.White
      positions(4)(4) = Piece.White
      val board = new Board(positions, Turn.Black)
      print(board.toString() + "\n")
      SelfCaptureChecker(Piece.Black, (3, 4), board).isValid should be(true)
    }
  }
  describe("A single spot surrounded by whites") {
    it("should not allow black to go there") {
      val positions = Array.ofDim[Piece](5, 5)
      positions(1)(0) = Piece.White
      positions(0)(1) = Piece.White
      positions(2)(1) = Piece.White
      positions(1)(2) = Piece.White
      val board = new Board(positions, Turn.Black)
      print(board.toString() + "\n")
      SelfCaptureChecker(Piece.Black, (1, 1), board).isValid should be(false)
    }
  }
  describe("A spot connected to another surrounded black") {
    it("should not allow black to go there") {
      val positions = Array.ofDim[Piece](5, 5)
      positions(2)(1) = Piece.White
      positions(1)(2) = Piece.White
      positions(3)(2) = Piece.White
      positions(2)(3) = Piece.Black
      positions(1)(3) = Piece.White
      positions(3)(3) = Piece.White
      positions(2)(4) = Piece.White
      val board = new Board(positions, Turn.Black)
      print(board.toString() + "\n")
      SelfCaptureChecker(Piece.Black, (2, 2), board).isValid should be(false)
    }
  }
  describe("connected to another black in the corner") {
    it("should not allow black to go there") {
      val positions = Array.ofDim[Piece](5, 5)
      positions(3)(2) = Piece.White
      positions(2)(3) = Piece.White
      positions(3)(3) = Piece.Black
      positions(4)(3) = Piece.White
      positions(2)(4) = Piece.White
      positions(4)(4) = Piece.White
      val board = new Board(positions, Turn.Black)
      print(board.toString() + "\n")
      SelfCaptureChecker(Piece.Black, (3, 4), board).isValid should be(false)
    }
  }
  describe("surrounded by white and can capture a white") {
    it("should allow black to go there") {
      val positions = Array.ofDim[Piece](5, 5)
      positions(0)(2) = Piece.Black
      positions(1)(3) = Piece.Black
      positions(2)(2) = Piece.Black
      positions(1)(2) = Piece.White
      positions(0)(1) = Piece.White
      positions(2)(1) = Piece.White
      positions(1)(0) = Piece.White
      val board = new Board(positions, Turn.Black)
      print(board.toString() + "\n")
      SelfCaptureChecker(Piece.Black, (1, 1), board).isValid should be(true)
    }
  }
}
