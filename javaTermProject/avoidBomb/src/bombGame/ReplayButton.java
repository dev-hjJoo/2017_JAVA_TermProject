package bombGame;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ReplayButton extends JButton {
	
	/*
	 * 필드 영역
	 */
	private String status = "normal";
	
	
	/*
	 * 생성자
	 */
	public ReplayButton() {
		
		changeImage(status);
		
	}
	
	
	/*
	 * 메소드 영역
	 */
	
	// status를 반환하는 메소드
	public String getStatus() {
		return status;
	}
	
	// 버튼 image를 변경하는 메소드
	public void changeImage(String status) {
		
		this.status = status;
		
		System.out.println("ReplayButton.changeImage :: status : " + status);
		
		ImageIcon image = new ImageIcon("images/emotion/"+status+".png");
		this.setIcon(image);
	}

}
