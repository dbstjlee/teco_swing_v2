package ch03;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 중첩 클래스 --> 외부, 내부 클래스로 내부 클래스로 -> 인스턴스 클래스, static 클래스
 */
public class MyImageFrame extends JFrame {

	// 내부 클래스로 정의한 데이터 타입이다.
	
	private MyImagePanel myImagePanel; // 변수 선언
	
	public MyImageFrame() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("이미지 활용 연습");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		myImagePanel = new MyImagePanel();
	}

	private void setInitLayout() {
		add(myImagePanel); //Panel 이라서 Frame 에 붙일 수 있음
		setVisible(true);
	}

	// 내부 클래스 --> static 키워드 활용
	// 정적(static) 내부 클래스라고 한다.
	static class MyImagePanel extends JPanel {
		
		private Image image1;
		private Image image2;

		public MyImagePanel() {
			// ImageIcon 데이터 타입 안에 getImage() 메서드를 호출하면
			// image 라는 테이터 타입을 만들어 낼 수 있다. 
			image1 = new ImageIcon("image1.png").getImage(); // 경로를 읽어서 코드화 시킴. //멤버 변수
			image2 = new ImageIcon("image2.png").getImage(); // 경로를 읽어서 코드화 시킴. //멤버 변수

		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			// 재정의함
			// x1, y1, width, height
			g.drawImage(image1, 100, 100, 100, 100, null ); //MyImagePanel의 데이터를 읽어서 재이미지화 함.
			g.drawImage(image2, 300, 300, 200, 200, null ); 
			}
	}

}
