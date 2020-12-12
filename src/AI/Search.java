/**
 * 
 */
package AI;

import AI.TransPositionRecord;

/**
 * 搜索类 
 * @author gclx
 * @version 1.0
 * @date 2018年11月18日 下午2:08:23
 * @copyright 
 * @remarks TODO
 *
 */
public class Search {
	// 搜索时间
	private static int thinkingTime = 1000;
	// 无棋可走的编号
	public static final int NO_LEGAL_MOVE = 0;
	// 空着裁剪步数
	public static final int NULL_MOVE_SKIP = 2;
	
	public static final int NULL_MOVE_MARGIN = 400;
	
	public static long timeCost = 0;
	private static long count = 0;
	private static long nullCutTries = 0;
	private static long nullCutSuccessTime = 0;
	private static long PVCutTries = 0;
	private static long PVCutFailTime = 0;
	private static long rootBestValue = 0;
	
	
	public static void setThinkingTime(int mils){
		thinkingTime = mils;
	}

	/**
	 * 针对当前局面返回最佳着法
	 * @param position
	 * @return
	 */
	public static int mainSearch(Position position) {
		System.out.println("Enter mainSearch:");
		// TODO Auto-generated method stub
		// 初始化监视变量
		initWatchVar();
		TranspositionTable.initWatchVar();
		// 清空历史表
		HistoryTable.clear();
		
		long beginTime = System.currentTimeMillis();
		int bestStep = NO_LEGAL_MOVE;
		int depth = 0;
		for (depth = 1; ; depth++){
//			System.out.println("depth: " + depth);
			bestStep = searchRoot(depth, -Evalutor.WIN_VALUE, Evalutor.WIN_VALUE, position);
			timeCost = System.currentTimeMillis() - beginTime;
			if (timeCost > thinkingTime){
				break;
			}
		}
		
		//监控
		System.out.println("  deepest depth " + depth);
		System.out.println("  Total nodes searched: " + count);
		System.out.println("  Time: " + (1.0 * timeCost / 1000.0));
		System.out.println("  PV cut tries: " + PVCutTries);
		System.out.println("  PV cut succeeds: " + (PVCutTries - PVCutFailTime));
		System.out.println("  PV cut success ratio: " + (1.0 * (PVCutTries - PVCutFailTime) / PVCutTries));
		System.out.println("  Null cut tries: " + nullCutTries);
		System.out.println("  Null cut succeeds: " + nullCutSuccessTime);
		System.out.println("  Null cut seccess ratio: " + (1.0 * nullCutSuccessTime / nullCutTries));
		System.out.println("  Best move value: " + rootBestValue);
		
		TranspositionTable.display();
		
		return bestStep;
	}

	/**
	 * 根据搜索层数返回最佳着法
	 * @param depth
	 * @param alpha
	 * @param beta
	 * @param position
	 * @return
	 */
	private static int searchRoot(int depth, int alpha, int beta, Position position) {
		// TODO Auto-generated method stub
		//System.out.println("Enter searchRoot: ");
		++ count;
		
		if (position.isEnd()){
			return NO_LEGAL_MOVE;
		}
		int bestVal = -Evalutor.WIN_VALUE;
		int bestMove = NO_LEGAL_MOVE;
		// 根节点禁止空着裁剪
		Pruner pruner = new Pruner(position, depth, 0, alpha, beta);
		// 检查置换表是否存在满足当前深度的记录
		if (pruner.isDeeplySearched()){
			bestMove = pruner.getBestMove();
			rootBestValue = pruner.getAdjustedValue();
			return bestMove;
		}
		for (int step = pruner.getAStep(); step != NO_LEGAL_MOVE; step = pruner.getAStep()){
			int from = MoveGenerator.getFromLoc(step);
			int to = MoveGenerator.getToLoc(step);
			position.move(from, to);
			
			if (position.isFaceKing()){
				position.undomove();
				continue;
			}
			
			int val;
			// 未找到PV节点，则做正常的α-β搜索
			if (bestVal < alpha || bestMove == NO_LEGAL_MOVE){
				val = -PVSearch(depth - 1, -beta, -alpha, position, 1);
			} else {	// 否则，只需检查当前着法是否比PV着法好	
				PVCutTries++;
				val = -limitSearch(depth - 1, -alpha, position, 1, true);
				// 如果当前着法更好
				if (val > alpha){
					++ PVCutFailTime;
					val = -PVSearch(depth - 1, -beta, -alpha, position, 1);
				}
			}
			position.undomove();
			if (val >= beta){
				pruner.saveRecord(TransPositionRecord.NodeType.BETA, val, step);
				rootBestValue = val;
				return step;
			}
			if (val > bestVal){
				bestVal = val;
				bestMove = step;
				if (val > alpha){
					alpha = val;
				}
			}
		}
		
		// 记录当前局面
		if (bestVal >= alpha){
			pruner.saveRecord(TransPositionRecord.NodeType.PVS, bestVal, bestMove);
		} else {
			pruner.saveRecord(TransPositionRecord.NodeType.ALPHA, bestVal, bestMove);
		}
		rootBestValue = bestVal;
		return bestMove;
	}

