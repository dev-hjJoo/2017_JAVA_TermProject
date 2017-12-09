package lowLevel;

public class GameSystem {
	/*
	 * field part
	 */
	private final static int sizeX = 10, sizeY = 10;
	private static int[][] valueArr = new int[sizeX][sizeY];
	// �������� ���� ������ �ִ� �迭 
	
	/*
	 * ������
	 */
	public GameSystem() {
		// GameSystem Ŭ������ �������ڸ��� valueArray�� �ʱ�ȭ�ϴ� �޼ҵ� ����
		initBoard();
		printBoard();
	}
	
	
	/*
	 * method part
	 */

	public static int getValue(int x, int y) {
		/*
		 * �޼ҵ� ȣ�� �� �ش� �ε����� �´� �� ����
		 */
		return valueArr[x][y];
	} // getValue �޼ҵ�
	
	public static int getSizeX() {
		return sizeX;
	}
	public static int getSizeY() {
		return sizeY;
	}
	
	
	public void initBoard() {
		/*
		 * ������ position ����(random) -> �ֺ��� ���ڿ� ���� �� ����
		 */
		
		final int bomb = 10;
		// ������ ��ġ ����
		for (int num=0; num<bomb; num++) {
			while(true) {
				int x = (int)(Math.random()*(valueArr.length-1));
				int y = (int)(Math.random()*(valueArr[0].length-1));
				// ������ �迭�̱� ������ � ���� ��Ƶ� ���� ũ�Ⱑ ���´�.
				
				if( valueArr[x][y] != -1) {
					valueArr[x][y] = -1;
					// ���ڰ� ������� ���� ���̸� ����(-1)�� �ٲپ��ش�.
					
					// ���� �ֺ� 3*3 ũ���� �迭�� count�� ���������ش�.
					for(int i=(x-1); i<=(x+1); i++) {
						for(int j=(y-1); j<=(y+1); j++) {
							
							if ((i>=valueArr.length || j>=valueArr[0].length) || (i<0 || j<0)) {
								break;
								// 3*3�迭�� valueArray�迭�� ����� �����.
							} else {
								// ����(-1)�� �ƴϸ� ���������ش�.
								if( valueArr[i][j] != (-1)) {
									valueArr[i][j] += 1;
								}
							}
							
						}
					}
					break;
				}
			}
		}
		
	}// initBoard �޼ҵ�
	
	public void printBoard() {
		/*
		 * consoleâ�� board �� ���
		 */
		for (int i=0; i<valueArr.length; i++) {
			for(int j=0; j<valueArr[i].length; j++) {
				System.out.print(valueArr[i][j] + "\t");
			}
			System.out.println();
		}
	}// printBoard �޼ҵ�
	
}
