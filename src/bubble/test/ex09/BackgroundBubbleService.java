package bubble.test.ex09;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// 물방울 벽 감지 -> 쓰레드 사용하지 않고 메서드로 설계함.
// bubble 객체 생성 시 BackgroundBubbleService 작용하도록 설계하기
public class BackgroundBubbleService {
	// bubble 이 하나하나 객체인데 하나하나 위치 찍기에는 메모리 공간을 많이 차지함.
	// -> 메서드로 접근하기 위해 클래스 하나 새로 생성(벽에 닿이면 위로 가도록)

	private BufferedImage image;
	private Bubble bubble; // 참조값을 전달받음(연관관계)(생성자 의존 주입)

	// 생성자
	public BackgroundBubbleService(Bubble bubble) {
		this.bubble = bubble;
		try {
			image = ImageIO.read(new File("img//backgroundMapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 왼쪽 벽에 부딪혔는지
	public boolean leftWall() {
		Color leftColor = new Color(image.getRGB(bubble.getX() + 10, bubble.getY() + 25)); // image -> // 현재 버블의 위치. x :
																							// + 안으로 들여옴. y: + 최상단의 반정도
		// 255 0 0 <---- 빨간색(왼쪽벽 확인)
		// 빠른 평가
		if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
			return true;
		}
		return false;// 기본값
	}

	// 오른쪽 벽에
	public boolean rightWall() {
		Color rightColor = new Color(image.getRGB(bubble.getX() + 50 + 10, bubble.getY() + 25));
		// 맨 끝에 가면 50 , 버블의 절반 = 25 50 -> 오른쪽 벽을 파고 들어감 + 10 만큼 오른쪽 공간 넓혀주도록 설정
		if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
			return true;
		}
		return false;
	}

	// 천장 벽에
	public boolean topWall() {
		Color topColor = new Color(image.getRGB(bubble.getX() + 25, bubble.getY())); // Y 좌표는 최상단 그대로
		if (topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0) {
			return true;
		}
		return false;
	}

}
