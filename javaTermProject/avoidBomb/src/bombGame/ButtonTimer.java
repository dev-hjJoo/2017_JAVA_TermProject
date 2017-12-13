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
		int time = 0;		// 타이머 카운트 값
		
		while (true) {

			time++;
			
			try {
				Thread.sleep(10);
				// System.out.println("Test_ButtonTimer.run_time : " +time);
				// Test_thread 작동하는 동안 time 출력
				
			} catch (InterruptedException e) {
				return;
			}
			if(time == 20) {				
				selectedBtn.setPink();
				return;
				// 색깔 바꾸어 줬으니 Thread는 더 이상 작동할 필요 ㄴㄴ
			}
		}
	}
}