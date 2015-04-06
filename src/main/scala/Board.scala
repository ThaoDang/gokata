import Piece.Piece

/**
 * Created by thaodang on 4/4/15.
 */


class Board(val positions: Array[Array[Piece]]) {
  
  def pieceAt(i: Int, j: Int): Piece = positions(i)(j)
  
  def isEmpty(): Boolean = positions.forall(array => array.forall(piece => piece == null))
  
  def isOffEdge(i: Int, j:Int): Boolean = i > positions.head.length - 1 || j > positions.length - 1

  override def toString() = positions.foldLeft[StringBuffer](new StringBuffer())((sb, array) => sb.append(array.mkString(" ").replaceAll("null", "+")).append("\n")).toString

}
