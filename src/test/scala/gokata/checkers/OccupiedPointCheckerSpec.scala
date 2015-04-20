package gokata.checkers

import gokata.model.Piece._
import gokata.model.{Board, Piece, Turn}
import org.scalatest.{FunSpec, Matchers}

class OccupiedPointCheckerSpec extends FunSpec with Matchers {
   describe("Try going to a already occupied point") {
     it("should not allow black to go there") {
       val board = Board.fromArray(Array(
         "- - - - -",
         "- - o - -",
         "- o - o -",
         "- - o - -",
         "- - - - -"
       ), Turn.Black)
       OccupiedPointChecker(Piece.Black, (1, 2), board).isValid should be(false)
     }
   }
   describe("Try going to a empty point") {
     it("should allow black to go there") {
       val board = Board.fromArray(Array(
         "- - - - -",
         "- - o - -",
         "- o - o -",
         "- - o - -",
         "- - - - -"
       ), Turn.Black)
       OccupiedPointChecker(Piece.Black, (4, 2), board).isValid should be(true)
     }
   }
 }
