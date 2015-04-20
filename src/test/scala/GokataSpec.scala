import gokata.model.Piece.Piece
import gokata.model.{Board, GoGame, Piece, Turn}
import org.scalatest._


class GokataSpec extends FlatSpec with Matchers{
  val goGame = new GoGame
  "1. The board" should "be empty at the start of the game" in {
    val board = goGame.startGame()
    boardIsEmpty(board) should be(true)
  }

  "2." should "allow black to move on black's turn" in {
    val board = goGame.startGame()
    goGame.isLegalMove(Piece.Black, (0,0), board, boardHistory) should be (true)
  }

  "2." should " not allow white to move on black's turn" in {
    val board = goGame.startGame()
    goGame.isLegalMove(Piece.White, (0,0), board, boardHistory) should be (false)
  }

  "4." should " not allow black to be put in when surrounded by all whites" in {
    val board = Board.fromArray(Array(
      "- - - - -",
      "- - o - -",
      "- o - o -",
      "- - o - -",
      "- - - - -"
    ), Turn.Black)
    goGame.isLegalMove(Piece.Black, (2, 2), board, boardHistory) should be(false)
  }

  "5. when surrounded by white and can capture a white" should "allow black to go there" in {
      val board = Board.fromArray(Array(
        "- o x - -",
        "o - o x -",
        "- o x - -",
        "- - - - -",
        "- - - - -"
      ), Turn.Black)
      print(board.toString() + "\n")
      goGame.isLegalMove(Piece.Black, (1, 1), board, boardHistory) should be(true)
  }

  "6. " should "not allow move that could create an infinite loop" in {
    val board = Board.fromArray(Array(
      "- o x - -",
      "o x o x -",
      "- o x - -",
      "- - - - -",
      "- - - - -"
    ), Turn.Black)
    print(board.toString() + "\n")

    val board1 = Board.fromArray(Array(
      "- o x - -",
      "o x o x -",
      "- o x - -",
      "- - - - -",
      "- - - - -"
    ), Turn.White)
    print(board1.toString() + "\n")

    val board2 = Board.fromArray(Array(
      "- o x - -",
      "o - o x -",
      "- o x - -",
      "- - - - -",
      "- - - - -"
    ), Turn.Black)
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