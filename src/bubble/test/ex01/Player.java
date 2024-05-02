package bubble.test.ex01;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	private int x;
	private int y;
	private ImageIcon playerR, playerL; // 이미지 불러옴.

	public Player() {
		initData();
		setInitLayout();

	}

	private void initData() {
		playerR = new ImageIcon("img/playerR.png");
		playerL = new ImageIcon("img/playerL.png");

		// 처음 실행 시 초기 값 셋팅
		x = 55;
		y = 535;
		setIcon(playerR); // setIcon은 JLabel의 기능이다.
		setSize(50, 50);
		setLocation(x, y);

	}

	private void setInitLayout() {

	}

	@Override
	public void left() {
		// 왼쪽 방향키 이벤트 발생 시
		// 이미지를 왼쪽을 보는 이미지로 셋팅
		setIcon(playerL);
		x = x - 10;
		setLocation(x, y);

	}

	@Override
	public void right() {
		setIcon(playerR);
		x = x + 10;
		setLocation(x, y); // 다시 위치를 찍어주는 동작이다.

	}

	@Override
	public void up() {
		System.out.println("점프");

	}

	@Override
	public void down() {
		System.out.println("다운");

	}

}
