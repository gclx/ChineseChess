/**
 * 
 */
package AI;

import java.util.Stack;

/**
 * ����������������һ���ض��ĵ�����
 * @author gclx
 * @version 1.0
 * @date 2018��11��15�� ����15:46:40
 * @copyright 
 * @remarks TODO
 *
 */

public class Position {
	// ��ʼ�����̵ĳ���
	private static int BK = Board.BLACK_KING;
	private static int BA1 = Board.BLACK_ADVISOR_1;
	private static int BA2 = Board.BLACK_ADVISOR_2;
	private static int BE1 = Board.BLACK_ELEPHANT_1;
	private static int BE2 = Board.BLACK_ELEPHANT_2;
	private static int BH1 = Board.BLACK_HORSE_1;
	private static int BH2 = Board.BLACK_HORSE_2;
	private static int BR1 = Board.BLACK_ROOK_1;
	private static int BR2 = Board.BLACK_ROOK_2;
	private static int BC1 = Board.BLACK_CANNON_1;
	private static int BC2 = Board.BLACK_CANNON_2;
	private static int BS1 = Board.BLACK_SOLDIER_1;
	private static int BS2 = Board.BLACK_SOLDIER_2;
	private static int BS3 = Board.BLACK_SOLDIER_3;
	private static int BS4 = Board.BLACK_SOLDIER_4;
	private static int BS5 = Board.BLACK_SOLDIER_5;
	
	private static int RK = Board.RED_KING;
	private static int RA1 = Board.RED_ADVISOR_1;
	private static int RA2 = Board.RED_ADVISOR_2;
	private static int RE1 = Board.RED_ELEPHANT_1;
	private static int RE2 = Board.RED_ELEPHANT_2;
	private static int RH1 = Board.RED_HORSE_1;
	private static int RH2 = Board.RED_HORSE_2;
	private static int RR1 = Board.RED_ROOK_1;
	private static int RR2 = Board.RED_ROOK_2;
	private static int RC1 = Board.RED_CANNON_1;
	private static int RC2 = Board.RED_CANNON_2;
	private static int RS1 = Board.RED_SOLDIER_1;
	private static int RS2 = Board.RED_SOLDIER_2;
	private static int RS3 = Board.RED_SOLDIER_3;
	private static int RS4 = Board.RED_SOLDIER_4;
	private static int RS5 = Board.RED_SOLDIER_5;
	
	private static final int INIT_BOARD[] = {
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,BR1,BH1,BE1,BA1, BK,BA2,BE2,BH2,BR2,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,BC1,  0,  0,  0,  0,  0,BC2,  0,  0,  0,  0,  0,
		0,  0,  0,BS1,  0,BS2,  0,BS3,  0,BS4,  0,BS5,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,RS1,  0,RS2,  0,RS3,  0,RS4,  0,RS5,  0,  0,  0,  0,
		0,  0,  0,  0,RC1,  0,  0,  0,  0,  0,RC2,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,RR1,RH1,RE1,RA1, RK,RA2,RE2,RH2,RR2,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
	};
	
	// �Ƿ�췽����
	private boolean isRedMove;
	// �������ݣ�0��ʾ��ǰλ��������
	private int[] board = new int[Board.BOARD_SIZE];
	// �洢ÿ�������������ϵ�λ�ã�ֵΪ0��ʾû�иñ�ŵ����ӻ����������
	private int[] pieceLoc = new int[Board.MAX_PEICES_ID];
	// ѹλ�洢��ǰ��ÿ�������Ƿ����ӣ������±��ʾ�кţ�rowBit[i]�ĵ�jλ��ʾ��i�е�j���Ƿ�����
	private int[] rowBit = new int[Board.MAX_ROW];
	// ѹλ�洢��ǰ��ÿ�������Ƿ����ӣ������±��ʾ�кţ�colBit[i]�ĵ�jλ��ʾ��i�е�j���Ƿ�����
	private int[] colBit = new int[Board.MAX_COL];
	// ��������ֵ����1��0
	private int evaluation[] = new int[2];
	// Zobrist ��ֵ�������û�����Ҫ�ڸı������Ϣʱ�޸ļ�ֵ
	private Zobrist zobrist = new Zobrist();
	// ��ֻع��ṹ
	private Stack<RollBackRecord> records = new Stack<>();
	
	
	public Position() {
		// TODO Auto-generated constructor stub
		setRedMove(true);
		for (int i = 0; i < Board.BOARD_SIZE; i++){
			int piece = INIT_BOARD[i];
			if (piece != 0){
				addPiece(i, piece);
			}
		}
	}
	
