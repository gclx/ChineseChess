/**
 * 
 */
package AI;

/**
 * ����ΪAIʹ�õ����̣���board�е�ChessBoard�нϴ��࣬������Կ���λ����
 * @author gclx
 * @version 1.0
 * @date 2018��11��22�� ����1:58:09
 * @copyright 
 * @remarks TODO
 */
public class Board {
	
	// �ж������Ƿ���������
	private static final int IN_BOARD[] = {
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
		0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
		0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
	  	0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
	  	0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
	  	0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
	  	0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
	  	0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
	  	0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
	  	0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
	  	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
	};	
	
	// �ж������Ƿ��ھŹ�����
	private static final int IN_FORT[] = {
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
	};
	
	// AI���̴�С��16 * 16���ڽ���λ����
	public static final int BOARD_SIZE = 256;
	// AI��������
	public static final int MAX_COL = 16;
	// AI��������
	public static final int MAX_ROW = 16;
	// ��ʵ��������
	public static final int REAL_MAX_COL = 9;
	// ��ʵ��������
	public static final int REAL_MAX_ROW = 10;
	// ���������
	public static final int MAX_PIECES = 32;
	// ������ӱ��
	public static final int MAX_PEICES_ID = 1 << 7;
	// �����������
	public static final int MAX_PIECE_TYPECNT = 8;
	
	// �߽�����
	public static final int MARGIN_L = 3;
	public static final int MARGIN_R = 3;
	public static final int MARGIN_U = 3;
	public static final int MARGIN_D = 4;
	
	// ��ڷ����
	public static final int RED_SIDE = 1;
	public static final int BLACK_SIDE = 0;
	
	/**
	 * һϵ������Ķ��壬���ڽ�����λ����
	 */
	// ������ɫ���룬�췽����λΪ1���ڷ�Ϊ0
	public static final int PIECE_SIDE_MASK = 0x08;
	// �����������룬����λ��ʾ���ֲ�ͬ������
	public static final int PIECE_TYPE_MASK = 0x07;
	// �췽�볡�ڰ�λΪ1���ڷ�Ϊ0
	public static final int BOARD_SIDE_MASK = 0x80;
	// ���������е�����
	public static final int ROW_MASK = 0xf0;
	// ���������е�����
	public static final int COL_MASK = 0x0f;
	
	// �������ӵ����룬��1��7
	public static final int KING_MASK = 1;
	public static final int ADVISOR_MASK = 2;
	public static final int ELEPHANT_MASK = 3;
	public static final int HORSE_MASK = 4;
	public static final int ROOK_MASK = 5;
	public static final int CANNON_MASK = 6;
	public static final int SOLDIER_MASK = 7;
	
	// �������ӵı���
	//								      	   ����ڼ�������	|	      ������ɫ	      |		��������	
	public static final int RED_KING 		= ((0 << 4) | PIECE_SIDE_MASK | KING_MASK);
	public static final int RED_ADVISOR_1 	= ((0 << 4) | PIECE_SIDE_MASK | ADVISOR_MASK);
	public static final int RED_ADVISOR_2 	= ((1 << 4) | PIECE_SIDE_MASK | ADVISOR_MASK);
	public static final int RED_ELEPHANT_1	= ((0 << 4) | PIECE_SIDE_MASK | ELEPHANT_MASK);
	public static final int RED_ELEPHANT_2 	= ((1 << 4) | PIECE_SIDE_MASK | ELEPHANT_MASK);;
	public static final int RED_HORSE_1 	= ((0 << 4) | PIECE_SIDE_MASK | HORSE_MASK);
	public static final int RED_HORSE_2 	= ((1 << 4) | PIECE_SIDE_MASK | HORSE_MASK);
	public static final int RED_ROOK_1  	= ((0 << 4) | PIECE_SIDE_MASK | ROOK_MASK);
	public static final int RED_ROOK_2 		= ((1 << 4) | PIECE_SIDE_MASK | ROOK_MASK);
	public static final int RED_CANNON_1  	= ((0 << 4) | PIECE_SIDE_MASK | CANNON_MASK);
	public static final int RED_CANNON_2 	= ((1 << 4) | PIECE_SIDE_MASK | CANNON_MASK);
	public static final int RED_SOLDIER_1 	= ((0 << 4) | PIECE_SIDE_MASK | SOLDIER_MASK);
	public static final int RED_SOLDIER_2 	= ((1 << 4) | PIECE_SIDE_MASK | SOLDIER_MASK);
	public static final int RED_SOLDIER_3 	= ((2 << 4) | PIECE_SIDE_MASK | SOLDIER_MASK);
	public static final int RED_SOLDIER_4 	= ((3 << 4) | PIECE_SIDE_MASK | SOLDIER_MASK);
	public static final int RED_SOLDIER_5 	= ((4 << 4) | PIECE_SIDE_MASK | SOLDIER_MASK);
	
