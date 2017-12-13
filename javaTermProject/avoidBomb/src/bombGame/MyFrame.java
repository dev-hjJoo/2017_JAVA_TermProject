package bombGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * MyFrame Ŭ����
 *  : ����ڰ� ���� �ִ� ���� Frame���� �����ǰ� ����Ǵ� ��ҵ��� ����
 */

public class MyFrame extends JFrame {

	// gameSystem���� 
	private int valueBestScore = GameSystem.getBestScore();
	private int valueCurrentScore = GameSystem.getCurrentScore();
	
	// GameSystem���� �������� ���� �����ͼ� ũ�⸦ ��ü������ ���Ͻ�Ŵ.
	private int sizeX;
	private int sizeY;
	
	// ���� bomb�� üũ�� ����
	private int realBombCount;
	// ����ڰ� ������ Ŭ������ ��ź üũ�� ����
	private int bombCount;

	
	// ������ �����ϴ� ��ư �κ�
	private GameButton[][] btnArray;
	
	
	// �� �κп� �ش��ϴ� �гΰ� �гο� �μ������� ���� ������,���̺�
	JPanel upPanel;
	Timer timeThread;
	ReplayButton replay;
	
	JPanel buttonPanel;
	JLabel bombCnt;
	JLabel time;
	
	JPanel downPanel;
	JLabel getScore;
	JLabel bestScore;
	JLabel currentScore;
	
	
	
	public MyFrame() {
		
		setTitle("����ã�� level1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bombCount = GameSystem.getBombCount();
		realBombCount = bombCount;
		
		// BorderLayout���� ��ġ������ ����
		setLayout(new BorderLayout());
		
		// �� �Ʒ� �г� ����
		makeUpDownPanel();
		add(upPanel, BorderLayout.NORTH);
		add(downPanel, BorderLayout.SOUTH);
		
		// ��ư �г� ����
		makeButtonPanel();
		add(buttonPanel, BorderLayout.CENTER);
	
		
		setSize(450,500);
		setVisible(true);
		
	}
	
	public void makeUpDownPanel() {
		
		/*
		 * upPanel : �ܿ� �ð�, �Ͻ� ����, �ٽ� ����.
		 */
		upPanel = new JPanel(new GridLayout(1,5));
		
		// time Label
		JLabel timeLabel = new JLabel ("time : ", JLabel.RIGHT);
		time = new JLabel("0", JLabel.LEFT);
		
		// bomb Label
		JLabel bombLabel = new JLabel ("bomb : ", JLabel.RIGHT);
		bombCnt = new JLabel(Integer.toString(bombCount), JLabel.LEFT);
		
		// replay Button
		replay = new ReplayButton();
		replay.setPreferredSize(new Dimension(20,50));
		
		// replay ��ư�� ��� ���� �����ִ� �޼ҵ�
		replay.setContentAreaFilled(false);
		// mouseListener�� �޾��ش�.
		replay.addMouseListener(new StartMouseListener(time));
		
		
		upPanel.add(timeLabel);
		upPanel.add(time);
		upPanel.add(replay);
		upPanel.add(bombLabel);
		upPanel.add(bombCnt);
		

		
		
		
		/*
		 * downPanel : �ְ� ��ϰ� ���� ���� score
		 */
		downPanel = new JPanel();
		downPanel.setLayout(new GridLayout(1,4));
		bestScore = new JLabel("Best Score : " + valueBestScore, JLabel.CENTER);
		currentScore = new JLabel("Current Score : "+ valueCurrentScore, JLabel.CENTER);
		getScore = new JLabel();
		//getScore.setFont(font);
		
		downPanel.add(bestScore);
		downPanel.add(currentScore);
		downPanel.add(getScore);
		
	} // makeUpDownPanel �޼ҵ�
	
	
	
	public void makeButtonPanel() {
		
		
		/*
		 * buttonPanel : ����ã�� ���� ��ư�� ��ġ�ϴ� ��
		 */
		
		// ��ư ����, ���� ũ�⸦ ������ ���� sizeX, sizeY
		sizeX = GameSystem.getSizeX();
		sizeY = GameSystem.getSizeY();
		
		
		// ��ư �г��� GridLayout�� ��ġ �����ڷ� �����Ѵ�.
		// ���ʿ��� ����������, ������ �Ʒ��� ���������� ��ġ�ǰ� �Ѵ�.
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(sizeX, sizeY));
	
		btnArray = new GameButton[sizeX][sizeY]; 
		// GameSystem���� ���� �迭�� ���� ũ�⸦ �����ϱ� ���� ũ�⸦ �޾� ��.
		
		
		
		for (int i=0; i<btnArray.length; i++) {
			for (int j=0; j<btnArray[i].length; j++) 
			{
				// ���ο� ��ư�� �����Ͽ� �迭�� �� ��ġ�� �����Ѵ�.
				btnArray[i][j] = new GameButton(i,j);
				
				// ��ư�� �ʱ� ���� light_gray�� ����
				btnArray[i][j].setBackground(Color.LIGHT_GRAY);
				
				// ��ư���� mouseListener�� �ٿ��ش�.
				btnArray[i][j].addMouseListener(new ButtonMouseEvent());
				
				// �� ��ư���� panel�� �ٿ��ش�.
				buttonPanel.add(btnArray[i][j]);
			}
		}
		
		
		
	} // MakeButtonPanel �޼ҵ�
	
	
	
