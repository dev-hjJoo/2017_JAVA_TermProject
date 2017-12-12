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
			 * 6�� �ö󰡰�, 3�� ���, 6�� ��������
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
				
				// 1.5�� ���� �ʱ�ȭ
				if(wait == 15) {
		
					// �̹��� ����
					randomImage.setIcon(null);
					// wait���� �ʱ�ȭ
					wait = 1;
					
					// �̹��� ����
					
					// �̹����� �������� �����ϱ� ���� ���� �� �ޱ�
					int rand = (int)(Math.random()*4);
					if (rand >= 4) rand=3; 
					
					System.out.println("main.ImageThread.run :: rand : "+rand);
					
					// �������� ���� ���� �迭�� �־� �̹��� ����
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
