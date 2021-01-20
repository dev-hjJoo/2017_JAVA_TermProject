package nyangIGame;

/* Key Listener */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;//sound file i/o
import javax.sound.sampled.*; // clip

public class MyKeyListener implements KeyListener{
	Clip clip;
	
	private Container c;
	private JLabel focus;
	private CatThread catTh;
	private DogThread dogTh;
	private FishThread fishTh;
	private JLabel scorelabel;
	private JLabel plusone;
	private JLabel minus3;
	private JLabel plus5;
	
	
	public static int score = 0;  // score calculate
	int a = 0;  // 동물 잡은 후 점수 이미지 표시할 때 쓰임

	public MyKeyListener(Container c, JLabel focus, JLabel scorelabel, JLabel plusone, JLabel minus3, JLabel plus5){
		this.c = c;
		this.focus = focus;
		this.scorelabel = scorelabel;
		this.plusone = plusone;
		this.minus3 = minus3;
		this.plus5 = plus5;
	}
	
	public void cat_play_sound(String cat_pathName, boolean set) {
		if(set == true) {
			try {
				clip = AudioSystem.getClip();
				File audio_file = new File(cat_pathName);
				
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(audio_file);
				clip.open(audioStream);
				clip.start();
			}catch(LineUnavailableException e) {
				e.printStackTrace();
			}catch(UnsupportedAudioFileException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			clip.close();
		}
	}
	
	public void dog_play_sound(String dog_pathName, boolean set) {
		if(set == true) {
			try {
				clip = AudioSystem.getClip();
				File audio_file = new File(dog_pathName);
				
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(audio_file);
				clip.open(audioStream);
				clip.start();
			}catch(LineUnavailableException e) {
				e.printStackTrace();
			}catch(UnsupportedAudioFileException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			clip.close();
		}
	}
	
	public void fish_play_sound(String fish_pathName, boolean set) {
		if(set == true){
			try {
				clip = AudioSystem.getClip();
				File audio_file = new File(fish_pathName);
				
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(audio_file);
				clip.open(audioStream);
				clip.start();
			}catch(LineUnavailableException e) {
				e.printStackTrace();
			}catch(UnsupportedAudioFileException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			clip.close();
		}
	}
	
	public void setThread(CatThread catTh, DogThread dogTh, FishThread fishTh) {
		this.catTh = catTh;
		this.dogTh = dogTh;
		this.fishTh = fishTh;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {		
		int keyCode = e.getKeyCode();  // input key code variable
		
		ScoreThread scoreTh = new ScoreThread();  // score image print thread create
		scoreTh.start();
		
		// key code (up, down, left, right) & cursor position
		switch(keyCode) { 
			case KeyEvent.VK_UP:
				if(focus.getY() <= 70) {  // focus 1row not up condition
					focus.setLocation(focus.getX(), 70);
					break;
				} 
				focus.setLocation(focus.getX(), focus.getY()-100);
				focus.repaint(); 
				break;
			case KeyEvent.VK_DOWN:
				if(focus.getY() >= 270) {  // focus 3row not down condition
					focus.setLocation(focus.getX(), 270);
					break;
				}
				focus.setLocation(focus.getX(), focus.getY()+100);
				focus.repaint();
				break;
			case KeyEvent.VK_LEFT:
				if(focus.getX() <= 50) {  // focus 3column not left condition
					focus.setLocation(50, focus.getY());
					break;
				}
				focus.setLocation(focus.getX()-160, focus.getY());
				focus.repaint();
				break;
			case KeyEvent.VK_RIGHT:
				if(focus.getX() >= 370) {  // focus 3column not right condition
					focus.setLocation(370, focus.getY());
					break;
				}
				focus.setLocation(focus.getX()+160, focus.getY());
				focus.repaint();
				break;
			
			// space key catch condition
			case KeyEvent.VK_SPACE:
				// cat catch condition
				if(catchcat() == true) {
					cat_play_sound("sound/cat.wav",true);
					score++;
					getScore();
					a++;
					break;
				} else 
					c.remove(plusone);
				
				// dog catch condition
				if(catchdog() == true) {
					dog_play_sound("sound/dog.wav",true);
					score -= 3;
					getScore();
					a += 2;
					break;
				} else 
					c.remove(minus3);
				
				// fish catch condition
				if(catchfish() == true) {
					fish_play_sound("sound/fish.wav",true);
					score += 5;
					getScore();
					a += 3;
					break;
				} else
					c.remove(plus5);
		}
	}
	
	/* cat position focus & catch position focus condition */
	public boolean catchcat() {
		if(catTh.x == focus.getX()+5 && catTh.y == focus.getY()+10) {
			return true;
		}
		else
			return false;
	}
	
	/* dog position focus & catch position focus condition */
	public boolean catchdog() {
		if(dogTh.x == focus.getX()+5 && dogTh.y == focus.getY()+10) {
			return true;
		}
		else
			return false;
	}
	
	/* fish position focus & catch position focus condition */
	public boolean catchfish() {
		if(fishTh.x == focus.getX()+5 && fishTh.y == focus.getY()+10) {
			return true;
		}
		else
			return false;
	}
	
	int b = 0;  // dogTh and fishTh section start variable
	
	/* score calculate method */
	void getScore() {		
		scorelabel.setText(" SCORE : " + Integer.toString(score));  // label score value print
		if(score == 10 && b == 0) {  // 10score dog appearance
			dogTh.start();
			b++;
		}
		else if(score == 15 && b == 1) {  // 15score fish appearance
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}