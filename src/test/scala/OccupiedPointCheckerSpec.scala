import Piece._
import org.scalatest.{FunSpec, Matchers}

class OccupiedPointCheckerSpec extends FunSpec with Matchers {
   describe("Try going to a already occupied point") {
     it("should not allow black to go there") {
       val positions = Array.ofDim[Piece](5, 5)
       positions(1)(2) = Piece.White
       positions(2)(1) = Piece.White
       positions(2)(3) = Piece.White
       positions(3)(2) = Piece.White
       val board = new Board(positions, Turn.Black)
       OccupiedPointChecker(Piece.Black, (1, 2), board).isValid should be(false)
     }
   }
   describe("Try going to a empty point") {
     it("should allow black to go there") {
       val positions = Array.ofDim[Piece](5, 5)
       positions(1)(2) = Piece.White
       positions(2)(1) = Piece.White
       positions(2)(3) = Piece.White
       positions(3)(2) = Piece.White
       val board = new Board(positions, Turn.Black)
       OccupiedPointChecker(Piece.Black, (4, 2), board).isValid should be(true)
     }
   }
 }
