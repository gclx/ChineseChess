/**
 * 
 */
package AI;

/**
 * TODO 
 * @author gclx
 * @version 1.0
 * @date 2018年11月30日 下午3:07:13
 * @copyright 
 * @remarks TODO
 *
 */
public class HistoryTable {
	
	public static final int STEP_UPPER_BOUND = 256 * 256;
	
	public static int[] table = new int[STEP_UPPER_BOUND];

	public HistoryTable() {}
	
	public static void clear() {
		// TODO Auto-generated method stub
		for (int i = 0; i < table.length; i++){
			table[i] = 0;
		}
	}

	/**
	 * @param step
	 * @return
	 */
	public static int getRecord(int step) {
		// TODO Auto-generated method stub
		return table[step];
	}

	/**
	 * @param step
	 * @param depth
	 */
	public static void saveRecord(int step, int depth) {
		// TODO Auto-generated method stub
		table[step] += depth * depth;
	}

}
