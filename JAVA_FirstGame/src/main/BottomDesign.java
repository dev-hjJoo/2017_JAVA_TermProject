package main;

import java.awt.Container;

import javax.swing.*;


public class BottomDesign {
	public BottomDesign(JFrame container) {
		
		/*
		 * 파이프 이미지 label 설정
		 */
		ImageIcon imagePipe = new ImageIcon("./images/bottom/pipe.png");
		JLabel pipe = new JLabel(imagePipe);
		
		pipe.setBounds(330, 380, 100, 100);
		
		/*
		 * 튀어나올 이미지 label 설정
		 */
		ImageThread thread = new ImageThread(container);
		thread.start();
		
		
		// container에 pipe를 부착		
		container.add(pipe);
	}
}
