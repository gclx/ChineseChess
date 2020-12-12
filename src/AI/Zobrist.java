/**
 * 
 */
package AI;

import java.util.Random;

/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年11月29日 下午8:32:43
 * @copyright 
 * @remarks TODO
 *
 */
public class Zobrist {
	// 存储双方每个棋子的hash值
	public static final Zobrist[][][] Z_TABLE = new Zobrist[2][Board.MAX_PIECE_TYPECNT][Board.BOARD_SIZE];
	// 相同局面， 需要在hash值上体现出轮到哪方下棋
	
	public static final Zobrist Z_SIDE = new Zobrist();
	// 随机数发生器
		/* 
		 * THE MEANING OF UNIVERSE BLESS MY HASH FEW CONFLICTS !
		 * --------------------------------------------
		 * -         ---                --------      -
		 * -        ---               ------------    -
		 * -       ---    ---        ---        ---   -
		 * -      ---    ---        ---         ---   -
		 * -     ---    ---                    ---    -
		 * -    ---    ---                    ---     -
		 * -   ---    ---                   ---       -
		 * -  -------------------         ---         -
		 * -  -------------------       ---           -
		 * -         ---              ---             -
		 * -        ---              --------------   -
		 * -       ---              ----------------  -
		 * -                                          -
		 * --------------------------------------------
		 * DEVOTE THE REST OF MY LIFE TO YOU, 42! MERCY, MY LORD!
		 */
		private static Random random = new Random(42);
		
		private long key = 0;
		private long lock = 0;
		
		public Zobrist() {}
		
		public void xor(Zobrist rhs){
			key ^= rhs.key;
			lock ^= rhs.lock;
		}

		static{
			Z_SIDE.key = random.nextLong();
			Z_SIDE.lock = random.nextLong();
			for (int side = 0; side < 2; side++){
				for (int pieceType = Board.KING_MASK; pieceType <= Board.SOLDIER_MASK; pieceType++){
					for (int loc = 0; loc < Board.BOARD_SIZE; loc++){
						Z_TABLE[side][pieceType][loc] = new Zobrist();
						Z_TABLE[side][pieceType][loc].key = random.nextLong();
						Z_TABLE[side][pieceType][loc].lock = random.nextLong();
					}
				}
			}
			System.out.println("Zobrist has been Initialized!");
		}
		
		public long getKey() {
			return key;
		}

		public void setKey(long key) {
			this.key = key;
		}

		public long getLock() {
			return lock;
		}

		public void setLock(long lock) {
			this.lock = lock;
		}
}
