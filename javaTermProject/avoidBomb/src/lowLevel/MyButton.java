package lowLevel;

import javax.swing.JButton;

/*
 * JButton�� ��ӹ޴� ������ Button 
 */

public class MyButton extends JButton {
	
	private int row,col;	// �� ��, �� ���� �� �˷��ִ� row(��), col(��);
	private int value;
	
	
	/*
	 * ������ : row,col �� �ʱ�ȭ
	 */
	public MyButton(int x, int y) {
		
		row = x;
		col = y;
		
		value = GameSystem.getValue(row,col);
		
	}
	
	
	// ValueArray �ʱ�ȭ
	
	
	
	// ���� ��ư ������ �� ��� ���������� �ٲٱ�
	// 

}
