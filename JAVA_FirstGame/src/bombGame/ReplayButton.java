package bombGame;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ReplayButton extends JButton {
	
	/*
	 * �ʵ� ����
	 */
	private String status = "normal";
	
	
	/*
	 * ������
	 */
	public ReplayButton() {
		
		changeImage(status);
		
	}
	
	
	/*
	 * �޼ҵ� ����
	 */
	
	// status�� ��ȯ�ϴ� �޼ҵ�
	public String getStatus() {
		return status;
	}
	
	// ��ư image�� �����ϴ� �޼ҵ�
	public void changeImage(String status) {
		
		this.status = status;
		
		System.out.println("ReplayButton.changeImage :: status : " + status);
		
		ImageIcon image = new ImageIcon("images/emotion/"+status+".png");
		this.setIcon(image);
	}

}
