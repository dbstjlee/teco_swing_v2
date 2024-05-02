package ch07;

import java.util.Arrays;
import java.util.Random;

// 클래스를 설계할 때는 단일 책임 원칙을 설정하는 것이 좋다.

public class LottoRandomNumber {

	final int LOTTO_NUMBER_COUNT = 6;

	// 6개의 랜덤 번호를 생성하는 메서드 필요하다.
	public int[] createNumber() {

		int[] lottoWinNum = new int[LOTTO_NUMBER_COUNT];
		Random random = new Random();

		// outer for -> 0 -> inner for --> 동작X
		// outer for -> 1 -> inner for --> 한번 동작(0)
		// outer for -> 2 -> inner for --> 두번 동작(0,1)
		for (int i = 0; i < lottoWinNum.length; i++) {
			lottoWinNum[i] = random.nextInt(45) + 1;
			//                  0
			//              0 < 1 
			//            0,1 < 2
			//             2  =  2
			//           => i = 2 -> i-- -> 1 
			//           => outer for -> i++ -> 다시 2부터 코드 실행
			for (int j = 0; j < i; j++) {
				if (lottoWinNum[j] == lottoWinNum[i]) {
					// 값이 중복된 상태이다.
					i--;
				}
			}
		}
		// 중복 제거 완료

		Arrays.sort(lottoWinNum); // 기본값 오름차순으로 자동 정렬

		return lottoWinNum; //lottoWinNum(null)을 변수명 및 결과 값으로 채워넣음.
	}

	// 코드 테스트
	public static void main(String[] args) {
		LottoRandomNumber randomNumber = new LottoRandomNumber();

		int[] resultArray = randomNumber.createNumber();
		for (int i = 0; i < resultArray.length; i++) {
			System.out.println(resultArray[i]);
		}

	}

}
