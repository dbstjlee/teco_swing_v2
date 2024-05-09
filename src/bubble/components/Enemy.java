package bubble.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bubble.BubbleFrame;
import bubble.interfaces.Moveable;
import bubble.state.EnemyWay;

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

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;

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

	public BubbleFrame getmContext() {
		return mContext;
	}

	public void setmContext(BubbleFrame mContext) {
		this.mContext = mContext;
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

	public ImageIcon getEnemyR() {
		return enemyR;
	}

	public void setEnemyR(ImageIcon enemyR) {
		this.enemyR = enemyR;
	}

	public ImageIcon getEnemyL() {
		return enemyL;
	}

	public void setEnemyL(ImageIcon enemyL) {
		this.enemyL = enemyL;
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

	public EnemyWay getEnemyWay() {
		return enemyWay;
	}

	public void setEnemyWay(EnemyWay enemyWay) {
		this.enemyWay = enemyWay;
	}

	public int getSPEED() {
		return SPEED;
	}

	public int getJUMPSPEED() {
		return JUMPSPEED;
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

		enemyWay = EnemyWay.LEFT; // 기본값(왼쪽 바라봄)
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
		new Thread(new Runnable() { // -> 익명 클래스

			@Override
			public void run() {
				// left 가 true 일때 까지 반복
				while (left) { // left = true; 움직임
					x = x - SPEED; // 왼쪽으로 가니까 '-'(마이너스)
					setLocation(x, y); // 다시 위치를 찍어줌
					try {
						Thread.sleep(3000); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				left = false;
				right();
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
						Thread.sleep(300); // 0.01
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				right = false;
				left();
			}
		}).start();

	}// end of right

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

	@Override
	public void up() {
	}
}