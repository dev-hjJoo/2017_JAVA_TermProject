package nyangIGame;

/* 두더냥 잡기 게임 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NyangiGame extends JFrame {
	int ha [] = {50, 210, 370};  // 구멍 x좌표 값들
	int hb [] = {110, 210, 310};  // 구멍 y좌표 값들
	public static JButton startbtn;
	
	public NyangiGame() {
		setTitle("두더냥 잡기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		Container c = getContentPane();
		c.setLayout(null);
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				// 구멍 아이콘 생성
				ImageIcon himg = new ImageIcon("images/hole.png");
				JLabel hole = new JLabel(himg);
				
				hole.setSize(himg.getIconWidth(),himg.getIconWidth());
				hole.setLocation(ha[i], hb[j]);
				c.add(hole);
			}
		}
		
		// 타이머 레이블 생성
		JLabel timerlabel = new JLabel();  
		timerlabel.setText(" TIME : 30");
		timerlabel.setFont(new Font("Gothic", Font.ITALIC, 20));
		timerlabel.setSize(100,50);
		timerlabel.setLocation(50,20);
		
		
		JLabel cat = new JLabel();  // 고양이 레이블 생성
		
		// 점수 레이블 생성
		JLabel scorelabel = new JLabel();  
		scorelabel.setText(" SCORE : 0" );  // 레이블에 점수 값 출력
		scorelabel.setFont(new Font("Gothic", Font.ITALIC, 20));
		scorelabel.setSize(150,50);
		scorelabel.setLocation(320,20);
		c.add(scorelabel);
		
		// +1 이미지
		ImageIcon poimg = new ImageIcon("images/plusone.png");
		JLabel plusone = new JLabel(poimg);
		plusone.setSize(poimg.getIconWidth(),poimg.getIconWidth());
		// -3 이미지
		ImageIcon m3img = new ImageIcon("images/minus3.png");
		JLabel minus3 = new JLabel(m3img);
		minus3.setSize(m3img.getIconWidth(),m3img.getIconWidth());
		// +5 이미지
		ImageIcon p5img = new ImageIcon("images/plus5.png");
		JLabel plus5 = new JLabel(p5img);
		plus5.setSize(p5img.getIconWidth(),p5img.getIconWidth());
		
		
		// 스레드 생성
		CatThread catTh = new CatThread(cat, c, plusone);  // 고양이
		DogThread dogTh = new DogThread(c, minus3, catTh);  // 강이지
		FishThread fishTh = new FishThread(c, plus5, catTh, dogTh);  // 물고기
		TimerThread timerTh = new TimerThread(timerlabel, catTh);  // 타이머
		
		catTh.setTh(dogTh, fishTh);
		dogTh.setFishTh(fishTh);

		// 시작 버튼 생성
		startbtn = new JButton("START");
		startbtn.setSize(450,50);
		startbtn.setLocation(20,400);
		startbtn.setBackground(Color.PINK);
		startbtn.setOpaque(true);
		startbtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {  // 버튼 누르면 실행
				startbtn.setText("FINISE");
				if (timerTh.isAlive()) {  // 타이머 스레드가 실행되고 있을 때 버튼 누르면 종료
					TimerThread.n = -1;
					timerlabel.setText(" TIME : 30");
					startbtn.setText("START");
				} else
					timerTh.start();
			}
		});
				
		c.add(timerlabel);
		c.add(startbtn);
		
		// 포커스 아이콘 생성
		ImageIcon fimg = new ImageIcon("images/focus.png");
		JLabel focus = new JLabel(fimg);
		
		// Key 리스너 달기
		MyKeyListener key = new MyKeyListener(c, focus, scorelabel, plusone, minus3, plus5);
		key.setThread(catTh, dogTh, fishTh);
		startbtn.addKeyListener(key);
		
		focus.setLocation(210, 170);
		focus.setSize(fimg.getIconWidth(),fimg.getIconWidth());
		c.add(focus);
		c.setFocusable(true);
		c.requestFocus();  // 컨텐트팬이 키 입력을 받을 수 있도록 포커스 강제 지정
				
		setSize(500,500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new NyangiGame();
	}
}