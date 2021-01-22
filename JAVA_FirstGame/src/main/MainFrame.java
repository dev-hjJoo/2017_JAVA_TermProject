package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.io.*;//sound file i/o
import javax.sound.sampled.*; // clip


import nyangIGame.NyangiGame;


public class MainFrame extends JFrame {
	JScrollPane scrollPane;
	ImageIcon icon;
	public MainFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		/*sound I/O*/
		File soundfile = new File("sound/mainBGM.wav"); //sound file addr
		AudioInputStream main_sound = AudioSystem.getAudioInputStream(soundfile); //sound file audio input stream add
		Clip main_sound_clip = AudioSystem.getClip(); //get clip
		main_sound_clip.open(main_sound); // main sound clip add
		
		main_sound_clip.start(); // game start sound play
		
	
		setTitle("두더냥잡기");
		icon = new ImageIcon("images/doodunyang.png");
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		background.setLayout(null);

		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
		
		
		
		/*The cat catch button*/
		ImageIcon dog = new ImageIcon("images/dog.png");
		JButton nyang = new JButton("게임시작",dog);
		//button create
		nyang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// nyangIGame package The cat Game class main start
				main_sound_clip.close();
				nyangIGame.NyangiGame.main(null);
			}
			
		});
		//button position and size settingggggggggggggggggggggggggggggg		
		nyang.setFont(new Font("바탕", Font.BOLD, 20));
		nyang.setLocation(120,370);
		nyang.setSize(270,60);
		//button color setting
		nyang.setBackground(Color.CYAN);
		
		
		/*game rules*/
		ImageIcon cat = new ImageIcon("images/cat.png");
		JButton rules = new JButton("게임 방법",cat);
		rules.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nyangIGame.GameRules.main(null);
			}
		});
		
		//button 
		rules.setFont(new Font("바탕", Font.BOLD, 20));
		rules.setLocation(120,450);
		rules.setSize(270,60);
		// 버튼 색깔 지정
		rules.setBackground(Color.CYAN);
		
		/*game ranking*/
		ImageIcon fish = new ImageIcon("images/fish.png");
		JButton rank = new JButton("랭킹",fish);
		rank.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nyangIGame.rank.main(null);
			}
		});
		
		//button 
		rank.setFont(new Font("바탕", Font.BOLD, 20));
		rank.setLocation(120,530);
		rank.setSize(270,60);
		// 버튼 색깔 지정
		rank.setBackground(Color.CYAN);
		
		background.add(nyang);
		background.add(rules);
		background.add(rank);
		
	}

}
