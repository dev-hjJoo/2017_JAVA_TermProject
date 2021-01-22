package main;

import java.awt.Container;

import javax.swing.*;


public class BottomDesign {
	public BottomDesign(JFrame container) {
		
		/*Pipe image label setting*/
		ImageIcon imagePipe = new ImageIcon("./images/bottom/pipe.png");
		JLabel pipe = new JLabel(imagePipe);
		
		pipe.setBounds(330, 380, 100, 100);
		
		/*Jump out image label setting*/
		ImageThread thread = new ImageThread(container);
		thread.start();
		
		// container pipe add		
		container.add(pipe);
	}
}
