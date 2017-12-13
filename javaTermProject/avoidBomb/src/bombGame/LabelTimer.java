package bombGame;

import java.awt.Color;

import javax.swing.JLabel;

public class LabelTimer extends Thread{
	
	JLabel getScore;
	
	public LabelTimer(JLabel getScore) {
		this.getScore = getScore;
	}
	
	@Override
	public void run() {
		
		int count = 0;
		
		while(true) {
			
			if(count==1) {
				getScore.setForeground(Color.gray);
				return;
			}
			count ++;
			
			try {
				sleep(500);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
