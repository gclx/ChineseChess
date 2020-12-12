/**
 * 
 */
package pieces;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.text.AbstractDocument.LeafElement;

import board.ChessBoard;
import step.Step;

/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年11月15日 下午15:29:42
 * @copyright 
 * @remarks TODO
 *
 */
public class Cannon extends ChessPieces{

	/**
	 * @param x
	 * @param y
	 * @param isalive
	 * @param isred
	 * @param name
	 */
	public Cannon(int x, int y, boolean isalive, boolean isred, String name) {
		super(x, y, isalive, isred, name);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see pieces.ChessPieces#isLegalMove(board.ChessBoard, int, int)
	 */
	@Override
	public boolean isLegalMove(ChessBoard board, int toX, int toY) {
		// TODO Auto-generated method stub
		if (!isPointLegal(toX, toY))		return false;
		System.out.println("check point successfully!");
		if (!isMyTurnToMove(board))			return false;
		System.out.println("check turn successfully!");
		if (getX() != toX && getY() != toY)	return false;
		System.out.println("check move successfully!");
		if (!isDestLegal(board, toX, toY))	return false;
		System.out.println("check destination successfully!");
		if (getX() != toX){
			if (board.getPiece(toX, toY) == null){
				System.out.println("dest has no pieces");
				if (toX > getX()){
					for (int i = getX() + 1; i < toX; i++)
						if (board.getPiece(i, toY) != null)
							return false;
				} else {
					for (int i = getX() - 1; i > toX; i--)
						if (board.getPiece(i, toY) != null)
							return false;
				}
			} else {
				System.out.println("dest has pieces");
				int cnt = 0;
				if (toX > getX()){
					for (int i = getX() + 1; i < toX; i++)
						if (board.getPiece(i, toY) != null)
							cnt++;
				} else {
					for (int i = getX() - 1; i > toX; i--)
						if (board.getPiece(i, toY) != null)
							cnt++;
				}
				if (cnt != 1)	return false;
			}
		} else {
			if (board.getPiece(toX, toY) == null){
				System.out.println("dest has no pieces");
				if (toY > getY()){
					for (int i = getY() + 1; i < toY; i++)
						if (board.getPiece(toX, i) != null)
							return false;
				} else {
					for (int i = getY() - 1; i > toY; i--)
						if (board.getPiece(toX, i) != null)
							return false;
				}
			} else {
				System.out.println("dest has pieces");
				int cnt = 0;
				if (toY > getY()){
					for (int i = getY() + 1; i < toY; i++)
						if (board.getPiece(toX, i) != null)
							cnt++;
				} else {
					for (int i = getY() - 1; i > toY; i--)
						if (board.getPiece(toX, i) != null)
							cnt++;
				}
				if (cnt != 1)	return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see pieces.ChessPieces#getNextMoveList(board.ChessBoard)
	 */
//	@Override
//	public List<Step> getNextMoveList(ChessBoard board) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
