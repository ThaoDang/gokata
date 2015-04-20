package gokata.model

import gokata.model.Piece._
import gokata.model.Turn.Turn

class Board(val positions: Array[Array[Piece]], val turn: Turn) {
  
  def pieceAt(position: (Int, Int)): Option[Piece] = Option(positions(position._1)(position._2))
  
  def isEmpty(): Boolean = positions.forall(array => array.forall(piece => piece == null))
  
  def isOffEdge(position: (Int, Int)): Boolean = {
    val (i, j) = position
    i > positions.head.length - 1 || j > positions.length - 1 || i < 0 || j < 0
  }

  override def toString() = positions.foldLeft[StringBuffer](new StringBuffer())((sb, array) => sb.append(array.mkString(" ").replaceAll("null", "-")).append("\n")).toString

  override def equals(that: Any) = {
    that.isInstanceOf[Board] && toString() == that.asInstanceOf[Board].toString() && turn == that.asInstanceOf[Board].turn
  }
  override def hashCode() = toString.hashCode + turn.hashCode()
}

object Board {
  private def makePieceArray(s: String): Array[Piece] = s.split("\\s").map(string => if(string == "o") Piece.White else if(string == "x") Piece.Black else null)

  def fromArray(arr: Array[String], turn: Turn) = new Board(arr.map(makePieceArray(_)), turn)
}
