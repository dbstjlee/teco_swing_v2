package ch04;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 
JLabel 을 이용해서 이미지를 다룰 수 있다.
JLabel.add(); 메소드를 통해서 이미지를 겹칠 수 있다.
좌표 기준으로 이미지를 세팅하려면
배치관리자를 null 값으로 세팅 해야한다.
*/
public class MyFrame extends JFrame{

    private JLabel backgroundMap;
    private JLabel player;

    public MyFrame() {
        initData();
        setInitLayout();
    }

    private void initData() {
        setTitle("JLable을 활용한 이미지 사용 연습");
        setSize(700,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Icon icon = new ImageIcon("images/background1.png"); // 업 캐스팅 상태
                                    // 이미지 만들기
        backgroundMap = new JLabel(icon);

        backgroundMap.setSize(500, 500);
        backgroundMap.setLocation(0, 0);

        player = new JLabel(new ImageIcon("images/3.png"));
                                    // 이미지 만들기
        player.setSize(100, 100);
        player.setLocation(330,50);
    }

    private void setInitLayout() {
        // 좌표 값으로 셋팅하기 위해서는 배치관리자를
        // null 값으로 만들어야 합니다.
        setLayout(null);
        add(backgroundMap);
        backgroundMap.add(player);
        setVisible(true);

    }

}