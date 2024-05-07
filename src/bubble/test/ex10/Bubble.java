package bubble.test.ex10;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// 물방울 생성
// JLabel은 컴포넌트라 붙여야만(add) 눈에 보임
public class Bubble extends JLabel implements Moveable {

	private BubbleFrame mContext;

	// 의존성 컴포지션 관계
	private Player player; // 내 객체. 플레이어에 접근 가능
	private BackgroundBubbleService backgroundBubbleService;
	// 버블 객체 생성 시 메모리에 올라가야 함. -> initData로 가서 초기화하기

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
	public Bubble(BubbleFrame mContext) { // bubble 생성시 player를 받는 것이 아니라
		this.mContext = mContext;
		this.player = mContext.getPlayer(); // this.player에 player를 넣음
		initData();
		setInitLayout();
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
		backgroundBubbleService = new BackgroundBubbleService(this); // 자기 자신이 들어와야 함.// 메서드 호출 가능

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

	@Override
	public void left() { // 플레이어가 왼쪽을 바라보면 왼쪽으로 방울을 쏨
		left = true;
		for (int i = 0; i < 400; i++) {
			x--; // 방울이 왼쪽으로 이동
			setLocation(x, y); // 좌표를 찍어서 방울이 보이도록 함.
			if (backgroundBubbleService.leftWall()) {
				break;
			}

			// 현재 버블의 x,y 좌표값을 알고 있다.
			System.out.println("적군 x 좌표 위치 : " + mContext.getEnemy().getX());
			System.out.println("적군 y 좌표 위치 : " + mContext.getEnemy().getY());
			// x 절대값만 확인해보자
			int absX = Math.abs(x - mContext.getEnemy().getX()); // double의 x좌표에서 뺀다.
			System.out.println("absX :" + absX);
			int absY = Math.abs(y - mContext.getEnemy().getY());
			System.out.println("absY :" + absY);
			// 40보다 작다면 맞았다고 함.
			if (absX < 10 && absY < 50) {
				// 단, 적군이 살아 있을때 crash() 메서드 호출이 되어야 함.
				if (mContext.getEnemy().getState() == 0) {
					crash();
				}
			}

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
			if (backgroundBubbleService.rightWall()) {
				// 만약에 부딪힌 상태라면
				break;
			}

			// 적군 감지 및 충돌 처리
			int absX = Math.abs(x - mContext.getEnemy().getX());
			int absY = Math.abs(y - mContext.getEnemy().getY());
			if (absX < 10 && absY < 50) {
				if (mContext.getEnemy().getState() == 0) {
					crash();
				}
			}
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
			if (backgroundBubbleService.topWall()) {
				break;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		clearBubble();
	}

	// 외부 호출 안 될 메서드
	// 최상단에 올라갔을 때 메서드 호출하도록
	private void clearBubble() {
		// 멈췄을 때(3초 뒤에) 터짐
		// 터졌지만 heap 메모리에는 쌓았던 객체들이 존재한다. -> 가비지 컬렉션?이 수거해감
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(500);
			// todo 테스트 필요!!
			setIcon(null);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 적군이 물방울에 갇힌 상태
	public void crash() {
		mContext.getEnemy().setState(1); // 1로 상태값을 바꿈
		state = 1;
		setIcon(bubbled);
		// 적군이 살아있는 상태에서 부딪히면
		// 버블에 갇힌 상태로 변경
		// mContext.getEnemy().setIcon(null);
		mContext.remove(mContext.getEnemy());
		// mContext.getEnemy() 가비지 컬렉터의 제거 대상이 된다.
		mContext.repaint();// 다시 그림을 그리다.
		// 버블의 이미지를 변경처리

	}

}
