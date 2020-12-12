/**
 * 
 */
package AI;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年11月30日 下午3:05:18
 * @copyright 
 * @remarks TODO
 *
 */
public class Pruner {
	
	private Position position;
	private int depth;
	private int curDepth;
	private boolean isDeeplySearched = false;
	private boolean isSearched = false;
	private TransPositionRecord record;
	private int adjustedValue;
	private static enum GenState{
		NO_STEP, DONE_TRANS, DONE_SORT, DONE_HIS
	};
	private GenState state;
	private List<Integer> steps = null;
	private Iterator<Integer> iterator = null;

	/**
	 * @param position
	 * @param depth
	 * @param curDepth
	 * @param alpha
	 * @param beta
	 */
	public Pruner(Position position, int depth, int curDepth, int alpha, int beta) {
		// TODO Auto-generated constructor stub
		setPosition(position);
		setDepth(depth);
		setCurDepth(curDepth);
		setState(GenState.NO_STEP);
		setRecord(TranspositionTable.getRecord(position));
		
		if (record != null){
			isSearched = true;
			setAdjustedValue(record.getValue());
			if (record.getBestMove() == Search.NO_LEGAL_MOVE){
				state = GenState.DONE_TRANS;
			}
			
			if (adjustedValue >= Evalutor.WIN_LOWER_BOUND &&
					record.getType() != TransPositionRecord.NodeType.ALPHA){
				adjustedValue -= curDepth;
				isDeeplySearched = true;
				state = GenState.DONE_TRANS;
			} else if (adjustedValue <= -Evalutor.WIN_LOWER_BOUND &&
					record.getType() != TransPositionRecord.NodeType.BETA){
				adjustedValue += curDepth;
				isDeeplySearched = true;
				state = GenState.DONE_TRANS;
			} else if (record.getDepth() >= this.depth){
				if (record.getType() == TransPositionRecord.NodeType.PVS) {
					isDeeplySearched = true;
					state = GenState.DONE_TRANS;
				} else if (record.getType() == TransPositionRecord.NodeType.BETA && 
							adjustedValue >= beta) {
					isDeeplySearched = true;
					state = GenState.DONE_TRANS;
				} else if (record.getType() == TransPositionRecord.NodeType.ALPHA &&
							adjustedValue <= alpha) {
					isDeeplySearched = true;
					state = GenState.DONE_TRANS;
				}
			}
		} else {
			state = GenState.DONE_TRANS;
		}
	}
	
	/**
	 * @return
	 */
	public int getAStep() {
		// TODO Auto-generated method stub
		int move = Search.NO_LEGAL_MOVE;
		switch (state) {
		case NO_STEP:
			move = record.getBestMove();
			state = GenState.DONE_TRANS;
			break;
		case DONE_TRANS:
			sortStep();
			state = GenState.DONE_SORT;
		case DONE_SORT:
			if (iterator.hasNext()){
				move = iterator.next();
				break;
			} else {
				state = GenState.DONE_HIS;
			}
			// 此处不需要break
		case DONE_HIS:
			move = Search.NO_LEGAL_MOVE;
			break;
		default:
			break;
		}
		return move;
	}
	
	
	/**
	 * 
	 */
	private void sortStep() {
		// TODO Auto-generated method stub
		steps = MoveGenerator.getAllMove(this.position);
		steps.sort(new SortedByHistoryTable());
		iterator = steps.iterator();
	}
	
	private class SortedByHistoryTable implements Comparator<Integer> {
		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Integer arg0, Integer arg1) {
			// TODO Auto-generated method stub
			return -HistoryTable.getRecord(arg0) + HistoryTable.getRecord(arg1);
		}
	}

	/**
	 * @param type
	 * @param val
	 * @param step
	 */
	public void saveRecord(TransPositionRecord.NodeType type, int val, int step) {
		// TODO Auto-generated method stub
		if (val >= Evalutor.WIN_LOWER_BOUND){
			val += curDepth;
		}
		if (val <= -Evalutor.WIN_LOWER_BOUND){
			val -= curDepth;
		}
		TransPositionRecord record = new TransPositionRecord(type, position.getZobrist().getLock(), val, depth, step);
		
		TranspositionTable.saveRecord(position, record);
		HistoryTable.saveRecord(step, depth);
	}
	
	/**
	 * 从历史表中找出最佳着法
	 * @return
	 */
	public int getBestMove() {
		// TODO Auto-generated method stub
		return record.getBestMove();
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getCurDepth() {
		return curDepth;
	}

	public void setCurDepth(int curDepth) {
		this.curDepth = curDepth;
	}

	public boolean isDeeplySearched() {
		return isDeeplySearched;
	}

	public void setDeeplySearched(boolean isDeeplySearched) {
		this.isDeeplySearched = isDeeplySearched;
	}

	public boolean isSearched() {
		return isSearched;
	}

	public void setSearched(boolean isSearched) {
		this.isSearched = isSearched;
	}

	public TransPositionRecord getRecord() {
		return record;
	}

	public void setRecord(TransPositionRecord record) {
		this.record = record;
	}

	public int getAdjustedValue() {
		return adjustedValue;
	}

	public void setAdjustedValue(int adjustedValue) {
		this.adjustedValue = adjustedValue;
	}

	public GenState getState() {
		return state;
	}

	public void setState(GenState state) {
		this.state = state;
	}

	public List<Integer> getSteps() {
		return steps;
	}

	public void setSteps(List<Integer> steps) {
		this.steps = steps;
	}

	public Iterator<Integer> getIterator() {
		return iterator;
	}

	public void setIterator(Iterator<Integer> iterator) {
		this.iterator = iterator;
	}

	


}
