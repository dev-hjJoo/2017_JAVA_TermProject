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
			
			
			/*
			 * 6초 올라가고, 3초 대기, 6초 내려가기
			 */
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
				
				// 1.5초 마다 초기화
				if(wait == 15) {
		
					// 이미지 제거
					randomImage.setIcon(null);
					// wait변수 초기화
					wait = 1;
					
					// 이미지 변경
					
					// 이미지를 랜덤으로 선택하기 위한 랜덤 값 받기
					int rand = (int)(Math.random()*4);
					if (rand >= 4) rand=3; 
					
					System.out.println("main.ImageThread.run :: rand : "+rand);
					
					// 랜덤으로 받은 값을 배열에 넣어 이미지 선택
					String addrImg = "images/bottom/"+imageArr[rand];
					ImageIcon image = new ImageIcon(addrImg);
					
					if (image.getClass() != null) {
						System.out.println("main.ImageThread.run :: addrImg : "+ addrImg);
					}
					
					randomImage.setIcon(image);
				}
				
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
