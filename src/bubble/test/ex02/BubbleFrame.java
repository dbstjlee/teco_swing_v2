package bubble.test.ex02;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;
	// 포함관계 - 콤포지션
	private Player player;

	public BubbleFrame() {
		initDate(); // 먼저 호출되어야 한다.
		setInitLayout();
		addEventListener();
	}

	private void initDate() {
		backgroundMap = new JLabel(new ImageIcon("img/backgroundMap.png")); // new ImageIcon() : 익명 클래스
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Frame 안에 root Pannel이 있음.
		setContentPane(backgroundMap); // add 처리 - backgroundMap 붙이기
		setSize(1000, 640);

		player = new Player(); // 메모리에 올림
	}

	private void setInitLayout() {
		// 좌표 값으로 배치
		setLayout(null);
		setResizable(false); // 프레임 크기 조절 불가하도록 설정
		setLocationRelativeTo(null); // JFrame 열어 본 모니터 가운데 자동 배치
		setVisible(true);

		add(player); 
	}

	private void addEventListener() {
		this.addKeyListener(new KeyAdapter() { // new KeyListener() 추상 -> 구현 클래스로 바꿈.

			@Override
			public void keyPressed(KeyEvent e) { // 누를때
				System.out.println("key code : " + e.getKeyCode()); // key code 번호 출력

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.left();
					break;
				case KeyEvent.VK_RIGHT:
					player.right();
					break;
				case KeyEvent.VK_UP:
					player.up();
					break;
				}
			}// end of KeyPressed

			@Override
			public void keyReleased(KeyEvent e) { // 손 뗄때

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				case KeyEvent.VK_UP:
					player.setUP(false);
					break;
				}
			}// end of KeyRealsed
		});
	}

	// 코드 테스트
	public static void main(String[] args) {
		new BubbleFrame(); // 생성자(익명 클래스) 호출
	}
}