	public void remakeButton() {
		
		/*
		 * ���� ������ ������ ��
		 */
		
		buttonPanel.removeAll();
		
		btnArray = new GameButton[sizeX][sizeY]; 
		// GameSystem���� ���� �迭�� ���� ũ�⸦ �����ϱ� ���� ũ�⸦ �޾� ��.
		
		
		// ���� �迭�� �� ��ư ��ü�� �����Ͽ� �������ִ� �κ� 
		for (int i=0; i<btnArray.length; i++) {
			for (int j=0; j<btnArray[i].length; j++) 
			{
				btnArray[i][j] = new GameButton(i,j);
				btnArray[i][j].setBackground(Color.LIGHT_GRAY);
				
				btnArray[i][j].addMouseListener(new ButtonMouseEvent());
				buttonPanel.add(btnArray[i][j]);
			}
		}
		
		
	} // remakeButton �޼ҵ�
	
	
	
	
	public String getScoreMessage(int score) {
		return Integer.toString(score);
	}
	
	public void upGradeBestScore() {
		// ������ �����ϸ�,
		
		// �ְ� ���� ���� �ڵ�
		if ( valueBestScore < valueCurrentScore) {
			// ���� ���� ������ �ְ� �����̸� ���׷��̵� �����ش�.
			
			// GameSystem Ŭ������ �ְ� ������ �������ش�.
			GameSystem.setBestScore(valueCurrentScore);
			
			// MyFrame�� BestScore���� ���̺� �� ���� �ְ� ���� ������ �����Ѵ�.
			valueBestScore = GameSystem.getBestScore();
			bestScore.setText("Best Score : " + valueBestScore);
			
			replay.changeImage("bestScore");
		}
		
		// ���� ��ư �̹��� ���� �ڵ�
	}
	
	
	public void spreadButtonClicked(int x, int y) {
		
		// �ش� ��ư �Ͼ��
		btnArray[x][y].showNumberImage();
		
		// ��ư ������ ...
		if (btnArray[x][y].getValue() == 0) {
			for(int i=x-1; i<=x+1; i++) {
				if(i>=btnArray.length || i<0) continue;
				for(int j=y-1; j<=y+1; j++) {
					if(j>=btnArray[0].length || j<0) continue;
					
					if(btnArray[i][j].getValue() != -1 && !btnArray[i][j].isBlocked()) {
						spreadButtonClicked(i, j);
					}
				}
				
			}
			
		}
		
	}
	
	
	/*
	 * EventListener
	 */
	class ButtonMouseEvent extends MouseAdapter {
		
