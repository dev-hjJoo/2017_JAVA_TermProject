package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import nyangIGame.NyangiGame;


public class MainFrame extends JFrame {
	
	public MainFrame() {
		
		setTitle("두더냥 잡기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container container = getContentPane();
		container.setLayout(null);
		
		container.setBackground(Color.BLACK);
		
		// 별모양 비가 내리는 StarRain 클래스 호출
		new StarRain(container);
		
		/*
		 *	랜덤 버튼
		 */
//		JButton rand = new JButton("랜덤버튼");
//		// 버튼 생성
//		rand.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// 버튼을 클릭했다가 놓으면 아래 코드가 실행
//				
//				/*
//				 * 랜덤으로 값을 받아 게임 선택
//				 */				
//				
//				if( (int)(Math.random()*2) == 0 ) {
//					// 랜덤 값이 0이면 bombGame package에 Main클래스의 main문 실행
//					bombGame.Main.main(null);
//				} else {
//					// 그 외, nyangIGame package에 NyangiGame클래스의 main문 실행
//					nyangIGame.NyangiGame.main(null);
//				}
//						
//				
//			}
//					
//		});
//		// 버튼 위치 및 크기 설정
//		rand.setBounds(50, 100, 330, 80);
//		// 버튼 색깔 지정
//		rand.setBackground(Color.BLUE);
//		// 버튼 글자 색깔 지정
//		rand.setForeground(Color.WHITE);
//		
//		
//		/*
//		 * 지뢰찾기 버튼
//		 */
//		JButton bomb = new JButton("지뢰찾기");
//		// 버튼 생성
//		bomb.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// 버튼을 클릭했다가 놓으면 아래 코드가 실행
//				
//				// bombGame package에 Main클래스의 main문 실행
//				bombGame.Main.main(null);
//			}
//			
//		});
//		// 버튼 위치 및 크기 설정
//		bomb.setBounds(rand.getX(), 200, 150, 80);
//		// 버튼 색깔 지정
//		bomb.setBackground(Color.RED);
//		// 버튼 글자 색깔 지정
//		bomb.setForeground(Color.WHITE);
//		
		
		/*
		 * 냥더지 잡기 버튼
		 */
		JButton nyang = new JButton("게임시작");
		// 버튼 생성
		nyang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// nyangIGame package에 NyangiGame클래스의 main문 실행
				nyangIGame.NyangiGame.main(null);
			}
			
		});
		// 버튼 위치 및 크기 설정		
		nyang.setBounds(50, 100, 330, 80);
		// 버튼 색깔 지정
		nyang.setBackground(Color.ORANGE);
		
		
		/* 게임방법
		 * 
		 */
		
		JButton rules = new JButton("게임 방법");
		rules.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nyangIGame.GameRules.main(null);
			}
		});
		// 버튼 위치 및 크기 설정		
		rules.setBounds(50, 200,330, 80);
		// 버튼 색깔 지정
		rules.setBackground(Color.ORANGE);
		
		
		
		/*
		 * 하단 디자인
		 */
		new BottomDesign(this);
		
		/*
		 * 컨테이너에 버튼 붙이기
		 */
		//container.add(rand);
		//container.add(bomb);
		container.add(nyang);
		container.add(rules);
		
		setSize(450,500);
		setVisible(true);
		
	}

}
