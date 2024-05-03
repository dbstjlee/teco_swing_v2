package bubble.test.ex07;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// 물방울 생성
// JLabel은 컴포넌트라 붙여야만(add) 눈에 보임
public class Bubble extends JLabel implements Moveable {

	private Player player;

	private int x; // 플레이어의 x 좌표
	private int y; // 플레이어의 y 좌표

	// bubble의 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	// down 은 없음

	// 적군을 맞춘 상태
	private int state; // 물방울 상태 => 0.(기본 물방울), 1.(적을 가둔 상태 물방울)

	private ImageIcon bubble; // 기본 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울 팡!(터짐)

	// 연관관계 , 의존성 컴포지션 관계, 생성자 의존(DI)
	public Bubble(Player player) {
		this.player = player; // this.player에 player를 넣음
		initData();
		setInitLayout();
		// 객체 생성 시 무조건 스레드 시작
		initThread(); // 호출
	}

	// get, set
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public ImageIcon getBubble() {
		return bubble;
	}

	public void setBubble(ImageIcon bubble) {
		this.bubble = bubble;
	}

	public ImageIcon getBubbled() {
		return bubbled;
	}

	public void setBubbled(ImageIcon bubbled) {
		this.bubbled = bubbled;
	}

	public ImageIcon getBomb() {
		return bomb;
	}

	public void setBomb(ImageIcon bomb) {
		this.bomb = bomb;
	}

	private void initData() { // 초기화 과정
		bubble = new ImageIcon("img/bubble.png");
		bubbled = new ImageIcon("img/bubbled.png");
		bomb = new ImageIcon("img/bomb.png");

		// 기본값
		left = false;
		right = false;
		up = false;
		state = 0;
	}

	private void setInitLayout() {

		// x, y, setIcon, setSixe(50,50), setLocation(x,y)
		x = player.getX(); // getX()에서 불러온 x 좌표값을 x로 설정
		y = player.getY();

		setIcon(bubble); // 버블 그림 아이콘
		setSize(50, 50); // 버블 크기
		setLocation(x, y); // 버블의 좌표 
	}

	// 공통으로 사용하는 부분을 메서드로 만들어 보자.
	// 이 메서드는 내부에서만 사용할 예정
	private void initThread() {
		// 버블은 스레드가 하나면 된다.
		// 인터페이스 -> 클래스로 만드는 것 = 구현 클래스(익명 클래스, 익명 구현 클래스)

		new Thread(new Runnable() {

			@Override
			public void run() {
				if (player.playerWay == PlayerWay.LEFT) { 
					// player 클래스의 playWay 변수와 PlayerWay enum의 LEFT와 같다면 밑에 있는 left() 호출
					left(); // 호출되면 플레이어가 왼쪽을 바라보면 왼쪽으로 방울을 쏘게 만듬
				} else { // player 클래스의 playWay 변수와 PlayerWay enum의 LEFT와 같다면
					right(); // 호출되면 플레이어가 오른쪽을 바라보면 오른쪽으로 방울을 쏘게 만듬
				}
			}
		}).start();
	}

	@Override
	public void left() { // 플레이어가 왼쪽을 바라보면 왼쪽으로 방울을 쏨
		left = true; 
		for (int i = 0; i < 400; i++) {
			x--; // 방울이 왼쪽으로 이동
			setLocation(x, y); // 좌표를 찍어서 방울이 보이도록 함.
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// left() 메서드는 스레드 쓴거 아님
		up(); // 끝나고 올라가게 하려고 up() 메서드 호출
	}

	@Override
	// 일정 거리까지 방울이 가려면 횟수 있어야 함.
	public void right() {
		right = true;
		for (int i = 0; i < 400; i++) {
			x++; // 방울이 오른쪽으로 이동
			setLocation(x, y);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up(); // 끝나고 올라가게 해
	}

	@Override
	// 방울이 천장까지 올라가야 해서 횟수를 지정하지 못함.
	public void up() {
		up = true;
		while (true) {
			y--;
			setLocation(x, y);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

//	// 참 좋은 코드 일까? // 지우면 오류 발생함
//	@Override
//	public void down() {}
// -> Moveable에서 default 로 만들어서 구현해서 지워도 오류 안 생김