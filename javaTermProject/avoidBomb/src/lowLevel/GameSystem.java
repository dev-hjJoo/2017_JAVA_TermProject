package lowLevel;

public class GameSystem {
	/*
	 * field part
	 */
	private final static int sizeX = 10, sizeY = 10;
	private static int[][] valueArr = new int[sizeX][sizeY];
	// 지뢰판의 값을 가지고 있는 배열 
	
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
	
	
	public void initBoard() {
		/*
		 * 지뢰의 position 선정(random) -> 주변의 지뢰에 따라 값 결정
		 */
		
		final int bomb = 10;
		// 지뢰의 위치 선정
		for (int num=0; num<bomb; num++) {
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
								break;
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
				}
			}
		}
		
	}// initBoard 메소드
	
	public void printBoard() {
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
