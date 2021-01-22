package nyangIGame;

/* The  cat catch game */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NyangiGame extends JFrame {
	int ha [] = {50, 210, 370};  // hole x position value
	int hb [] = {110, 210, 310};  // hole y position value
	public static JButton startbtn;
	
	public NyangiGame() {
		setTitle("두더냥 잡기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		Container c = getContentPane();
		c.setLayout(null);
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				// hole icon create
				ImageIcon himg = new ImageIcon("images/hole.png");
				JLabel hole = new JLabel(himg);
				
				hole.setSize(himg.getIconWidth(),himg.getIconWidth());
				hole.setLocation(ha[i], hb[j]);
				c.add(hole);
			}
		}
		
		// Timer label create
		JLabel timerlabel = new JLabel();  
		timerlabel.setText(" TIME : 30");
		timerlabel.setFont(new Font("Gothic", Font.ITALIC, 20));
		timerlabel.setSize(100,50);
		timerlabel.setLocation(50,20);
		
		
		JLabel cat = new JLabel();  // cat label create
		
		// score label create
		JLabel scorelabel = new JLabel();  
		scorelabel.setText(" SCORE : 0" );  //label score value print
		scorelabel.setFont(new Font("Gothic", Font.ITALIC, 20));
		scorelabel.setSize(150,50);
		scorelabel.setLocation(320,20);
		c.add(scorelabel);
		
		// +1 image
		ImageIcon poimg = new ImageIcon("images/plusone.png");
		JLabel plusone = new JLabel(poimg);
		plusone.setSize(poimg.getIconWidth(),poimg.getIconWidth());
		
		// -3 image
		ImageIcon m3img = new ImageIcon("images/minus3.png");
		JLabel minus3 = new JLabel(m3img);
		minus3.setSize(m3img.getIconWidth(),m3img.getIconWidth());
		
		// +5 image
		ImageIcon p5img = new ImageIcon("images/plus5.png");
		JLabel plus5 = new JLabel(p5img);
		plus5.setSize(p5img.getIconWidth(),p5img.getIconWidth());
		
		// Thread create
		CatThread catTh = new CatThread(cat, c, plusone);  //cat
		DogThread dogTh = new DogThread(c, minus3, catTh);  //dog
		FishThread fishTh = new FishThread(c, plus5, catTh, dogTh);  //fish
		TimerThread timerTh = new TimerThread(timerlabel, catTh);  //timer
		
		catTh.setTh(dogTh, fishTh);
		dogTh.setFishTh(fishTh);

		// start button create
		startbtn = new JButton("START");
		startbtn.setSize(450,50);
		startbtn.setLocation(20,400);
		startbtn.setBackground(Color.PINK);
		startbtn.setOpaque(true);
		startbtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {  //button pressed start
				startbtn.setText("FINISE");
				if (timerTh.isAlive()) {  //timer thread starting  button pressed end
					TimerThread.n = -1;
					timerlabel.setText(" TIME : 30");
					startbtn.setText("START");
				} else
					timerTh.start();
			}
		});
				
		c.add(timerlabel);
		c.add(startbtn);
		
		//focus icon create
		ImageIcon fimg = new ImageIcon("images/focus.png");
		JLabel focus = new JLabel(fimg);
		
		//Key listener add
		MyKeyListener key = new MyKeyListener(c, focus, scorelabel, plusone, minus3, plus5);
		key.setThread(catTh, dogTh, fishTh);
		startbtn.addKeyListener(key);
		
		focus.setLocation(210, 170);
		focus.setSize(fimg.getIconWidth(),fimg.getIconWidth());
		c.add(focus);
		c.setFocusable(true);
		c.requestFocus();  //content pane focus to receive input
		
		setSize(500,500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new NyangiGame();
	}
}