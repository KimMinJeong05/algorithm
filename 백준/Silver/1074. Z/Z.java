import java.util.Scanner;

/*
 * [핵심 로직]
 * 분할 탐색
 * 1. 현재 박스의 크기가 2보다 크면 분할
 * 1-1. 4분할된 박스들에서 자신이 있는 박스로 재귀
 * 1-2. 이때 이전 박스들에서 미리 계산이 되어야하는 경우, 그 박스들의 크기만큼 cur에 더해준 후 자신의 박스로 재귀해야함
 * 2. 현재 박스의 크기가 2보다 작으면 방문(cur count)
 * 2-1. 방문 했는데 (r,c)이면 방문 숫자 반환
 * 2-2. 방문 했는데 (r,c)가 아니면 -1 반환
 * 
 * -> 따로 배열에 방문 횟수 저장하지 않아도 됨
 */

public class Main {
	
	static int N, r, c;
	static int cur;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		r=sc.nextInt();
		c=sc.nextInt();
		
		System.out.println(visit(0,0,(int)Math.pow(2, N)));
	}
	
	// 현재 크기가 2*2보다 크면 더 분할하기
	public static int visit(int sr, int sc, int curN) {
		int halfN=curN/2;
		
		if(curN<=2) {
			if(r==sr && c==sc) return cur;
			cur++;
			if(r==sr && c==sc+1) return cur;
			cur++;
			if(r==sr+1 && c==sc) return cur;
			cur++;
			if(r==sr+1 && c==sc+1) return cur;
			cur++;
			return -1;
		}
		
		// 4분할 된 곳 중 자신의 구간을 찾아감.
		if(r<sr+halfN&&c<sc+halfN) {
			return visit(sr, sc, halfN);
		}else if(r<sr+halfN&&c>=sc+halfN) {
			cur+=(halfN*halfN);
			return visit(sr, sc+halfN, halfN);
		}else if(r>=sr+halfN&&c<sc+halfN) {
			cur+=(halfN*halfN)*2;
			return visit(sr+halfN, sc, halfN);
		}else {
			cur+=(halfN*halfN)*3;
			return visit(sr+halfN, sc+halfN, halfN);
		}
	}
}