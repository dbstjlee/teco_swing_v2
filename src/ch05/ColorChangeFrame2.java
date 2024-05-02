package ch05;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorChangeFrame2 extends JFrame implements ActionListener {

	private JPanel panel;
	private JButton button1;
	private JButton button2;

	public ColorChangeFrame2() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setBackground(Color.yellow);

		button1 = new JButton("click1");
		button2 = new JButton("click2");
	}

	private void setInitLayout() {
		add(panel, BorderLayout.CENTER);
		add(button2, BorderLayout.SOUTH);
		add(button1, BorderLayout.NORTH);
		setVisible(true);
	}

	// 이 메서드의 책임은 이벤트 리스너만 등록
	private void addEventListener() {
		button1.addActionListener(this);
		button2.addActionListener(this);
	}

	// 오버라이드 : 이벤트가 일어나면 호출 되어지는 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		//주소값으로 비교 가능
		// 문자열 값으로 비교 가능
		
		JButton selectedButton = (JButton)e.getSource();
		if(selectedButton == this.button1) {
			System.out.println("button1 객체가 눌러졌다라고 판명 가능");
			panel.setBackground(Color.yellow);
		} else {
			System.out.println("button2 객체가 눌러졌다라고 판명 가능 ");
			panel.setBackground(Color.black);
		}
		
//		System.out.println(selectedButton);
//		System.out.println(selectedButton.getText());
		
//		System.out.println(button1);
//		System.out.println(button2);
		
//		System.out.println(e.getSource().toString());
//		panel.setBackground(Color.BLACK);
		
	}

//		// 이벤트 리스너를 구현하는 두번째 방법
//		button1.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("button1이 눌러 졌어요");
//				
//			}
//		});

	// 코드 테스트
	public static void main(String[] args) {
		new ColorChangeFrame2();
	}

}
