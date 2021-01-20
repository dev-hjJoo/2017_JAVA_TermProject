package nyangIGame;
/* ����â */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

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
		
		// save button
		JButton save_btn = new JButton();
		save_btn.setText("SAVE");
		save_btn.setSize(100, 100);
		c.add(save_btn);
		
		
		// ��ư Ŭ���� ��� â ����
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
					System.out.println("��������Ϸ�");
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
