package bombGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import bombGame.MyFrame.ButtonMouseEvent;

/*
 * JButton�� ��ӹ޴� ������ Button 
 */

public class GameButton extends JButton {
	
	/*
	 * 			< field part >
	 */
	
	private int row,col;	// �� ��, �� ���� �� �˷��ִ� row(��), col(��);
	private int value;		// ����, Ȥ�� �ֺ� ���� ������ ������ ��� �ִ� ��
	
	private boolean isFlag;	// ����� �޾Ҵ� �� �� �� �ʾҴ� �� Ȯ���ϴ� ����
	
	private boolean isPink;	// ��ư ���� ��ũ���� �� Ȯ���ϴ� ���� (��ư�� �� ������ ��ũ������ ����)
	private boolean isBlocked;	// ��ư�� 0�� �� Ȯ���ϴ� ����
	private boolean isWhite;	// ��ư�� ���콺 ���� Ŭ���Ǿ������� Ȯ���ϴ� ����
	
	private ButtonTimer thread;
	
	
	/*
	 * 			< ������ > : �� �ʱ�ȭ
	 */
	public GameButton(int x, int y) {
		
		row = x;
		col = y;
		
		value = GameSystem.getValue(row,col);
		
		isFlag = false;
		// ���ʿ��� ����� ���� ����
		isPink = false;
		isBlocked = false;
		isWhite = false;
		
	}
	
	
	
	/*
	 * 			< method part >
	 */
	
	// get method
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public int getValue() {
		return value;
	}

	// flag method
	public boolean isFlag() {
		return isFlag;
	}
	public void toggleFlag() {
		isFlag = !isFlag;
		if(isFlag) {
			ImageIcon image = new ImageIcon("images/flag.png");
			this.setIcon(image);
		} else {
			this.setIcon(null);
		}
	}
	
	
	/*
	 *  button click ���� method
	 */
	
	/* ��ư image Change*/
	public void showNumberImage() {

		this.setWhite();

		if (value == 0) {
			this.setBlock();
			this.setEnabled(false); 
		}
		
		if (value != 0) {
			this.setText(Integer.toString(value));
			
			switch (this.getText()) {
			case "1":
				this.setForeground(Color.BLUE);
				break;
			case "2":
				this.setForeground(Color.RED);
				break;
			case "3":
				this.setForeground(Color.ORANGE);
				break;
			case "4" :
				this.setForeground(Color.GREEN);
			default :
				this.setForeground(Color.MAGENTA);	
			}
			
			//this.setIcon(numberImage[this.value]);
			// ���� image�� ���� �ȿ��� ����� pass...
			System.out.println("GameButton.showNumberImage_value :: " + value);
			
		}
	}
	
	// �ƾ� ���� ����! -> block
	public void setBlock() {
		isBlocked = true;
	}
	public boolean isBlocked() {
		return isBlocked;
	}
	
	// ���� ���� ������ ���� ���ϸ� �ȵ�!!
	public void setWhite() {
		isWhite = true;
		this.btnColorChange(Color.WHITE);
	}
	public boolean isWhite() {
		return isWhite;
	}
	
	
	/* color Change _pink */
	public boolean isPink() {
		return isPink;
	}
	public void setPink() {
		isPink = true;
		this.btnColorChange(Color.PINK);
	}
	public void releasePink() {
		isPink = false;
		this.btnColorChange(Color.LIGHT_GRAY);
	}
		public void btnColorChange(Color color) {
		this.setBackground(color);
		this.repaint();
	}
		
		
	// ���� �ð��� �������ִ� thread ���� method
	public void startThread() {
		// �ش� ��ư�� �����带 ����
		// 200ms ���� ������ ������� �� Thread
		thread = new ButtonTimer(this);
		thread.start();
	}
	public void stopThread() {
		// �ش� ��ư�� �����带 ����
		thread.interrupt();
	}
	
	

	
	

}
