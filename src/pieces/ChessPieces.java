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
 * @date 2018��11��15�� ����12:57:37
 * @copyright 
 * @remarks TODO
 *
 */
public abstract class ChessPieces {//implements Cloneable{
	// ���ӵ�����
	private int x;
	private int y;
	// �����Ƿ���
	private boolean isAlive;
	// �����������Ƿ�Ϊ��
	private boolean isRed;
	// ���ӵ�����
	private String name;

	/**
	 * ���췽����������
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
	 * �����Ƿ�Ϸ������жϺ�ڷ���
	 * @param board
	 * @param toX
	 * @param toY
	 * @return 
	 */
	abstract public boolean isLegalMove(ChessBoard board, int toX, int toY);
	
	/**
	 * ��ѯ�ƶ��������������������߷��� ͻȻ���ֺ���ûɶ��
	 * @param board
	 * @return 
	 */
	//abstract public List<Step> getNextMoveList(ChessBoard board);
	
	/**
	 * �жϸ����ӵ�ǰ�Ƿ񽫶Է��ľ�������V2.0ʵ��
	 * @param board
	 * @return 
	 */
	//abstract public boolean isCheck(ChessBoard board);
	
	/**
	 * �ж����ӵ�ǰλ���Ƿ�Ϸ���Ӧ�� isLegalMove ����
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isPointLegal(int x, int y){
		return x >= 0 && x < ChessBoard.MAXROW && y >= 0 && y < ChessBoard.MAXCOL;
	}
	
	/**
	 * �ж��������Ƿ���ͬһ��
	 * @param aPieces
	 * @param bPieces
	 * @return
	 */
	public static boolean isSameSide(ChessPieces aPieces, ChessPieces bPieces){
		return aPieces.isRed == bPieces.isRed;
	}
	
	/**
	 * �жϸ���������غ��ǲ��Ǳ�������
	 * @param board
	 * @return
	 */
	protected boolean isMyTurnToMove(ChessBoard board){
		return board.isRedMove() == isRed();
	}
	
	/**
	 * �ж�ֱ�����ж������ӣ������ж�ֱ������ʱ�Ƿ��Խ����
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
	 * �ж�Ŀ�ĵ��Ƿ������ӣ����У��Ƿ�Ϊ��������
	 * @param board
	 * @param toX
	 * @param toY
	 * @return
	 */
	protected boolean isDestLegal(ChessBoard board, int toX, int toY) {
		if (board.getPiece(toX, toY) == null || (!isSameSide(board.getPiece(toX, toY), this))){
			return true;
		} else {
			System.out.println("����������: " + isRed);
			System.out.println("Ŀ�ĵ���������: " + board.getPiece(toX, toY).isRed);
			return false;
		}
	}
	
	/**
	 * ��ֱ�ƶ�ʱ·���ϵ�������
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
	 * ˮƽ�ƶ�ʱ·���ϵ�������
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
	 * ��������������Ϣ
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
