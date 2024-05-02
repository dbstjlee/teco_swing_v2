package ch01;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

// Swing 에서 제공하는 배치관리자 : FlowLayout
// 컴포넌트들을 (버튼, 라벨) 등을 수평, 수직으로 배치를 해주는 클래스이다.
// module-info.java 에 module tenco_swing {requires java.desktop;} 이라고 선언해야 JFrame에 빨간줄이 사라짐.
public class FlowLayoutEx extends JFrame { // JFrame 틀이라는 개념이 만들어짐.

	private JButton button1; // JButton은 참조 타입임. // 주소값을 담을 수 있는 메모리 공간을 선언만 함.
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	
	// 생성자
	public FlowLayoutEx() {
		super.setTitle("FlowLayout 연습");
		super.setSize(500, 500);// 프레임의 사이즈 설정
		super.setVisible(true); // 기본값이 false 라서 보이게 하려면 true로 설정// 화면에 프레임 출력
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ctrl + 좌측 마우스 -> EXIT_ON_CLOSE = 3

		// 생성자에서 메서드 호출 가능함.
		initData();
		setInitLayout();
	}

	// 멤버 변수를 초기화 하는 기능(값을 넣다)
	public void initData() {
		button1 = new JButton("button1"); // 기본 생성자 호출해서 넣음// 객체 생성함.
		button2 = new JButton("button2");
		button3 = new JButton("button3");
		button4 = new JButton("button4");
		button5 = new JButton("button5");
		button6 = new JButton("button6");

	}

	// 컴포넌트들을 배치하는 기능
	public void setInitLayout() {
		// 배치 관리자 --> BorderLayout 이라는 배치관리자가 기본으로 활용된다.
		// FlowLayoutEx flowLayoutEx = new FlowLayoutEx();
		// super.setLayout(flowLayout()); // 배치 관리자 --> FlowLayout

		// 배치관리자 생성 및 JFrame 셋팅
		//FlowLayout -> 컴포넌트들을 일렬로 배치(가운데를 기준)
		super.setLayout(new FlowLayout(FlowLayout.LEADING, 50, 50)); //LEADING 글을 읽는 순서(왼-> 오: 우리나라), 상하좌우 간격
		// FlowLayout에 ctrl + 좌클릭 -> 맨 위에 한 줄
		// super = JFrame이고 JFrame컨테이너에 FlowLayout 배치 관리자 설정
		// vGap: button 간의 상하(수직) 간격 픽셀 , hGap: 좌우(수평) 간격 픽셀

		// 컴포넌트들을 붙이다.
		super.add(button1); // JFrame 내의 add 라는 메서드에 들어올 수 있는 거는 컴포넌트임.
		super.add(button2);
		super.add(button3);
		super.add(button4);
		super.add(button5);
		super.add(button6);
	}

	// 코드 테스트
	public static void main(String[] args) {
		// FlowLayoutEX f1 = new FlowLayoutEx(); <--- 부를 수 있는 이름이 있는 상태
		new FlowLayoutEx(); // <---- 참조변수 주소값이 없음(익명 클래스)// 생성자 호출
		// 다시 접근해서 사용할 일이 없으면 new로 선언만 해도 가능

		// 출력하려고 할 시 GUI가 열림 (ctrl + shift + o or ctrl + F11)

		//디플리케이티드 : 버전 호환이 안된다는 것을 알려줌

	}// end of main

}
