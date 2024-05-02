package ch02;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

// 내부 클래스를 활용해서 코드를 완성해주세요.
public class PaintFrame extends JFrame{

	PaintPanel paintPanel;
	
	public PaintFrame() {
		initData();
		setInitLayout();
	}
	
	private void initData() {
		setTitle("내부 클래스를 활용한 그림 그리기2");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		paintPanel = new PaintPanel();
	}
	private void setInitLayout() {
		add(paintPanel);
		setVisible(true);
	}
	
	
	//inner class
	class PaintPanel extends JPanel{
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawRect(200, 200, 200, 200);
			g.drawLine(200, 200, 50, 50);
		}
		
		
	}
	
	
	
}
