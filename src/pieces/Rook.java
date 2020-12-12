/**
 * 
 */
package pieces;

import java.util.List;

import org.omg.CORBA.IdentifierHelper;

import board.ChessBoard;
import step.Step;

/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年11月15日 下午15:25:08
 * @copyright 
 * @remarks TODO
 *
 */
public class Rook extends ChessPieces{

	/**
	 * @param x
	 * @param y
	 * @param isalive
	 * @param isred
	 * @param name
	 */
	public Rook(int x, int y, boolean isalive, boolean isred, String name) {
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
			if (toX > getX()){
				for (int i = getX() + 1; i < toX; i++)
					if (board.getPiece(i, getY()) != null)
						return false;
			} else {
				for (int i = getX() - 1; i > toX; i--)
					if (board.getPiece(i, getY()) != null)
						return false;
			}
		} else {
			if (toY > getY()){
				for (int i = getY() + 1; i < toY; i++)
					if (board.getPiece(getX(), i) != null)
						return false;
			} else {
				for (int i = getY() - 1; i > toY; i--)
					if (board.getPiece(getX(), i) != null)
						return false;
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
