/**
 * 
 */
package step;
import pieces.ChessPieces;

/**
 * ����켣�࣬���ڼ�¼ÿһ�����߷�����List�����������ڻ���ѡ��
 * @author gclx
 * @version 1.0
 * @date 2018��11��15�� ����13:52:28
 * @copyright 
 * @remarks TODO
 *
 */
public class StepTrack {
	private ChessPieces from;
	private ChessPieces to;
	private int fromX, fromY;
	private int toX, toY;
	
	/**
	 * @param from
	 * @param to
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 */
	public StepTrack(ChessPieces from, ChessPieces to, int fromX, int fromY, int toX, int toY) {
		setFrom(from);
		setTo(to);
		setFromX(fromX);
		setFromY(fromY);
		setToX(toX);
		setToY(toY);
	}
	
	public ChessPieces getFrom() {
		return from;
	}
	public void setFrom(ChessPieces from) {
		this.from = from;
	}
	public ChessPieces getTo() {
		return to;
	}
	public void setTo(ChessPieces to) {
		this.to = to;
	}
	public int getFromX() {
		return fromX;
	}
	public void setFromX(int fromX) {
		this.fromX = fromX;
	}
	public int getFromY() {
		return fromY;
	}
	public void setFromY(int fromY) {
		this.fromY = fromY;
	}
	public int getToX() {
		return toX;
	}
	public void setToX(int toX) {
		this.toX = toX;
	}
	public int getToY() {
		return toY;
	}
	public void setToY(int toY) {
		this.toY = toY;
	}
	
}
