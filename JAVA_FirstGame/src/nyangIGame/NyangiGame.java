package nyangIGame;

/* �δ��� ��� ���� */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NyangiGame extends JFrame {
	int ha [] = {50, 210, 370};  // ���� x��ǥ ����
	int hb [] = {110, 210, 310};  // ���� y��ǥ ����
	public static JButton startbtn;
	
	public NyangiGame() {
		setTitle("�δ��� ���");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		Container c = getContentPane();
		c.setLayout(null);
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				// ���� ������ ����
				ImageIcon himg = new ImageIcon("images/hole.png");
				JLabel hole = new JLabel(himg);
				
				hole.setSize(himg.getIconWidth(),himg.getIconWidth());
				hole.setLocation(ha[i], hb[j]);
				c.add(hole);
			}
		}
		
		// Ÿ�̸� ���̺� ����
		JLabel timerlabel = new JLabel();  
		timerlabel.setText(" TIME : 30");
		timerlabel.setFont(new Font("Gothic", Font.ITALIC, 20));
		timerlabel.setSize(100,50);
		timerlabel.setLocation(50,20);
		
		
		JLabel cat = new JLabel();  // ����� ���̺� ����
		
		// ���� ���̺� ����
		JLabel scorelabel = new JLabel();  
		scorelabel.setText(" SCORE : 0" );  // ���̺� ���� �� ���
		scorelabel.setFont(new Font("Gothic", Font.ITALIC, 20));
		scorelabel.setSize(150,50);
		scorelabel.setLocation(320,20);
		c.add(scorelabel);
		
		// +1 �̹���
		ImageIcon poimg = new ImageIcon("images/plusone.png");
		JLabel plusone = new JLabel(poimg);
		plusone.setSize(poimg.getIconWidth(),poimg.getIconWidth());
		// -3 �̹���
		ImageIcon m3img = new ImageIcon("images/minus3.png");
		JLabel minus3 = new JLabel(m3img);
		minus3.setSize(m3img.getIconWidth(),m3img.getIconWidth());
		// +5 �̹���
		ImageIcon p5img = new ImageIcon("images/plus5.png");
		JLabel plus5 = new JLabel(p5img);
		plus5.setSize(p5img.getIconWidth(),p5img.getIconWidth());
		
		
		// ������ ����
		CatThread catTh = new CatThread(cat, c, plusone);  // �����
		DogThread dogTh = new DogThread(c, minus3, catTh);  // ������
		FishThread fishTh = new FishThread(c, plus5, catTh, dogTh);  // �����
		TimerThread timerTh = new TimerThread(timerlabel, catTh);  // Ÿ�̸�
		
		catTh.setTh(dogTh, fishTh);
		dogTh.setFishTh(fishTh);

		// ���� ��ư ����
		startbtn = new JButton("START");
		startbtn.setSize(450,50);
		startbtn.setLocation(20,400);
		startbtn.setBackground(Color.PINK);
		startbtn.setOpaque(true);
		startbtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {  // ��ư ������ ����
				startbtn.setText("FINISE");
				if (timerTh.isAlive()) {  // Ÿ�̸� �����尡 ����ǰ� ���� �� ��ư ������ ����
					TimerThread.n = -1;
					timerlabel.setText(" TIME : 30");
					startbtn.setText("START");
				} else
					timerTh.start();
			}
		});
				
		c.add(timerlabel);
		c.add(startbtn);
		
		// ��Ŀ�� ������ ����
		ImageIcon fimg = new ImageIcon("images/focus.png");
		JLabel focus = new JLabel(fimg);
		
		// Key ������ �ޱ�
		MyKeyListener key = new MyKeyListener(c, focus, scorelabel, plusone, minus3, plus5);
		key.setThread(catTh, dogTh, fishTh);
		startbtn.addKeyListener(key);
		
		focus.setLocation(210, 170);
		focus.setSize(fimg.getIconWidth(),fimg.getIconWidth());
		c.add(focus);
		c.setFocusable(true);
		c.requestFocus();  // ����Ʈ���� Ű �Է��� ���� �� �ֵ��� ��Ŀ�� ���� ����
				
		setSize(500,500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new NyangiGame();
	}
}