	/**
	 * @param depth
	 * @param beta
	 * @param position
	 * @param curDepth
	 * @param allowNullCut
	 * @return
	 */
	private static int limitSearch(int depth, int beta, Position position, int curDepth, boolean allowNullCut) {
		// TODO Auto-generated method stub
		count ++;
		
		if (position.isEnd()){
			return - Evalutor.WIN_VALUE + curDepth;
		}
		Pruner pruner = new Pruner(position, depth, curDepth, beta - 1, beta);
		if (pruner.isDeeplySearched()){
			return pruner.getAdjustedValue();
		}
		
		if (depth <= 0){
			return position.evaluate();
		}
		int bestVal = -Evalutor.WIN_VALUE;
		int bestMove = NO_LEGAL_MOVE;
		
		// 尝试空着裁剪
		if (allowNullCut && beta != Evalutor.WIN_VALUE && doAllowNullMove(position) && !position.isChecked()){
			position.makeNullMove();
			nullCutTries++;
			
			int val = -limitSearch(depth - 1 - NULL_MOVE_SKIP, -beta + 1, position, curDepth + 1, false);
			position.undoNullMove();
			if (val >= beta){
				nullCutSuccessTime++;
				pruner.saveRecord(TransPositionRecord.NodeType.BETA, val, 0);
				return val;
			}
		}
		
		for (int step = pruner.getAStep(); step != NO_LEGAL_MOVE; step = pruner.getAStep()){
			int from = MoveGenerator.getFromLoc(step);
			int to = MoveGenerator.getToLoc(step);
			position.move(from, to);

			if (position.isFaceKing()){
				position.undomove();
				continue;
			}
			
			int val = -limitSearch(depth - 1, -beta + 1, position, curDepth + 1, true);
			position.undomove();
			
			if (val >= beta){
				pruner.saveRecord(TransPositionRecord.NodeType.BETA, val, step);
				return val;
			}
			if (val > bestVal){
				bestVal = val;
				bestMove = step;
			}
		}
		
		// 记录当前局面
		pruner.saveRecord(TransPositionRecord.NodeType.ALPHA, bestVal, bestMove);
		return bestVal;
	}

	/**
	 * @param position
	 * @return
	 */
	private static boolean doAllowNullMove(Position position) {
		// TODO Auto-generated method stub
		return (position.isRedMove() ? position.getEvaluation()[Board.RED_SIDE] : position.getEvaluation()[Board.BLACK_SIDE]) >= NULL_MOVE_MARGIN;
	}

	/**
	 * @param depth
	 * @param alpha
	 * @param beta
	 * @param position
	 * @param curDepth
	 * @return
	 */
	private static int PVSearch(int depth, int alpha, int beta, Position position, int curDepth) {
		// TODO Auto-generated method stub
		count ++;
		if (position.isEnd()){
			return - Evalutor.WIN_VALUE + curDepth;
		}
		Pruner pruner = new Pruner(position, depth, curDepth, alpha, beta);
		if (pruner.isDeeplySearched()){
			return pruner.getAdjustedValue();
		}
		
		if (depth == 0){
			return position.evaluate();
		}
		int bestVal = -Evalutor.WIN_VALUE;
		int bestMove = NO_LEGAL_MOVE;
		
		for (int step = pruner.getAStep(); step != NO_LEGAL_MOVE; step = pruner.getAStep()){
			int from = MoveGenerator.getFromLoc(step);
			int to = MoveGenerator.getToLoc(step);
			position.move(from, to);

			if (position.isFaceKing()){
				position.undomove();
				continue;
			}

			int val;
			if (bestVal < alpha || bestMove == NO_LEGAL_MOVE) {
				val = -PVSearch(depth - 1, -beta, -alpha, position, curDepth + 1);
			} else {
				PVCutTries ++;
				val = -limitSearch(depth - 1, -alpha, position, curDepth + 1, true);
				if (val > alpha){
					val = -PVSearch(depth - 1, -beta, -alpha, position, curDepth + 1);
					PVCutFailTime++;
				}
			}
			position.undomove();
			
			if (val >= beta){
				pruner.saveRecord(TransPositionRecord.NodeType.BETA, val, step);
				return val;
			}
			if (val > bestVal){
				bestVal = val;
				bestMove = step;
				if (val > alpha){
					alpha = val;
				}
			}
		}
		
		if (bestVal >= alpha){
			pruner.saveRecord(TransPositionRecord.NodeType.PVS, bestVal, bestMove);
		} else {
			pruner.saveRecord(TransPositionRecord.NodeType.ALPHA, bestVal, bestMove);
		}
		return bestVal;
	}

	/**
	 * 监视变量的初始化
	 */
	private static void initWatchVar() {
		// TODO Auto-generated method stub
		count = 0;
		nullCutTries = 0;
		nullCutSuccessTime = 0;
		PVCutTries = 0;
		PVCutFailTime = 0;
	}
	
	public static void main(String[] args) {
		
	}
}
