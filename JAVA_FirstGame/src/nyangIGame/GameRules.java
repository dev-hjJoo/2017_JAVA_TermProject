package nyangIGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GameRules extends JFrame {
	JScrollPane scrollPane;
	ImageIcon icon;
	
	public GameRules() {
		setTitle("게임방법");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		icon = new ImageIcon("");
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
		
		
	
		
		//game information layout
		JLabel text1 = new JLabel("게임 목표!!");
		text1.setLocation(150,-30);
		text1.setSize(250,100);
		text1.setFont(new Font("궁서", Font.BOLD, 30));
		background.add(text1);
		
		JLabel text2 = new JLabel("시간 내에 두더냥을 잡아 점수를 얻어");
		text2.setLocation(90,30);
		text2.setSize(300,100);
		text2.setFont(new Font("궁서", Font.ITALIC, 17));
		background.add(text2);
		JLabel text3 = new JLabel("친구들과 경쟁하여 승리해보자!!");
		text3.setLocation(105,60);
		text3.setSize(250,100);
		text3.setFont(new Font("궁서", Font.ITALIC, 17));
		background.add(text3);
		
		
		//game manipulate explain
		JLabel mani = new JLabel("게임 설명");
		mani.setLocation(150,120);
		mani.setSize(250,100);
		mani.setFont(new Font("궁서", Font.BOLD, 30));
		background.add(mani);
		
		
		JLabel mani1 = new JLabel("키보드 방향키를 이용해 조준할 수 있습니다.");
		mani1.setLocation(20,150);
		mani1.setSize(430,200);
		mani1.setFont(new Font("궁서", Font.BOLD, 20));
		background.add(mani1);
		
		JLabel mani2 = new JLabel("스페이스바를 눌러 잡을 수 있습니다.");
		mani2.setLocation(50,200);
		mani2.setSize(370,200);
		mani2.setFont(new Font("궁서", Font.BOLD, 20));
		background.add(mani2);
		

		
		
	//cat image layout
	ImageIcon cat1 = new ImageIcon("images/cat.png");
	JLabel cat3 = new JLabel(cat1);
	cat3.setLocation(70,400);
	cat3.setSize(60,60);
	background.add(cat3);
	
	//cat score layout
	JLabel textcat = new JLabel("고양이를 잡으면 1점 획득");
	textcat.setLocation(150,380);
	textcat.setSize(250,100);
	textcat.setFont(new Font("궁서", Font.BOLD, 17));
	background.add(textcat);
	

	//dog image layout
	ImageIcon dog1 = new ImageIcon("images/dog.png");
	JLabel dog3 = new JLabel(dog1);
	dog3.setLocation(70,470);
	dog3.setSize(60,60);
	background.add(dog3);
	
	//DOG score layout
	JLabel textdog = new JLabel("강아지를 잡으면 -3점 손실");
	textdog.setLocation(150,450);
	textdog.setSize(250,100);
	textdog.setFont(new Font("궁서", Font.BOLD, 17));
	background.add(textdog);
	
	//fish image layout
	ImageIcon fish1 = new ImageIcon("images/fish.png");
	JLabel fish3 = new JLabel(fish1);
	fish3.setLocation(70,540);
	fish3.setSize(60,60);
	background.add(fish3);
	
	//fish text layout
	JLabel textfish = new JLabel("물고기를 잡으면 +5점 획득 ");
	textfish.setLocation(150,520);
	textfish.setSize
	(250,100);
	textfish.setFont(new Font("궁서", Font.BOLD, 17));
	background.add(textfish);
	
	
//	//keyboard image layout
//	
//	ImageIcon keyboard1 = new ImageIcon("images/klipartz.png");
//	JLabel keyboard3 = new JLabel(keyboard1);
//	
//	keyboard3.setLocation(20,250);
//	keyboard3.setSize(100,100);
//	background.add(keyboard3);
	}
	public static void main(String[] args) {
		GameRules frame = new GameRules();
		
		frame.setSize(460,700);
		frame.setVisible(true);
	}

}
