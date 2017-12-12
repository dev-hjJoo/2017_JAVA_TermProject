package bombGame;

import javax.swing.JLabel;

public class Timer extends Thread {
	/*
	 * game ��ü �ð� �����ϴ� Thread Ŭ����
	 */
	
	// �ð��� ������� label
	private JLabel timeLabel;

	
	public Timer(JLabel timeLabel) {
		// MyFrame�� label�� ����
		this.timeLabel = timeLabel;
	}
	
	@Override
	public void run() {

		int count = 0;
		
		while(true) {
			// �ð��� �帣�� ���� �ٷ� timeLabel�� ���
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
