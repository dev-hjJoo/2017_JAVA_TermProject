package bombGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import bombGame.MyFrame.ButtonMouseEvent;

/*
 * JButton을 상속받는 나만의 Button 
 */

public class GameButton extends JButton {
	
	/*
	 * 			< field part >
	 */
	
	private int row,col;	// 몇 행, 몇 열인 지 알려주는 row(행), col(열);
	private int value;		// 지뢰, 혹은 주변 지뢰 개수의 정보를 담고 있는 값
	
	private boolean isFlag;	// 깃발을 달았는 지 달 지 않았는 지 확인하는 변수
	
	private boolean isPink;	// 버튼 색이 핑크색인 지 확인하는 변수 (버튼을 꾹 누르면 핑크색으로 변함)
	private boolean isBlocked;	// 버튼이 0인 지 확인하는 변수
	private boolean isWhite;	// 버튼이 마우스 왼쪽 클릭되었었는지 확인하는 변수
	
	private ButtonTimer thread;
	
	
	/*
	 * 			< 생성자 > : 값 초기화
	 */
	public GameButton(int x, int y) {
		
		row = x;
		col = y;
		
		value = GameSystem.getValue(row,col);
		
		isFlag = false;
		// 최초에는 깃발이 없는 상태
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
	 *  button click 관련 method
	 */
	
	/* 버튼 image Change*/
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
			// 숫자 image가 별로 안예쁜 관계로 pass...
			System.out.println("GameButton.showNumberImage_value :: " + value);
			
		}
	}
	
	// 아애 접근 ㄴㄴ! -> block
	public void setBlock() {
		isBlocked = true;
	}
	public boolean isBlocked() {
		return isBlocked;
	}
	
	// 누를 수는 있지만 색은 변하면 안돼!!
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
		
		
	// 일정 시간을 측정해주는 thread 관련 method
	public void startThread() {
		// 해당 버튼의 스레드를 실행
		// 200ms 이후 색깔을 변경시켜 줄 Thread
		thread = new ButtonTimer(this);
		thread.start();
	}
	public void stopThread() {
		// 해당 버튼의 스레드를 종료
		thread.interrupt();
	}
	
	

	
	

}
