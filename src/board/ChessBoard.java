/**
 * 
 */
package board;
import java.util.*;
import AI.*;
import AI.ChessEngine.Level;
import pieces.*;
import step.*;
/**
 * 棋盘主类，包含棋盘所有信息 
 * @author gclx
 * @version 1.0
 * @date 2018年11月15日 下午12:44:23
 * @copyright 
 * @remarks TODO
 *
 */
public class ChessBoard {
	//当前棋局状态
	public static enum State{REDWIN, BLACKWIN, DRAW, UNFINISH};
	private State state;
	
	// 棋盘信息
	private ChessPieces[][] board;
	
	// AI对象
	private ChessEngine AI;
	
	// 当前行棋方，True则为红方行棋，暂定用户为红方，先行，
	private boolean isRedMove;
	
	// 棋盘最大行数
	static public final int MAXROW = 10;
	// 棋盘最大列数
	static public final int MAXCOL = 9;
	// 棋盘最多棋子数
	static public final int MAXPIECE = 32;
	
	// 记录棋子轨迹
	public Stack<StepTrack> stepTracks;
	
	/**
	 * 构造函数
	 */
	public ChessBoard(ChessPieces[][] board, boolean isRedMove) {
		// TODO Auto-generated constructor stub
		setBoard(board);
		setRedMove(isRedMove);
		setState(State.UNFINISH);
	}
	
	public ChessBoard() {
		// TODO Auto-generated constructor stub
		board = new ChessPieces[MAXROW][MAXCOL];
		AI = new ChessEngine();
		init();
	}
	
	/**
	 * 初始化棋盘类，红方先行，状态为UNFINISH
	 */
	private void init(){
		stepTracks = new Stack<StepTrack>();
		setRedMove(true);
		setState(State.UNFINISH);
		initPieces();
	}
	
	/**
	 * 用户行棋函数
	 * @param step
	 * @return
	 */
	public boolean update(Step step){
		if (isMoveLegal(step)){			//如果移动合法
			try {
				pushPath(step);
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("测试行棋");
			move(step);					//行棋
			System.out.println("行棋完毕");
			// 把这个放在move（）里更方便
			//isRedMove = !isRedMove;		//更改当前行棋方
			AI.applyMove(step);			//更新AI棋盘状态
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param step
	 */
	private void move(Step step) {
		// 如果有吃子发生
		if (board[step.getToX()][step.getToY()] != null){
			System.out.println("(" + step.getToX() + ", " + step.getToY() + ")处的 " + board[step.getToX()][step.getToY()].getName() + "被吃了");
			board[step.getToX()][step.getToY()].setAlive(false);
			System.out.println(board[step.getToX()][step.getToY()].getName());
			if (board[step.getToX()][step.getToY()].getName().equals("BKing")){
				System.out.println("state has been set for RedWin");
				state = State.REDWIN;
			} else if (board[step.getToX()][step.getToY()].getName().equals("RKing")){
				System.out.println("state has been set for BlackWin");
				state = State.BLACKWIN;
			} else {
				System.out.println("equals nothing");
			}
		}
		// 移动棋子
		board[step.getFromX()][step.getFromY()].move(step.getToX(), step.getToY());
		board[step.getToX()][step.getToY()] = board[step.getFromX()][step.getFromY()];
		// 清楚原位置棋子
		board[step.getFromX()][step.getFromY()] = null;
		isRedMove = !isRedMove;
	}

	/**
	 * @param step
	 * @return
	 */
	public boolean isMoveLegal(Step step) {
		System.out.println("测试合法性检验");
		boolean isLegal = false;
		//直接调用ChessPieces类中的函数判断
		if (step == null){
			System.out.println("step == null");
		}
		System.out.println("(" + step.getFromX() + ", " + step.getFromY() + ")");
		if (board[step.getFromX()][step.getFromY()] != null){
			System.out.println("初始位置不空，开始检测");
			System.out.println(board[step.getFromX()][step.getFromY()].toString());
			isLegal = board[step.getFromX()][step.getFromY()].isLegalMove(this, step.getToX(), step.getToY());
		}
		System.out.println("合法性检验完毕! isLegal = " + isLegal);
		return isLegal;
	}

	/**
	 * 检查是否终局
	 * @return
	 */
	public State isEnd(){
		return state;
	}
	
	/**
	 * 悔棋功能相关，暂不实现
	 * @param step
	 * @throws CloneNotSupportedException
	 */
	void pushPath(Step step) throws CloneNotSupportedException{
		
	}

	/**
	 * 实现悔棋功能，暂不实现
	 */
	public void back(){
		
	}
	
	//	以下为AI行棋函数
	
	/**
	 * 询问AI下一步的走法
	 * @return
	 */
	public Step AIMove(){
		Step step = AI.generateAMove();
		System.out.println("(" + step.getFromX() + ", " + step.getFromY() + ") -> (" + step.getToX() + ", " + step.getToY() + ")");
		boolean isLegal = update(step);
		if (!isLegal){			// 只要其他函数没问题，这种情况应该是不会出现的，可视情况删去
			System.out.println("AI gives an illegal move!");
			System.exit(-1);
		}
		return step;
	}
	
	/**
	 * 设置AI的难度
	 * @param level
	 */
	public void setLevel(Level level){
		AI.setLevel(level);
	}
	
	/**
	 * 根据坐标返回棋子，若无棋子则返回null
	 * @param x
	 * @param y
	 * @return
	 */
	public ChessPieces getPiece(int x, int y){
		return board[x][y];
	}
	
	/**
	 * 打印当前棋盘情况，用于观察调试等
	 */
	public void show(){
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 9; j++)
				if (board[i][j] != null){
					System.out.print(board[i][j].getName() + " ");
				} else {
					System.out.print("null ");
				}
			System.out.println("");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~存活情况~~~~~~~~~~~~~~~~~~~~~");
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 9; j++)
				if (board[i][j] != null){
					System.out.print(board[i][j].isAlive() + " ");
				} else {
					System.out.print("false ");
				}
			System.out.println("");
		}
	}
	
