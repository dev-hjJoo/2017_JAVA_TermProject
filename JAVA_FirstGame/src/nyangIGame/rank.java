package nyangIGame;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.swing.*;

public class rank extends JFrame{
	int[] score_arr = new int[10];
	
	JScrollPane scrollPane;
	ImageIcon icon;
	
	public rank() {
		setTitle("��ŷ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		icon = new ImageIcon("images/rnak.png");
		
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
//		JPanel north_pn = new JPanel();
//		JPanel center_pn = new JPanel();
//		JPanel left_pn = new JPanel();
//		
//		JFrame rank_fr = new JFrame();!!!!
		//JPanel main_pn = new JPanel();
		JLabel rank_score = new JLabel("�ְ� ����");
		rank_score.setLocation(180,90);
		rank_score.setSize(100,100);
		rank_score.setFont(new Font("�ü�", Font.BOLD,20));
		background.add(rank_score);

		
		JLabel rank1 = new JLabel("1��  ===>");
		rank1.setLocation(100,170);
		rank1.setSize(100,150);
		rank1.setFont(new Font("����", Font.BOLD,17));
		background.add(rank1);
		
		JLabel rank2 = new JLabel("2��  ===>");
		rank2.setLocation(100,250);
		rank2.setSize(100,150);
		rank2.setFont(new Font("����", Font.BOLD,17));
		background.add(rank2);
		
		JLabel rank3 = new JLabel("3��  ===>");
		rank3.setLocation(100,320);
		rank3.setSize(100,150);
		rank3.setFont(new Font("����", Font.BOLD,17));
		background.add(rank3);
		
		
	
		//���� �о� �� �� ��ũ�������� ���� ���ڿ��� �и�, ���� ������ ��ȯ
		String temp_score = get_score();
		StringTokenizer st = new StringTokenizer(temp_score,",");
		int i = 0;
		int j = 0;
		
		while(st.hasMoreTokens()) {
			score_arr[i] = Integer.parseInt(st.nextToken());
			i++;
		}
		
		//���� �迭�� ���� �˰����� ���� ���� ���� �켱������ ����
		for(i = 0; i<score_arr.length;i++){
			for(j = 0; j<(score_arr.length -1); j++) {
				if(score_arr[j]< score_arr[j+1]) {
					int temp = score_arr[j];
					score_arr[j] = score_arr[j+1];
					score_arr[j+1] = temp;
				}
			}
		}
		
		
		JLabel score1 = new JLabel("");
		score1.setLocation(250,170);
		score1.setSize(100,150);
		score1.setFont(new Font("����", Font.BOLD,30));
		
		
		JLabel score2 = new JLabel("");
		score2.setLocation(250,245);
		score2.setSize(100,150);
		score2.setFont(new Font("����", Font.BOLD,30));
		
		JLabel score3 = new JLabel("");
		score3.setLocation(250,320);
		score3.setSize(100,150);
		score3.setFont(new Font("����", Font.BOLD,30));
		/*���� 3���� ������ ���*/
		score1.setText(Integer.toString(score_arr[0]));
		score2.setText(Integer.toString(score_arr[1]));
		score3.setText(Integer.toString(score_arr[2]));
	
		
		
	
		 
		background.add(score1);		
		background.add(score2);
		background.add(score3);

		

		
	}
	/*Resource ���Ͽ��� ������ �о����*/
	public static String get_score(){
		String best_score = "";
		File file_addr = new File("resource/score.txt");
		
		try {
			BufferedReader read_file = new BufferedReader(new InputStreamReader(new FileInputStream(file_addr.getAbsolutePath()),"UTF8"));
			String line = "";
			
			while((line = read_file.readLine()) != null) {
                if(line.trim().length() > 0) {
                    best_score += line + "," ;
                    }
                }
			read_file.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return best_score;
	}
	
	public static void main(String[] args) {
		rank frame = new rank();
		frame.setSize(460,550);
		frame.setVisible(true);
		
	}

}
