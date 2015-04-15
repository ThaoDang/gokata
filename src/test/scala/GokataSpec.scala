import Piece.Piece
import org.scalatest._



class GokataSpec extends FlatSpec with Matchers{
  val goGame = new GoGame
  "1. The board" should "be empty at the start of the game" in {
    val board = goGame.startGame()

    assert(boardIsEmpty(board))
  }

  "2." should "allow black to move first" in {
    val board = goGame.startGame()
    goGame.isLegal(Piece.Black, (0,0), board) should be (true)
//    assert()
  }

  "2." should " not allow white to move first" in {
    val board = goGame.startGame()
    assert(goGame.isLegal(Piece.White, (0,0), board) === false)
  }

  "3." should " not allow black to be put in when surrounded by all whites" in {
    val positions = Array.ofDim[Piece](5, 5)
    positions(1)(2) = Piece.White
    positions(2)(1) = Piece.White
    positions(2)(3) = Piece.White
    positions(3)(2) = Piece.White
    val board = new Board(positions, Turn.Black)
//    print(board.toString())
    assert(goGame.isLegal(Piece.Black, (2, 2), board) === false)
  }


  "3." should " not allow black to be put in when connected to another black in the corner" in {
    val positions = Array.ofDim[Piece](5, 5)
    positions(2)(3) = Piece.White
    positions(3)(2) = Piece.White
    positions(3)(3) = Piece.Black
    positions(3)(4) = Piece.White
    positions(4)(2) = Piece.White
    positions(4)(4) = Piece.White
    val board = new Board(positions, Turn.Black)
    print(board.toString()+"\n")
    assert(goGame.isLegal(Piece.Black, (4, 3), board) === false)
  }
  private def boardIsEmpty(board: Board): Boolean = {
    board.positions.forall(array => array.forall(piece => piece == null))
  }
}