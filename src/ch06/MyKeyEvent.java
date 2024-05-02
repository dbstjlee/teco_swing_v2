package ch06;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * 키 이벤트 리스너 사용해보기
 */

public class MyKeyEvent extends JFrame implements KeyListener { // KeyListener 키보드를 눌렀을 때 호출되는 메서드를 가지고 있는 인터페이스

	private JTextArea textArea;
	// private final int up = 38;

	public MyKeyEvent() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 프레임 사이즈 조절 불가

		textArea = new JTextArea();
	}

	private void setInitLayout() {
		// 좌표 기준으로 셋팅하고 싶다면 null
		setLayout(new BorderLayout());
		add(textArea);
		setVisible(true);
	}

	private void addEventListener() {
		// textArea 위에서 사용자가 키보드를 누르면 감지해서 나에게(코드 기준) 알려줘
		textArea.addKeyListener(this); // KeyListener가 들어가야 하는데 다형성이라 this가 들어감.
	}

	@Override
	public void keyTyped(KeyEvent e) { // 누른 키를 떼는 순간 Unicode 키의 경우(문자키에만 반응)
		// 응답 받고 싶다면 1
		System.out.println(e.toString());

	}

	@Override
	public void keyPressed(KeyEvent e) { // 키를 누르는 순간
		// 응답 받고 싶다면 2
		System.out.println("KeyPressed : " + e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) { // 누른 키를 떼는 순간
		// 응답 받고 싶다면 3
		// 제어문을 활용해서 방향키 위 아래 왼쪽 오른쪽을 눌렀다가 때면
		// 콘솔창에 한글로 위 아래 왼쪽 오른쪽 글자를 표기하시오.

		// if(e.getKeyCode() == up) {
		// if(e.getKeyCode() == 38) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("위");
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("아래");
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("왼");
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("오른");
		}
	}

	public static void main(String[] args) {

		new MyKeyEvent();
	}

}
