package ch02;

import javax.swing.JFrame;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MyComponents extends JFrame {

	private JButton button;
	private JLabel label;
	private JTextField textField;
	private JPasswordField passwordField; // 비밀번호를 ***으로 변경해주는 코드
	private JCheckBox checkBox; // 체크박스 형성(라디오 버튼)

	public MyComponents() {

		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("컴포넌트 확인");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		button = new JButton("JButton");
		label = new JLabel("글자를 띄우는 컴포넌트");
		textField = new JTextField("아이디입력", 20);
		passwordField = new JPasswordField("비번입력", 20);
		checkBox = new JCheckBox("동의");
	}

	private void setInitLayout() {
		setLayout(new FlowLayout());
		setVisible(true);

		add(button);
		add(label);
		add(textField);
		add(passwordField);
		add(checkBox);
	}

	// 메인 작업자
	public static void main(String[] args) {
		MyComponents components = new  MyComponents(); // 객체 안에 들어가려면 주소값을 알고 있어야 함.
		// a.b.c.d.getText();
		components.textField.setText("반가워"); //JTextField는 참조타입
	}


}
