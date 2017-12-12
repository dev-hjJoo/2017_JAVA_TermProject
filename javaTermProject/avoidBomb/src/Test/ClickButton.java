package Test;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ClickButton extends JFrame {
	
	public ClickButton() {
		setTitle("클릭하면 버튼이 없어지게 하기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		JButton bt = new JButton();
		bt.setSize(100, 100);
		c.add(bt);
		
		bt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				bt.setVisible(false);
			}
		});
		
		
		setSize(200,200);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ClickButton();
	}

}
