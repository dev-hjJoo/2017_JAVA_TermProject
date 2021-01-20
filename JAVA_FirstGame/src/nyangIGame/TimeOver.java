package nyangIGame;
/* 종료창 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

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
		
		// save button
		JButton save_btn = new JButton();
		save_btn.setText("SAVE");
		save_btn.setSize(100, 100);
		c.add(save_btn);
		
		
		// 버튼 클릭시 모든 창 종료
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}	
		});
		
		save_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File file = new File("resource/score.txt");
				FileWriter fw = null;
				
				try {
					fw = new FileWriter(file, true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(Integer.toString(MyKeyListener.score));
					bw.write("\n");
					System.out.println("파일저장완료");
					bw.close();
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		setVisible(true);
		setSize(200,100);
		setLocation(250,250);
	}
}
