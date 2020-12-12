/**
 * 
 */
package AI;

import board.*;
import step.Step;
/**
 * AI引擎 
 * @author gclx
 * @version 1.0
 * @date 2018年11月15日 下午15:50:28
 * @copyright 
 * @remarks TODO
 *
 */
public class ChessEngine {
	public static enum Level {ONE, TWO, THREE};
	public Position position;
	
	// 是否已经开始下棋,界面选择后手的时候会用到
	private boolean flag = false;
	
	public ChessEngine() {
		// TODO Auto-generated constructor stub
		position = new Position();
		Search.setThinkingTime(1000);
	}
	
	public void setLevel(Level level){
		switch(level){
			case ONE:
				Search.setThinkingTime(1000);
				break;
			case TWO:
				Search.setThinkingTime(2000);
				break;
			case THREE:
				Search.setThinkingTime(3000);
				break;
			default:
				break;
		}
	}

	/**
	 * AI走一步棋
	 * @return
	 */
	public Step generateAMove() {
		int bestStep = Search.mainSearch(position);
		int from = MoveGenerator.getFromLoc(bestStep);
		int to = MoveGenerator.getToLoc(bestStep);
//		assert (Board.inBoard(from));
//		assert (Board.inBoard(to));
		Step step = transformStep(from, to);
		setFlag(true);
		return step;
	}

	/**
	 * 将用户行棋更新至AI棋盘中
	 * @param step
	 */
	public void applyMove(Step step) {
		// TODO Auto-generated method stub
		int fromX = step.getFromX();
		int fromY = step.getFromY();
		int toX = step.getToX();
		int toY = step.getToY();
		int from = (((fromX + Board.MARGIN_U) << 4) | (fromY + Board.MARGIN_L));
		int to = (((toX + Board.MARGIN_U) << 4) | (toY + Board.MARGIN_L));
		position.move(from, to);
	}
	
	/**
	 * @param from
	 * @param to
	 * @return
	 */
	private Step transformStep(int from, int to) {
		// TODO Auto-generated method stub
		if (Board.inBoard(from) == false || Board.inBoard(to) == false){
			System.out.println("生成的步法不在棋盘内，估计写了bug");
			System.exit(-1);
		}
		int fromX = Board.getRow(from) - Board.MARGIN_U;
		int fromY = Board.getCol(from) - Board.MARGIN_L;
		int toX = Board.getRow(to) - Board.MARGIN_U;
		int toY = Board.getCol(to) - Board.MARGIN_L;
		return new Step(fromX, fromY, toX, toY);
	}
	
	// 测试函数
	public static void main(String[] args) {
		ChessEngine AI = new ChessEngine();
		Step step = AI.transformStep(55, 88);
		System.out.println("(" + step.getFromX() + ", " + step.getFromY() + ") to ("
							+ step.getToX() + ", " + step.getToY() + ")");
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
