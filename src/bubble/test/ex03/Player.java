package bubble.test.ex03;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	private int x; // x 좌표
	private int y; // y 좌표
	private ImageIcon playerR, playerL; // ImageIcon = 이미지 불러옴.

	// 움직임의 상태
	private boolean left; // true : 움직임 false : 멈춤
	private boolean right;
	private boolean up;
	private boolean down;

	// 플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;

	// setter // setLeft(true: 움직임 false: 멈춤)
	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public Player() {
		initData(); // 호출
		setInitLayout();

	}

	private void initData() {
		playerR = new ImageIcon("img/playerR.png"); // playerR 객체 생성
		playerL = new ImageIcon("img/playerL.png");

		// 처음 실행 시 초기값 셋팅(초기화)
		x = 450;
		y = 535;

		// 플레이어가 가만히 멈춤 상태
		left = false; // false가 멈춤 상태임을 선언
		right = false;
		up = false;
		down = false;

		// 플레이어 이미지 불러냄
		setIcon(playerR); // setIcon은 JLabel의 기능에서 불러옴.
		setSize(50, 50);
		setLocation(x, y);

	}

	private void setInitLayout() {

	}

	@Override
	public void left() { // 1번 누르면 쓰레드 1개/ 2번 누르면 2개 -> 누를 수록 속도가 배로 올라감.
		left = true; // 왼쪽으로 움직임(가고 있는 상태)
		setIcon(playerL); // 왼쪽 방향키 이벤트 발생 시 이미지를 왼쪽을 보는 이미지로 셋팅

		// <- <- <- 반복문(횟수 제한X) => while
		// main 은 KeyEvent 받음 -> 다른 작업자한테 위임함.
		// new Thread(new Runnable()).start();
		new Thread(new Runnable() { // -> 익명 클래스

			@Override
			public void run() {
				// left 가 true 일때 까지 반복
				while (left) { // left = true; 움직임
					x = x - SPEED; // 왼쪽으로 가니까 '-'(마이너스)
					setLocation(x, y);
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

				for (int i = 0; i < 130 / JUMPSPEED; i++) { // JUMPSPEED = 2
					y = y - JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5); // 0.05
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 객체의 상태값을 잘 조절해야 한다.
				up = false;
				down();// 메서도 호출
			}
		}).start();
	}

	@Override
	public void down() {
		System.out.println("다운");
		down = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 130 / JUMPSPEED; i++) {
					y = y + JUMPSPEED;
					setLocation(x, y);
					// Thread.sleep(3);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
		// 상태 처리
		down = false;
		// 대각선은 쓰레드를 써야만 가능함
	}
}
