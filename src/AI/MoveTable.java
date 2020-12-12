/**
 * 
 */
package AI;

/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年11月28日 下午7:16:09
 * @copyright 
 * @remarks TODO
 *
 */

public class MoveTable {

	// 0表示结束了
	public static final int[][] advisorMoveTable = new int[Board.BOARD_SIZE][8];
	public static final int[][][] soldierMoveTable = new int[2][Board.BOARD_SIZE][4];
	public static final int[][] elephantMoveTable = new int[Board.BOARD_SIZE][8];
	public static final int[][] elephantPinTable = new int[Board.BOARD_SIZE][8];
	public static final int[][] horseMoveTable = new int[Board.BOARD_SIZE][12];
	public static final int[][] horsePinTable = new int[Board.BOARD_SIZE][8];
	public static final int[][] kingMoveTable = new int[Board.BOARD_SIZE][8];
	
	// 当前位置所在列的掩码
	public static final int rowMask[] = new int[Board.BOARD_SIZE];
	// 当前位置所在行的掩码
	public static final int colMask[] = new int[Board.BOARD_SIZE];
	// 马腿表
	private static final int HORSE_PIN_TABLE[] = new int[]{
									0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,-16,  0,-16,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0, -1,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0, -1,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0, 16,  0, 16,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0
	};
	
	static{
		makePieceTable();
		makeMask();
		System.out.println("MoveTable has been initialized!");
	}
	
	/**
	 * 在其他类中会用到
	 */
	public static void makeMask(){
		for (int i = 0; i < 256; i++){
			if (Board.inBoard(i)){
				rowMask[i] = 1 << (Board.getCol(i) - Board.MARGIN_L);
				colMask[i] = 1 << (Board.getRow(i) - Board.MARGIN_U);
			} else {
				rowMask[i] = 0;
				colMask[i] = 0;
			}
		}
	}

	/**
	 * 生成棋子着法生成表
	 */
	private static void makePieceTable() {
		// TODO Auto-generated method stub
		for (int from = 0; from < 256; from++){
			if (Board.inBoard(from)){
				if (Board.inFort(from)){
					generateKingMove(from);
					generateAdvisorMove(from);
				}
				generateElephantMove(from);
				generateHorseMove(from);
				generateSoldierMove(from);
			}
		}
	}

	/**
	 * @param from
	 */
	private static void generateSoldierMove(int from) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 2; i++){
			int cnt = 0;
			int to = from + (i == Board.BLACK_SIDE ? Board.rowToIndex(1) : Board.rowToIndex(-1));
			if (Board.inBoard(to)){
				soldierMoveTable[i][from][cnt] = to;
				cnt++;
			}
		
			int piece = (i == Board.RED_SIDE ? Board.RED_SOLDIER_1 : Board.BLACK_SOLDIER_1);
			if (!Board.isInOwnHalf(piece, from)){
				for (int j = -1; j <= 1; j += 2){
					to = from + j;
					if (Board.inBoard(to)){
						soldierMoveTable[i][from][cnt] = to;
						cnt ++;
					}
				}
			}
			soldierMoveTable[i][from][cnt] = 0;
		}
	}

	/**
	 * @param from
	 */
	private static void generateHorseMove(int from) {
		// TODO Auto-generated method stub
		int cnt = 0;
		for (int i = 0; i < 8; i++){
			int to = from + Board.HORSE_STEP[i];
			if (Board.inBoard(to)){
				horseMoveTable[from][cnt] = to;
				horsePinTable[from][cnt] = getHorsePin(from, to);
				cnt++;
			}
		}
		horseMoveTable[from][cnt] = 0;
	}

	/**
	 * @param from
	 * @param to
	 * @return
	 */
	public static int getHorsePin(int from, int to) {
		// TODO Auto-generated method stub
		int way1 = from + HORSE_PIN_TABLE[to - from + Board.BOARD_SIZE];
		int way2;
		if (Math.abs(from - to) == Board.rowToIndex(2) + 1 || 
				Math.abs(from - to) == Board.rowToIndex(2) - 1){
			if (Board.getRow(from) - Board.getRow(to) == 2){
				way2 = from - Board.rowToIndex(1);
			} else if (Board.getRow(from) - Board.getRow(to) == -2){
				way2 = from + Board.rowToIndex(1);
			} else {
				return -1;
			}
		} else if (Math.abs(from - to) == Board.rowToIndex(1) + 2 ||
				Math.abs(from - to) == Board.rowToIndex(1) - 2){
			if (Board.getCol(from) - Board.getCol(to) == 2){
				way2 = from - 1;
			} else if (Board.getCol(from) - Board.getCol(to) == -2){
				way2 = from + 1;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
		return way1;
	}

	/**
	 * @param from
	 */
	private static void generateElephantMove(int from) {
		// TODO Auto-generated method stub
		int cnt = 0;
		for (int i = 0; i < 4; i++){
			int to = from + Board.ELEPHANT_STEP[i];
			if (Board.inBoard(to) && Board.isSameHalf(from, to)){
				elephantMoveTable[from][cnt] = to;
				elephantPinTable[from][cnt] = (from + to) >> 1;
				cnt++;
			}
		}
		elephantMoveTable[from][cnt] = 0;
	}

	/**
	 * @param from
	 */
	private static void generateAdvisorMove(int from) {
		// TODO Auto-generated method stub
		int cnt = 0;
		for (int i = 0; i < 4; i++){
			int to = from + Board.ADVISOR_STEP[i];
			if (Board.inFort(to)){
				advisorMoveTable[from][cnt] = to;
				cnt++;
			}
		}
		advisorMoveTable[from][cnt] = 0;
	}

	/**
	 * 将的着法表生成函数
	 * @param from
	 */
	private static void generateKingMove(int from) {
		// TODO Auto-generated method stub
		int cnt = 0;
		for (int i = 0; i < 4; i++){
			int to = from + Board.KING_STEP[i];
			if (Board.inFort(to)){
				kingMoveTable[from][cnt] = to;
				cnt++;
			}
		}
		kingMoveTable[from][cnt] = 0;
	}
	
	public static void main(String[] args) {
		System.out.println(2);
		for (int i = 0; i < Board.BOARD_SIZE; i++){
			if (horseMoveTable[i][0] != 0){
				System.out.print(i + " : ");
				for (int j = 0; j < horseMoveTable[i].length; j++){
					System.out.print(horseMoveTable[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		System.out.println(getHorsePin(52, 85));
	}
	
}

