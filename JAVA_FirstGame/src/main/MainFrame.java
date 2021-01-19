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
		setTitle("�δ��� ���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container container = getContentPane();
		container.setLayout(null);
		
		container.setBackground(Color.BLACK);
		
		// call StarRain class
		new StarRain(container);
		
		/*�ɴ��� ��� ��ư*/
		JButton nyang = new JButton("���ӽ���");
		// ��ư ����
		nyang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// nyangIGame package�� NyangiGameŬ������ main�� ����
				main_sound_clip.close();
				nyangIGame.NyangiGame.main(null);
			}
			
		});
		// ��ư ��ġ �� ũ�� ����		
		nyang.setBounds(50, 100, 330, 80);
		// ��ư ���� ����
		nyang.setBackground(Color.ORANGE);
		
		
		/*���ӹ��*/
		JButton rules = new JButton("���� ���");
		rules.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nyangIGame.GameRules.main(null);
			}
		});
		
		// ��ư ��ġ �� ũ�� ����		
		rules.setBounds(50, 200,330, 80);
		// ��ư ���� ����
		rules.setBackground(Color.ORANGE);
		
		
		
		/*�ϴ� ������*/
		new BottomDesign(this);
		
		container.add(nyang);
		container.add(rules);
		
		setSize(450,500);
		setVisible(true);
	}

}
