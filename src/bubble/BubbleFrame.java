package bubble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bubble.components.Enemy;
import bubble.components.Player;

public class BubbleFrame extends JFrame {

	// 컨텍스트를 생성하는 방법(셀프 참조)
	// BubbleFrame bubbleFrame = this; // bubbleFrame 변수 자기 자신의 주소가 들어가 있음.
	// this = BubbleFrame // 안에 add() 호출 가능
	BubbleFrame mContext = this; // 컨텍스트 생성

	private JLabel backgroundMap;
	// 포함관계 - 콤포지션
	private Player player;// BubbleFrame 안에 Player 포함
	// Bubble bubble;// 하나의 객체 주소만 담으려고 끼워 넣은 거임.
	
	private Enemy enemy1;
	

	public BubbleFrame() {
		initData(); // 먼저 호출되어야 한다.
		setInitLayout();
		addEventListener();

		// Player 백그라운드 서비스 시작
		// new BackgroundPlayerService(player); // 객체는 생성되었는데 일하는 건 아님
		//new Thread(new BackgroundPlayerService(player)).start(); // BackgroundPlayerService 가 객체 안에 생성됨.
		// new.remove(player); -> 플레이어 지워짐
	}

	private void initData() {
		// todo 이미지 변경
		backgroundMap = new JLabel(new ImageIcon("img/backgroundMap.png")); // new ImageIcon() : 익명 클래스
		// backgroundMap = new JLabel(new ImageIcon("img/test.png")); // new ImageIcon()
		// : 익명 클래스
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Frame 안에 root Pannel이 있음.
		setContentPane(backgroundMap); // add 처리 - backgroundMap 붙이기
		setSize(1000, 640);

		// mContext -> 참조 타입() --> 주소값이 들어감. 주소값 크기: 기본 4byte 이다.
		player = new Player(mContext); // 메모리에 올림 // 생성자 매개변수 타입에 mContext 넣기
		
		enemy1 = new Enemy(mContext);
	}

	private void setInitLayout() {
		// 좌표 값으로 배치
		setLayout(null);
		setResizable(false); // 프레임 크기 조절 불가하도록 설정
		setLocationRelativeTo(null); // JFrame 열어 본 모니터 가운데 자동 배치
		setVisible(true);

		add(player);
		add(enemy1);
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
						// 왼쪽 상태도 아니고 왼쪽 벽에 충돌한게 아니어야만 (둘 다 true 여야만) 호출됨.
						player.left();
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
//						Bubble bubble = new Bubble(player); //Bubble 데이터 타입의 bubble 객체를 생성함. 
//						//객체만 생성하면 눈에 보이지 않음 
//						add(bubble);// 눈에 보이도록 bubble을 붙임.  
//						// player로부터 bubble이 형성되도록 객체 안에 player를 호출함.

					// add(new Bubble(player));// 다른 곳에 쓸 거 아니면 new Bubble(player) => 익명클래스로 써도 무방함
					// 누를 때마다 객체가 형성이 됨. bubble 하나 하나가 전부 객체임
					// 멤버변수에 Bubble을 선언해서 하나의 주소값만 담아서는 안됨.
					// player가 버블을 생성한다? 애매함 -> player가 attack한다
					player.attack();
					// 프레임의 컴포넌트를 add 동작은 누구? JFrame --> add() 메서드이다.
					// 버블 실행 시에 끊김 현상이 발생하는 이유는?
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

	// getter
	public Player getPlayer() {
		return player;
	}
	
	public Enemy getEnemy() {
		return enemy1;
	}

	// 코드 테스트
	public static void main(String[] args) {
		// main 함수를 가지고 있는 클래스는 하위에 생성된 모든 객체들의
		// 주소값을 알고 있다. (매우 중요!!!!!!)
		new BubbleFrame(); // 생성자(익명 클래스) 호출
	}
}
