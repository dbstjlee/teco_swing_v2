package ch01;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

//단축키 습관 --> ctrl + shift + s, f, o
public class BorderLayoutEx2 extends JFrame {

	final int WIDTH = 600;
	final int HEIGHT = 600;
	
	JButton[] buttons; // buttons 쓰려면 메모리에 올려야 함.
	String[] directions = { BorderLayout.EAST, BorderLayout.WEST, BorderLayout.NORTH, 
			BorderLayout.SOUTH, BorderLayout.CENTER };
	

	// 생성자
	public BorderLayoutEx2() {
		initData();
		setInitLayout();
	}

	public void initData() {
		setTitle("borderLayout 연습");
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 누르면 프로그램 종료해라고 설정한 것

		buttons = new JButton[directions.length]; // 5칸 공간을 메모리에 올림
//		buttons[0] = new JButton("동");
//		buttons[1] = new JButton("서");
//		buttons[2] = new JButton("남");
//		buttons[3] = new JButton("북");
//		buttons[4] = new JButton("센터");

	}

	public void setInitLayout() {
		// 배치 관리자 선정(컨테이너)
		// BorderLayout -- 컴포넌트들을 동서남북 가운데로 배치 시켜주는 레이아웃이다.
		setLayout(new BorderLayout());

		// 반복문을 확용해서 코드를 완성하세요
		// add(new JButton("동"), BorderLayout.EAST);
		for (int i = 0; i < buttons.length; i++) {
			add(new JButton(directions[i]), directions[i]);

		}

	}

	public static void main(String[] args) {
		new BorderLayoutEx2();
	}

}
