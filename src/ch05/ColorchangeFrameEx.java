package ch05;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorchangeFrameEx extends JFrame implements ActionListener {
	// ActionListener 인터페이스: 버튼 눌렀을 때 이벤트 발생
	// 인터페이스 불러오면 actionPerformed() 메서드가 자동으로 호출됨.-> 해당 메서드를 재정의해서 이벤트 발생 시 실행 코드 작성 가능

	private JPanel panel1;
	private JPanel panel2;
	private JButton button1;
	private JButton button2;

	public ColorchangeFrameEx() {
		initData();
		setInitLayout();
		addEventListner();
	}

	private void initData() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		panel1 = new JPanel();
		panel2 = new JPanel();

		panel1.setBackground(Color.BLACK);
		panel2.setBackground(Color.BLUE);
		// 버튼 인스턴스화
		button1 = new JButton("button1");
		button2 = new JButton("button2");
	}

	private void setInitLayout() {
		// 컴포넌트 추가
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);
		panel2.add(button1);
		panel2.add(button2);
		setVisible(true);
	}

	private void addEventListner() {
		// 이벤트 리스너 등록(이벤트 발생 기준) -> 메서드 들어오면 어떻게 동작시킬꺼야
		button1.addActionListener(this);
		button2.addActionListener(this);
	}


	@Override //: 이벤트가 일어나면 호출되어지는 메서드.(콜백 메서드)
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource(); // e.getSource()의 메서드로 e 타입을 확인해보면 Object 타입임.
		// -> 코드를 설계할 때 더 편리하게 사용하기 위해 기존의 타입으로 다운 캐스팅 할 수 있음.

		JButton seletedButton = (JButton) e.getSource(); // 다운 캐스팅
		// getSource() : 이벤트를 발생시킨 객체의 위치값을 가져온다.
		if (seletedButton == this.button1) {
			System.out.println("button1 눌러짐");
			panel1.setBackground(Color.gray);
		} else {
			System.out.println("button2 눌러짐");
			panel1.setBackground(Color.PINK);

		}
	}

	public static void main(String[] args) {
		new ColorchangeFrameEx();
	}
}
