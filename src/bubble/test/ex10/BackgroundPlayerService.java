package bubble.test.ex10;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//쓰레드 -> start 시키기
/**
 * 현재 메인 쓰레드는 너무 바쁨 백그라운드에서 계속 Player 의 움직임을 관찰할 예정
 */
public class BackgroundPlayerService implements Runnable {

	private BufferedImage image; // 위치를 알아야 함 // BufferedImage 다시 공부...
	private Player player; // Player 참조함.

	// 생성자 의존 주입 DI - 연관관계?
	public BackgroundPlayerService(Player player) {
		this.player = player;

		try {
			image = ImageIO.read(new File("img/backgroundMapService.png")); // 백그라운드 좌표 서비스 -> 이미지 올리기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// 색상 확인 todo (보정값 필요)
//		Color leftColor = new Color(image.getRGB(player.getX(), player.getY()));
//		System.out.println(leftColor.getRed());
//		System.out.println(image.getRGB(player.getX(), player.getY())); // 위치 확인됨

		while (true) {

			Color leftColor = new Color(image.getRGB(player.getX() + 10, player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 50 + 10, player.getY() + 25));
			// x 좌표: 왼 -> 오 (50) +10 넓힘
			// y 좌표: 25만큼 낮춤

			// Color bottomColor = new Color(image.getRGB(player.getX(), player.getY()));
			// 흰색이면 RGB => 255 255 255
			// 바닥인 경우 --> 255 0 0(바닥이라고 판단 가능)
			// 바닥(1층)인 경우 --> 0 0 255(바닥이라고 판단 가능)
			int bottomColorLeft = image.getRGB(player.getX() + 30, player.getY() + 50 + 5); // 객체를 이미지로. int로 하면 수치 나옴.
			int bottomColorRight = image.getRGB(player.getX() + 40, player.getY() + 50 + 5); // 객체를 이미지로. int로 하면

			if (bottomColorLeft + bottomColorRight != -2) { // 빨간 바닥 + 파란색 바닥 -2 가 아니면
				// 여기는 멈춰야 함(빨간 바닥 또는 파란색 바닥)
				player.setDown(false);
			} else {
				// 플레이어가 올라가는 상태가 아니라면
				// 그리고
				// 플레이어가 내려가는 상태가 아니라면
				// down() 호출
				if (!player.isUp() && !player.isDown()) {
					player.down();
				}

			}

			// 하얀색 ---> int 값이 - 1이 됨.
			// if(bottomColorLeft == -1) {}
			// 밑으로 계속 떨어져라는 개념

			// 왼쪽벽에 충돌함(왼쪽 벽 색이 빨강일때)
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				System.out.println("왼쪽벽에 충돌함");
				player.setLeftWallCrash(true); // 벽에 충돌함 = true
				player.setLeft(false); // while문이 멈춤

			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				System.out.println("오른쪽벽에 충돌함");
				player.setRightWallCrash(true);
				player.setRight(false);
			} else { // 아니면 계속 안 부딪혔는지
				player.setLeftWallCrash(false);// 안부딪힌 상태
				player.setRightWallCrash(false);
			}

			// 위 두 조건이 아니면 player 마음대로 움직일 수 있다.
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// System.out.println(player.getX() + " , " + player.getX()); // 위치 확인됨
		}
		// 플레이어 위치 확인
		// System.out.println("이거 한 번 출력하고 쓰레드 상태는 데스로 빠진다....");
	}
}