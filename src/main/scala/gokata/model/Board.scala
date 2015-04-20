package gokata.model

import gokata.checkers.{BoardHistoryChecker, SelfCaptureChecker, OccupiedPointChecker, TurnChecker}
import gokata.model.Piece.Piece
import gokata.model.Turn.Turn

import scala.collection.immutable.Queue

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