	/**
	 * 32颗棋子的初始化
	 */
	private void initPieces(){
		board[0][0] = new Rook(0, 0, true, false, "BRook");
		board[0][8] = new Rook(0, 8, true, false, "BRook");
		board[0][1] = new Horse(0, 1, true, false, "BHorse");
		board[0][7] = new Horse(0, 7, true, false, "BHorse");
		board[0][2] = new Elephant(0, 2, true, false, "BElephant");
		board[0][6] = new Elephant(0, 6, true, false, "BElephant");
		board[0][3] = new Advisor(0, 3, true, false, "BAdvisor");
		board[0][5] = new Advisor(0, 5, true, false, "BAdvisor");
		board[0][4] = new BlackKing(0, 4, true, false, "BKing");
		board[2][1] = new Cannon(2, 1, true, false, "BCannon");
		board[2][7] = new Cannon(2, 7, true, false, "BCannon");
		for (int i = 0; i < ChessBoard.MAXCOL; i += 2){
			board[3][i] = new Soldiers(3, i, true, false, "BSoldier");
		}
		
		board[9][0] = new Rook(9, 0, true, true, "RRook");
		board[9][8] = new Rook(9, 8, true, true, "RRook");
		board[9][1] = new Horse(9, 1, true, true, "RHorse");
		board[9][7] = new Horse(9, 7, true, true, "RHorse");
		board[9][2] = new Elephant(9, 2, true, true, "RElephant");
		board[9][6] = new Elephant(9, 6, true, true, "RElephant");
		board[9][3] = new Advisor(9, 3, true, true, "RAdvisor");
		board[9][5] = new Advisor(9, 5, true, true, "RAdvisor");
		board[9][4] = new RedKing(9, 4, true, true, "RKing");
		board[7][1] = new Cannon(7, 1, true, true, "RCannon");
		board[7][7] = new Cannon(7, 7, true, true, "RCannon");
		for (int i = 0; i < ChessBoard.MAXCOL; i += 2){
			board[6][i] = new Soldiers(6, i, true, true, "RSoldier");
		}
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public ChessPieces[][] getBoard() {
		return board;
	}

	public void setBoard(ChessPieces[][] board) {
		this.board = board;
	}

	public ChessEngine getAI() {
		return AI;
	}

	public void setAI(ChessEngine aI) {
		AI = aI;
	}

	public boolean isRedMove() {
		return isRedMove;
	}

	public void setRedMove(boolean isRedMove) {
		this.isRedMove = isRedMove;
	}
	
	public static void main(String[] args) {
//		System.out.println((1 << 7));
//		chessBoard.show();
//		Step step = new Step(2, 1, 9, 1);
//		chessBoard.update(step);
//		chessBoard.show();
//		List<Step> list = chessBoard.getPiece(0, 7).getNextMoveList(chessBoard);
//		if (list == null) System.out.println("null");
//		for (int i = 0; i < list.size(); i++){
//			System.out.println("(" + list.get(i).getToX() + ", " + list.get(i).getToY() + ")");
//		}
		Scanner input = new Scanner(System.in);
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.init();
		System.out.println("test");
//		chessBoard.isRedMove = !chessBoard.isRedMove;
//		chessBoard.AI.position.setRedMove(!chessBoard.AI.position.isRedMove());
		while (chessBoard.isEnd() == State.UNFINISH){
//			chessBoard.AIMove();
			display(chessBoard);
			int fromx = 0;
			int fromy = 0;
			int tox = 0;
			int toy = 0;
			int cnt = 0;
			Step step;
			do {
				if (cnt == 0){
					System.out.println("开始输入：");
				} else {
					System.out.println("请按象棋规则行棋！");
				}
				fromx = input.nextInt();
				fromy = input.nextInt();
				tox = input.nextInt();
				toy = input.nextInt();
				step = new Step(fromx, fromy, tox, toy);
				cnt++;
			} while (chessBoard.update(step) == false);
			System.out.println(chessBoard.getState());
//			chessBoard.update(step);
//			chessBoard.AI.applyMove(step);
//			chessBoard.setRedMove(!chessBoard.isRedMove);
			chessBoard.AIMove();
			//chessBoard.setRedMove(!chessBoard.isRedMove);
				
		}
		System.out.println(chessBoard.getState());
		input.close();
	}
/*
  7 1 7 4
  9 8 9 7 
  7 4 3 4
  7 7 7 4
  7 4 3 4
  3 4 3 0
  3 0 3 6
  3 6 0 6
  0 6 0 4
  
 */
	
	public static void display(ChessBoard chessBoard){
		for (int i = 3 * 16; i <= Board.BOARD_SIZE - (Board.MARGIN_D - 1) * 16; i ++) {
			if (i % 16 == 0) {
				System.out.println();
				System.out.println();
			}
			if (i % 16 < 3 || i % 16 > 11){
				continue;
			}
			int piece = chessBoard.AI.position.getBoard()[i];
			int pieceType = Board.getPieceType(piece);
			switch (pieceType){
				case 0: 
					System.out.print("0" + '\t');
					break;
				case 1:
//					if (Board.isRedSide(piece)){
//						System.err.print("帅\t");
//					} else {
//						System.out.print("将\t");
//					}
					System.out.print((Board.isRedSide(piece) ? "帅" : "将") + '\t');
					break;
				case 2:
//					if (Board.isRedSide(piece)){
//						System.err.print("士\t");
//					} else {
//						System.out.print("士\t");
//					}
					System.out.print((Board.isRedSide(piece) ? "红士" : "黑士") + '\t');
					break;
				case 3:
//					if (Board.isRedSide(piece)){
//						System.err.print("相\t");
//					} else {
//						System.out.print("象\t");
//					}
					System.out.print((Board.isRedSide(piece) ? "红相" : "黑象") + '\t');
					break;
				case 4:
//					if (Board.isRedSide(piece)){
//						System.err.print("馬\t");
//					} else {
//						System.out.print("马\t");
//					}
					System.out.print((Board.isRedSide(piece) ? "红馬" : "黑马") + '\t');
					break;
				case 5:
//					if (Board.isRedSide(piece)){
//						System.err.print("車\t");
//					} else {
//						System.out.print("車\t");
//					}
					System.out.print((Board.isRedSide(piece) ? "红車" : "黑车") + '\t');
					break;
				case 6:
//					if (Board.isRedSide(piece)){
//						System.err.print("炮\t");
//					} else {
//						System.out.print("炮\t");
//					}
					System.out.print((Board.isRedSide(piece) ? "红炮" : "黑炮") + '\t');
					break;
				case 7:
//					if (Board.isRedSide(piece)){
//						System.err.print("兵\t");
//					} else {
//						System.out.print("卒\t");
//					}
					System.out.print((Board.isRedSide(piece) ? "红兵" : "黑卒") + '\t');
					break;
				default:
					System.out.print("Error!");
			}
		}
	}
}
