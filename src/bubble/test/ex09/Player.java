package bubble.test.ex09;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	BubbleFrame mContext;
	private int x; // x 좌표
	private int y; // y 좌표
	private ImageIcon playerR, playerL; // ImageIcon = 이미지 불러옴.

	// 움직임의 상태
	private boolean left; // true : 움직임 false : 멈춤
	private boolean right;
	private boolean up;
	private boolean down;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	// 플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;

	// enum 타입의 활용
	PlayerWay playerWay; // 변수에 데이터 타입이 선언됨.

//	// setter // setLeft(true: 움직임 false: 멈춤)
//	public void setLeft(boolean left) {
//		this.left = left;
//	}
//
//	public void setRight(boolean right) {
//		this.right = right;
//	}

	// get, set
	// 부모에게로부터 멤머변수 받을 수 있도록
	public Player(BubbleFrame mContext) { // 기본생성자에서 //부모의 주소값이 들어오는 설계다.
		// mContext 멤버로 올려야 선언 가능
		this.mContext = mContext;
		initData(); // 호출
		setInitLayout();

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

	public ImageIcon getPlayerR() {
		return playerR;
	}

	public void setPlayerR(ImageIcon playerR) {
		this.playerR = playerR;
	}

	public ImageIcon getPlayerL() {
		return playerL;
	}

	public void setPlayerL(ImageIcon playerL) {
		this.playerL = playerL;
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

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}

	public int getSPEED() {
		return SPEED;
	}

	public int getJUMPSPEED() {
		return JUMPSPEED;
	}

	private void initData() {
		playerR = new ImageIcon("img/playerR.png"); // playerR 이미지 객체 생성
		playerL = new ImageIcon("img/playerL.png");

		// 처음 실행 시 초기값 셋팅(초기화)
		x = 450;
		y = 535;

		// 플레이어가 가만히 멈춤 상태
		left = false; // false 가 멈춤 상태임(boolean - 초기값은 false임)
		right = false;
		up = false;
		down = false;

		leftWallCrash = false; // 초기값, 안 부딪힌 상태
		rightWallCrash = false;

		// 기본값처럼 설정 => 창을 열면 playerWay는 오른쪽을 향함
		playerWay = PlayerWay.RIGHT; // bubble은 왼/오 2개만 열거타입으로 관리

	}

	private void setInitLayout() {
		// 플레이어 이미지 불러냄
		setIcon(playerR); // setIcon은 JLabel의 기능에서 불러옴.
		setSize(50, 50);
		setLocation(x, y);
	}

	@Override
	public void left() { // 1번 누르면 쓰레드 1개/ 2번 누르면 2개 -> 누를 수록 속도가 배로 올라감.
		playerWay = PlayerWay.LEFT;
		left = true; // 왼쪽으로 움직임(가고 있는 상태)
		setIcon(playerL); // 왼쪽 방향키 이벤트 발생 시 이미지를 왼쪽을 보는 이미지로 셋팅

		// <- <- <- 반복문(횟수 제한X) => while
		// main 은 KeyEvent 받음 -> 다른 작업자한테 위임함 -> new Thread(new Runnable()).start();
		new Thread(new Runnable() { // -> 익명 클래스

			@Override
			public void run() {
				// left 가 true 일때 까지 반복
				while (left) { // left = true; 움직임
					x = x - SPEED; // 왼쪽으로 가니까 '-'(마이너스)
					setLocation(x, y); // 다시 위치를 찍어줌
					try {
						Thread.sleep(10); // 0.01초
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	// 멈춰있는 상태란 개념이 필요하다.

	@Override
	public void right() {
		playerWay = PlayerWay.RIGHT; // 방향에 따라 값이 변함
		right = true;
		setIcon(playerR);

		new Thread(new Runnable() { // Runnable 구현 클래스로 생성

			@Override
			public void run() {
				while (right) {
					x = x + SPEED;
					setLocation(x, y); // 다시 위치를 찍어주는 동작이다.
					try {
						Thread.sleep(10); // 0.01
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}// end of right

	@Override
	public void up() {
		System.out.println("점프");
		up = true;

		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 0; i < 130 / JUMPSPEED; i++) { // JUMPSPEED = 2 , 130 / JUMPSPEED: 뛰는 높이
					y = y - JUMPSPEED; // y 축 만큼 올라감
					setLocation(x, y);
					try {
						Thread.sleep(5); // 0.05
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 객체의 상태값을 잘 조절해야 한다.
				up = false; // 반복문이 끝나면 멈춘다는 의미
				down();// 메서도 호출 -> 뛴 다음에 바로 떨어지도록 호출함
			}
		}).start();
	} // up() 메서드 끝

	@Override
	public void down() {
		System.out.println("다운");
		down = true;

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (down) {
					y = y + SPEED;
					setLocation(x, y);

					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				down = false; // while 문 끝나면 down 을 false로 바꿈
			}
		}).start();

	}

	// 플레이어의 attack 동작 추가하기
	public void attack() { // 메인쓰레드가 동작(바쁜데)

		// 일 작업자에게 위임 처리
		// 람다 표현식 --> 말 그대로 표현식, 타입 추론 가능(자바는)
		new Thread(() -> {
			// 구현부 안에 run() 안에 들어오는 식을 작성해주면 된다.
			Bubble bubble = new Bubble(mContext); // 공격하면 Bubble 객체가 생성되어야 함.
			// this(player)에서 mContext로 변경됨
			// mContext 통해서 (JFrame의 메서드를 호출할 수 있다.)
			mContext.add(bubble); // add() 호출함.

			if (playerWay == PlayerWay.LEFT) {
				// 객체지향스럽게 bubble 코드 수정함.
				// 버블을 왼쪽으로 쏘기
				bubble.left();
			} else {
				// 버블을 오른쪽으로 쏘기
				bubble.right();
			}
		}).start(); // 끊김 없이 동작하고 있음 -> 쓰레드 확인하기

	}
}
