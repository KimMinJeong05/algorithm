import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 핵심 로직
 * 
 * 1. 현재 바퀴를 회전했음 체크
 * 2. 현재 바퀴 돌리기 전 양쪽 바퀴와 극이 같은지 다른지 확인
 *    2-1. 톱니바퀴가 같으면 회전X
 *    2-2. 톱니바퀴가 다르면 회전
 * 3. 현재 바퀴 돌리기
 * 4. 2번 결과로 옆의 바퀴 돌리기
 */

public class Main {
	static char[][] wheels = new char[5][]; // 2, 6
	static boolean[] checkWheels = new boolean[5];
	static int k;
	
	public static void main(String[] args) throws IOException {
		input();
		System.out.println(score());
	}
	
	static void input() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		
		// 톱니바퀴 상태
		for(int i=1; i<=4;i++) {
			wheels[i] = in.readLine().toCharArray();
		}
		
		// 회전
		k = Integer.parseInt(in.readLine());
		for(int i=0; i<k;i++) {
			tokens = new StringTokenizer(in.readLine(), " ");
			checkWheels = new boolean[5];
			turnWheel(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
		}
	}
	
	static void turnWheel(int wheelIdx, int dir) {
		// 현재 바퀴 돌림 확인
		checkWheels[wheelIdx] = true;
		
		// 양쪽 바퀴 극 확인
		boolean leftWheel = false, rightWheel = false;
		// 방문 안하고, 극이 다르면 회전시키기
		if(wheelIdx-1>0 && !checkWheels[wheelIdx-1] && wheels[wheelIdx-1][2] != wheels[wheelIdx][6]) {
			leftWheel = true;
		}
		if(wheelIdx+1<=4 && !checkWheels[wheelIdx+1] && wheels[wheelIdx][2] != wheels[wheelIdx+1][6]) {
			rightWheel = true;
		}
		
		// 현재 바퀴 돌리기
		if(dir==-1) {
			char back = wheels[wheelIdx][0];
			for(int i=1; i<8;i++) {
				wheels[wheelIdx][i-1] = wheels[wheelIdx][i];
			}
			wheels[wheelIdx][7] = back;
		}else {
			char front = wheels[wheelIdx][7];
			for(int i=6; i>=0;i--) {
				wheels[wheelIdx][i+1] = wheels[wheelIdx][i];
			}
			wheels[wheelIdx][0] = front;
		}
		
		// 양 옆 바퀴 돌리기
		if(leftWheel) {
			turnWheel(wheelIdx-1, (dir==1)?-1:1);
		}
		if(rightWheel) {
			turnWheel(wheelIdx+1, (dir==1)?-1:1);
		}
	}
	
    // 점수 계산
	static int score() {
		int score = 0;
		for(int i=1; i<=4;i++) {
			if(wheels[i][0]=='1') {
				score+=Math.pow(2, i-1);
			}
		}
		return score;
	}
}