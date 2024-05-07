package bubble.test.ex10;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel implements Moveable {

	BubbleFrame mContext;

	// 살아 있는 상태 0, 물방울에 갇힌 상태 1
	private int state; // 초기값 0

	// 적군의 좌표값 위치 상태
	private int x; // x 좌표
	private int y; // y 좌표
	private ImageIcon enemyR, enemyL; // ImageIcon = 이미지 불러옴.

	// 움직임의 상태
	private boolean left; // true : 움직임 false : 멈춤
	private boolean right;
	private boolean up;
	private boolean down;

	// 적군 속도 상태
	private final int SPEED = 3;
	private final int JUMPSPEED = 1;

	// 적군 방향 상태
	private EnemyWay enemyWay; // 변수에 데이터 타입이 선언됨.

	// get, set
	// 부모에게로부터 멤머변수 받을 수 있도록
	public Enemy(BubbleFrame mContext) { // 기본생성자에서 //부모의 주소값이 들어오는 설계다.
		// mContext 멤버로 올려야 선언 가능
		this.mContext = mContext;
		initData(); // 호출
		setInitLayout();
		left();

	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	private void initData() {
		enemyR = new ImageIcon("img/enemyR.png"); // playerR 이미지 객체 생성
		enemyL = new ImageIcon("img/enemyL.png");

		state = 0; // 물방울에 안 갇힌 상태

		// 처음 실행 시 적군 위치
		x = 720;
		y = 175;

		// 적군 방향 상태
		left = false; // false 가 멈춤 상태임(boolean - 초기값은 false임)
		right = false;
		up = false;
		down = false;

		enemyWay = EnemyWay.LEFT;
	}

	private void setInitLayout() {
		// 플레이어 이미지 불러냄
		setIcon(enemyL); // setIcon은 JLabel의 기능에서 불러옴.
		setSize(50, 50);
		setLocation(x, y);
	}

	@Override
	public void left() { // 1번 누르면 쓰레드 1개/ 2번 누르면 2개 -> 누를 수록 속도가 배로 올라감.
		enemyWay = EnemyWay.LEFT;
		left = true; // 왼쪽으로 움직임(가고 있는 상태)
		setIcon(enemyL); // 왼쪽 방향키 이벤트 발생 시 이미지를 왼쪽을 보는 이미지로 셋팅

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
		enemyWay = EnemyWay.RIGHT; // 방향에 따라 값이 변함
		right = true;
		setIcon(enemyR);

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

	public void setDown(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
