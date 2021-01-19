package nyangIGame;

/* game Thread */
import java.awt.*;
import javax.swing.*;

/* timer Thread */
class TimerThread extends Thread {
	private JLabel timerlabel;  //timer value print lable
	private CatThread catTh;
	public static int n=30;
	
	public TimerThread(JLabel timerlabel, CatThread catTh) {
		this.timerlabel = timerlabel;
		this.catTh = catTh;
	}
	
	// Thread code. run() end Thread end
	@Override
	public void run() {
		catTh.start();  // cat Thread starting
		
		while(true) {
			timerlabel.setText(" TIME : " + Integer.toString(n));  //count value print label
			
			n--;
			
			try {
				Thread.sleep(1000);
				if(n == -1) {  // 0sec Thread end
					new TimeOver();
					return;  
				}
			}
			catch(InterruptedException e) { return; }  //exception occurrence thread end
		}	
	}
}

/* cat image thread */
class CatThread extends Thread {
	private JLabel cat;
	private Container c;
	private JLabel plusone;
	private DogThread dogTh;
	private FishThread fishTh;
	
	public int ca [] = {55, 215, 375};  // cat x position value
	public int cb [] = {80, 180, 280};  // cat y position value
	int x=0, y=0;  //cat position random variable
	
	public CatThread(JLabel cat, Container c, JLabel plusone) {
		this.c = c;
		this.cat = cat;
		this.plusone = plusone;
	}
	public void setTh(DogThread dogTh, FishThread fishTh) {
		this.dogTh = dogTh;
		this.fishTh = fishTh;
	}
	
	//Thread code. run() end Thread end
	@Override
	public void run() {
		// cat icon create
		ImageIcon cimg = new ImageIcon("images/cat.png");
		cat = new JLabel(cimg);
			
		while(true) {
			cat.setSize(cimg.getIconWidth(),cimg.getIconWidth());   //label size == icon size
			
			int i = (int)(Math.random()*3);  // 0 ~ 2
			int j = (int)(Math.random()*3);  // 0 ~ 2
			x = ca[i];
			y = cb[j];

			cat.setLocation(x,y);
			
			// cat position cat & fish position conditional statements to avoid overlap
			if ((x == dogTh.x && y == dogTh.y) || (fishTh.x == x && fishTh.y == y)) {
				c.remove(cat);
			} else { c.add(cat); }
				
			try {
				Thread.sleep(800);  // 0.8sec random cat appears in cycles
				if ((x == dogTh.x && y == dogTh.y) || (fishTh.x == x && fishTh.y == y)) {
					c.remove(cat);
				} else { c.repaint(); }
				
				if(TimerThread.n == -1) {
					c.remove(cat);
					c.remove(plusone);
					return;  //thread end
				}
			}
			catch(InterruptedException e) { return; }  //end the thread when an exception occurs
			}
	}
}

/* ������ ������ �ϴ� ������ */
class DogThread extends Thread {
	private Container c;
	private JLabel minus3;
	private CatThread catTh;
	private FishThread fishTh;
	
	public int da [] = {55, 215, 375};  // ������ x��ǥ ����
	public int db [] = {80, 180, 280};  // ������ y��ǥ ����
	int x=0, y=0;  // ������ ��ǥ �� �������� ���õ� �� �־��� ����
	
	public DogThread(Container c, JLabel minus3, CatThread catTh) {
		this.c = c;
		this.minus3 = minus3;
		this.catTh = catTh;
	}
	public void setFishTh(FishThread fishTh) { 
		this.fishTh = fishTh;
	}
	
	// ������ �ڵ�. run()�� �����ϸ� ������ ����
	@Override
	public void run() {
		// ������ ������ ����
		ImageIcon dimg = new ImageIcon("images/dog.png");
		JLabel dog = new JLabel(dimg);
			
		while(true) {
			dog.setSize(dimg.getIconWidth(),dimg.getIconWidth());   // ���̺� ũ�� = �̹��� ũ��
			
			int i = (int)(Math.random()*3);  // 0 ~ 2
			int j = (int)(Math.random()*3);  // 0 ~ 2
			x = da[i];
			y = db[j];

			dog.setLocation(x,y);
			
			// ������ ��ǥ�� ����� & ����� ��ǥ�� ��ġ�� �ʰ� �ϱ� ���� ���ǹ�
			if ((x == catTh.x && y == catTh.y) || (x == fishTh.x && y == fishTh.y)) {
				c.remove(dog);
			} else { c.add(dog); }
				
			try {
				Thread.sleep(1200);  // 1.2�� �ֱ�� ������ �����ϰ� ��Ÿ��
				
				if ((catTh.x == x && catTh.y == y) || (fishTh.x == x && fishTh.y == y)) {
					c.remove(dog);
				} else { c.repaint(); }
				
				if(TimerThread.n == -1) {
					c.remove(dog);
					c.remove(minus3);
					return;  // ������ ����
				}
			}
			catch(InterruptedException e) { return; }  // ���� �߻� �� ������ ����
			}
	}
}

/* ����� ������ �ϴ� ������ */
class FishThread extends Thread {
	private Container c;
	private JLabel plus5;
	private CatThread catTh;
	private DogThread dogTh;
	
	public int fia [] = {55, 215, 375};  // ����� x��ǥ ����
	public int fib [] = {80, 180, 280};  // ����� y��ǥ ����
	int x=0, y=0;  // ����� ��ǥ �� �������� ���õ� �� �־��� ����
	
	public FishThread(Container c, JLabel plus5, CatThread catTh, DogThread dogTh) {
		this.c = c;
		this.plus5 = plus5;
		this.catTh = catTh;
		this.dogTh = dogTh;
	}
	
	// ������ �ڵ�. run()�� �����ϸ� ������ ����
	@Override
	public void run() {
		// ����� ������ ����
		ImageIcon fiimg = new ImageIcon("images/fish.png");
		JLabel fish = new JLabel(fiimg);
			
		while(true) {
			fish.setSize(fiimg.getIconWidth(),fiimg.getIconWidth());   // ���̺� ũ�� = �̹��� ũ��
			
			int i = (int)(Math.random()*3);  // 0 ~ 2
			int j = (int)(Math.random()*3);  // 0 ~ 2
			x = fia[i];
			y = fib[j];

			fish.setLocation(x,y);
			
			// ����� ��ǥ�� ����� & ������ ��ǥ�� ��ġ�� �ʰ� �ϱ� ���� ���ǹ�
			if ((x == catTh.x && y == catTh.y) || (x == dogTh.x && y == dogTh.y)) {
				c.remove(fish);
			} else { c.add(fish); }
				
			try {
				Thread.sleep(1600);  // 1.6�� �ֱ�� ����� �����ϰ� ��Ÿ��
				if ((x == catTh.x && y == catTh.y) || (x == dogTh.x && y == dogTh.y)) {
					c.remove(fish);
				} else { c.repaint(); }
				
				if(TimerThread.n == -1) {
					c.remove(fish);
					c.remove(plus5);
					return;  // ������ ����
				}
			}
			catch(InterruptedException e) { return; }  // ���� �߻� �� ������ ����
		}
	}
}
