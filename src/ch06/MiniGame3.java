package ch06;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MiniGame3 extends JFrame {

	private JLabel jPlayer;
	private int x = 100;
	private int y = 50;
	private final int MOVE_DISTANCE = 50;
	private final int FRAME_WIDTH = 500;
	private final int FRAME_HEIGHT = 500;
	private final String PLAYER_NAME = "야스오";
	private final int PLAYER_WIDTH = 100;
	private final int PLAYER_HEIGHT = 100;

	public MiniGame3() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jPlayer = new JLabel(PLAYER_NAME);
		jPlayer.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);// 좌표 기반이라 -> 크기 설정해야 함.
	}

	private void setInitLayout() {
		// 좌표 기반으로 배치 관리자 변경
		setLayout(null);
		add(jPlayer);
		jPlayer.setLocation(x, y); // 좌표 기반이라 -> 위치 설정해야 함.
		setVisible(true);
	}

	private void addEventListener() {
		// jPlayer 객체에게서만 keyListener 동작을 시키고자 한다면
		// 익명 구현 클래스로 KeyListener 인터페이스를 재정의할 수 있다.
		// jPlayer.addKeyListener(this); -> this 안됨.

		// new KeyListener() { // 주소값, 이름이 없음 -> 익명 클래스
		// KeyListener keyListener1 = new KeyListener() { // 주소값, 이름이 없음 -> 익명 클래스

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					// 변수는 변하는 수이기도 하다.
					y -= MOVE_DISTANCE;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					x -= MOVE_DISTANCE;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					x += MOVE_DISTANCE;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					y += MOVE_DISTANCE;
				}
				jPlayer.setLocation(x, y);
				// default(100,100)
			}
		});
	}

	// 코드 테스트
	public static void main(String[] args) {
		new MiniGame3();
	}// end of main

}// end of class
