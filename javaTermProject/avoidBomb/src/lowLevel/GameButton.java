package lowLevel;

import javax.swing.JButton;

/*
 * JButton을 상속받는 나만의 Button 
 */

public class MyButton extends JButton {
	
	private int row,col;	// 몇 행, 몇 열인 지 알려주는 row(행), col(열);
	private int value;
	
	
	/*
	 * 생성자 : row,col 값 초기화
	 */
	public MyButton(int x, int y) {
		
		row = x;
		col = y;
		
		value = GameSystem.getValue(row,col);
		
	}
	
	
	// ValueArray 초기화
	
	
	
	// 왼쪽 버튼 눌렀을 때 깃발 아이콘으로 바꾸기
	// 

}
