/**
 * 
 */
package AI;

/**
 * 此类为AI使用的棋盘，和board中的ChessBoard有较大差距，设计用以快速位运算
 * @author gclx
 * @version 1.0
 * @date 2018年11月22日 下午1:58:09
 * @copyright 
 * @remarks TODO
 */
public class Board {
	
	// 判断棋子是否在棋盘内
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
	
	// 判断棋子是否在九宫格内
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
	
	// AI棋盘大小，16 * 16便于进行位运算
	public static final int BOARD_SIZE = 256;
	// AI棋盘列数
	public static final int MAX_COL = 16;
	// AI棋盘行数
	public static final int MAX_ROW = 16;
	// 真实棋盘列数
	public static final int REAL_MAX_COL = 9;
	// 真实棋盘行数
	public static final int REAL_MAX_ROW = 10;
	// 最大棋子数
	public static final int MAX_PIECES = 32;
	// 最大棋子编号
	public static final int MAX_PEICES_ID = 1 << 7;
	// 最大棋子类型
	public static final int MAX_PIECE_TYPECNT = 8;
	
	// 边界留白
	public static final int MARGIN_L = 3;
	public static final int MARGIN_R = 3;
	public static final int MARGIN_U = 3;
	public static final int MARGIN_D = 4;
	
	// 红黑方编号
	public static final int RED_SIDE = 1;
	public static final int BLACK_SIDE = 0;
	
	/**
	 * 一系列掩码的定义，用于将来的位运算
	 */
	// 棋子颜色掩码，红方第四位为1，黑方为0
	public static final int PIECE_SIDE_MASK = 0x08;
	// 棋子类型掩码，低三位表示七种不同的棋子
	public static final int PIECE_TYPE_MASK = 0x07;
	// 红方半场第八位为1，黑方为0
	public static final int BOARD_SIDE_MASK = 0x80;
	// 坐标所在行的掩码
	public static final int ROW_MASK = 0xf0;
	// 坐标所在列的掩码
	public static final int COL_MASK = 0x0f;
	
	// 七种棋子的掩码，从1到7
	public static final int KING_MASK = 1;
	public static final int ADVISOR_MASK = 2;
	public static final int ELEPHANT_MASK = 3;
	public static final int HORSE_MASK = 4;
	public static final int ROOK_MASK = 5;
	public static final int CANNON_MASK = 6;
	public static final int SOLDIER_MASK = 7;
	
	// 所有棋子的编码
	//								      	   该类第几颗棋子	|	      棋子颜色	      |		棋子类型	
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
	
	// 所有棋子的数组，第一维表示颜色
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
	
	// 几种走法比较固定的棋子的走法生成数列
	public static final int KING_STEP[] = {-0x10, -0x01, 0x10, 0x01};
	public static final int ADVISOR_STEP[] = {-0x11, -0x0f, +0x0f, +0x11};
	public static final int ELEPHANT_STEP[]  = {-0x22, -0x1e, +0x1e, +0x22};
	public static final int HORSE_STEP[]  = {-0x21, -0x1f, -0x12, -0x0e, +0x0e, +0x12, +0x1f, +0x21};
	
	/**
	 * 判断是否在真实棋盘内
	 * @param loc
	 * @return
	 */
	public static boolean inBoard(int loc){
		return (IN_BOARD[loc] == 1);
	}
	
	/**
	 * 判断是否在九宫格内
	 * @param loc
	 * @return
	 */
	public static boolean inFort(int loc){
		return (IN_FORT[loc] == 1);
	}
	
	/**
	 * 获得loc的行数
	 * @param loc
	 * @return
	 */
	public static int getRow(int loc){
		return (loc >> 4);
	}
	
	/**
	 * 获得loc的列数
	 * @param loc
	 * @return
	 */
	public static int getCol(int loc){
		return (loc & COL_MASK);
	}
	
	/**
	 * 获得棋子的类型
	 * @param piece
	 * @return
	 */
	public static int getPieceType(int piece){
		return (piece & PIECE_TYPE_MASK);
	}
	
	/**
	 * 获得棋子的颜色
	 * @param piece
	 * @return
	 */
	public static int getPieceSide(int piece){
		return ((piece & PIECE_SIDE_MASK) >> 3);
	}
	
	/**
	 * 根据行获得数组下标
	 * @param x
	 * @return
	 */
	public static int rowToIndex(int x){
		return (x << 4);
	}
	
	/**
	 * 判断是否在同一半面
	 * @param loc1
	 * @param loc2
	 * @return
	 */
	public static boolean isSameHalf(int loc1, int loc2){
		return (((loc1 ^ loc2) & BOARD_SIDE_MASK) == 0);
	}
	
	/**
	 * 判断是否是同一方的棋子
	 * @param piece1
	 * @param piece2
	 * @return
	 */
	public static boolean isSameSide(int piece1, int piece2){
		return (((piece1 ^ piece2) & PIECE_SIDE_MASK) == 0);
	}
	
	/**
	 * 判断是否是红方棋子，感觉可能没用
	 * @param piece
	 * @return
	 */
	public static boolean isRedSide(int piece){
		return ((piece & PIECE_SIDE_MASK) != 0);
	}
	
	/**
	 * 判断棋子是否在本方半场
	 * @param piece
	 * @param loc
	 * @return
	 */
	public static boolean isInOwnHalf(int piece, int loc){
		return (((piece & PIECE_SIDE_MASK) << 4) ^ (loc & BOARD_SIDE_MASK)) == 0;
	}
	
	// 测试函数
	public static void main(String[] args) {
		System.out.println(isSameHalf(1, 2));
		for (int i = 0; i < 16; i++)
			System.out.println(PIECE_ARRAY[0][i] + "\t" + PIECE_ARRAY[1][i]);
	}
}
