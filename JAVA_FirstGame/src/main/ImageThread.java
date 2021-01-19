package main;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageThread extends Thread {
	
	private JLabel randomImage = new JLabel();
	private String[] imageArr = {"flower.png", "mushroom.png","star.png","stars.png"};
	private Container container;
	
	public ImageThread(Container container) {
		this.container = container;
	}
	
	@Override
	public void run() {
		
		int wait = 1;
		int x = 365;
		int y = 380;
		
		while(true) {
			
			
			/*6sec up, 3sec wait, 6sec down*/
			if (wait < 6) {
				y -= 10;
				randomImage.setBounds(x, y, 100, 100);
			} else if (wait <= 9) {
				randomImage.setBounds(x, y, 100, 100);
			} else {
				y += 10;
				randomImage.setBounds(x, y, 100, 100);
			}
			
			
			container.add(randomImage);
			
			
			try {
				sleep(100);
				wait++;
				
				//1.5sec Initialize
				if(wait == 15) {
		
					//image delete
					randomImage.setIcon(null);
					//wait variable Initialize
					wait = 1;
					
					//image change
					
					//Receive random values for randomly selecting images
					int rand = (int)(Math.random()*4);
					if (rand >= 4) rand=3; 
					
					System.out.println("main.ImageThread.run :: rand : "+rand);
					
					//Choose an image by placing randomly received values in an array
					String addrImg = "images/bottom/"+imageArr[rand];
					ImageIcon image = new ImageIcon(addrImg);
					
					if (image.getClass() != null) {
						System.out.println("main.ImageThread.run :: addrImg : "+ addrImg);
					}
					randomImage.setIcon(image);
				}
			}
			catch (InterruptedException e) {
				return;
			}
		}
	}
}
