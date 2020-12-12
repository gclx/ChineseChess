/**
 * 
 */
package AI;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类用于生成所有当前可行的着法 
 * @author gclx
 * @version 1.0
 * @date 2018年11月22日 下午5:11:07
 * @copyright 
 * @remarks 本类中使用int描述一个着法，低8位表示目的坐标，高8位表示起始坐标
 *
 */
public class MoveGenerator {
	
	// 移位码
	public static final int LOC_SHIFT = 8;
	// 起始坐标掩码
	public static final int FROMLOC_MASK = 0xff00;
	// 目的坐标掩码
	public static final int TOLOC_MASK = 0x00ff;
	// 存储该棋子当前所有吃子着法（到时对于每颗棋子都调用即可，不然大小不好控制）
	public static List<Integer> capMoves = new ArrayList<>(64);
	// 存储该棋子当前所有非吃子着法
	public static List<Integer> nonCapMoves = new ArrayList<>(128);
	
	/**
	 * @param step
	 * @return
	 */
	public static int getFromLoc(int step) {
		// TODO Auto-generated method stub
		return ((step & FROMLOC_MASK) >> LOC_SHIFT);
	}

	/**
	 * @param step
	 * @return
	 */
	public static int getToLoc(int step) {
		// TODO Auto-generated method stub
		return ((step & TOLOC_MASK));
	}

	/**
	 * 生成当前局面下的所有着法
	 * @param position
	 * @return
	 */
	public static List<Integer> getAllMove(Position position){
		List<Integer> list = new ArrayList<>(256);
		generateMove(position);
//		generateNonCapMove(position);
		list.addAll(capMoves);
		list.addAll(nonCapMoves);
		return list;
	}

