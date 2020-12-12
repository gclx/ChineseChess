/**
 * 
 */
package pieces;
import java.util.*;

import board.ChessBoard;
import step.Step;
/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年11月15日 下午12:57:37
 * @copyright 
 * @remarks TODO
 *
 */
public abstract class ChessPieces {//implements Cloneable{
	// 棋子的坐标
	private int x;
	private int y;
	// 棋子是否存活
	private boolean isAlive;
	// 棋子所属方是否为红
	private boolean isRed;
	// 棋子的名字
	private String name;

	/**
	 * 构造方法，不解释
	 * @param x
	 * @param y
	 * @param isalive
	 * @param isred
	 * @param name
	 */
	public ChessPieces(int x, int y, boolean isalive, boolean isred, String name){
		setAlive(isalive);
		setName(name);
		setRed(isred);
		setX(x);
		setY(y);
	}
	
	public ChessPieces clone() throws CloneNotSupportedException{
		return (ChessPieces)super.clone();
	}

	/**
	 * 行棋是否合法（需判断红黑方）
	 * @param board
	 * @param toX
	 * @param toY
	 * @return 
	 */
	abstract public boolean isLegalMove(ChessBoard board, int toX, int toY);
	
	/**
	 * 查询移动该棋子所产生的所有走法， 突然发现好像没啥用
	 * @param board
	 * @return 
	 */
	//abstract public List<Step> getNextMoveList(ChessBoard board);
	
	/**
	 * 判断该棋子当前是否将对方的军，可在V2.0实现
	 * @param board
	 * @return 
	 */
	//abstract public boolean isCheck(ChessBoard board);
	
	/**
	 * 判断棋子当前位置是否合法，应和 isLegalMove 连用
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isPointLegal(int x, int y){
		return x >= 0 && x < ChessBoard.MAXROW && y >= 0 && y < ChessBoard.MAXCOL;
	}
	
	/**
	 * 判断两棋子是否是同一方
	 * @param aPieces
	 * @param bPieces
	 * @return
	 */
	public static boolean isSameSide(ChessPieces aPieces, ChessPieces bPieces){
		return aPieces.isRed == bPieces.isRed;
	}
	
	/**
	 * 判断该棋子在这回合是不是本方棋子
	 * @param board
	 * @return
	 */
	protected boolean isMyTurnToMove(ChessBoard board){
		return board.isRedMove() == isRed();
	}
	
	/**
	 * 判断直线上有多少棋子，用于判断直线行走时是否跨越棋子
	 * @param board
	 * @param toX
	 * @param toY
	 * @return
	 */
	protected int piecesInStraightWay(ChessBoard board, int toX, int toY) {
		if (toX == this.x)
			return piecesInHerizontalWay(board, toX, toY);
		else
			return piecesInVerticalWay(board, toX, toY);
	}
	
	/**
	 * 判断目的地是否有棋子，若有，是否为本方棋子
	 * @param board
	 * @param toX
	 * @param toY
	 * @return
	 */
	protected boolean isDestLegal(ChessBoard board, int toX, int toY) {
		if (board.getPiece(toX, toY) == null || (!isSameSide(board.getPiece(toX, toY), this))){
			return true;
		} else {
			System.out.println("该棋子所属: " + isRed);
			System.out.println("目的地棋子所属: " + board.getPiece(toX, toY).isRed);
			return false;
		}
	}
	
	/**
	 * 竖直移动时路径上的棋子数
	 * @param board
	 * @param toX
	 * @param toY
	 * @return
	 */
	private int piecesInVerticalWay(ChessBoard board, int toX, int toY) {
		int cnt = 0;
		if (toX > x){
			for (int i = x + 1; i < toX; i++){
				if (board.getPiece(i, y) != null)
					++cnt;
			}
		} else {
			for (int i = x - 1; i > toX; i--){
				if (board.getPiece(i, y) != null)
					++cnt;
			}
		}
		return cnt;
	}

	/**
	 * 水平移动时路径上的棋子数
	 * @param board
	 * @param toX
	 * @param toY
	 * @return
	 */
	private int piecesInHerizontalWay(ChessBoard board, int toX, int toY) {
		int cnt = 0;
		if (toY > y){
			for (int i = y + 1; i < toY; i++){
				if (board.getPiece(x, i) != null)
					++cnt;
			}
		} else {
			for (int i = y - 1; i > toY; i--){
				if (board.getPiece(x, i) != null)
					++cnt;
			}
		}
		return cnt;
	}

	/**
	 * 行棋后更新棋子信息
	 * @param toX
	 * @param toY
	 */
	public void move(int toX, int toY){
		setX(toX);
		setY(toY);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public boolean isRed() {
		return isRed;
	}
	public void setRed(boolean isRed) {
		this.isRed = isRed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
