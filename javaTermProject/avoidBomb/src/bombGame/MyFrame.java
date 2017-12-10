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
 * MyFrame 클래스
 *  : 사용자가 보고 있는 실제 Frame에서 생성되고 실행되는 요소들을 구현
 */

public class MyFrame extends JFrame {

	// gameSystem에서 
	private int valueBestScore = GameSystem.getBestScore();
	private int valueCurrentScore = GameSystem.getCurrentScore();
	
	// GameSystem에서 지정해준 값을 가져와서 크기를 전체적으로 통일시킴.
	private int sizeX;
	private int sizeY;
	
	// 실제 bomb를 체크한 개수
	private int realBombCount;
	// 사용자가 오른쪽 클릭으로 폭탄 체크한 개수
	private int bombCount;

	
	// 게임을 진행하는 버튼 부분
	private GameButton[][] btnArray;
	
	
	// 각 부분에 해당하는 패널과 패널에 부속적으로 들어가는 스레드,레이블
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
		
		setTitle("지뢰찾기 level1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bombCount = GameSystem.getBombCount();
		realBombCount = bombCount;
		
		// BorderLayout으로 배치관리자 지정
		setLayout(new BorderLayout());
		
		// 위 아래 패널 생성
		makeUpDownPanel();
		add(upPanel, BorderLayout.NORTH);
		add(downPanel, BorderLayout.SOUTH);
		
		// 버튼 패널 생성
		makeButtonPanel();
		add(buttonPanel, BorderLayout.CENTER);
	
		
		setSize(450,500);
		setVisible(true);
		
	}
	
	public void makeUpDownPanel() {
		
		/*
		 * upPanel : 잔여 시간, 일시 정지, 다시 시작.
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
		
		// replay 버튼의 배경 색을 없애주는 메소드
		replay.setContentAreaFilled(false);
		// mouseListener를 달아준다.
		replay.addMouseListener(new StartMouseListener(time));
		
		
		upPanel.add(timeLabel);
		upPanel.add(time);
		upPanel.add(replay);
		upPanel.add(bombLabel);
		upPanel.add(bombCnt);
		

		
		
		
		/*
		 * downPanel : 최고 기록과 현재 나의 score
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
		
	} // makeUpDownPanel 메소드
	
	
	
	public void makeButtonPanel() {
		
		
		/*
		 * buttonPanel : 지뢰찾기 게임 버튼이 위치하는 곳
		 */
		
		// 버튼 가로, 세로 크기를 보관할 변수 sizeX, sizeY
		sizeX = GameSystem.getSizeX();
		sizeY = GameSystem.getSizeY();
		
		
		// 버튼 패널은 GridLayout을 배치 관리자로 지정한다.
		// 왼쪽에서 오른쪽으로, 위에서 아래로 순차적으로 배치되게 한다.
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(sizeX, sizeY));
	
		btnArray = new GameButton[sizeX][sizeY]; 
		// GameSystem에서 만든 배열과 같은 크기를 생성하기 위해 크기를 받아 옴.
		
		
		