		ButtonTimer thread;
		
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
			// Game�� ���۵� ���¿����� ���콺 �̺�Ʈ �۵�
			if(GameSystem.isStarted()) {
			
				// selectedBtn������ Ŭ���� ��ư�� ��ü�� �����Ѵ�.
				GameButton selectedBtn = (GameButton)e.getSource();
				
				//if(!selectedBtn.isBlocked()) selectedBtn.removeMouseListener(ButtonMouseEvent.this);
			
				// Ŭ���� ��ư�� x,y���� �����´�.
				int x = selectedBtn.getRow();
				int y = selectedBtn.getCol();
				// ���콺�� ������ ��ư���� �� �� ��ư�� �� �����ϱ� ����.
				int select = e.getButton();
			
				// Test
				System.out.println("MyFrmae.GameMouseEvent.MouseReleased :: x, y : ( " + x + ", " + y + " )");
			
			
				// ���� ��ư�� ������
				if (select == e.BUTTON1 && !selectedBtn.isBlocked()) {
					// �̹� ������ �ֺ��� ���ڰ� ���� ��ư�� ������ �۵����� �ʴ´�. 
				
					// ���콺 Ŭ�� �� ���ư��� thread ����
					// -> 200ms �̳��� ���� thread ���� (200ms���Ĵ� thread ��ü���� �ڵ� ����)
				
					for (int i=x-1; i<=x+1; i++) {
						if(i<0 || i>=btnArray.length) {
							// �迭�� i ���� ���̸� ����
							continue;
						}
						for (int j=y-1; j<=y+1; j++) {
							if(j<0 || j>=btnArray[i].length) {
								// j ���� ���̸� ����
								continue;
							}

							if (btnArray[i][j].isWhite()) {
								// �̹� ������ ��ư�� (�ֺ��� ������ ������ �������) �����带 �۵���Ű�� �ʾ����Ƿ�
								// ������ �����尡 �������� �ʴ´�. ���� ���ܷ� ���ش�.
								continue;
							}
							btnArray[i][j].stopThread();
						}
					}
				
					if(selectedBtn.isPink() || selectedBtn.isWhite()) {
						// isWhite�� true�̸� isPink�� �۵����� �����Ƿ� ���ܷ� ���ش�.
						
						/*
						 *  �� �������ٸ� �Ʒ� �ڵ� ����
						 *  -> 200ms�̻� ������ isPink = true�� �Ǿ� ���콺�� �� �� �ش� �ڵ� ����
						 */
										
						// ���õ� ��ư �ֺ� 3*3 �迭 : ��ũ -> �׷���
						for (int i=x-1; i<=x+1; i++) {
							if(i<0 || i>=btnArray.length) {
								// �迭�� i ���� ���̸� ����
								continue;
							}
							for (int j=y-1; j<=y+1; j++) {
								if(j<0 || j>=btnArray[i].length) {
									// j ���� ���̸� ����
									continue;
								}
								if (btnArray[i][j].isWhite()) {
									// �̹� ������ ��ư�� (�ֺ��� ������ ������ �������) ���� ������ �ʴ´�.
									continue;
								}
								if (btnArray[i][j].isPink()) {
									btnArray[i][j].releasePink();
								}
							}
						}
					}				
					else {
						// ª�� �����ٸ� �Ʒ� �ڵ� ����
						
						if(selectedBtn.getValue() != -1) {
							// ���ڸ� ������ ������
							
							/*
							 * score ���� �κ�
							 */
							GameSystem.upScore(5);
							getScore.setText(getScoreMessage(5));
							getScore.setForeground(Color.RED);
							
							LabelTimer labelThread = new LabelTimer(getScore);
							labelThread.start();
							
						
							valueCurrentScore = GameSystem.getCurrentScore();
							currentScore.setText(Integer.toString(valueCurrentScore));
							
							/*
							 * ��ư Ŭ�� �� �ֺ� �� ó�� ���� �۵� �κ�
							 */
							spreadButtonClicked(selectedBtn.getRow(),selectedBtn.getCol());
							
						}
						else {
							// ���ڸ� �����ٸ�
							
							// ����� ���� ���°� �ƴ϶�� ����
							if(!selectedBtn.isFlag()) {
								// ������ �����Ѵ�.
								GameSystem.stopGame();
								// bestScore�� �� Ȯ���ϰ� �����ϴ� �޼ҵ�
								upGradeBestScore();
							
								// replay ��ư�� �̹����� dead �̹����� �����Ѵ�.
								replay.changeImage("dead");
								// ���� ��ư�� ���� ������ �����Ѵ�.
								selectedBtn.btnColorChange(Color.RED);
							
								timeThread.interrupt();
								// �ð� ������ ����
							}
						}
					}
					

					/*
					 * replay ��ư �̹��� ó�� (�� �� emotion ��ư) 
					 */
					if(replay.getStatus().equals("clicked"))
						// -> clicked���� normal�̹����� ���ƿ����� ��
						replay.changeImage("normal");
					
					
				
				}
			
				// ������ ��ư�� ������
				if (select == e.BUTTON3 && !selectedBtn.isWhite()) {
					// �̹� ������ ��ư�� ������ ��ư�� ������ �۵����� �ʴ´�.
				
					if (!selectedBtn.isFlag()) {
						// isFlag = false��, Bomb ������ �پ���.
						// Bomb�� ���� ���� ���� (��� �������� ���� ����. ����ڰ� flag�� ���� ���� ��� ���ڶ�� �� �� ������..)
						bombCount -= 1;
						// ����� bombCount�� �ش� label�� ������ ��, panel�� ���δ�.
						bombCnt.setText(Integer.toString(bombCount));
						upPanel.add(bombCnt);
						
						
						// ������ flag ó���� ��ư�� ���ڿ��ٸ�
						if (selectedBtn.getValue() == -1) {
							realBombCount -= 1;
							
							// ���� ���ڸ� �� ã����
							if (realBombCount == 0) {
								
								// ���� ����
								GameSystem.stopGame();
								// �ð� ������ ����
								timeThread.interrupt();
								
								// replay ��ư �̹��� ����
								replay.changeImage("completed");
								
								/*
								 * score ����
								 */
								
								GameSystem.upScore(30);
								String txt = "complete(30)";
								
								int t = 50-Integer.parseInt(time.getText());
								if( t>0 ) {
									txt = "time(" + t + ") + " + txt;
								}
								
								getScore.setText(txt);
								
								getScore.setForeground(Color.RED);
								
								upGradeBestScore();
								
								
							}
						}
						
					}
					else {
						// ��� ���� -> ���� ���� ����
						bombCount += 1;
						// ����� bombCount�� �ش� label�� ������ ��, panel�� ���δ�.
						bombCnt.setText(Integer.toString(bombCount));
						upPanel.add(bombCnt);
						
						selectedBtn.btnColorChange(Color.LIGHT_GRAY);
					}
				
					selectedBtn.toggleFlag();
					// ���õ� ��ư�� Flag���°� toggle�� (isFlag : false -> true, true-> false)
				}
			}
		} // mouseReleased method
		
