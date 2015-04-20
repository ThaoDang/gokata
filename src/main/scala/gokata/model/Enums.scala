package gokata.model

import gokata.model.Piece.Piece

object Piece extends Enumeration {
  type Piece = Value
  val Black = Value("x")
  val White = Value("o")

  class PieceValue(piece: Value) {
    def opponent = piece match {
      case Black =>  White
      case _ => Black
    }
  }
  implicit def value2PieceValue(piece: Value) = new PieceValue(piece)
}

object Turn {
  type Turn = Piece
  val Black = Piece.Black
  val White = Piece.White
}
