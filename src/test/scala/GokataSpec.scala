import gokata.model.{GoGame, Turn, Piece, Board}
import Piece.Piece
import org.scalatest._

import scala.collection.immutable.Queue


class GokataSpec extends FlatSpec with Matchers{
  val goGame = new GoGame
  "1. The board" should "be empty at the start of the game" in {
    val board = goGame.startGame()

    boardIsEmpty(board) should be(true)
  }

  "2." should "allow black to move on black's turn" in {
    val board = goGame.startGame()
    goGame.isLegalMove(Piece.Black, (0,0), board, boardHistory) should be (true)
//    )
  }

  "2." should " not allow white to move on black's turn" in {
    val board = goGame.startGame()
    goGame.isLegalMove(Piece.White, (0,0), board, boardHistory) should be (false)
  }

  "3." should " not allow black to be put in when surrounded by all whites" in {
    val positions = Array.ofDim[Piece](5, 5)
    positions(1)(2) = Piece.White
    val board = new Board(positions, Turn.Black)
    //    print(board.toString())
    goGame.isLegalMove(Piece.Black, (1, 2), board, boardHistory) should be(false)
  }

  "4." should " not allow black to be put in when surrounded by all whites" in {
    val positions = Array.ofDim[Piece](5, 5)
    positions(1)(2) = Piece.White
    positions(2)(1) = Piece.White
    positions(2)(3) = Piece.White
    positions(3)(2) = Piece.White
    val board = new Board(positions, Turn.Black)
//    print(board.toString())
    goGame.isLegalMove(Piece.Black, (2, 2), board, boardHistory) should be(false)
  }

  "5. when surrounded by white and can capture a white" should "allow black to go there" in {
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
      goGame.isLegalMove(Piece.Black, (1, 1), board, boardHistory) should be(true)
  }

  "6. " should "not allow move that could create an infinite loop" in {
    val positions = Array.ofDim[Piece](5, 5)
    positions(0)(2) = Piece.Black
    positions(1)(3) = Piece.Black
    positions(2)(2) = Piece.Black
    positions(1)(1) = Piece.Black
    positions(0)(1) = Piece.White
    positions(1)(2) = Piece.White
    positions(2)(1) = Piece.White
    positions(1)(0) = Piece.White
    val board = new Board(positions, Turn.Black)
    print(board.toString() + "\n")

    val positions1 = Array.ofDim[Piece](5, 5)
    positions1(0)(2) = Piece.Black
    positions1(1)(3) = Piece.Black
    positions1(2)(2) = Piece.Black
    positions1(1)(1) = Piece.Black
    positions1(1)(2) = Piece.White
    positions1(0)(1) = Piece.White
    positions1(2)(1) = Piece.White
    positions1(1)(0) = Piece.White
    val board1 = new Board(positions1, Turn.White)
    print(board1.toString() + "\n")

    val positions2 = Array.ofDim[Piece](5, 5)
    positions2(0)(2) = Piece.Black
    positions2(1)(3) = Piece.Black
    positions2(2)(2) = Piece.Black
    positions2(1)(2) = Piece.White
    positions2(0)(1) = Piece.White
    positions2(2)(1) = Piece.White
    positions2(1)(0) = Piece.White
    val board2 = new Board(positions2, Turn.Black)
    print(board2.toString() + "\n")
    goGame.isLegalMove(Piece.Black, (1,1), board2, List(board, board1)) should be (false)
  }

  private def boardIsEmpty(board: Board): Boolean = {
    board.positions.forall(array => array.forall(piece => piece == null))
  }

  private def boardHistory = {
    val positions1 = Array.ofDim[Piece](5,5)
    positions1(0)(0) = Piece.White
    positions1(1)(0) = Piece.Black
    val board1 = new Board(positions1, Turn.White)

    val positions2 = Array.ofDim[Piece](5,5)
    positions2(0)(0) = Piece.White
    positions2(1)(0) = Piece.Black
    positions2(1)(1) = Piece.White
    val board2 = new Board(positions2, Turn.Black)

    List[Board](board1, board2)
  }
}