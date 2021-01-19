package nyangIGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GameRules extends JFrame {
	//private MyPanel panel = new MyPanel();

	public GameRules() {
		setTitle("게임방법");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	Container c = getContentPane();
	c.setLayout(new FlowLayout());

	
//	ImageIcon back = new ImageIcon("images/바타에ㅜㅎ.png");
//	Image img = back.getImage();
//	JPanel background = new JPanel() {
//	public void paintComponent(Graphics g) {
//		g.drawImage(img, 0,0,null);
//		setOpaque()
//		super.paintComponents(g);
//	}
//	};
	
	c.setBackground(Color.cyan);
	//cat image layout
	ImageIcon cat1 = new ImageIcon("images/cat.png");
	JLabel cat3 = new JLabel("고양이를 잡을 시 +1점 !!!",cat1, SwingConstants.CENTER);
	cat3.setFont(new Font("Gothic", Font.ITALIC, 20));
	
	//dog image layout
	ImageIcon dog1 = new ImageIcon("images/dog.png");
	JLabel dog3 = new JLabel("강아지를 잡을 시 -3점 !!!",dog1, SwingConstants.CENTER);
	dog3.setFont(new Font("Gothic", Font.ITALIC, 20));
	
	//fish image layout
	ImageIcon fish1 = new ImageIcon("images/fish.png");
	JLabel fish3 = new JLabel("물고기를 잡을 시 +5점 !!!",fish1, SwingConstants.CENTER);
	fish3.setFont(new Font("Gothic", Font.ITALIC, 20));
	
	c.add(cat3);
	c.add(dog3);
	c.add(fish3);

	setSize(400,600);
	setVisible(true);

	}
	public static void main(String[] args) {
		new GameRules();

	}

}
