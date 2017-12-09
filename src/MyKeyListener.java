/* Key 리스너 */
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
	
	public static int score = 0;  // 점수 계산
	int a = 0;  // 동물 잡은 후 점수 이미지 표시할 때 쓰임
	
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
		int keyCode = e.getKeyCode();  // 입력된 키의 키코드 알아내는 변수
		
		ScoreThread scoreTh = new ScoreThread();  // 점수 이미지 나타나게 하는 스레드 생성
		scoreTh.start();
		
		// 키 코드 값에 따라 상,하,좌,우 키 판별 & 커서 위치 이동
		switch(keyCode) { 
			case KeyEvent.VK_UP:
				if(focus.getY() <= 70) {  // 포커스가 1행의 구멍 위로 가지 않도록 하는 조건문
					focus.setLocation(focus.getX(), 70);
					break;
				} 
				focus.setLocation(focus.getX(), focus.getY()-100);
				focus.repaint(); 
				break;
			case KeyEvent.VK_DOWN:
				if(focus.getY() >= 270) {  // 포커스가 3행의 구멍 밑으로 가지 않도록 하는 조건문
					focus.setLocation(focus.getX(), 270);
					break;
				}
				focus.setLocation(focus.getX(), focus.getY()+100);
				focus.repaint();
				break;
			case KeyEvent.VK_LEFT:
				if(focus.getX() <= 50) {  // 포커스가 1열의 구멍 왼쪽으로 가지 않도록 하는 조건문
					focus.setLocation(50, focus.getY());
					break;
				}
				focus.setLocation(focus.getX()-160, focus.getY());
				focus.repaint();
				break;
			case KeyEvent.VK_RIGHT:
				if(focus.getX() >= 370) {  // 포커스가 3열의 구멍 오른쪽으로 가지 않도록 하는 조건문
					focus.setLocation(370, focus.getY());
					break;
				}
				focus.setLocation(focus.getX()+160, focus.getY());
				focus.repaint();
				break;
			
			// ENTER 키로 고양이 잡기
			case KeyEvent.VK_ENTER:
				// 고양이 잡았을 때 조건문
				if(catchcat() == true) {  
					score++;
					getScore();
					a++;
					break;
				} else 
					c.remove(plusone);
				
				// 강아지 잡았을 때
				if(catchdog() == true) {
					score -= 3;
					getScore();
					a += 2;
					break;
				} else 
					c.remove(minus3);
				
				// 물고기 잡았을 때
				if(catchfish() == true) {
					score += 5;
					getScore();
					a += 3;
					break;
				} else
					c.remove(plus5);
		}
	}
	
	/* 고양이 좌표와 포커스 좌표 비교하는 메소드 */
	public boolean catchcat() {
		if(catTh.x == focus.getX()+5 && catTh.y == focus.getY()+10) {
			return true;
		}
		else
			return false;
	}
	
	/* 강아지 좌표와 포커스 좌표 비교하는 메소드 */
	public boolean catchdog() {
		if(dogTh.x == focus.getX()+5 && dogTh.y == focus.getY()+10) {
			return true;
		}
		else
			return false;
	}
	
	/* 물고기 좌표와 포커스 좌표 비교하는 메소드 */
	public boolean catchfish() {
		if(fishTh.x == focus.getX()+5 && fishTh.y == focus.getY()+10) {
			return true;
		}
		else
			return false;
	}
	
	int b = 0;  // dogTh랑 fishTh 중복 시작 방지
	
	/* 점수 계산하는 메소드 */
	void getScore() {
		scorelabel.setText(" SCORE : " + Integer.toString(score));  // 레이블에 점수 값 출력
		if(score == 10 && b == 0) {  // 10점 되면 강아지 등장
			dogTh.start();
			b++;
		}
		else if(score == 15 && b == 1) {  // 15점 되면 물고기 등장
			fishTh.start();	 
			b++;
		}
	}

	/* 점수 이미지 추가했다가 사라지는 스레드 */
	class ScoreThread extends Thread {
		int d = 0;
		public void run() {
			while(true) {
				if(a == 1) {  // 고양이 잡았을 때 +1
					plusone.setLocation(catTh.x+10, catTh.y);
					c.add(plusone);
					a = 0;
					d++;
				} 
				else if(a == 2) {  // 강아지 잡았을 때 -3
					minus3.setLocation(dogTh.x+10, dogTh.y);
					c.add(minus3);
					a = 0;
					d += 2;
				} 
				else if(a == 3) {  // 물고기 잡았을 때 +5
					plus5.setLocation(fishTh.x+10, fishTh.y);
					c.add(plus5);
					a = 0;
					d += 3;
				}
				
				try {
					Thread.sleep(800); // 0.8초 후 동물 잡았을 때 추가한 점수 이미지 삭제
					if (d == 1) {  // +1 이미지 삭제
						c.remove(plusone);
						d = 0;
					} else if (d == 2) {  // -3 이미지 삭제
						c.remove(minus3);
						d = 0;
					} else if (d == 3) {  // +5 이미지 삭제
						c.remove(plus5);
						d = 0;
					}
					c.repaint();
					
					if(TimerThread.n == -1) {  // 타이머 스레드 종료하면 모든 점수 이미지 삭제
						c.remove(plusone);
						c.remove(minus3);
						c.remove(plus5);
						return;  // 스레드 종료
					}
				}
				catch(InterruptedException e) { return; }  // 예외 발생 시 스레드 종료	
			}
		}
	}
}