	/**
	 * 产生所有着法
	 * @param position
	 */
	private static void generateMove(Position position) {
		// TODO Auto-generated method stub
		capMoves.clear();
		nonCapMoves.clear();
		int side = position.isRedMove() ? Board.RED_SIDE : Board.BLACK_SIDE;
		for (int i = 0; i < Board.PIECE_ARRAY[side].length; i++){
			int piece = Board.PIECE_ARRAY[side][i];
			int from = position.getPieceLoc(piece);
			if (from == 0){
				continue;
			}
			int pieceType = Board.getPieceType(piece);
			switch (pieceType) {
			case Board.KING_MASK:
				generateKingMove(piece, from, position);
				break;
			case Board.ROOK_MASK:
				generateRookMove(piece, from, position);
				break;
			case Board.CANNON_MASK:
				generateCannonMove(piece, from, position);
				break;
			case Board.HORSE_MASK:
				generateHorseMove(piece, from, position);
				break;
			case Board.ELEPHANT_MASK:
				generateElephantMove(piece, from, position);
				break;
			case Board.SOLDIER_MASK:
				generateSoldierMove(side, piece, from, position);
				break;
			case Board.ADVISOR_MASK:
				generateAdvisorMove(piece, from, position);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 产生士的所有着法
	 * @param piece
	 * @param from
	 * @param position
	 */
	private static void generateAdvisorMove(int piece, int from, Position position) {
		// TODO Auto-generated method stub
		for (int j = 0; MoveTable.advisorMoveTable[from][j] != 0; j++){
			int to = MoveTable.advisorMoveTable[from][j];
			if (position.getPiece(to) != 0) {
				if (!Board.isSameSide(piece, position.getPiece(to))) {
					capMoves.add(makeStep(from, to));
				}
			} else {
				nonCapMoves.add(makeStep(from, to));
			}
		}
	}

	/**
	 * 产生兵的所有着法
	 * @param piece
	 * @param from
	 * @param position
	 */
	private static void generateSoldierMove(int side, int piece, int from, Position position) {
		// TODO Auto-generated method stub
		for (int j = 0; MoveTable.soldierMoveTable[side][from][j] != 0; j++){
			int to = MoveTable.soldierMoveTable[side][from][j];
			if (position.getPiece(to) != 0){
				if (!Board.isSameSide(piece, position.getPiece(to))){
					capMoves.add(makeStep(from, to));
				}
			} else {
				nonCapMoves.add(makeStep(from, to));
			}
		}
	}

	/**
	 * 产生相的所有着法
	 * @param piece
	 * @param from
	 * @param position
	 */
	private static void generateElephantMove(int piece, int from, Position position) {
		// TODO Auto-generated method stub
		for (int j = 0; MoveTable.elephantMoveTable[from][j] != 0; j++){
			int to = MoveTable.elephantMoveTable[from][j];
			int pin = MoveTable.elephantPinTable[from][j];
			if (position.getPiece(pin) == 0){
				if (position.getPiece(to) != 0){
					if (!Board.isSameSide(piece, position.getPiece(to))){
						capMoves.add(makeStep(from, to));
					}
				} else {
					nonCapMoves.add(makeStep(from, to));
				}
			}
		}
	}

	/**
	 * 产生马的所有着法
	 * @param piece
	 * @param from
	 * @param position
	 */
	private static void generateHorseMove(int piece, int from, Position position) {
		// TODO Auto-generated method stub
		for (int j = 0; MoveTable.horseMoveTable[from][j] != 0; j++){
			int to = MoveTable.horseMoveTable[from][j];
			int pin = MoveTable.horsePinTable[from][j];
			if (position.getPiece(pin) == 0){
				if (position.getPiece(to) != 0){
					if(!Board.isSameSide(piece, position.getPiece(to))){
						capMoves.add(makeStep(from, to));
					}
				} else {
					nonCapMoves.add(makeStep(from, to));
				}
			}
		}
	}

	/**
	 * 产生炮的所有着法
	 * @param piece
	 * @param from
	 * @param position
	 */
	private static void generateCannonMove(int piece, int from, Position position) {
		
		int to;
		for (to = from + 1; ; to++){
			if (Board.inBoard(to) && position.getPiece(to) == 0){
				nonCapMoves.add(makeStep(from, to));
			} else {
				break;
			}
		}
		if (Board.inBoard(to) && position.getPiece(to) != 0){
			int next;
			for (next = to + 1; Board.inBoard(next); next++){
				if (position.getPiece(next) != 0){
					break;
				}
			}
			if (position.getPiece(next) != 0 && !Board.isSameSide(piece, position.getPiece(next))){
				capMoves.add(makeStep(from, next));
			}
		}
		
		for (to = from - 1; ; to--){
			if (Board.inBoard(to) && position.getPiece(to) == 0){
				nonCapMoves.add(makeStep(from, to));
			} else {
				break;
			}
		}
		if (Board.inBoard(to) && position.getPiece(to) != 0){
			int next;
			for (next = to - 1; Board.inBoard(next); next--){
				if (position.getPiece(next) != 0){
					break;
				}
			}
			if (position.getPiece(next) != 0 && !Board.isSameSide(piece, position.getPiece(next))){
				capMoves.add(makeStep(from, next));
			}
		}
		
		for (to = from + Board.rowToIndex(1); ; to += Board.rowToIndex(1)){
			if (Board.inBoard(to) && position.getPiece(to) == 0){
				nonCapMoves.add(makeStep(from, to));
			} else {
				break;
			}
		}
		if (Board.inBoard(to) && position.getPiece(to) != 0){
			int next;
			for (next = to + Board.rowToIndex(1); Board.inBoard(next); next += Board.rowToIndex(1)){
				if (position.getPiece(next) != 0){
					break;
				}
			}
			if (position.getPiece(next) != 0 && !Board.isSameSide(piece, position.getPiece(next))){
				capMoves.add(makeStep(from, next));
			}
		}
		
		for (to = from - Board.rowToIndex(1); ; to -= Board.rowToIndex(1)){
			if (Board.inBoard(to) && position.getPiece(to) == 0){
				nonCapMoves.add(makeStep(from, to));
			} else {
				break;
			}
		}
		if (Board.inBoard(to) && position.getPiece(to) != 0){
			int next;
			for (next = to - Board.rowToIndex(1); Board.inBoard(next); next -= Board.rowToIndex(1)){
				if (position.getPiece(next) != 0){
					break;
				}
			}
			if (position.getPiece(next) != 0 && !Board.isSameSide(piece, position.getPiece(next))){
				capMoves.add(makeStep(from, next));
			}
		}
		
	}

	/**
	 * 产生车的所有着法
	 * @param piece
	 * @param from
	 * @param position
	 */
	private static void generateRookMove(int piece, int from, Position position) {
		
		int to;
		
		for (to = from + 1; ; to++){
			if (Board.inBoard(to) && position.getPiece(to) == 0){
				nonCapMoves.add(makeStep(from, to));
			} else {
				break;
			}
		}
		if (Board.inBoard(to) && position.getPiece(to) != 0 && !Board.isSameSide(piece, position.getPiece(to))){
			capMoves.add(makeStep(from, to));
		}
		
		for (to = from - 1; ; to--){
			if (Board.inBoard(to) && position.getPiece(to) == 0){
				nonCapMoves.add(makeStep(from, to));
			} else {
				break;
			}
		}
		if (Board.inBoard(to) && position.getPiece(to) != 0 && !Board.isSameSide(piece, position.getPiece(to))){
			capMoves.add(makeStep(from, to));
		}
		
		for (to = from + Board.rowToIndex(1); ; to += Board.rowToIndex(1)){
			if (Board.inBoard(to) && position.getPiece(to) == 0){
				nonCapMoves.add(makeStep(from, to));
			} else {
				break;
			}
		}
		if (Board.inBoard(to) && position.getPiece(to) != 0 && !Board.isSameSide(piece, position.getPiece(to))){
			capMoves.add(makeStep(from, to));
		}
		
		for (to = from - Board.rowToIndex(1); ; to -= Board.rowToIndex(1)){
			if (Board.inBoard(to) && position.getPiece(to) == 0){
				nonCapMoves.add(makeStep(from, to));
			} else {
				break;
			}
		}
		if (Board.inBoard(to) && position.getPiece(to) != 0 && !Board.isSameSide(piece, position.getPiece(to))){
			capMoves.add(makeStep(from, to));
		}
		
	}

	/**
	 * 产生将帅的所有着法
	 * @param piece
	 * @param from
	 * @param position
	 */
	private static void generateKingMove(int piece, int from, Position position) {
		// TODO Auto-generated method stub
		for (int j = 0; MoveTable.kingMoveTable[from][j] != 0; j++){
			int to = MoveTable.kingMoveTable[from][j];
			if (position.getPiece(to) != 0){
				if (!Board.isSameSide(piece, position.getPiece(to))){
					capMoves.add(makeStep(from, to));
				}
			} else {
				nonCapMoves.add(makeStep(from, to));
			}
		}
	}

	/**
	 * 将初始位置和目的位置压位成为一个着法
	 * @param from
	 * @param to
	 * @return
	 */
	private static Integer makeStep(int from, int to) {
		// TODO Auto-generated method stub
		return ((from << LOC_SHIFT) | to);
	}

	
}
