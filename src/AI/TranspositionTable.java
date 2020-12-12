/**
 * 
 */
package AI;


/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年11月30日 下午3:05:33
 * @copyright 
 * @remarks TODO
 *
 */

class TransPositionRecord{
	public static enum NodeType{
		ALPHA, BETA, PVS
	};
	
	private NodeType type;
	private long lock = 0;
	private int value = 0;
	private int depth = -1;
	private int bestMove = Search.NO_LEGAL_MOVE;
	
	public TransPositionRecord(NodeType type, long lock, int value, int depth, int bestMove) {
		// TODO Auto-generated constructor stub
		setType(type);
		setLock(lock);
		setValue(value);
		setDepth(depth);
		setBestMove(bestMove);
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

	public long getLock() {
		return lock;
	}

	public void setLock(long lock) {
		this.lock = lock;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getBestMove() {
		return bestMove;
	}

	public void setBestMove(int bestMove) {
		this.bestMove = bestMove;
	}
	
}

public class TranspositionTable {

	public static final int MAX_SIZE = (1 << 20) - 1;
	
	private static final TransPositionRecord[] records = new TransPositionRecord[MAX_SIZE + 1];
	
	private static int usedLocNum = 0;
	private static int queryNum = 0;
	private static int hitNum = 0;
	
	public TranspositionTable(){}
	
	/**
	 * @param position
	 * @param record
	 */
	public static void saveRecord(Position position, TransPositionRecord record) {
		// TODO Auto-generated method stub
		int i = (int)(position.getZobrist().getKey()) & MAX_SIZE;
		if (isReplace(records[i], record)){
			if (records[i] == null){
				usedLocNum++;
			}
			records[i] = record;
		}
	}
	/**
	 * @param transPositionRecord
	 * @param record
	 * @return
	 */
	private static boolean isReplace(TransPositionRecord oldRecord, TransPositionRecord newRecord) {
		// TODO Auto-generated method stub
		return true;
	}
	/**
	 * 
	 */
	public static void initWatchVar() {
		// TODO Auto-generated method stub
		queryNum = 0;
		hitNum = 0;
	}
	
	public static void display(){
		System.out.println("In chess.AI.TranpositionTable.display:");
		System.out.println("  Sum of records : " + usedLocNum);
		System.out.println("  hitNum : " + hitNum);
		System.out.println("  queryNum : " + queryNum);
		System.out.println("  hit ratio: " + (1.0 * hitNum / queryNum));
	}

	/**
	 * @param position
	 * @return
	 */
	public static TransPositionRecord getRecord(Position position) {
		// TODO Auto-generated method stub
		queryNum ++;
		int i = (int)(position.getZobrist().getKey()) & MAX_SIZE;
		if (records[i] == null){
			return null;
		}
		if (position.getZobrist().getLock() == records[i].getLock()){
			hitNum ++;
			return records[i];
		} else {
			return null;	
		}
	}
	
	public static void main(String[] args) {
		
	}

}
