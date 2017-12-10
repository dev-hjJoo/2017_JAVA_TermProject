/* 종료창 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TimeOver extends JFrame {
	public TimeOver() {
		setTitle("종료");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// 점수 표시해 주는 레이블
		JLabel la = new JLabel();
		la.setText("      Your score is  " + Integer.toString(MyKeyListener.score) +"     ");
		la.setFont(new Font("Gothic", Font.BOLD, 15));
		la.setSize(100,100);
		c.add(la);
		
		// 종료 버튼
		JButton btn = new JButton();
		btn.setText("THE END");
		btn.setToolTipText("Bye Bye~~");
		btn.setSize(100,100);
		c.add(btn);
		
		// 버튼 클릭시 모든 창 종료
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
