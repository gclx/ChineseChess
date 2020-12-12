/**
 * 
 */
package pieces;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.ReferenceType;

import board.ChessBoard;
import step.Step;

/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年11月15日 下午15:29:00
 * @copyright 
 * @remarks TODO
 *
 */
public class Horse extends ChessPieces{

	/**
	 * @param x
	 * @param y
	 * @param isalive
	 * @param isred
	 * @param name
	 */
	public Horse(int x, int y, boolean isalive, boolean isred, String name) {
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
		if (Math.pow(toX - getX(), 2) + Math.pow(toY - getY(), 2) != 5)	return false;		//似乎可以省略，步法构造应该已经保证满足
		//马脚
		if (Math.abs(getX() - toX) == 2){
			if (board.getPiece((getX() + toX) / 2, getY()) != null){
				return false;
			}
		}
		if (Math.abs(getY() - toY) == 2){
			if (board.getPiece(getX(), (getY() + toY) / 2) != null){
				return false;
			}
		}
		System.out.println("check whether can go successfully!");
		return true;
	}

	/* (non-Javadoc)
	 * @see pieces.ChessPieces#getNextMoveList(board.ChessBoard)
	 */
	//@Override
//	public List<Step> getNextMoveList(ChessBoard board) {
//		// TODO Auto-generated method stub
//		List<Step> list = new ArrayList<Step>();
//		int[][] nstep = { {-2, +1}, {-1, +2}, {+1, +2}, {+2, +1}, {+2, -1}, {+1, -2}, {-1, -2}, {-2, -1}};
//		for (int i = 0; i < 8; i++){
//			System.out.print(i + " start: ");
//			System.out.print("(" + (getX() + nstep[i][0]) + ", " + (getY() + nstep[i][1]) + ")");
//			if (isLegalMove(board, getX() + nstep[i][0], getY() + nstep[i][1])){
//				list.add(new Step(getX(), getY(), getX() + nstep[i][0], getY() + nstep[i][1]));
//				System.out.println(" is legal");
//			} else {
//				System.out.println(" is illegal!");
//			}
//		}
//		return list;
//	}
}