	/**
	 * ������ǰ����
	 * @return
	 */
	public int evaluate(){
		return isRedMove ? 
				(evaluation[Board.RED_SIDE] - evaluation[Board.BLACK_SIDE]) :
				(evaluation[Board.BLACK_SIDE] - evaluation[Board.RED_SIDE]);
	}
	
	/**
	 * �Ƿ��վ�
	 * @return
	 */
	public boolean isEnd(){
		return (pieceLoc[Board.RED_KING] == 0 || pieceLoc[Board.BLACK_KING] == 0);
	}

	/**
	 * �ж��Ƿ񱻽���
	 * @return
	 */
	public boolean isChecked(){
		int myKing = (isRedMove) ? Board.RED_KING : Board.BLACK_KING;
		int myKingLoc = getPieceLoc(myKing);
		return (isCheckByKingOrRook(myKing, myKingLoc) || 
			    isCheckByCannon(myKing, myKingLoc) ||
			    isCheckByHorse(myKing, myKingLoc) ||
			    isCheckBySoldier(myKing, myKingLoc));
	}
	
	/**
	 * �жϽ�˧�Ƿ����
	 * @return
	 */
	public boolean isFaceKing(){
		int redKingLoc = getPieceLoc(Board.RED_KING);
		int blackKingLoc = getPieceLoc(Board.BLACK_KING);
		if (redKingLoc == 0 || blackKingLoc == 0){
			return false;
		}
		int col1 = Board.getCol(redKingLoc);
		int col2 = Board.getCol(blackKingLoc);
		if (col1 != col2){
			return false;
		}
		for (int i = blackKingLoc + 16; i < redKingLoc; i += 16){
			if (getPiece(i) != 0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * �Ƿ��佫��
	 * @param myKing
	 * @param myKingLoc
	 * @return
	 */
	private boolean isCheckBySoldier(int myKing, int myKingLoc) {
		// TODO Auto-generated method stub
		int piece;
		int pieceLoc;
		if (Board.getPieceSide(myKing) == Board.RED_SIDE){
			pieceLoc = myKingLoc - Board.rowToIndex(1);
			piece = getPiece(pieceLoc);
		} else {
			pieceLoc = myKingLoc + Board.rowToIndex(1);
			piece = getPiece(pieceLoc);
		}
		if (Board.getPieceType(piece) == Board.SOLDIER_MASK){
			return true;
		}
		
		for (int i = -1; i <= 1; i += 2){
			pieceLoc = myKingLoc + i;
			piece = getPiece(pieceLoc);
			if (Board.getPieceType(piece) == Board.SOLDIER_MASK){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * �Ƿ�����
	 * @param myKing
	 * @param myKingLoc
	 * @return
	 */
	private boolean isCheckByHorse(int myKing, int myKingLoc) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 8; i++){
			int loc = myKingLoc + Board.HORSE_STEP[i];
			if (!Board.inBoard(loc)){
				continue;
			}
			int piece = getPiece(loc);
			int pieceType = Board.getPieceType(piece);
			int horsePin = MoveTable.getHorsePin(loc, myKingLoc);
			if (piece != 0 && !Board.isSameSide(piece, myKing) && pieceType == Board.HORSE_MASK && getPiece(horsePin) == 0){
				return true;
			}
		}
		return false;
	}

	/**
	 * @param myKing
	 * @param myKingLoc
	 * @return
	 */
	private boolean isCheckByCannon(int myKing, int myKingLoc) {
		// TODO Auto-generated method stub
		int piece = 0;
		int shelf = 0;
		int pieceType = 0;
		// 
		for (int loc = myKingLoc + 1; Board.inBoard(loc); loc++){
			if (getPiece(loc) != 0){
				shelf = loc;
				break;
			}
		}
		if (shelf != 0){
			for (int loc = shelf + 1; Board.inBoard(loc); loc++){
				if (getPiece(loc) != 0){
					piece = getPiece(loc);
					break;
				}
			}
			pieceType = Board.getPieceType(piece);
			if (!Board.isSameSide(piece, myKing) && pieceType == Board.CANNON_MASK){
				return true;
			}
		}
		//
		piece = 0;
		shelf = 0;
		pieceType = 0;
		for (int loc = myKingLoc - 1; Board.inBoard(loc); loc--){
			if (getPiece(loc) != 0){
				shelf = loc;
				break;
			}
		}
		if (shelf != 0){
			for (int loc = shelf - 1; Board.inBoard(loc); loc--){
				if (getPiece(loc) != 0){
					piece = getPiece(loc);
					break;
				}
			}
			pieceType = Board.getPieceType(piece);
			if (!Board.isSameSide(piece, myKing) && pieceType == Board.CANNON_MASK){
				return true;
			}
		}
		// 
		piece = 0;
		shelf = 0;
		pieceType = 0;
		for (int loc = myKingLoc + Board.rowToIndex(1); Board.inBoard(loc); loc += Board.rowToIndex(1)){
			if (getPiece(loc) != 0){
				shelf = loc;
				break;
			}
		}
		if (shelf != 0){
			for (int loc = shelf + Board.rowToIndex(1); Board.inBoard(loc); loc += Board.rowToIndex(1)){
				if (getPiece(loc) != 0){
					piece = getPiece(loc);
					break;
				}
			}
			pieceType = Board.getPieceType(piece);
			if (!Board.isSameSide(piece, myKing) && pieceType == Board.CANNON_MASK){
				return true;
			}
		}
		//
		piece = 0;
		shelf = 0;
		pieceType = 0;
		for (int loc = myKingLoc - Board.rowToIndex(1); Board.inBoard(loc); loc -= Board.rowToIndex(1)){
			if (getPiece(loc) != 0){
				shelf = loc;
				break;
			}
		}
		if (shelf != 0){
			for (int loc = shelf - Board.rowToIndex(1); Board.inBoard(loc); loc -= Board.rowToIndex(1)){
				if (getPiece(loc) != 0){
					piece = getPiece(loc);
					break;
				}
			}
			pieceType = Board.getPieceType(piece);
			if (!Board.isSameSide(piece, myKing) && pieceType == Board.CANNON_MASK){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * �ж��Ƿ񱻳������˧�����ķ���
	 * @param myKing
	 * @param myKingLoc
	 * @return
	 */
	private boolean isCheckByKingOrRook(int myKing, int myKingLoc) {
		// TODO Auto-generated method stub
		int piece = 0;
		int pieceType = 0;
		for (int loc = myKingLoc + 1; Board.inBoard(loc); loc++){
			if (getPiece(loc) != 0){
				piece = getPiece(loc);
				break;
			}
		}
		pieceType = Board.getPieceType(piece);
		if (!Board.isSameSide(piece, myKing) && pieceType == Board.ROOK_MASK){
			return true;
		}
		
		piece = 0;
		pieceType = 0;
		for (int loc = myKingLoc - 1; Board.inBoard(loc); loc--){
			if (getPiece(loc) != 0){
				piece = getPiece(loc);
				break;
			}
		}
		pieceType = Board.getPieceType(piece);
		if (!Board.isSameSide(piece, myKing) && pieceType == Board.ROOK_MASK){
			return true;
		}
		
		piece = 0;
		pieceType = 0;
		for (int loc = myKingLoc + Board.rowToIndex(1); Board.inBoard(loc); loc += Board.rowToIndex(1)){
			if (getPiece(loc) != 0){
				piece = getPiece(loc);
				break;
			}
		}
		pieceType = Board.getPieceType(piece);
		if (!Board.isSameSide(piece, myKing) && (pieceType == Board.ROOK_MASK || pieceType == Board.KING_MASK)){
			return true;
		}
		
		piece = 0;
		pieceType = 0;
		for (int loc = myKingLoc - Board.rowToIndex(1); Board.inBoard(loc); loc -= Board.rowToIndex(1)){
			if (getPiece(loc) != 0){
				piece = getPiece(loc);
				break;
			}
		}
		pieceType = Board.getPieceType(piece);
		if (!Board.isSameSide(piece, myKing) && (pieceType == Board.ROOK_MASK || pieceType == Board.KING_MASK)){
			return true;
		}
		
		return false;
	}

	/**
	 * �������
	 * @param i
	 * @param piece
	 */
	private void addPiece(int loc, int piece) {
		// TODO Auto-generated method stub
		//System.out.println(loc + ": ");
		board[loc] = piece;
		pieceLoc[piece] = loc;
		//System.out.println(rowBit[Board.getRow(loc)] + " ^ " + MoveTable.rowMask[loc] + " = ");
		rowBit[Board.getRow(loc)] ^= MoveTable.rowMask[loc];
		//System.out.println(rowBit[Board.getRow(loc)]);
		colBit[Board.getCol(loc)] ^= MoveTable.colMask[loc];
		zobrist.xor(Zobrist.Z_TABLE[Board.getPieceSide(piece)][Board.getPieceType(piece)][loc]);
		evaluation[Board.getPieceSide(piece)] += Evalutor.getPieceVal(piece, loc);
	}

	/**
	 * ��һ����
	 * @param from
	 * @param to
	 */
	public void move(int from, int to) {
		// TODO Auto-generated method stub
		records.push(new RollBackRecord(from, to, board[to]));
		movePiece(from, to);
		changeSide();
	}

	/**
	 * ��һ������
	 */
	public void makeNullMove(){
		changeSide();
	}
	
	/**
	 * ����һ������
	 */
	public void undoNullMove(){
		changeSide();
	}
	
	/**
	 * �������巽
	 */
	private void changeSide() {
		// TODO Auto-generated method stub
		setRedMove(!isRedMove);
		zobrist.xor(Zobrist.Z_SIDE);
	}

	/**
	 * �ƶ�����
	 * @param from
	 * @param to
	 */
	private void movePiece(int from, int to) {
		// TODO Auto-generated method stub
		if (board[to] != 0){
			delPiece(to);
		}
		int piece = board[from];
		delPiece(from);
		addPiece(to, piece);
	}

	/**
	 * ɾ������
	 * @param to
	 */
	private void delPiece(int loc) {
		// TODO Auto-generated method stub
		int piece = board[loc];
		board[loc] = 0;
		pieceLoc[piece] = 0;
		rowBit[Board.getRow(loc)] ^= MoveTable.rowMask[loc];
		colBit[Board.getCol(loc)] ^= MoveTable.colMask[loc];
		zobrist.xor(Zobrist.Z_TABLE[Board.getPieceSide(piece)][Board.getPieceType(piece)][loc]);
		evaluation[Board.getPieceSide(piece)] -= Evalutor.getPieceVal(piece, loc);	
	}

	/**
	 * ����һ����
	 */
	public void undomove() {
		// TODO Auto-generated method stub
		RollBackRecord record = records.pop();
		int from = record.getFrom();
		int to = record.getTo();
		int capedPiece = record.getCapedPiece();
		undoMovePiece(from, to, capedPiece);
		changeSide();
	}

	/**
	 * �����ƶ����ӣ��������ƻ�����
	 * @param from
	 * @param to
	 * @param capedPiece
	 */
	private void undoMovePiece(int from, int to, int capedPiece) {
		// TODO Auto-generated method stub
		int piece = board[to];
		delPiece(to);
		addPiece(from, piece);
		if (capedPiece != 0){
			addPiece(to, capedPiece);
		}
	}

	public boolean isRedMove() {
		return isRedMove;
	}

	public void setRedMove(boolean isRedMove) {
		this.isRedMove = isRedMove;
	}

	public int getPiece(int loc){
		return board[loc];
	}

	public int getPieceLoc(int piece) {
		return pieceLoc[piece];
	}

	public int getRowBit(int row) {
		return rowBit[row];
	}

	public int getColBit(int col) {
		return colBit[col];
	}

	public int[] getEvaluation() {
		return evaluation;
	}

	public Zobrist getZobrist() {
		return zobrist;
	}

	public void setZobrist(Zobrist zobrist) {
		this.zobrist = zobrist;
	}

	public int[] getBoard() {
		return board;
	}

	public void setBoard(int[] board) {
		this.board = board;
	}


}


//��ֻع���
class RollBackRecord{
	private int from;
	private int to;
	private int capedPiece;

	public RollBackRecord(int from, int to, int capedPiece) {
		// TODO Auto-generated constructor stub
		setFrom(from);
		setTo(to);
		setCapedPiece(capedPiece);
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public int getCapedPiece() {
		return capedPiece;
	}

	public void setCapedPiece(int capedPiece) {
		this.capedPiece = capedPiece;
	}
	
}
