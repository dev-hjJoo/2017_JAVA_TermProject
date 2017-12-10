/* Key ������ */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyKeyListener extends KeyAdapter {
	private Container c;
	private JLabel focus;
	private CatThread catTh;
	private DogThread dogTh;
	private FishThread fishTh;
	private JLabel scorelabel;
	private JLabel plusone;
	private JLabel minus3;
	private JLabel plus5;
	
	public static int score = 0;  // ���� ���
	int a = 0;  // ���� ���� �� ���� �̹��� ǥ���� �� ����
	
	public MyKeyListener(Container c, JLabel focus, JLabel scorelabel, JLabel plusone, JLabel minus3, JLabel plus5)
	{
		this.c = c;
		this.focus = focus;
		this.scorelabel = scorelabel;
		this.plusone = plusone;
		this.minus3 = minus3;
		this.plus5 = plus5;
	}
	
	public void setThread(CatThread catTh, DogThread dogTh, FishThread fishTh) {
		this.catTh = catTh;
		this.dogTh = dogTh;
		this.fishTh = fishTh;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();  // �Էµ� Ű�� Ű�ڵ� �˾Ƴ��� ����
		
		ScoreThread scoreTh = new ScoreThread();  // ���� �̹��� ��Ÿ���� �ϴ� ������ ����
		scoreTh.start();
		
		// Ű �ڵ� ���� ���� ��,��,��,�� Ű �Ǻ� & Ŀ�� ��ġ �̵�
		switch(keyCode) { 
			case KeyEvent.VK_UP:
				if(focus.getY() <= 70) {  // ��Ŀ���� 1���� ���� ���� ���� �ʵ��� �ϴ� ���ǹ�
					focus.setLocation(focus.getX(), 70);
					break;
				} 
				focus.setLocation(focus.getX(), focus.getY()-100);
				focus.repaint(); 
				break;
			case KeyEvent.VK_DOWN:
				if(focus.getY() >= 270) {  // ��Ŀ���� 3���� ���� ������ ���� �ʵ��� �ϴ� ���ǹ�
					focus.setLocation(focus.getX(), 270);
					break;
				}
				focus.setLocation(focus.getX(), focus.getY()+100);
				focus.repaint();
				break;
			case KeyEvent.VK_LEFT:
				if(focus.getX() <= 50) {  // ��Ŀ���� 1���� ���� �������� ���� �ʵ��� �ϴ� ���ǹ�
					focus.setLocation(50, focus.getY());
					break;
				}
				focus.setLocation(focus.getX()-160, focus.getY());
				focus.repaint();
				break;
			case KeyEvent.VK_RIGHT:
				if(focus.getX() >= 370) {  // ��Ŀ���� 3���� ���� ���������� ���� �ʵ��� �ϴ� ���ǹ�
					focus.setLocation(370, focus.getY());
					break;
				}
				focus.setLocation(focus.getX()+160, focus.getY());
				focus.repaint();
				break;
			
			// ENTER Ű�� ����� ���
			case KeyEvent.VK_ENTER:
				// ����� ����� �� ���ǹ�
				if(catchcat() == true) {  
					score++;
					getScore();
					a++;
					break;
				} else 
					c.remove(plusone);
				
				// ������ ����� ��
				if(catchdog() == true) {
					score -= 3;
					getScore();
					a += 2;
					break;
				} else 
					c.remove(minus3);
				
				// ����� ����� ��
				if(catchfish() == true) {
					score += 5;
					getScore();
					a += 3;
					break;
				} else
					c.remove(plus5);
		}
	}
	
	/* ����� ��ǥ�� ��Ŀ�� ��ǥ ���ϴ� �޼ҵ� */
	public boolean catchcat() {
		if(catTh.x == focus.getX()+5 && catTh.y == focus.getY()+10) {
			return true;
		}
		else
			return false;
	}
	
	/* ������ ��ǥ�� ��Ŀ�� ��ǥ ���ϴ� �޼ҵ� */
	public boolean catchdog() {
		if(dogTh.x == focus.getX()+5 && dogTh.y == focus.getY()+10) {
			return true;
		}
		else
			return false;
	}
	
	/* ����� ��ǥ�� ��Ŀ�� ��ǥ ���ϴ� �޼ҵ� */
	public boolean catchfish() {
		if(fishTh.x == focus.getX()+5 && fishTh.y == focus.getY()+10) {
			return true;
		}
		else
			return false;
	}
	
	int b = 0;  // dogTh�� fishTh �ߺ� ���� ����
	
	/* ���� ����ϴ� �޼ҵ� */
	void getScore() {
		scorelabel.setText(" SCORE : " + Integer.toString(score));  // ���̺� ���� �� ���
		if(score == 10 && b == 0) {  // 10�� �Ǹ� ������ ����
			dogTh.start();
			b++;
		}
		else if(score == 15 && b == 1) {  // 15�� �Ǹ� ����� ����
			fishTh.start();	 
			b++;
		}
	}

	/* ���� �̹��� �߰��ߴٰ� ������� ������ */
	class ScoreThread extends Thread {
		int d = 0;
		public void run() {
			while(true) {
				if(a == 1) {  // ����� ����� �� +1
					plusone.setLocation(catTh.x+10, catTh.y);
					c.add(plusone);
					a = 0;
					d++;
				} 
				else if(a == 2) {  // ������ ����� �� -3
					minus3.setLocation(dogTh.x+10, dogTh.y);
					c.add(minus3);
					a = 0;
					d += 2;
				} 
				else if(a == 3) {  // ����� ����� �� +5
					plus5.setLocation(fishTh.x+10, fishTh.y);
					c.add(plus5);
					a = 0;
					d += 3;
				}
				
				try {
					Thread.sleep(800); // 0.8�� �� ���� ����� �� �߰��� ���� �̹��� ����
					if (d == 1) {  // +1 �̹��� ����
						c.remove(plusone);
						d = 0;
					} else if (d == 2) {  // -3 �̹��� ����
						c.remove(minus3);
						d = 0;
					} else if (d == 3) {  // +5 �̹��� ����
						c.remove(plus5);
						d = 0;
					}
					c.repaint();
					
					if(TimerThread.n == -1) {  // Ÿ�̸� ������ �����ϸ� ��� ���� �̹��� ����
						c.remove(plusone);
						c.remove(minus3);
						c.remove(plus5);
						return;  // ������ ����
					}
				}
				catch(InterruptedException e) { return; }  // ���� �߻� �� ������ ����	
			}
		}
	}
}