	public static final int BLACK_KING 			= ((0 << 4) | KING_MASK);
	public static final int BLACK_ADVISOR_1 	= ((0 << 4) | ADVISOR_MASK);
	public static final int BLACK_ADVISOR_2 	= ((1 << 4) | ADVISOR_MASK);
	public static final int BLACK_ELEPHANT_1	= ((0 << 4) | ELEPHANT_MASK);
	public static final int BLACK_ELEPHANT_2 	= ((1 << 4) | ELEPHANT_MASK);;
	public static final int BLACK_HORSE_1 		= ((0 << 4) | HORSE_MASK);
	public static final int BLACK_HORSE_2 		= ((1 << 4) | HORSE_MASK);
	public static final int BLACK_ROOK_1  		= ((0 << 4) | ROOK_MASK);
	public static final int BLACK_ROOK_2 		= ((1 << 4) | ROOK_MASK);
	public static final int BLACK_CANNON_1  	= ((0 << 4) | CANNON_MASK);
	public static final int BLACK_CANNON_2 		= ((1 << 4) | CANNON_MASK);
	public static final int BLACK_SOLDIER_1 	= ((0 << 4) | SOLDIER_MASK);
	public static final int BLACK_SOLDIER_2 	= ((1 << 4) | SOLDIER_MASK);
	public static final int BLACK_SOLDIER_3 	= ((2 << 4) | SOLDIER_MASK);
	public static final int BLACK_SOLDIER_4 	= ((3 << 4) | SOLDIER_MASK);
	public static final int BLACK_SOLDIER_5 	= ((4 << 4) | SOLDIER_MASK);
	
	// �������ӵ����飬��һά��ʾ��ɫ
	public static final int[][] PIECE_ARRAY = new int[][]{
		{BLACK_KING, BLACK_ADVISOR_1, BLACK_ADVISOR_2, BLACK_ELEPHANT_1, 
			BLACK_ELEPHANT_2, BLACK_HORSE_1, BLACK_HORSE_2, BLACK_ROOK_1, 
			BLACK_ROOK_2, BLACK_CANNON_1, BLACK_CANNON_2, BLACK_SOLDIER_1, 
			BLACK_SOLDIER_2, BLACK_SOLDIER_3, BLACK_SOLDIER_4, BLACK_SOLDIER_5},
		{RED_KING, RED_ADVISOR_1, RED_ADVISOR_2, RED_ELEPHANT_1, 
			RED_ELEPHANT_2, RED_HORSE_1, RED_HORSE_2, RED_ROOK_1, 
			RED_ROOK_2, RED_CANNON_1, RED_CANNON_2, RED_SOLDIER_1, 
			RED_SOLDIER_2, RED_SOLDIER_3, RED_SOLDIER_4, RED_SOLDIER_5}
	};
	
	// �����߷��ȽϹ̶������ӵ��߷���������
	public static final int KING_STEP[] = {-0x10, -0x01, 0x10, 0x01};
	public static final int ADVISOR_STEP[] = {-0x11, -0x0f, +0x0f, +0x11};
	public static final int ELEPHANT_STEP[]  = {-0x22, -0x1e, +0x1e, +0x22};
	public static final int HORSE_STEP[]  = {-0x21, -0x1f, -0x12, -0x0e, +0x0e, +0x12, +0x1f, +0x21};
	
	/**
	 * �ж��Ƿ�����ʵ������
	 * @param loc
	 * @return
	 */
	public static boolean inBoard(int loc){
		return (IN_BOARD[loc] == 1);
	}
	
	/**
	 * �ж��Ƿ��ھŹ�����
	 * @param loc
	 * @return
	 */
	public static boolean inFort(int loc){
		return (IN_FORT[loc] == 1);
	}
	
	/**
	 * ���loc������
	 * @param loc
	 * @return
	 */
	public static int getRow(int loc){
		return (loc >> 4);
	}
	
	/**
	 * ���loc������
	 * @param loc
	 * @return
	 */
	public static int getCol(int loc){
		return (loc & COL_MASK);
	}
	
	/**
	 * ������ӵ�����
	 * @param piece
	 * @return
	 */
	public static int getPieceType(int piece){
		return (piece & PIECE_TYPE_MASK);
	}
	
	/**
	 * ������ӵ���ɫ
	 * @param piece
	 * @return
	 */
	public static int getPieceSide(int piece){
		return ((piece & PIECE_SIDE_MASK) >> 3);
	}
	
	/**
	 * �����л�������±�
	 * @param x
	 * @return
	 */
	public static int rowToIndex(int x){
		return (x << 4);
	}
	
	/**
	 * �ж��Ƿ���ͬһ����
	 * @param loc1
	 * @param loc2
	 * @return
	 */
	public static boolean isSameHalf(int loc1, int loc2){
		return (((loc1 ^ loc2) & BOARD_SIDE_MASK) == 0);
	}
	
	/**
	 * �ж��Ƿ���ͬһ��������
	 * @param piece1
	 * @param piece2
	 * @return
	 */
	public static boolean isSameSide(int piece1, int piece2){
		return (((piece1 ^ piece2) & PIECE_SIDE_MASK) == 0);
	}
	
	/**
	 * �ж��Ƿ��Ǻ췽���ӣ��о�����û��
	 * @param piece
	 * @return
	 */
	public static boolean isRedSide(int piece){
		return ((piece & PIECE_SIDE_MASK) != 0);
	}
	
	/**
	 * �ж������Ƿ��ڱ����볡
	 * @param piece
	 * @param loc
	 * @return
	 */
	public static boolean isInOwnHalf(int piece, int loc){
		return (((piece & PIECE_SIDE_MASK) << 4) ^ (loc & BOARD_SIDE_MASK)) == 0;
	}
	
	// ���Ժ���
	public static void main(String[] args) {
		System.out.println(isSameHalf(1, 2));
		for (int i = 0; i < 16; i++)
			System.out.println(PIECE_ARRAY[0][i] + "\t" + PIECE_ARRAY[1][i]);
	}
}
