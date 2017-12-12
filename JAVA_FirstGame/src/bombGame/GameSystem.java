package bombGame;

public class GameSystem {
	/*
	 * field part
	 */
	private final static int sizeX = 10, sizeY = 10;	
	private static int[][] valueArr = new int[sizeX][sizeY];
	// �������� ���� ������ �ִ� �迭 
	
	final static int bombCount = 10;
	// ��ź�� ����
	
	private static boolean start = false;
	// ������ ���� ���� ���� ���� ���� ������ �ִ� ����
	
	private static int bestScore;
	private static int currentScore = 0;
	// ������ ������ �ִ� score
	
	
	
	
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
	public static int getBombCount() {
		return bombCount;
	}
	
	/*
	 * ���۰� ���õ� method
	 */
	public static void startGame() {
		start = true;
		System.out.println("startGame");
		initCurrentScore();
	}
	public static void stopGame() {
		start = false;
		System.out.println("The end");
		
	}
	public static boolean isStarted() {
		return start;
	}
	
	/*
	 * score�� ���õ� method
	 */
	
	public static int getBestScore() {
		return bestScore;
	}
	public static int getCurrentScore() {
		return currentScore;
	}
	
	public static void setBestScore(int score) {
		bestScore = score;
	}
	
	public static void initCurrentScore() {
		currentScore = 0;
	}
	public static void upScore(int score) {
		currentScore += score;
	}
	
	
	
	
	
	public static void initBoard() {
		/*
		 * ������ position ����(random) -> �ֺ��� ���ڿ� ���� �� ����
		 */
		
		for(int i=0;i<valueArr.length; i++) {
			for(int j=0; j<valueArr[i].length; j++) {
				valueArr[i][j] = 0;
				// valueArr �迭�� ��� 0������ �ʱ�ȭ���ش�.
			}
		}
		
		// ������ ��ġ ����
		for (int num=0; num<bombCount; num++) {
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
								continue;
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
					// ���ڰ� ���� (-1�� �ƴ�) ���� ���ڸ� �ְ� ���� ����.
				} // if��
			} // while��
		}
		
	}// initBoard �޼ҵ�
	
	public static void printBoard() {
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
