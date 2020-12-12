/**
 * 
 */
package pieces;

import java.awt.Toolkit;
import java.util.List;

import board.ChessBoard;
import step.Step;

/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年11月15日 下午15:30:39
 * @copyright 
 * @remarks TODO
 *
 */
public class Advisor extends ChessPieces{

	/**
	 * @param x
	 * @param y
	 * @param isalive
	 * @param isred
	 * @param name
	 */
	public Advisor(int x, int y, boolean isalive, boolean isred, String name) {
		super(x, y, isalive, isred, name);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see pieces.ChessPieces#isLegalMove(board.ChessBoard, int, int)
	 */
	@Override
	public boolean isLegalMove(ChessBoard board, int toX, int toY) {
		// TODO Auto-generated method stub
		if (!isPointLegal(toX, toY))	return false;
		System.out.println("check point successfully!");
		if (!isMyTurnToMove(board))	return false;
		System.out.println("check turn successfully!");
		if (getX() == toX || getY() == toY)	return false;
		System.out.println("check move successfully!");
		if (!isDestLegal(board, toX, toY))	return false;
		System.out.println("check destination successfully!");
		if (Math.abs(toX - getX()) != 1 || Math.abs(toY - getY()) != 1)	return false;
		if (isRed() && (toX < 7 || toY > 5 || toY < 3))	return false;
		if (!isRed() && (toX > 2 || toY > 5 || toY < 3))	return false;
		System.out.println("check whether can go successfully!");
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
