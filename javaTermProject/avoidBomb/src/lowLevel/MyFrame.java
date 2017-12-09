package lowLevel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrame extends JFrame {

	private int valueBestScore = 0;
	private int valueCurrentScore = 0;
	
	private int sizeX;
	private int sizeY;

	private MyButton[][] btnArray;
	
	
	JPanel upPanel;
	JPanel buttonPanel;
	JPanel downPanel;
	
	public MyFrame() {
		
		setTitle("����ã�� level1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		makePanel();
		add(upPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
		add(downPanel, BorderLayout.SOUTH);
		
		setSize(400,450);
		setVisible(true);
		
	}
	
	public void makePanel() {
		
		/*
		 * upPanel : �ܿ� �ð�, �Ͻ� ����, �ٽ� ����.
		 */
		upPanel = new JPanel(new GridLayout(1,4));
		JLabel timeLabel = new JLabel ("time", JLabel.CENTER);
		JLabel time = new JLabel("00", JLabel.CENTER);
		JLabel temp = new JLabel();
		JButton replay = new JButton("Replay");
		replay.setBackground(Color.ORANGE);
		
		upPanel.add(timeLabel);
		upPanel.add(time);
		upPanel.add(temp);
		upPanel.add(replay);
		
		
		
		/*
		 * buttonPanel : ����ã�� ���� ��ư�� ��ġ�ϴ� ��
		 */
		sizeX = GameSystem.getSizeX();
		sizeY = GameSystem.getSizeY();
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(sizeX, sizeY));
		
		btnArray = new MyButton[sizeX][sizeY]; 
		// GameSystem���� ���� �迭�� ���� ũ�⸦ �����ϱ� ���� ũ�⸦ �޾� ��.
		
		int size = this.getWidth()/10;
		
		for (int i=0; i<btnArray.length; i++) {
			for (int j=0; j<btnArray[i].length; j++) 
			{
				btnArray[i][j] = new MyButton(i,j);
				btnArray[i][j].setBackground(Color.LIGHT_GRAY);
				
				buttonPanel.add(btnArray[i][j]);
			}
		}
		
		
		
		/*
		 * downPanel : �ְ� ��ϰ� ���� ���� score
		 */
		downPanel = new JPanel();
		downPanel.setLayout(new GridLayout(1,2));
		JLabel bestScore = new JLabel("Best Score : " + valueBestScore, JLabel.CENTER);
		JLabel currentScore = new JLabel("Current Score : "+ valueCurrentScore, JLabel.CENTER);
		
		downPanel.add(bestScore);
		downPanel.add(currentScore);
		
	} // makePanel �޼ҵ�
	
}
