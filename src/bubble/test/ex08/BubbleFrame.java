package bubble.test.ex08;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;
	// 포함관계 - 콤포지션
	private Player player;// BubbleFrame 안에 Player 포함
	//Bubble bubble;// 이거는 하나의 객체 주소만 담으려고 끼워 넣은 거임.

	public BubbleFrame() {
		initDate(); // 먼저 호출되어야 한다.
		setInitLayout();
		addEventListener();

		// Player 백그라운드 서비스 시작
		// new BackgroundPlayerService(player); // 객체는 생성되었는데 일하는 건 아님
		new Thread(new BackgroundPlayerService(player)).start(); // BackgroundPlayerService 객체가 Thread 객체 안에서 생성되어 일하기 시작함.
	}

	private void initDate() {
		// todo 이미지 변경
		backgroundMap = new JLabel(new ImageIcon("img/backgroundMap.png")); // new ImageIcon() : 익명 클래스
		// backgroundMap = new JLabel(new ImageIcon("img/test.png")); // new ImageIcon()
		// : 익명 클래스
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Frame 안에 root Pannel이 있음.
		setContentPane(backgroundMap); // add 처리 - backgroundMap 붙이기
		setSize(1000, 640);

		player = new Player(); // 메모리에 올림
	}

	private void setInitLayout() {
		// 좌표 값으로 배치
		setLayout(null);
		setResizable(false); // 프레임 크기 조절 불가하도록 설정
		setLocationRelativeTo(null); // JFrame 열어 본 모니터 가운데 자동 배치
		setVisible(true);

		add(player);
	}

	private void addEventListener() {
		this.addKeyListener(new KeyAdapter() { // new KeyListener() 추상 -> 구현 클래스로 바꿈.

			@Override
			public void keyPressed(KeyEvent e) { // 누를때
				System.out.println("key code : " + e.getKeyCode()); // key code 번호 출력

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:

					// 왼쪽으로 방향키 누르고 있다면 Key 이벤트가 계속 <- <- <- <- <-
					// 한번만 실행했을 때 계속 쓰레드를 생성? 반복 실행?하지 않도록
					// 왼쪽 상태가 아니라면
					// 왼쪽 벽에 충돌한 게 아니라면
					// 단 한 번만 호출할 수 있음
					if (!player.isLeft() && !player.isLeftWallCrash()) { 
						// 왼쪽 상태도 아니고 And 왼쪽 벽에 충돌한게 아니어야만 (둘 다 true 여야만) 호출됨.
						player.left(); // player의 left() 메서드(움직임 상태)를 호출함
					}
					break;

				case KeyEvent.VK_RIGHT:
					if (!player.isRight() && !player.isRightWallCrash()) {
						player.right();
					}
					break;

				case KeyEvent.VK_UP:
					player.up();
					break;
					
				case KeyEvent.VK_SPACE:
					if (e.getKeyCode() == 32) {
//						Bubble bubble = new Bubble(player); //Bubble 데이터 타입의 bubble 객체를 생성함. 
//						//객체만 생성하면 눈에 보이지 않음 
//						add(bubble);// 눈에 보이도록 bubble을 붙임.  
//						// player로부터 bubble이 형성되도록 객체 안에 player를 호출함.
						
					add(new Bubble(player));// 다른 곳에 쓸 거 아니면 new Bubble(player) => 익명클래스로 써도 무방함
					//누를 때마다 객체가 형성이 됨. bubble 하나 하나가 전부 객체임
					// 멤버변수에 Bubble을 선언해서 하나의 주소값만 담아서는 안됨.
					}
					break;
				default:
					break;
				}
			}// end of KeyPressed

			@Override
			public void keyReleased(KeyEvent e) { // 손 뗄때

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					// 손을 떼면 왼쪽으로 가는 상태 멈춤
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					// 손을 떼면 오른쪽을 가는 상태 멈춤
					player.setRight(false);
					break;
				default:
					break;
				}
			}// end of KeyRealsed
		});
	}

	// 코드 테스트
	public static void main(String[] args) {
		new BubbleFrame(); // 생성자(익명 클래스) 호출
	}
}