		for (int i=0; i<btnArray.length; i++) {
			for (int j=0; j<btnArray[i].length; j++) 
			{
				// 새로운 버튼을 생성하여 배열에 각 위치에 연결한다.
				btnArray[i][j] = new GameButton(i,j);
				
				// 버튼의 초기 색은 light_gray로 통일
				btnArray[i][j].setBackground(Color.LIGHT_GRAY);
				
				// 버튼마다 mouseListener를 붙여준다.
				btnArray[i][j].addMouseListener(new ButtonMouseEvent());
				
				// 각 버튼들을 panel에 붙여준다.
				buttonPanel.add(btnArray[i][j]);
			}
		}
		
		
		
	} // MakeButtonPanel 메소드
	
	
	
	public void remakeButton() {
		
		/*
		 * 새로 게임을 시작할 때
		 */
		
		buttonPanel.removeAll();
		
		btnArray = new GameButton[sizeX][sizeY]; 
		// GameSystem에서 만든 배열과 같은 크기를 생성하기 위해 크기를 받아 옴.
		
		
		// 기존 배열에 새 버튼 객체를 생성하여 연결해주는 부분 
		for (int i=0; i<btnArray.length; i++) {
			for (int j=0; j<btnArray[i].length; j++) 
			{
				btnArray[i][j] = new GameButton(i,j);
				btnArray[i][j].setBackground(Color.LIGHT_GRAY);
				
				btnArray[i][j].addMouseListener(new ButtonMouseEvent());
				buttonPanel.add(btnArray[i][j]);
			}
		}
		
		
	} // remakeButton 메소드
	
	
	
	
	public String getScoreMessage(int score) {
		return Integer.toString(score);
	}
	
	public void upGradeBestScore() {
		// 게임이 종료하면,
		
		// 최고 점수 관련 코드
		if ( valueBestScore < valueCurrentScore) {
			// 현재 게임 성적이 최고 점수이면 업그레이드 시켜준다.
			
			// GameSystem 클래스의 최고 점수를 수정해준다.
			GameSystem.setBestScore(valueCurrentScore);
			
			// MyFrame의 BestScore값과 레이블 값 또한 최고 점수 값으로 변경한다.
			valueBestScore = GameSystem.getBestScore();
			bestScore.setText("Best Score : " + valueBestScore);
			
			replay.changeImage("bestScore");
		}
		
		// 시작 버튼 이미지 관련 코드
	}
	
	
	public void spreadButtonClicked(int x, int y) {
		
		// 해당 버튼 하얀색
		btnArray[x][y].showNumberImage();
		
		// 버튼 퍼지자 ...
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
			
			// Game이 시작된 상태에서만 마우스 이벤트 작동
			if(GameSystem.isStarted()) {
			
				// selectedBtn변수에 클릭된 버튼의 객체와 연결한다.
				GameButton selectedBtn = (GameButton)e.getSource();
				
				//if(!selectedBtn.isBlocked()) selectedBtn.removeMouseListener(ButtonMouseEvent.this);
			
				// 클릭된 버튼의 x,y값을 가져온다.
				int x = selectedBtn.getRow();
				int y = selectedBtn.getCol();
				// 마우스의 오른쪽 버튼인지 왼 쪽 버튼인 지 구분하기 위함.
				int select = e.getButton();
			
				// Test
				System.out.println("MyFrmae.GameMouseEvent.MouseReleased :: x, y : ( " + x + ", " + y + " )");
			
			
				// 왼쪽 버튼을 누르면
				if (select == e.BUTTON1 && !selectedBtn.isBlocked()) {
					// 이미 눌러진 주변의 지뢰가 없는 버튼은 눌러도 작동하지 않는다. 
				
					// 마우스 클릭 시 돌아가는 thread 종료
					// -> 200ms 이내에 끝난 thread 종료 (200ms이후는 thread 자체에서 자동 종료)
				
					for (int i=x-1; i<=x+1; i++) {
						if(i<0 || i>=btnArray.length) {
							// 배열의 i 범위 밖이면 무시
							continue;
						}
						for (int j=y-1; j<=y+1; j++) {
							if(j<0 || j>=btnArray[i].length) {
								// j 범위 밖이면 무시
								continue;
							}

							if (btnArray[i][j].isWhite()) {
								// 이미 눌러진 버튼은 (주변의 지뢰의 개수와 상관없이) 스레드를 작동시키지 않았으므로
								// 멈춰줄 스레드가 존재하지 않는다. 따라서 예외로 빼준다.
								continue;
							}
							btnArray[i][j].stopThread();
						}
					}
				
					if(selectedBtn.isPink() || selectedBtn.isWhite()) {
						// isWhite가 true이면 isPink로 작동되지 않으므로 예외로 빼준다.
						
						/*
						 *  꾹 눌렀었다면 아래 코드 실행
						 *  -> 200ms이상 누르면 isPink = true가 되어 마우스를 뗀 후 해당 코드 실행
						 */
										
						// 선택된 버튼 주변 3*3 배열 : 핑크 -> 그레이
						for (int i=x-1; i<=x+1; i++) {
							if(i<0 || i>=btnArray.length) {
								// 배열의 i 범위 밖이면 무시
								continue;
							}
							for (int j=y-1; j<=y+1; j++) {
								if(j<0 || j>=btnArray[i].length) {
									// j 범위 밖이면 무시
									continue;
								}
								if (btnArray[i][j].isWhite()) {
									// 이미 눌러진 버튼은 (주변의 지뢰의 개수와 상관없이) 색이 변하지 않는다.
									continue;
								}
								if (btnArray[i][j].isPink()) {
									btnArray[i][j].releasePink();
								}
							}
						}
					}				
					else {
						// 짧게 눌렀다면 아래 코드 실행
						
						if(selectedBtn.getValue() != -1) {
							// 지뢰를 누르지 않으면
							
							/*
							 * score 관련 부분
							 */
							GameSystem.upScore(5);
							getScore.setText(getScoreMessage(5));
							getScore.setForeground(Color.RED);
							
							LabelTimer labelThread = new LabelTimer(getScore);
							labelThread.start();
							
						
							valueCurrentScore = GameSystem.getCurrentScore();
							currentScore.setText(Integer.toString(valueCurrentScore));
							
							/*
							 * 버튼 클릭 시 주변 값 처리 관련 작동 부분
							 */
							spreadButtonClicked(selectedBtn.getRow(),selectedBtn.getCol());
							
						}
						else {
							// 지뢰를 눌렀다면
							
							// 깃발을 놓은 상태가 아니라면 실행
							if(!selectedBtn.isFlag()) {
								// 게임을 종료한다.
								GameSystem.stopGame();
								// bestScore인 지 확인하고 실행하는 메소드
								upGradeBestScore();
							
								// replay 버튼의 이미지를 dead 이미지로 변경한다.
								replay.changeImage("dead");
								// 누른 버튼을 빨간 색으로 변경한다.
								selectedBtn.btnColorChange(Color.RED);
							
								timeThread.interrupt();
								// 시간 스레드 종료
							}
						}
					}
					

					/*
					 * replay 버튼 이미지 처리 (맨 위 emotion 버튼) 
					 */
					if(replay.getStatus().equals("clicked"))
						// -> clicked에서 normal이미지로 돌아오도록 함
						replay.changeImage("normal");
					
					
				
				}
			
				// 오른쪽 버튼을 누르면
				if (select == e.BUTTON3 && !selectedBtn.isWhite()) {
					// 이미 눌러진 버튼은 오른쪽 버튼을 눌러도 작동하지 않는다.
				
					if (!selectedBtn.isFlag()) {
						// isFlag = false면, Bomb 개수가 줄어든다.
						// Bomb는 남은 지뢰 개수 (라고 단정지을 수는 없음. 사용자가 flag를 꽂은 곳이 모두 지뢰라고 할 수 없으니..)
						bombCount -= 1;
						// 변경된 bombCount를 해당 label에 대입한 후, panel에 붙인다.
						bombCnt.setText(Integer.toString(bombCount));
						upPanel.add(bombCnt);
						
						
						// 실제로 flag 처리한 버튼이 지뢰였다면
						if (selectedBtn.getValue() == -1) {
							realBombCount -= 1;
							
							// 실제 지뢰를 다 찾으면
							if (realBombCount == 0) {
								
								// 게임 종료
								GameSystem.stopGame();
								// 시간 스레드 종료
								timeThread.interrupt();
								
								// replay 버튼 이미지 변경
								replay.changeImage("completed");
								
								/*
								 * score 관련
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
						// 깃발 해제 -> 지뢰 개수 증가
						bombCount += 1;
						// 변경된 bombCount를 해당 label에 대입한 후, panel에 붙인다.
						bombCnt.setText(Integer.toString(bombCount));
						upPanel.add(bombCnt);
						
						selectedBtn.btnColorChange(Color.LIGHT_GRAY);
					}
				
					selectedBtn.toggleFlag();
					// 선택된 버튼의 Flag상태가 toggle됨 (isFlag : false -> true, true-> false)
				}
			}
		} // mouseReleased method
		
		@Override
		public void mousePressed(MouseEvent e) {
			// 왼쪽 마우스를 꾹 누르면 그 버튼의 영역이 나타난다.
			
			// Game이 시작된 상태에서만 마우스 이벤트 작동
			if(GameSystem.isStarted()) {
				// selectedBtn변수에 클릭된 버튼의 객체와 연결한다.
				GameButton selectedBtn = (GameButton)e.getSource();
			
				// 클릭된 버튼의 x,y값을 가져온다.
				int x = selectedBtn.getRow();
				int y = selectedBtn.getCol();
				// 마우스의 오른쪽 버튼인지 왼 쪽 버튼인 지 구분하기 위함.
				int select = e.getButton();
			
				System.out.println("MyFrmae.GameMouseEvent.MouseClicked :: x, y : ( " + x + ", " + y + " )");
			
			
				// 왼쪽 버튼을 누르면 
				if(select == e.BUTTON1 && !selectedBtn.isBlocked()) {
					// 주변에 지뢰가 없고 이미 눌러진 버튼은 왼쪽 버튼을 눌러도 작동하지 않는다.
			
					// 200ms이상 누르고 있으면 색깔 보이기.
					for (int i=x-1; i<=x+1; i++) {
						if(i<0 || i>=btnArray.length) {
							continue;
						}
						for (int j=y-1; j<=y+1; j++) {
							if(j<0 || j>=btnArray[i].length) {
								continue;
							}

							if (btnArray[i][j].isWhite()) {
								// 이미 눌러진 버튼은 (주변의 지뢰의 개수와 상관없이) 스레드를 작동시키지 않는다.
								continue;
							}
								
							btnArray[i][j].startThread();
							
						}
					}
					
					/*
					 * replay 버튼 이미지 처리 (맨 위 emotion 버튼)
					 */
					replay.changeImage("clicked");
					
				} // 오른쪽 버튼 클릭 시에는 실행되는 메소드 없음.
			}
			
		} // mousePressed method
		
	}	// class_ButtonMouseEvent
	
	
	// 게임 시작 버튼
	class StartMouseListener extends MouseAdapter {
		
		private JLabel time;
		
		public StartMouseListener(JLabel time) {
			this.time = time;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(!GameSystem.isStarted()) {
				// 새 게임을 시작하면,
				
				// GameSystem의 start = true
				GameSystem.startGame();
				
				// GameSystem의 보드를 초기화한다.
				GameSystem.initBoard();
				GameSystem.printBoard();
				
				// 버튼을 새로 만든다. (새 게임을 시작하기 때문)
				remakeButton();
				
				
				// 기존에 붙어있던 getScore 레이블을 제거한다.
				downPanel.remove(getScore);
				// 새 게임을 시작하면 getScore를 새로 만들어준다.
				getScore = new JLabel();
				downPanel.add(getScore);
				
				
				// 시간 스레드 작동
				timeThread = new Timer(time);
				timeThread.start();
				
				// bombCount, realBombCount 초기화
				bombCount = GameSystem.getBombCount();
				realBombCount = bombCount;
				
				// 시간 label의 값은 0으로 초기화
				time.setText("0");
				
				// 초기화된 bombCount를 label에 대입한 후 panel에 붙인다.
				bombCnt.setText(Integer.toString(bombCount));
				upPanel.add(bombCnt);
				
				// replay button 초기화
				replay.changeImage("normal");
			}
			
			else {
				// 게임을 끝내면,
				GameSystem.stopGame();
				// GameSystem의 start = False
				
				
				timeThread.interrupt();
				// 시간 스레드 종료
				
			}
		}		
	}
	
}
