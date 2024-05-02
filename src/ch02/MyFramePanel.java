package ch02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFramePanel extends JFrame {

	private JButton button1;
	private JButton button2;

	// 패널 추가하기
	private JPanel panel1;
	private JPanel panel2;

	public MyFramePanel() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("패널추가 연습");
		setSize(600, 400); // 창 전체 크기 지정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel1 = new JPanel();
		panel1.setBackground(Color.blue);
		panel2 = new JPanel();
		panel2.setBackground(Color.black);

		button1 = new JButton("button1");
		button2 = new JButton("button2");
	}

	public void setInitLayout() {
		//루트 패널 --> BorderLayout
		add(panel1, BorderLayout.CENTER);  // add(컴포넌트명): root 패널에 추가할 때 
		add(panel2, BorderLayout.SOUTH);
		
		panel1.add(button1); // 패널명.add(컴포넌트명) --> 생성한 패널에 추가할 때
		panel1.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 0));
		panel2.add(button2);
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
		
		
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new MyFramePanel(); // 변수명 없이 생성자만 호출함.
	}

}