		@Override
		public void mousePressed(MouseEvent e) {
			// ���� ���콺�� �� ������ �� ��ư�� ������ ��Ÿ����.
			
			// Game�� ���۵� ���¿����� ���콺 �̺�Ʈ �۵�
			if(GameSystem.isStarted()) {
				// selectedBtn������ Ŭ���� ��ư�� ��ü�� �����Ѵ�.
				GameButton selectedBtn = (GameButton)e.getSource();
			
				// Ŭ���� ��ư�� x,y���� �����´�.
				int x = selectedBtn.getRow();
				int y = selectedBtn.getCol();
				// ���콺�� ������ ��ư���� �� �� ��ư�� �� �����ϱ� ����.
				int select = e.getButton();
			
				System.out.println("MyFrmae.GameMouseEvent.MouseClicked :: x, y : ( " + x + ", " + y + " )");
			
			
				// ���� ��ư�� ������ 
				if(select == e.BUTTON1 && !selectedBtn.isBlocked()) {
					// �ֺ��� ���ڰ� ���� �̹� ������ ��ư�� ���� ��ư�� ������ �۵����� �ʴ´�.
			
					// 200ms�̻� ������ ������ ���� ���̱�.
					for (int i=x-1; i<=x+1; i++) {
						if(i<0 || i>=btnArray.length) {
							continue;
						}
						for (int j=y-1; j<=y+1; j++) {
							if(j<0 || j>=btnArray[i].length) {
								continue;
							}

							if (btnArray[i][j].isWhite()) {
								// �̹� ������ ��ư�� (�ֺ��� ������ ������ �������) �����带 �۵���Ű�� �ʴ´�.
								continue;
							}
								
							btnArray[i][j].startThread();
							
						}
					}
					
					/*
					 * replay ��ư �̹��� ó�� (�� �� emotion ��ư)
					 */
					replay.changeImage("clicked");
					
				} // ������ ��ư Ŭ�� �ÿ��� ����Ǵ� �޼ҵ� ����.
			}
			
		} // mousePressed method
		
	}	// class_ButtonMouseEvent
	
	
	// ���� ���� ��ư
	class StartMouseListener extends MouseAdapter {
		
		private JLabel time;
		
		public StartMouseListener(JLabel time) {
			this.time = time;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(!GameSystem.isStarted()) {
				// �� ������ �����ϸ�,
				
				// GameSystem�� start = true
				GameSystem.startGame();
				
				// GameSystem�� ���带 �ʱ�ȭ�Ѵ�.
				GameSystem.initBoard();
				GameSystem.printBoard();
				
				// ��ư�� ���� �����. (�� ������ �����ϱ� ����)
				remakeButton();
				
				
				// ������ �پ��ִ� getScore ���̺��� �����Ѵ�.
				downPanel.remove(getScore);
				// �� ������ �����ϸ� getScore�� ���� ������ش�.
				getScore = new JLabel();
				downPanel.add(getScore);
				
				
				// �ð� ������ �۵�
				timeThread = new Timer(time);
				timeThread.start();
				
				// bombCount, realBombCount �ʱ�ȭ
				bombCount = GameSystem.getBombCount();
				realBombCount = bombCount;
				
				// �ð� label�� ���� 0���� �ʱ�ȭ
				time.setText("0");
				
				// �ʱ�ȭ�� bombCount�� label�� ������ �� panel�� ���δ�.
				bombCnt.setText(Integer.toString(bombCount));
				upPanel.add(bombCnt);
				
				// replay button �ʱ�ȭ
				replay.changeImage("normal");
			}
			
			else {
				// ������ ������,
				GameSystem.stopGame();
				// GameSystem�� start = False
				
				
				timeThread.interrupt();
				// �ð� ������ ����
				
			}
		}		
	}
	
}
