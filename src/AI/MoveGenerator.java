/**
 * 
 */
package AI;

import java.util.ArrayList;
import java.util.List;

/**
 * ���������������е�ǰ���е��ŷ� 
 * @author gclx
 * @version 1.0
 * @date 2018��11��22�� ����5:11:07
 * @copyright 
 * @remarks ������ʹ��int����һ���ŷ�����8λ��ʾĿ�����꣬��8λ��ʾ��ʼ����
 *
 */
public class MoveGenerator {
	
	// ��λ��
	public static final int LOC_SHIFT = 8;
	// ��ʼ��������
	public static final int FROMLOC_MASK = 0xff00;
	// Ŀ����������
	public static final int TOLOC_MASK = 0x00ff;
	// �洢�����ӵ�ǰ���г����ŷ�����ʱ����ÿ�����Ӷ����ü��ɣ���Ȼ��С���ÿ��ƣ�
	public static List<Integer> capMoves = new ArrayList<>(64);
	// �洢�����ӵ�ǰ���зǳ����ŷ�
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
	 * ���ɵ�ǰ�����µ������ŷ�
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
	 * ���������ŷ�
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
	 * ����ʿ�������ŷ�
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
	 * �������������ŷ�
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
	 * ������������ŷ�
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
	 * ������������ŷ�
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
	 * �����ڵ������ŷ�
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
	 * �������������ŷ�
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
	 * ������˧�������ŷ�
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
	 * ����ʼλ�ú�Ŀ��λ��ѹλ��Ϊһ���ŷ�
	 * @param from
	 * @param to
	 * @return
	 */
	private static Integer makeStep(int from, int to) {
		// TODO Auto-generated method stub
		return ((from << LOC_SHIFT) | to);
	}

	
}
