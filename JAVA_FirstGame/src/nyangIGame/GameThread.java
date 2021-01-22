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

/* 강아지 나오게 하는 스레드 */
class DogThread extends Thread {
	private Container c;
	private JLabel minus3;
	private CatThread catTh;
	private FishThread fishTh;
	
	public int da [] = {55, 215, 375};  // 강아지 x좌표 값들
	public int db [] = {80, 180, 280};  // 강아지 y좌표 값들
	int x=0, y=0;  // 강아지 좌표 중 랜덤으로 선택된 값 넣어줄 변수
	
	public DogThread(Container c, JLabel minus3, CatThread catTh) {
		this.c = c;
		this.minus3 = minus3;
		this.catTh = catTh;
	}
	public void setFishTh(FishThread fishTh) { 
		this.fishTh = fishTh;
	}
	
	// 스레드 코드. run()이 종료하면 스레드 종료
	@Override
	public void run() {
		// 강아지 아이콘 생성
		ImageIcon dimg = new ImageIcon("images/dog.png");
		JLabel dog = new JLabel(dimg);
			
		while(true) {
			dog.setSize(dimg.getIconWidth(),dimg.getIconWidth());   // 레이블 크기 = 이미지 크기
			
			int i = (int)(Math.random()*3);  // 0 ~ 2
			int j = (int)(Math.random()*3);  // 0 ~ 2
			x = da[i];
			y = db[j];

			dog.setLocation(x,y);
			
			// 강아지 좌표가 고양이 & 물고기 좌표랑 겹치지 않게 하기 위한 조건문
			if ((x == catTh.x && y == catTh.y) || (x == fishTh.x && y == fishTh.y)) {
				c.remove(dog);
			} else { c.add(dog); }
				
			try {
				Thread.sleep(1200);  // 1.2초 주기로 강아지 랜덤하게 나타남
				
				if ((catTh.x == x && catTh.y == y) || (fishTh.x == x && fishTh.y == y)) {
					c.remove(dog);
				} else { c.repaint(); }
				
				if(TimerThread.n == -1) {
					c.remove(dog);
					c.remove(minus3);
					return;  // 스레드 종료
				}
			}
			catch(InterruptedException e) { return; }  // 예외 발생 시 스레드 종료
			}
	}
}

/* 물고기 나오게 하는 스레드 */
class FishThread extends Thread {
	private Container c;
	private JLabel plus5;
	private CatThread catTh;
	private DogThread dogTh;
	
	public int fia [] = {55, 215, 375};  // 물고기 x좌표 값들
	public int fib [] = {80, 180, 280};  // 물고기 y좌표 값들
	int x=0, y=0;  // 물고기 좌표 중 랜덤으로 선택된 값 넣어줄 변수
	
	public FishThread(Container c, JLabel plus5, CatThread catTh, DogThread dogTh) {
		this.c = c;
		this.plus5 = plus5;
		this.catTh = catTh;
		this.dogTh = dogTh;
	}
	
	// 스레드 코드. run()이 종료하면 스레드 종료
	@Override
	public void run() {
		// 물고기 아이콘 생성
		ImageIcon fiimg = new ImageIcon("images/fish.png");
		JLabel fish = new JLabel(fiimg);
			
		while(true) {
			fish.setSize(fiimg.getIconWidth(),fiimg.getIconWidth());   // 레이블 크기 = 이미지 크기
			
			int i = (int)(Math.random()*3);  // 0 ~ 2
			int j = (int)(Math.random()*3);  // 0 ~ 2
			x = fia[i];
			y = fib[j];

			fish.setLocation(x,y);
			
			// 물고기 좌표가 고양이 & 강아지 좌표랑 겹치지 않게 하기 위한 조건문
			if ((x == catTh.x && y == catTh.y) || (x == dogTh.x && y == dogTh.y)) {
				c.remove(fish);
			} else { c.add(fish); }
				
			try {
				Thread.sleep(1600);  // 1.6초 주기로 물고기 랜덤하게 나타남
				if ((x == catTh.x && y == catTh.y) || (x == dogTh.x && y == dogTh.y)) {
					c.remove(fish);
				} else { c.repaint(); }
				
				if(TimerThread.n == -1) {
					c.remove(fish);
					c.remove(plus5);
					return;  // 스레드 종료
				}
			}
			catch(InterruptedException e) { return; }  // 예외 발생 시 스레드 종료
		}
	}
}
