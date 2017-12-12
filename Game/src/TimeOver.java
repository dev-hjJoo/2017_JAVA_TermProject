/* ����â */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TimeOver extends JFrame {
	public TimeOver() {
		setTitle("����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// ���� ǥ���� �ִ� ���̺�
		JLabel la = new JLabel();
		la.setText("      Your score is  " + Integer.toString(MyKeyListener.score) +"     ");
		la.setFont(new Font("Gothic", Font.BOLD, 15));
		la.setSize(100,100);
		c.add(la);
		
		// ���� ��ư
		JButton btn = new JButton();
		btn.setText("THE END");
		btn.setToolTipText("Bye Bye~~");
		btn.setSize(100,100);
		c.add(btn);
		
		// ��ư Ŭ���� ��� â ����
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				}	
		});
		
		setVisible(true);
		setSize(200,100);
		setLocation(250,250);
	}
}
