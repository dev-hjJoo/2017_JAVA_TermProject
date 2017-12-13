package bombGame;

import javax.swing.JLabel;

public class Timer extends Thread {
	
	private JLabel timeLabel;
	
	public Timer(JLabel timeLabel) {
		this.timeLabel = timeLabel;
	}
	
	@Override
	public void run() {

		int count = 0;
		
		while(true) {
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
