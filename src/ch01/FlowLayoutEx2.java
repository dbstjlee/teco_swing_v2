package ch01;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

//JFrame 틀이라는 개념이 만들어짐.
public class FlowLayoutEx2 extends JFrame {

	// 배열 활용 - 하나의 변수로 여러개를 통으로 관리하고 싶다면 배열을 써보자.
	private JButton[] buttons; // JButton 배열로 선언한다. 주소값을 담을 수 있는 메모리 공간만 선언

	// 생성자
	public FlowLayoutEx2() {

		super.setTitle("FlowLayout 연습");
		super.setSize(500, 500);// 프레임의 사이즈 설정
		super.setVisible(true); // 기본값이 false 라서 보이게 하려면 true로 설정
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 생성자에서 메서드 호출 가능함.
		initData();
		setInitLayout();
	}

	// 멤버 변수를 초기화 하는 기능(값을 넣다)
	public void initData() {

		buttons = new JButton[6]; // 객체가 안 들어가 있고 공간만 선언함
		// 인덱스 번호를 통해 접근 가능함.

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton("[ button" + (i + 1) + "]");
		} // 기본 생성자 호출 및 객체 생성

	}

	// 컴포넌트들을 배치하는 기능
	public void setInitLayout() {
		// 배치 관리자 --> BorderLayout 이라는 배치관리자가 기본으로 활용된다.

		// 배치관리자 생성 및 JFrame 셋팅
		super.setLayout(getLayout());
		super.setLayout(new FlowLayout(FlowLayout.LEADING, 50, 50)); // LEADING 글을 읽는 순서(왼-> 오: 우리나라)
		// FlowLayout에 ctrl + 좌클릭 -> 맨 위에 한 줄

		// 컴포넌트들을 붙이다.
		// 반복문 활용
		for (int i = 0; i < buttons.length; i++) {
			super.add(buttons[i]);
		}
//		super.add(button1); // JFrame 내의 add 라는 메서드에 들어올 수 있는 거는 button[i]라는 컴포넌트임.
//		super.add(button2);
//		super.add(button3);
//		super.add(button4);
//		super.add(button5);
//		super.add(button6);
//		super.add(buttons[0]); 
//		super.add(buttons[1]);
//		super.add(buttons[2]);
//		super.add(buttons[3]);
//		super.add(buttons[4]);
//		super.add(buttons[5]);
	}

	// 코드 테스트
	public static void main(String[] args) {
		// FlowLayoutEX f1 = new FlowLayoutEx(); <--- 부를 수 있는 이름이 있는 상태
		new FlowLayoutEx2(); // <---- 참조변수 주소값이 없음(익명 클래스)// 생성자만 호출한 상태
		// 참조 변수명을 통해 다시 접근해서 사용할 일이 없으면 new로 선언만 해도 가능

		// 출력하려고 할 경우 GUI가 열림 (ctrl + F11)
		// ctrl + shift + o : 안쓰는 메모리 사라짐.

		// 디플리케이티드 : 버전 호환이 안된다는 것을 알려줌

	}// end of main

}
