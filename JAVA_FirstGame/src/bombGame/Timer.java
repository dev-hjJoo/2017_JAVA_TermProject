package bombGame;

import javax.swing.JLabel;

public class Timer extends Thread {
	/*
	 * game 전체 시간 관리하는 Thread 클래스
	 */
	
	// 시간을 출력해줄 label
	private JLabel timeLabel;

	
	public Timer(JLabel timeLabel) {
		// MyFrame의 label과 연결
		this.timeLabel = timeLabel;
	}
	
	@Override
	public void run() {

		int count = 0;
		
		while(true) {
			// 시간이 흐르는 것을 바로 timeLabel에 출력
			timeLabel.setText(Integer.toString(count));
			count ++;
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
