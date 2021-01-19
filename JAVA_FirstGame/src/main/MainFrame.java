package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.io.*;//sound file i/o
import javax.sound.sampled.*; // clip


import nyangIGame.NyangiGame;


public class MainFrame extends JFrame {
	public MainFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		/*sound I/O*/
		File soundfile = new File("sound/mainBGM.wav"); //sound file addr
		AudioInputStream main_sound = AudioSystem.getAudioInputStream(soundfile); //sound file audio input stream add
		Clip main_sound_clip = AudioSystem.getClip(); //get clip
		main_sound_clip.open(main_sound); // main sound clip add
		
		main_sound_clip.start(); // game start sound play
		
		
		/*Frame setting*/
		setTitle("두더냥 잡기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container container = getContentPane();
		container.setLayout(null);
		
		container.setBackground(Color.BLACK);
		
		// call StarRain class
		new StarRain(container);
		
		/*냥더지 잡기 버튼*/
		JButton nyang = new JButton("게임시작");
		// 버튼 생성
		nyang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// nyangIGame package에 NyangiGame클래스의 main문 실행
				main_sound_clip.close();
				nyangIGame.NyangiGame.main(null);
			}
			
		});
		// 버튼 위치 및 크기 설정		
		nyang.setBounds(50, 100, 330, 80);
		// 버튼 색깔 지정
		nyang.setBackground(Color.ORANGE);
		
		
		/*게임방법*/
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
		
		
		
		/*하단 디자인*/
		new BottomDesign(this);
		
		container.add(nyang);
		container.add(rules);
		
		setSize(450,500);
		setVisible(true);
	}

}
