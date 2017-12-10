package bombGame;

public class GameSystem {
	/*
	 * field part
	 */
	private final static int sizeX = 10, sizeY = 10;	
	private static int[][] valueArr = new int[sizeX][sizeY];
	// 지뢰판의 값을 가지고 있는 배열 
	
	final static int bombCount = 10;
	// 폭탄의 개수
	
	private static boolean start = false;
	// 게임이 시작 중인 지에 대한 값을 가지고 있는 변수
	
	private static int bestScore;
	private static int currentScore = 0;
	// 점수를 가지고 있는 score
	
	
	
	
	/*
	 * 생성자
	 */
	public GameSystem() {
		// GameSystem 클래스를 실행하자마자 valueArray를 초기화하는 메소드 실행
		initBoard();
		printBoard();
		
	}
	
	
	
	
	/*
	 * method part
	 */

	public static int getValue(int x, int y) {
		/*
		 * 메소드 호출 시 해당 인덱스에 맞는 값 리턴
		 */
		return valueArr[x][y];
	} // getValue 메소드
	
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
	 * 시작과 관련된 method
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
	 * score와 관련된 method
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
		 * 지뢰의 position 선정(random) -> 주변의 지뢰에 따라 값 결정
		 */
		
		for(int i=0;i<valueArr.length; i++) {
			for(int j=0; j<valueArr[i].length; j++) {
				valueArr[i][j] = 0;
				// valueArr 배열을 모두 0값으로 초기화해준다.
			}
		}
		
		// 지뢰의 위치 선정
		for (int num=0; num<bombCount; num++) {
			while(true) {
				int x = (int)(Math.random()*(valueArr.length-1));
				int y = (int)(Math.random()*(valueArr[0].length-1));
				// 정방형 배열이기 때문에 어떤 행을 잡아도 같은 크기가 나온다.
				
				if( valueArr[x][y] != -1) {
					
					valueArr[x][y] = -1;
					// 지뢰가 들어있지 않은 값이면 지뢰(-1)로 바꾸어준다.
					
					// 지뢰 주변 3*3 크기의 배열에 count를 증가시켜준다.
					for(int i=(x-1); i<=(x+1); i++) {
						for(int j=(y-1); j<=(y+1); j++) {
							
							if ((i>=valueArr.length || j>=valueArr[0].length) || (i<0 || j<0)) {
								continue;
								// 3*3배열이 valueArray배열을 벗어나면 멈춘다.
							} else {
								// 지뢰(-1)가 아니면 증가시켜준다.
								if( valueArr[i][j] != (-1)) {
									valueArr[i][j] += 1;
								}
							}
							
						}
					}
					break;
					// 지뢰가 없는 (-1이 아닌) 곳에 지뢰를 넣고 나면 종료.
				} // if문
			} // while문
		}
		
	}// initBoard 메소드
	
	public static void printBoard() {
		/*
		 * console창에 board 값 출력
		 */
		for (int i=0; i<valueArr.length; i++) {
			for(int j=0; j<valueArr[i].length; j++) {
				System.out.print(valueArr[i][j] + "\t");
			}
			System.out.println();
		}
	}// printBoard 메소드
	
	
}
