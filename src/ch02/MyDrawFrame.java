package ch02;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 패널에 그림을 그리는 방법 자바 문법 - 중첩 클래스(클래스 안에 클래스를 만드는 것) 외부 클래스, 내부 클래스, outer class,
 * inner class
 */
public class MyDrawFrame extends JFrame {

	// 내부 클래스를 외부 클래스에서 사용하려면 멤버로 가지고 있어야 한다.
	// int x1 = 100; // 멤버로 선언과 동시에 초기화함.

	MyDrawPanel myDrawPanel;

	public MyDrawFrame() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("내부클래스를 활용한 그림 그리는 연습");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		myDrawPanel = new MyDrawPanel();
	}

	private void setInitLayout() {
		add(myDrawPanel); // MyDrawPanel이 JPanel라서 붙일 수 있음
		setVisible(true);
	}

	// 내부 클래스 만들어 보기
	// paint --> 좌표값으로 x = 0 , y = 0
	// JPanel에 있는 기능으로 확장해 보자.
	class MyDrawPanel extends JPanel {

		// paints ---> x
		// print ---> x
		// paint ---> o
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			// g.drawRect(x1, 100, 50, 100); // x , y , width, height // 외부 클래스의 멤버를 바로 활용할
			// 수 있음
			g.drawRect(100, 100, 50, 100); // 사각형
			g.drawRect(200, 200, 150, 150);
			// int x1, int y1, int x2, int y2
			g.drawLine(250, 300, 300, 300); // 선 (x1,y1)(x2,y2)
			// int x, int y, int width, int height
			g.drawOval(100, 150, 200, 300); // 원
			// String str, int x, int y
			g.drawString("반가워", 400, 400); // 글
			g.drawString("😎", 300, 400);
		}

	}// end of inner class

}
