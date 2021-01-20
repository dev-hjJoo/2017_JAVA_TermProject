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
	
	public rank() {
		setTitle("랭킹");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel north_pn = new JPanel();
		JPanel center_pn = new JPanel();
		JPanel left_pn = new JPanel();
		
		JFrame rank_fr = new JFrame();
		JPanel main_pn = new JPanel();
		JLabel rank_score = new JLabel("최고 점수");
		rank_score.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel rank1 = new JLabel("1");
		rank1.setHorizontalAlignment(JLabel.CENTER);
		JLabel rank2 = new JLabel("2");
		rank2.setHorizontalAlignment(JLabel.CENTER);
		JLabel rank3 = new JLabel("3");
		rank3.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel score1 = new JLabel("");
		score1.setHorizontalAlignment(JLabel.CENTER);
		JLabel score2 = new JLabel("");
		score1.setHorizontalAlignment(JLabel.CENTER);
		JLabel score3 = new JLabel("");
		score1.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		main_pn.setLayout(new BorderLayout());
		north_pn.setLayout(new BorderLayout());
		center_pn.setLayout(new GridLayout(3,1));
		left_pn.setLayout(new GridLayout(3,1));
		
		
		rank_score.setFont(new Font("궁서", Font.BOLD,17));
		rank1.setFont(new Font("궁서", Font.BOLD,17));
		rank2.setFont(new Font("궁서", Font.BOLD,17));
		rank3.setFont(new Font("궁서", Font.BOLD,17));
		
		
		//파일 읽어 온 후 토크나이저를 통해 문자열을 분리, 정수 값으로 변환
		String temp_score = get_score();
		StringTokenizer st = new StringTokenizer(temp_score,",");
		int i = 0;
		int j = 0;
		
		while(st.hasMoreTokens()) {
			score_arr[i] = Integer.parseInt(st.nextToken());
			i++;
		}
		
		//점수 배열을 정렬 알고리즘을 통해 높은 값을 우선적으로 정렬
		for(i = 0; i<score_arr.length;i++){
			for(j = 0; j<(score_arr.length -1); j++) {
				if(score_arr[j]< score_arr[j+1]) {
					int temp = score_arr[j];
					score_arr[j] = score_arr[j+1];
					score_arr[j+1] = temp;
				}
			}
		}
		
		
		score1.setText(Integer.toString(score_arr[0]));
		score2.setText(Integer.toString(score_arr[1]));
		score3.setText(Integer.toString(score_arr[2]));
		
		score1.setFont(new Font("궁서", Font.BOLD,17));
		score2.setFont(new Font("궁서", Font.BOLD,17));
		score3.setFont(new Font("궁서", Font.BOLD,17));
		
		north_pn.add(rank_score);
		
		left_pn.add(rank1);
		left_pn.add(rank2);
		left_pn.add(rank3);
		
		center_pn.add(score1);
		center_pn.add(score2);
		center_pn.add(score3);
		
		main_pn.add(north_pn, BorderLayout.NORTH);
		main_pn.add(center_pn, BorderLayout.EAST);
		main_pn.add(left_pn, BorderLayout.WEST);
		
		rank_fr.add(main_pn);
		rank_fr.setSize(460, 700);
		rank_fr.setVisible(true);
		
	}
	
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
		// TODO Auto-generated method stub
		new rank();
	}

}
