/**
 * 
 */
package step;

/**
 * 步法信息，从（fromX，fromY）走到（toX，toY） 
 * @author gclx
 * @version 1.0
 * @date 2018年11月15日 下午13:38:22
 * @copyright 
 * @remarks TODO
 *
 */
public class Step {
	//步法描述
	private int fromX, fromY, toX, toY;
	
	/**
	 * 构造该步法
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 */
	public Step(int fromX, int fromY, int toX, int toY) {
		setFromX(fromX);
		setFromY(fromY);
		setToX(toX);
		setToY(toY);		
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
