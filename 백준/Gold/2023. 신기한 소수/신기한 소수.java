import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 첫째짜리는 2, 3, 5, 7
 * 둘째짜리부터는 홀수인 1, 3, 5, 7, 9
 * -> 끝자리가 짝수면 무조건 소수가 아니므로
 * 
 * N자리의 신기한 소수 = N-1자리의 신기한 소수*10+{홀수} 인 것 중 소수
 * 
 * => 4*(5^n) 이므로 최대 4*5^7
 */

public class Main {
	
	static int N;
	static List<Integer>[] Itdecimals; // 신기한 소수
	static int preNum;
	static int[] odd = {1,3,5,7,9}; // 호수
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		Itdecimals = new List[N+1];
		
		Itdecimals[1] = new ArrayList<>();
		for(int i:new int[] {2, 3, 5, 7}) {
			Itdecimals[1].add(i);
		}
		
		for(int i=2;i<=N;i++) { // N자리까지 2부터 순차적으로 신기한 소수 만들기
			Itdecimals[i] = new ArrayList<>();
			for(int d:Itdecimals[i-1]) {
				preNum=d;
				findItdecimals(i);
			}
		}
		
		// print
		for(int d: Itdecimals[N]) {
			sb.append(d);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	// curIdx자리의 신기한 소수 찾기
	static void findItdecimals(int curIdx) {
		int cur; 
		for(int o:odd) {
			cur = preNum*10+o;
			
			if(isPrime(cur)) Itdecimals[curIdx].add(cur);
		}
	}

	// 소수인지 확인
	static boolean isPrime(int num) {
		for(int i=2;i*i<=num;i++) {
			if(num%i==0) return false;
		}
		return true;
	}
}