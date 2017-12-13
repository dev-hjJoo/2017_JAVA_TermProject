package bombGame;

import java.awt.Color;

public class ButtonTimer extends Thread {
	private GameButton selectedBtn;
	int x,y;
	
	public ButtonTimer(GameButton selectedBtn) {
		this.selectedBtn = selectedBtn;
		this.x = selectedBtn.getRow();
		this.y = selectedBtn.getCol();
	}
	
	@Override
	public void run() {
		int time = 0;		// Ÿ�̸� ī��Ʈ ��
		
		while (true) {

			time++;
			
			try {
				Thread.sleep(10);
				// System.out.println("Test_ButtonTimer.run_time : " +time);
				// Test_thread �۵��ϴ� ���� time ���
				
			} catch (InterruptedException e) {
				return;
			}
			if(time == 20) {				
				selectedBtn.setPink();
				return;
				// ���� �ٲپ� ������ Thread�� �� �̻� �۵��� �ʿ� ����
			}
		}
	}
}