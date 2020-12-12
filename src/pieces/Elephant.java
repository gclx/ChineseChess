/**
 * 
 */
package pieces;

import java.util.List;

import board.ChessBoard;
import step.Step;

/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年11月15日 下午15:30:11
 * @copyright 
 * @remarks TODO
 *
 */
public class Elephant extends ChessPieces{

	/**
	 * @param x
	 * @param y
	 * @param isalive
	 * @param isred
	 * @param name
	 */
	public Elephant(int x, int y, boolean isalive, boolean isred, String name) {
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
		if (getX() == toX || getY() == toY)	return false;
		System.out.println("check move successfully!");
		if (!isDestLegal(board, toX, toY))	return false;
		System.out.println("check destination successfully!");
		if (Math.abs(toX - getX()) != 2 || Math.abs(toY - getY()) != 2)	return false;
		if (board.getPiece((toX + getX()) / 2, (toY + getY()) /2) != null)	return false;
		if (isRed() && toX < 5)	return false;
		if (!isRed() && toX > 4) return false;
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
